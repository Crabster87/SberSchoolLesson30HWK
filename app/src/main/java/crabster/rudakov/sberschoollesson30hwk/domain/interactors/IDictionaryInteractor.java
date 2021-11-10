package crabster.rudakov.sberschoollesson30hwk.domain.interactors;

import androidx.annotation.*;

import java.util.*;

import crabster.rudakov.sberschoollesson30hwk.domain.models.*;
import io.reactivex.*;

public interface IDictionaryInteractor {

    Completable add(@NonNull DictionaryItem dictionaryItem);

    Single<List<DictionaryItem>> getList();

    Completable delete(long id);

}
