package com.mlsdev.serhii.ormlitetestapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.mlsdev.serhii.ormlitetestapp.R;
import com.mlsdev.serhii.ormlitetestapp.databinding.ActivityNotesBinding;
import com.mlsdev.serhii.ormlitetestapp.viewmodel.NotesViewModel;

public class NotesActivity extends AppCompatActivity {
    private ActivityNotesBinding binding;
    private NotesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new NotesViewModel(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notes);
        binding.setViewModel(viewModel);
        initNotesRecyclerView();
    }

    private void initNotesRecyclerView() {
        binding.rvNotes.setHasFixedSize(true);
        binding.rvNotes.setLayoutManager(new LinearLayoutManager(this));
    }

}
