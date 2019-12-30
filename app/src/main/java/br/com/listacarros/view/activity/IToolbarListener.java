package br.com.listacarros.view.activity;

import android.graphics.drawable.Drawable;

public interface IToolbarListener {

    void setToolbarTitle(String title);
    void setToolbarIcon(Drawable icon);
    void setBackClickListener();
}
