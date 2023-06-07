package com.example.note_app.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.note_app.MainActivity;
import com.example.note_app.R;

public class AddNoteFragment extends Fragment {
    Toolbar new_note_toolbar;
    Button save_btn;
    public AddNoteFragment(){}

    public static AddNoteFragment newInstance(String params1, String params2)
    {
        AddNoteFragment fragment = new AddNoteFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_note_fragment, container,false);
        new_note_toolbar = view.findViewById(R.id.new_note_toolbar);
        save_btn = view.findViewById(R.id.save_note);



        save_btn.setOnClickListener(save_note);
        new_note_toolbar.setNavigationOnClickListener(it->{
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, new NoteFragment()).commit();
        });
        return (view);
    }

    View.OnClickListener save_note = it->{
        Toast.makeText(getActivity(), "Note saved", Toast.LENGTH_LONG).show();
    };
}
