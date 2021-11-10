package crabster.rudakov.sberschoollesson30hwk.presentation.main.viewmodel;

import androidx.annotation.*;
import androidx.lifecycle.*;

import java.util.*;

import crabster.rudakov.sberschoollesson30hwk.domain.interactors.*;
import crabster.rudakov.sberschoollesson30hwk.domain.models.*;
import crabster.rudakov.sberschoollesson30hwk.utils.*;
import io.reactivex.disposables.*;

public class DictionaryViewModel extends ViewModel {

    private IDictionaryInteractor dictionaryInteractor;
    private ISchedulers schedulers;

    private final MutableLiveData<List<DictionaryItem>> dictionaryItemsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();


    private Disposable disposable;

    public DictionaryViewModel(@NonNull IDictionaryInteractor dictionaryInteractor,
                               @NonNull ISchedulers schedulers) {
        this.dictionaryInteractor = dictionaryInteractor;
        this.schedulers = schedulers;
    }

    public void loadDataAsyncRx() {
        disposable = dictionaryInteractor.getList()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(dictionaryItemsLiveData::setValue, errorLiveData::setValue);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
    }

    public MutableLiveData<List<DictionaryItem>> getDictionaryItemsLiveData() {
        return dictionaryItemsLiveData;
    }

    public MutableLiveData<Throwable> getErrorLiveData() {
        return errorLiveData;
    }

}
