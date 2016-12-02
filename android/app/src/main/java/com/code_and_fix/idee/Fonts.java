package com.code_and_fix.idee;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Mary on 02.12.2016.
 */
public class Fonts {

    Context _context;

    public Fonts(Context context)
    {
        _context = context;
    }

    public Typeface lobster()
    {
        return Typeface.createFromAsset(_context.getAssets(), "Lobster_1.3.otf");
    }

    public Typeface caviarNorm()
    {
        return Typeface.createFromAsset(_context.getAssets(), "CaviarDreams.ttf");
    }

    public Typeface caviarBold()
    {
        return Typeface.createFromAsset(_context.getAssets(), "Caviar_Dreams_Bold.ttf");
    }

    public Typeface caviarBoldItalic()
    {
        return Typeface.createFromAsset(_context.getAssets(), "CaviarDreams_BoldItalic.ttf");
    }

    public Typeface caviarItalic()
    {
        return Typeface.createFromAsset(_context.getAssets(), "CaviarDreams_Italic.ttf");
    }

    public Typeface fff()
    {
        return Typeface.createFromAsset(_context.getAssets(), "FFF_Tusj.ttf");
    }

    public Typeface existence()
    {
        return Typeface.createFromAsset(_context.getAssets(), "Existence_Light.otf");
    }
}
