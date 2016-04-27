package com.mlsdev.serhii.ormlitetestapp.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mlsdev.serhii.ormlitetestapp.R;
import com.mlsdev.serhii.ormlitetestapp.databinding.NoteItemBinding;
import com.mlsdev.serhii.ormlitetestapp.model.Note;
import com.mlsdev.serhii.ormlitetestapp.viewmodel.NoteDetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private Context context;
    private List<Note> noteList;
    private OnItemClickListener onItemClickListener;
    private OnDataSetChangedListener onDataSetChangedListener;
    private int selectedItem;

    public NotesAdapter(Context context, OnItemClickListener onItemClickListener,
                        OnDataSetChangedListener onDataSetChangedListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.onDataSetChangedListener = onDataSetChangedListener;
        noteList = new ArrayList<>();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note item = noteList.get(position);
        NoteDetailsViewModel noteDetailsViewModel = new NoteDetailsViewModel(context);
        noteDetailsViewModel.setData(item);
        holder.noteItemBinding.setViewModel(noteDetailsViewModel);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private NoteItemBinding noteItemBinding;

        public NoteViewHolder(View itemView) {
            super(itemView);
            noteItemBinding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            selectedItem = getAdapterPosition();
            onItemClickListener.onItemClick(noteList.get(getAdapterPosition()));
        }
    }

    public void setData(List<Note> noteList) {
        int currentListSize = this.noteList.size();
        int newListSize = noteList.size();
        this.noteList = noteList;

        if (currentListSize < newListSize) {
            notifyItemInserted(0);
            onDataSetChangedListener.onDataSetChanged(0);
        } else if (currentListSize > newListSize) {
            notifyItemRemoved(selectedItem);
        } else {
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public interface OnDataSetChangedListener {
        void onDataSetChanged(int position);
    }
}
