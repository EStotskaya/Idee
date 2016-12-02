package com.code_and_fix.idee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Mary on 15.11.2016.
 */
public class DrawerAdapter extends ArrayAdapter<String> {
    private Context _context;
    private int _resource;
    public String[] _list;


    public DrawerAdapter(Context context, int resource, String[] list)
    {
        super(context, resource, list);
        _context = context;
        _resource = resource;
        _list = list;
    }

    @Override
    public int getCount()
    {
        return _list.length;
    }

    @Override
    public String getItem(int position)
    {
        return _list[position];
    }

    public class CellHolder
    {
        @Bind(R.id.textView) TextView text;
        CellHolder(View v) {ButterKnife.bind(this, v);}
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Fonts fonts = new Fonts(_context);
        CellHolder cell;

        if(convertView == null)
        {
            convertView = ((LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(_resource, parent, false);
            cell = new CellHolder(convertView);
            convertView.setTag(cell);
        }
        else
        {
            cell = (CellHolder)convertView.getTag();
        }

        cell.text.setTextColor(_context.getResources().getColor(R.color.LIGHTBLUE));
        cell.text.setTextSize(25);
        cell.text.setText(_list[position]);
        cell.text.setTypeface(fonts.caviarBold());

        return convertView;
    }


}
