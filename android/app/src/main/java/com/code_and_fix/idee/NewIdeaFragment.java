package com.code_and_fix.idee;


import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.io.ByteArrayOutputStream;
import java.io.File;


import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import java.awt.image.BufferedImage;
import java.lang.Object;
import java.awt.Image;
import javax.imageio.ImageIO;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewIdeaFragment extends Fragment {


    @Bind(R.id.relLay) RelativeLayout relLay;
    @Bind(R.id.submit) Button submit;
    @Bind(R.id.ideaBody) EditText ideaBody;
    @Bind(R.id.addTag) Button addTag;
    @Bind(R.id.makePic) Button makePic;
    @Bind(R.id.imageFrame) ImageView imageFrame;
    @Bind(R.id.takePic) Button takePic;
    Fonts fonts;
    private final int CAMERA_RESULT = 1;
    private final int GALLERY_RESULT = 2;

    private boolean tagWasClicked;

    private byte[] pic;

    String url = "http://darkside2016.herokuapp.com/";

    @OnClick(R.id.submit) public void jsonClick(Button button)
    {
        JSONObject json = submit();
        AsyncRequest request = new AsyncRequest(url, json.toString(), "POST");
    }

    @OnClick(R.id.addTag) public void addTag(Button butt)
    {
        addTag();
        butt.setVisibility(View.GONE);
        tagWasClicked = true;
    }

    //something is wrong here
    @OnClick(R.id.makePic) void makePhoto(Button butt)
    {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(cameraIntent, CAMERA_RESULT);
    }

    @OnClick(R.id.takePic) void openGallery(Button button)
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_RESULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(requestCode == CAMERA_RESULT)
        {
            if(resultCode == RESULT_OK) {

                try {
                    if(intent!=null) {
                        Bitmap image = (Bitmap) intent.getExtras().get("data");
                        if(image!= null)
                        {
                            imageFrame.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            imageFrame.setVisibility(View.VISIBLE);
                            imageFrame.setImageBitmap(image);

                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            pic = stream.toByteArray();

                        }
                    }else{
                        Toast.makeText(getActivity(), "Intent is null", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Can't get photo", Toast.LENGTH_LONG).show();
                }
            }
        }
        else if(requestCode == GALLERY_RESULT)
        {
            if(resultCode == RESULT_OK)
            {
                if(intent != null)
                {
                try
                {
                    final Uri imageUri = intent.getData();
                    String imageName = imageUri.toString();

                    BufferedImage image = ImageIO.read(imageUri);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(image, "jpg", baos);
                    baos.flush();

                    pic = baos.toByteArray();


                    ImageLoader loader = ImageLoader.getInstance();
                    ImageSize target = new ImageSize(200, 200);
                    imageFrame.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageFrame.setVisibility(View.VISIBLE);
                    loader.displayImage(imageName, imageFrame, target);


                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(), "Can't load image", Toast.LENGTH_LONG).show();
                }
                }
            }
        }
    }


    public NewIdeaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //change later


        View view = inflater.inflate(R.layout.fragment_new_idea, container, false);
        ButterKnife.bind(this, view);
        fonts = new Fonts(getActivity());
        submit.setTypeface(fonts.caviarBold());
        addTag.setTypeface(fonts.caviarNorm());
        makePic.setTypeface(fonts.caviarNorm());
        takePic.setTypeface(fonts.caviarNorm());




        try
        {
            SQLiteOpenHelper baseHelper = new BaseHelper(getActivity());
            SQLiteDatabase db = baseHelper.getReadableDatabase();
            Cursor cursor = db.query("saved_ideas", new String[]{"idea", "tag"}, "name = ?", new String[]{AppActivity.LOGIN_INFO}, null, null, null);
            if(cursor.moveToLast())
            {
                ideaBody.setText(cursor.getString(0));

                try{
                    if(getView().findViewById(R.id.tag) != null)
                    {
                        EditText tag = (EditText) getView().findViewById(R.id.tag);
                        tag.setText(cursor.getString(1));
                    }
                }catch(NullPointerException e)
                {
                    System.out.println(e);
                }
                db.delete("saved_ideas", null, null);
            }
            cursor.close();
            db.close();
        }
        catch(SQLiteException e)
        {
            Toast.makeText(getActivity(), "Can't access DB", Toast.LENGTH_LONG).show();
        }
        return view;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if(!isOnline())
        {
            try
            {
                String s = "Saved idea: ";
                SQLiteOpenHelper baseHelper = new BaseHelper(getActivity());
                SQLiteDatabase db = baseHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("name", AppActivity.LOGIN_INFO);
                cv.put("idea", ideaBody.getText().toString());
                s += " ' " + ideaBody.getText().toString() + " ' ";
                try
                {
                    if(getView().findViewById(R.id.tag) != null)
                    {
                        EditText tag = (EditText)getView().findViewById(R.id.tag);
                        cv.put("tag", tag.getText().toString());
                        s += ", tag: ' " + tag.getText().toString() + " ' ";
                    }
                }catch(NullPointerException e)
                {
                    System.out.println(e);
                }
                db.insert("saved_ideas", null, cv);

                Toast.makeText(getActivity(), "No internet connection \n" + s, Toast.LENGTH_LONG).show();
            }
            catch(SQLiteException e)
            {
                Toast.makeText(getActivity(), "Can't write to DB", Toast.LENGTH_LONG).show();
            }
        }


        Bundle b = new Bundle();
        onSaveInstanceState(b);
    }


    private JSONObject submit()
    {
        EditText tag = (EditText) getView().findViewById(R.id.tag);

        myJSONClass myJson;

        if(pic.length>0)
        {
            myJson = new myJSONClass(AppActivity.LOGIN, ideaBody.getText().toString(), tag.getText().toString(), pic);
        }
        else
        {
            myJson = new myJSONClass(AppActivity.LOGIN, ideaBody.getText().toString(), tag.getText().toString());
        }


        Gson gson = new Gson();
        String my = gson.toJson(myJson);

        JSONObject json = new JSONObject();
        try{
            json = new JSONObject(my);
            Toast.makeText(this.getActivity(), json.toString(), Toast.LENGTH_LONG).show();
        }catch(JSONException e)
        {
            Toast.makeText(this.getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
        }


        return json;
    }

    private class myJSONClass
    {
        private String name = "";
        private String idea = "";
        private String tag = "";
        private byte[] picture;

        private myJSONClass(String name, String idea, String tag, byte[] picture)
        {
            this.name = name;
            this.idea = idea;
            this.tag = tag;
            this.picture = picture;
        }

        private myJSONClass(String name, String idea, String tag)
        {
            this.name = name;
            this.idea = idea;
            this.tag = tag;
        }
    }

    private boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();
        return nf != null && nf.isConnectedOrConnecting();
    }

    private void addTag()
    {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.ideaBody);
        params.setMargins(5, 5, 0, 5);

        EditText tag = new EditText(this.getActivity());
        tag.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        tag.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        tag.setMaxLines(1);
        tag.setHint("#add_your_tag");
        tag.setId(R.id.tag);
        tag.setTypeface(fonts.caviarNorm());

        relLay.addView(tag, params);


        RelativeLayout.LayoutParams makePicParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        makePicParams.addRule(RelativeLayout.BELOW, R.id.tag);
        makePicParams.setMargins(7, 5, 0, 0);
        makePic.setLayoutParams(makePicParams);

        RelativeLayout.LayoutParams takePicParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        takePicParams.addRule(RelativeLayout.BELOW, R.id.tag);
        takePicParams.setMargins(5, 5, 0, 5);
        takePicParams.addRule(RelativeLayout.ALIGN_RIGHT, R.id.ideaBody);
        takePic.setLayoutParams(takePicParams);



    }

    @Override
    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("tag", tagWasClicked);
    }



}
