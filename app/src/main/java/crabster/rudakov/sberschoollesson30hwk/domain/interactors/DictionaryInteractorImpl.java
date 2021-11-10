package crabster.rudakov.sberschoollesson30hwk.domain.interactors;

import androidx.annotation.*;

import java.util.*;

import crabster.rudakov.sberschoollesson30hwk.domain.converter.*;
import crabster.rudakov.sberschoollesson30hwk.domain.models.*;
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.*;
import io.reactivex.*;

public class DictionaryInteractorImpl implements IDictionaryInteractor {

    private final IDictionaryRepository repository;
    private final IDictionaryConverter converter;

    public DictionaryInteractorImpl(@NonNull IDictionaryRepository repository,
                                    @NonNull IDictionaryConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Completable add(@NonNull DictionaryItem dictionaryItem) {
        return repository.add(converter.convert(dictionaryItem));
    }

    @Override
    public Single<List<DictionaryItem>> getList() {
        return repository.getList()
                .map(converter::reverseList);
    }

    @Override
    public Completable delete(long id) {
        return repository.delete(id);
    }

}
