package crabster.rudakov.sberschoollesson30hwk.domain.converter;

import java.util.*;

import crabster.rudakov.sberschoollesson30hwk.domain.models.*;
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.models.*;

public class DictionaryConverterImpl implements IDictionaryConverter {

    @Override
    public DictionaryItemModel convert(DictionaryItem dictionaryItem) {
        DictionaryItemModel dictionaryItemModel = new DictionaryItemModel();
        dictionaryItemModel.setKeyword(dictionaryItem.getKeyword());
        dictionaryItemModel.setTranslation(dictionaryItem.getTranslation());

        return dictionaryItemModel;
    }

    @Override
    public DictionaryItem reverse(DictionaryItemModel dictionaryItemModel) {
        DictionaryItem dictionaryItem = new DictionaryItem();
        dictionaryItem.setKeyword(dictionaryItemModel.getKeyword());
        dictionaryItem.setTranslation(dictionaryItemModel.getTranslation());

        return dictionaryItem;
    }

    @Override
    public List<DictionaryItem> reverseList(List<DictionaryItemModel> dictionaryItemModels) {
        List<DictionaryItem> result = new ArrayList<>(dictionaryItemModels.size());
        for (DictionaryItemModel model : dictionaryItemModels) {
            result.add(reverse(model));
        }

        return result;
    }

}
