package crabster.rudakov.sberschoollesson30hwk.domain.converter;

import java.util.*;

import crabster.rudakov.sberschoollesson30hwk.domain.models.*;
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.models.*;

public interface IDictionaryConverter {

    DictionaryItemModel convert(DictionaryItem dictionaryItem);

    DictionaryItem reverse(DictionaryItemModel dictionaryItemModel);

    List<DictionaryItem> reverseList(List<DictionaryItemModel> dictionaryItemModels);

}
