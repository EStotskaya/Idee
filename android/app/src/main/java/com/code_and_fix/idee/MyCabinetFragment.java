package com.code_and_fix.idee;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCabinetFragment extends Fragment {

    @Bind(R.id.name) TextView name;
    @Bind(R.id.profilePic) ImageView profilePic;
    @Bind(R.id.changePic) Button changePic;


    public MyCabinetFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_my_cabinet, container, false);


    }

    public static MyCabinetFragment newInstance(String name) {

        Bundle args = new Bundle();

        MyCabinetFragment fragment = new MyCabinetFragment();
        args.putString("Name", name);
        fragment.setArguments(args);
        return fragment;
    }


}
