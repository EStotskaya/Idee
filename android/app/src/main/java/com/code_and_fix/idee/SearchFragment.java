package com.code_and_fix.idee;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    @Bind(R.id.searchText) TextView searchText;
    @Bind(R.id.searchTag) EditText searchTag;
    @Bind(R.id.startSearch) Button start;
    Fonts fonts;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,view);
        fonts = new Fonts(getActivity());
        searchText.setTypeface(fonts.caviarBold());

        start.setTypeface(fonts.caviarBold());
        return view;
    }



}
