package com.code_and_fix.idee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mary on 14.11.2016.
 */
public class IdeeAdapter extends ArrayAdapter
{
    private Context _context;
    private int _resource;
    public List<JSONObject> _entries;

    @SuppressWarnings("unchecked")
    public IdeeAdapter(Context context, int resource, List<JSONObject> entries)
    {
        super(context, resource, entries);
        _context=context;
        _resource=resource;
        _entries=entries;
    }

    @Override
    public JSONObject getItem(int position)
    {
        return _entries.get(position);
    }

    @Override
    public int getCount()
    {
        return _entries.size();
    }

    public class Entry
    {
        @Bind(R.id.profPic) ImageView profPic;
        @Bind(R.id.username) TextView username;
        @Bind(R.id.timestamp) TextView time;
        @Bind(R.id.ideaText) TextView ideaText;
        @Bind(R.id.comment) ImageView comment;
        @Bind(R.id.like) ImageView like;
        @Bind(R.id.dislike) ImageView dislike;
        @Bind(R.id.rate) TextView rate;
        @Bind(R.id.tagText) TextView tagText;

        Entry(View v)
        {
            ButterKnife.bind(this, v);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Entry cell;

        if (convertView == null)
        {
            convertView = ((LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.idea_cell, parent, false);
            cell = new Entry(convertView);
            convertView.setTag(cell);
        }
        else
        {
            cell = (Entry)convertView.getTag();
        }


        cell.rate.setText(0);
        try {
            Date dt = new Date(_entries.get(position).getString("timestamp"));
            cell.username.setText(_entries.get(position).getString("name"));
            cell.time.setText(dt.toString());
            cell.ideaText.setText(_entries.get(position).getString("idea"));
            try {
                cell.tagText.setText(_entries.get(position).getString("tag"));
            }catch(NullPointerException e)
            {
                System.out.println("No tag at " + position);
            }
            if(_entries.get(position).getInt("rate") != 0)
            {
                cell.rate.setText(_entries.get(position).getInt("rate"));
            }
        }catch(JSONException e)
        {
            System.out.print("Something went wrong");
        }

        return convertView;
    }


}
