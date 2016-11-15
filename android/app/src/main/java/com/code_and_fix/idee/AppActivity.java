package com.code_and_fix.idee;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class AppActivity extends AppCompatActivity {

    public static String LOGIN_INFO = "login";
    private int currentPosition;
    ActionBarDrawerToggle drawerToggle;
    //change!!
    String oursite;

    @Bind(R.id.drawerLayout) DrawerLayout drawerLayout;
    @Bind(R.id.drawer) ListView drawerList;
    @Bind(R.id.logInfo) TextView logInfo;
    @Bind(R.id.profilePic) ImageView profilePic;

    ArrayList<String> titles;

    @OnItemClick(R.id.drawer) public void selectCategory(AdapterView<?> adapter, View view, int position, long id )
    {
        selectItem(position);
    }

    @OnClick(R.id.logInfo) public void newIdeaCreator(View view)
    {
        newIdeaCreator();
    }

    @OnClick(R.id.profilePic) public void myCabOpen(View view) {selectItem(1);};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        ButterKnife.bind(this);
        drawerList.setAdapter(new DrawerAdapter(this, R.layout.drawer_item, getResources().getStringArray(R.array.categories)));

        if (savedInstanceState == null)
        {
            selectItem(0);
        }
        else
        {
            currentPosition = savedInstanceState.getInt("position");
            selectItem(currentPosition);
        }

        Intent intent = getIntent();
        if (intent != null)
        {
            if(intent.getStringExtra(LOGIN_INFO) != null)
            {
                logInfo.setText("  Hello, " + intent.getStringExtra(LOGIN_INFO) + "!\n  Tell us your idea : ");
            }

        }

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.opendrawer, R.string.closedrawer)
        {
            @Override
            public void onDrawerClosed(View view)
            {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        new myAsyncTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        switch(id){
            case R.id.newIdea:
                //new frame with new idea creation form
                newIdeaCreator();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }




    }

    private void selectItem(int position)
    {
        currentPosition = position;
        Toast.makeText(this, "Chosen position: " + position, Toast.LENGTH_LONG).show();
        Fragment fragment;
        switch(currentPosition)
        {
            case 0:
                fragment = new AllIdeasFragment();
                break;
            case 1:
                fragment = MyCabinetFragment.newInstance(getIntent().getStringExtra(LOGIN_INFO));
                break;
            case 2:
                fragment = new SearchFragment();
                break;
            default:
                fragment = new AllIdeasFragment();
                break;
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, fragment, "chosen");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        setActionBarTitle(position);
        if(drawerLayout.isDrawerOpen(drawerList)){
        drawerLayout.closeDrawer(drawerList);}
    }

    private void setActionBarTitle(int position)
    {
        String title;

        if(position < getResources().getStringArray(R.array.categories).length)
        {
            title = getResources().getStringArray(R.array.categories)[position];
        }
        else
        {
            title = getResources().getString(R.string.app_name);
        }

        Toast.makeText(this, title, Toast.LENGTH_LONG).show();

        try {
            assert getSupportActionBar() != null;
            getSupportActionBar().setTitle(title);
        }catch(NullPointerException e)
        {
            Log.d("Error: ", e.toString());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putInt("position", currentPosition);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        Fragment fragment = getFragmentManager().findFragmentByTag("chosen");
        if(fragment instanceof NewIdeaFragment)
        {
            menu.findItem(R.id.newIdea).setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onPostCreate(Bundle bundle)
    {
        super.onPostCreate(bundle);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void newIdeaCreator()
    {
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(getResources().getString(R.string.new_idea));
        Fragment fragment = new NewIdeaFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, fragment, "chosen");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        if(drawerLayout.isDrawerOpen(drawerList))
        {
            drawerLayout.closeDrawer(drawerList);
        }
    }


    //+onPostExecute; +change method
    protected class myAsyncTask extends AsyncTask<Void, Void, String>
    {
        HttpURLConnection http;
        BufferedReader buffer;
        String json;
        URL url;

        @Override
        protected void onPreExecute()
        {
            Log.d("Begin","AsyncTask");
        }

        @Override
        protected String doInBackground(Void...params)
        {

            try {
                url = new URL(oursite);

                http= (HttpURLConnection)url.openConnection();
                http.setRequestMethod("GET");
                http.connect();

                StringBuffer sbuffer = new StringBuffer();
                buffer = new BufferedReader(new InputStreamReader(http.getInputStream()));

                String line = "";

                while (buffer.readLine() != null)
                {
                    line = buffer.readLine();
                    sbuffer.append(line);
                }

                json = sbuffer.toString();

            }catch(Exception e)
            {
                Log.e("Bad URL", "Check url");
            }

            return json;
        }
    }
}
