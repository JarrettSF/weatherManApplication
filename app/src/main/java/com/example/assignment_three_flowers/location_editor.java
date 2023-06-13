package com.example.assignment_three_flowers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

// Some of this code is your code that I modified,however, I could not get the methods to work in this fragment
//this was the case with the location fragment as well, Im sure it will work once I get these to work
//I feel like I am always so close and I never get it until after it is time to turn in the assignment
public class location_editor extends Fragment {

    public location_editor() {
        // Required empty public constructor
    }

    private View view;

    private MessageViewModel messageViewModel;

    private EditText txt1;

    private EditText txt2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_location_editor, container, false);


        txt1 = view.findViewById(R.id.b1);
        txt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                messageViewModel.getTripDetails().setContinent(txt1.getText().toString());
            }
        });

        txt2 = view.findViewById(R.id.b2);
        txt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                messageViewModel.getTripDetails().setCity(txt2.getText().toString());
            }
        });


        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        messageViewModel = new ViewModelProvider(requireActivity()).get(MessageViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        txt1.setText(messageViewModel.getTripDetails().getContinent());
        txt2.setText(messageViewModel.getTripDetails().getCity());
    }
}

