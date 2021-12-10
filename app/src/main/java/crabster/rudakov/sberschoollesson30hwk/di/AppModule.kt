package crabster.rudakov.sberschoollesson30hwk.di

import android.app.Application
import android.content.ContentResolver
import crabster.rudakov.sberschoollesson30hwk.data.datastores.db.DataBaseOpenHelper
import crabster.rudakov.sberschoollesson30hwk.data.repositories.DictionaryRepositoryImpl
import crabster.rudakov.sberschoollesson30hwk.data.repositories.LocalImageRepositoryImpl
import crabster.rudakov.sberschoollesson30hwk.domain.converter.DictionaryConverterImpl
import crabster.rudakov.sberschoollesson30hwk.domain.converter.IDictionaryConverter
import crabster.rudakov.sberschoollesson30hwk.domain.interactors.DictionaryInteractorImpl
import crabster.rudakov.sberschoollesson30hwk.domain.interactors.IDictionaryInteractor
import crabster.rudakov.sberschoollesson30hwk.domain.interactors.ILocalImageInteractor
import crabster.rudakov.sberschoollesson30hwk.domain.interactors.LocalImageInteractorImpl
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.IDictionaryRepository
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.ILocalImageRepository
import crabster.rudakov.sberschoollesson30hwk.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AppBindModule {

    @Binds
    abstract fun bindDictionaryRepositoryImplToIDictionaryRepository(dictionaryRepositoryImpl: DictionaryRepositoryImpl): IDictionaryRepository

    @Binds
    abstract fun bindDictionaryInteractorImplToIDictionaryInteractor(dictionaryInteractorImpl: DictionaryInteractorImpl): IDictionaryInteractor

    @Binds
    abstract fun bindDictionaryConverterImplToIDictionaryConverter(dictionaryConverterImpl: DictionaryConverterImpl): IDictionaryConverter

    @Binds
    abstract fun bindLocalImageRepositoryImplToILocalImageRepository(localImageRepository: LocalImageRepositoryImpl): ILocalImageRepository

    @Binds
    abstract fun bindLocalImageInteractiorImplToILocalImageInteractor(localImageInteractorImplImpl: LocalImageInteractorImpl): ILocalImageInteractor

}

@Module
class AppModule {

    @Provides
    fun provideContentResolver(application: Application): ContentResolver =
        application.contentResolver

    @Provides
    fun provideDataBaseOpenHelper(application: Application) =
        DataBaseOpenHelper(application.applicationContext)

}

@Module(includes = [AppBindModule::class, AppModule::class])
class DomainModule {

    @Provides
    fun provideDictionaryConverter() = DictionaryConverterImpl()

    @Provides
    fun provideDictionaryRepository(contentResolver: ContentResolver) =
        DictionaryRepositoryImpl(contentResolver)

    @Provides
    fun provideDictionaryInteractor(
        repository: IDictionaryRepository,
        converter: IDictionaryConverter) = DictionaryInteractorImpl(repository, converter)

    @Provides
    fun provideLocalImageRepository(contentResolver: ContentResolver) =
        LocalImageRepositoryImpl(contentResolver)

    @Provides
    fun provideLocalImageInteractor(localImageRepository: ILocalImageRepository) =
        LocalImageInteractorImpl(localImageRepository)

}

@Module(includes = [DomainModule::class])
class ViewModelFactoryModule {

    @Provides
    fun provideViewModelFactory(
        application: Application,
        dictionaryInteractor: IDictionaryInteractor,
        localImageInteractor: ILocalImageInteractor
    ): ViewModelFactory {
        return ViewModelFactory(application, dictionaryInteractor, localImageInteractor)
    }

}