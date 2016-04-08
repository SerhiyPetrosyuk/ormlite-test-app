package com.mlsdev.serhii.ormlitetestapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.mlsdev.serhii.ormlitetestapp.R;
import com.mlsdev.serhii.ormlitetestapp.databinding.ActivityNotesBinding;

public class NotesActivity extends AppCompatActivity {
    private ActivityNotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notes);
        initNotesRecyclerView();
    }

    private void initNotesRecyclerView() {
        binding.rvNotes.setHasFixedSize(true);
        binding.rvNotes.setLayoutManager(new LinearLayoutManager(this));
    }

}
