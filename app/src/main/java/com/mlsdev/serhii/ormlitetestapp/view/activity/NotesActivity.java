package com.mlsdev.serhii.ormlitetestapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.mlsdev.serhii.ormlitetestapp.R;
import com.mlsdev.serhii.ormlitetestapp.databinding.ActivityNotesBinding;
import com.mlsdev.serhii.ormlitetestapp.model.Note;
import com.mlsdev.serhii.ormlitetestapp.view.adapter.NotesAdapter;
import com.mlsdev.serhii.ormlitetestapp.viewmodel.NotesViewModel;

public class NotesActivity extends BaseActivity implements NotesAdapter.OnItemClickListener,
        NotesAdapter.OnDataSetChangedListener {
    private ActivityNotesBinding binding;
    private NotesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new NotesViewModel(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notes);
        binding.setViewModel(viewModel);
        initNotesRecyclerView();
        initToolBar(false);
    }

    private void initNotesRecyclerView() {
        binding.rvNotes.setHasFixedSize(true);
        binding.rvNotes.setLayoutManager(new LinearLayoutManager(this));
        binding.rvNotes.setItemAnimator(new DefaultItemAnimator());
        NotesAdapter notesAdapter = new NotesAdapter(this, this, this);
        notesAdapter.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(Note note) {
        viewModel.onEditNote(note);
    }

    @Override
    public void onDataSetChanged(int position) {
        binding.rvNotes.scrollToPosition(position);
    }
}
