package com.code_and_fix.idee;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.InputStream;
import java.net.MalformedURLException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCabinetFragment extends Fragment {

    @Bind(R.id.name) TextView name;
    @Bind(R.id.profilePic) ImageView profilePic;
    @Bind(R.id.changePic) Button changePic;
    @Bind(R.id.idea) TextView ideaTitle;
    @Bind(R.id.title) TextView cabTitle;
    Fonts fonts;

    SharedPreferences spref;

    final int PICK_PIC = 1;

    @OnClick(R.id.changePic) void picImage(Button butt)
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PIC);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case PICK_PIC:
                if (resultCode == RESULT_OK) {
                    try {
                        final Uri imageUri = intent.getData();
                        String imageName = imageUri.toString();
                        spref = getActivity().getSharedPreferences("accInfo", MODE_PRIVATE);
                        SharedPreferences.Editor edit = spref.edit();
                        edit.putString("image", imageName);
                        edit.apply();

                        ImageLoader loader = ImageLoader.getInstance();
                        ImageSize target = new ImageSize(200, 200);
                        loader.displayImage(imageName, profilePic, target);


                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Can't load image", Toast.LENGTH_LONG).show();
                    }
                }
        }
    }

    public MyCabinetFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_my_cabinet, container, false);
        ButterKnife.bind(this,view);
        fonts = new Fonts(getActivity());
        cabTitle.setTypeface(fonts.caviarBold());
        ideaTitle.setTypeface(fonts.caviarBold());
        changePic.setTypeface(fonts.caviarNorm());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity()).build();
        ImageLoader.getInstance().init(config);

        spref = getActivity().getSharedPreferences("accInfo", MODE_PRIVATE);
        String imagename = spref.getString("image", "");

        if(imagename.length()>0)
        {
            ImageLoader loader = ImageLoader.getInstance();
            ImageSize target = new ImageSize(200, 200);
            loader.displayImage(imagename, profilePic, target);
        }
        return view;


    }

    public static MyCabinetFragment newInstance(String name) {

        Bundle args = new Bundle();

        MyCabinetFragment fragment = new MyCabinetFragment();
        args.putString("Name", name);
        fragment.setArguments(args);
        return fragment;
    }


}
