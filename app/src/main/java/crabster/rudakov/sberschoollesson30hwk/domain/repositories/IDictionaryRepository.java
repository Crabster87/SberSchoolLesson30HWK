package crabster.rudakov.sberschoollesson30hwk.domain.repositories;

import androidx.annotation.*;

import java.util.*;

import crabster.rudakov.sberschoollesson30hwk.domain.repositories.models.*;
import io.reactivex.*;

public interface IDictionaryRepository {

    Completable add(@NonNull DictionaryItemModel dictionaryItemModel);

    Single<List<DictionaryItemModel>> getList();

    Completable delete(long id);

}
