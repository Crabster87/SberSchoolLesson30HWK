package crabster.rudakov.sberschoollesson30hwk.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import crabster.rudakov.sberschoollesson30hwk.domain.interactors.IDictionaryInteractor
import crabster.rudakov.sberschoollesson30hwk.domain.interactors.ILocalImageInteractor
import crabster.rudakov.sberschoollesson30hwk.domain.models.DictionaryItem
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.models.DictionaryItemModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Implementation of SharedViewModel
 * @param dictionaryInteractor - interactor for getting data from [crabster/rudakov/sberschoollesson30hwk/data/datastores/providers/DictionaryContentProvider]
 * @param localImageInteractor - interactor for getting pictures from Media Content Provider
 */
class SharedViewModel(
    private val dictionaryInteractor: IDictionaryInteractor,
    private val localImageInteractor: ILocalImageInteractor
) : ViewModel() {

    //Live Data
    private val _dictionaryItemsLiveData = MutableLiveData<List<DictionaryItemModel>>()
    private val _dictionaryItemsProgressLiveData = MutableLiveData<Boolean>()
    private val _dictionaryItemsErrorLiveData = MutableLiveData<Throwable?>()
    private val _completeAddingDictionaryItemsLiveData = MutableLiveData(false)
    private val _localImageLiveData = MutableLiveData<List<Uri>>()
    private val _localImageProgressLiveData = MutableLiveData<Boolean>()
    private val _localImageErrorLiveData = MutableLiveData<Throwable?>()
    private val _selectedImageLiveData = MutableLiveData<Uri>()

    val dictionaryItemsLiveData: LiveData<List<DictionaryItemModel>>
        get() = _dictionaryItemsLiveData
    val dictionaryItemsProgressLiveData: LiveData<Boolean>
        get() = _dictionaryItemsProgressLiveData
    val dictionaryItemsErrorLiveData: LiveData<Throwable?>
        get() = _dictionaryItemsErrorLiveData
    val completeAddingDictionaryItemsLiveData: LiveData<Boolean>
        get() = _completeAddingDictionaryItemsLiveData
    val localImageLiveData: LiveData<List<Uri>>
        get() = _localImageLiveData
    val localImageErrorLiveData: LiveData<Throwable?>
        get() = _localImageErrorLiveData
    val localImageProgressLiveData: LiveData<Boolean>
        get() = _localImageProgressLiveData
    val selectedImageLiveData: LiveData<Uri>
        get() = _selectedImageLiveData

    // CompositeDisposable to subscribe and unsubscribe receiving data in Rx
    private val compositeDisposable = CompositeDisposable()

    /**
     * Load data from [crabster/rudakov/sberschoollesson30hwk/data/datastores/providers/DictionaryContentProvider]
     */
    fun loadDataAsyncRx() {
        val disposable = dictionaryInteractor.getList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _dictionaryItemsProgressLiveData.value = true }
            .doFinally { _dictionaryItemsProgressLiveData.value = false }
            .subscribe(
                { items ->
                    _dictionaryItemsLiveData.value = items
                },
                { throwable ->
                    _dictionaryItemsErrorLiveData.value = throwable
                    _dictionaryItemsErrorLiveData.value = null
                }
            )
        compositeDisposable.add(disposable)
    }


    /**
     * Add data to [crabster/rudakov/sberschoollesson30hwk/data/datastores/providers/DictionaryContentProvider]
     */
    fun addToDictionary(dictionaryItem: DictionaryItem) {
        val disposable = dictionaryInteractor.add(dictionaryItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _completeAddingDictionaryItemsLiveData.value = true
            }
        compositeDisposable.add(disposable)
    }

    /**
     * Delete data from [crabster/rudakov/sberschoollesson30hwk/data/datastores/providers/DictionaryContentProvider]
     */
    fun deleteDictionary(id: Long) {
        val disposable = dictionaryInteractor.delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val items = dictionaryItemsLiveData.value as ArrayList<DictionaryItemModel>
                items.removeAll { it.id == id }
                _dictionaryItemsLiveData.value = items
            }
        compositeDisposable.add(disposable)
    }

    /**
     * Delete data from Media Content Provider
     */
    fun getLocalImagesAsyncRx() {
        val disposable = localImageInteractor.getLocalImages()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _localImageProgressLiveData.value = true }
            .doFinally { _localImageProgressLiveData.value = false }
            .subscribe(
                { items ->
                    _localImageLiveData.value = items
                },
                { throwable ->
                    _localImageErrorLiveData.value = throwable
                    _localImageErrorLiveData.value = null
                }
            )
        compositeDisposable.add(disposable)
    }

    fun pickImage(uri: Uri) {
        _selectedImageLiveData.value = uri
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}