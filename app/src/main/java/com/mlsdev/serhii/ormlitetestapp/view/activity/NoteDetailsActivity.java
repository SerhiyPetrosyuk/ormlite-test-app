package com.mlsdev.serhii.ormlitetestapp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mlsdev.serhii.ormlitetestapp.R;
import com.mlsdev.serhii.ormlitetestapp.databinding.ActivityNoteDetailsBinding;
import com.mlsdev.serhii.ormlitetestapp.model.Note;
import com.mlsdev.serhii.ormlitetestapp.viewmodel.BaseViewModel;
import com.mlsdev.serhii.ormlitetestapp.viewmodel.NoteDetailsViewModel;

public class NoteDetailsActivity extends AppCompatActivity {
    private ActivityNoteDetailsBinding binding;
    private NoteDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_details);
        viewModel = new NoteDetailsViewModel(this);
        binding.setViewModel(viewModel);
        checkNoteForEditing();
        initToolBar();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause(binding.etNoteTitle.getText().toString(), binding.etNoteDetails.getText().toString());
    }

    private void checkNoteForEditing() {
        Intent intent = getIntent();
        Note noteForEditing = intent.getParcelableExtra(BaseViewModel.EXTRA_NOTE);
        if (noteForEditing != null)
            viewModel.setData(noteForEditing);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setTitle("");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
