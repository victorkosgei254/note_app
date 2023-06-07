package com.example.note_app.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.note_app.R;
import com.example.note_app.models.Note;
import com.example.note_app.viewmodel.NoteViewModel;
import com.example.note_app.views.adapters.NoteAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment {
    RecyclerView noteRecyclerView;
    FloatingActionButton addNoteBtn;
    NoteAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;

    NoteViewModel viewModel;

    List<Note> allNotes;
    public NoteFragment() {
        // Required empty public constructor
    }


    public static NoteFragment newInstance(String param1, String param2) {
        NoteFragment fragment = new NoteFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        configureToolbar(view);
        configureRecyclerView(view);
        configureViewModel();

        addNoteBtn = view.findViewById(R.id.btn_new_note);


        addNoteBtn.setOnClickListener(it->{
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, new AddNoteFragment()).commit();
        });

        return (view);
    }
    void configureToolbar(View view)
    {
        toolbar = view.findViewById(R.id.home_toolbar);
        toolbar.inflateMenu(R.menu.notes_menu);
        toolbar.setOnMenuItemClickListener(toolbarListener);
    }
    void configureRecyclerView(View view)
    {
        adapter = new NoteAdapter(getActivity(),allNotes);
        adapter.setOnViewItemClickListener(itemClickListener);
        layoutManager = new LinearLayoutManager(getActivity());
        noteRecyclerView = view.findViewById(R.id.note_recycler_view);
        noteRecyclerView.setLayoutManager(layoutManager);
        noteRecyclerView.setItemAnimator(new DefaultItemAnimator());
        noteRecyclerView.setAdapter(adapter);
    }
    void configureViewModel()
    {
        viewModel = new NoteViewModel(getActivity().getApplication());
        viewModel.getAllNotes().observe(getActivity(),it->{
            allNotes = it;
            adapter.setItems(it);
            adapter.notifyDataSetChanged();
            noteRecyclerView.scrollToPosition(it.size() - 1);
        });

    }

    Toolbar.OnMenuItemClickListener toolbarListener = item->{
        if(item.getItemId() == R.id.save)
        {
            Toast.makeText(getActivity(),"From toolbar", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    };

    NoteAdapter.OnViewItemClickListener itemClickListener = item ->{
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, new AddNoteFragment())
                .commit();
    };
}