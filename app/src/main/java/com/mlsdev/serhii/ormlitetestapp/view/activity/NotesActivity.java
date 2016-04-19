package com.mlsdev.serhii.ormlitetestapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.mlsdev.serhii.ormlitetestapp.R;
import com.mlsdev.serhii.ormlitetestapp.databinding.ActivityNotesBinding;
import com.mlsdev.serhii.ormlitetestapp.view.adapter.NotesAdapter;
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
        binding.rvNotes.setItemAnimator(new DefaultItemAnimator());
        NotesAdapter notesAdapter = new NotesAdapter();
        binding.rvNotes.setAdapter(notesAdapter);
        viewModel.setNotesAdapter(notesAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.onStop();
    }
}
