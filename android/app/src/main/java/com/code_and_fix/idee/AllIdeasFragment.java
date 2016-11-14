package com.code_and_fix.idee;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.Bind;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllIdeasFragment extends Fragment {

    @Bind(R.id.frame) FrameLayout frame;


    public AllIdeasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //will be changed, when there'll be an adapter and database
        View view = inflater.inflate(R.layout.idea_cell, container, true);
        return inflater.inflate(R.layout.fragment_all_ideas, container, false);
    }


}
