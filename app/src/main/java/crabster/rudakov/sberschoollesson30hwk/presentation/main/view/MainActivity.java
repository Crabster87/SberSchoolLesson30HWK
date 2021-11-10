package crabster.rudakov.sberschoollesson30hwk.presentation.main.view;

import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.*;

import android.os.Bundle;

import java.util.*;

import crabster.rudakov.sberschoollesson30hwk.data.repositories.*;
import crabster.rudakov.sberschoollesson30hwk.databinding.*;
import crabster.rudakov.sberschoollesson30hwk.domain.converter.*;
import crabster.rudakov.sberschoollesson30hwk.domain.interactors.*;
import crabster.rudakov.sberschoollesson30hwk.domain.models.*;
import crabster.rudakov.sberschoollesson30hwk.domain.repositories.*;
import crabster.rudakov.sberschoollesson30hwk.presentation.main.viewmodel.*;
import crabster.rudakov.sberschoollesson30hwk.utils.*;

public class MainActivity extends AppCompatActivity {

    private DictionaryViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createViewModel();
        observeLiveData();
    }

    @Override
    protected void onStart() {
        super.onStart();

        viewModel.loadDataAsyncRx();
    }

    private void createViewModel() {
        IDictionaryRepository dictionaryRepository = new DictionaryRepositoryImpl(getApplicationContext());
        IDictionaryInteractor dictionaryInteractor = new DictionaryInteractorImpl(dictionaryRepository,
                new DictionaryConverterImpl());
        ISchedulers schedulers = new SchedulersImpl();
        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new DictionaryViewModel(dictionaryInteractor, schedulers);
            }
        }).get(DictionaryViewModel.class);
    }

    private void observeLiveData() {
        viewModel.getDictionaryItemsLiveData().observe(this, this::showWords);
    }

    private void showWords(List<DictionaryItem> list) {
        binding.log.setText(list.toString());
    }

}