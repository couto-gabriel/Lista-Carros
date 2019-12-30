package br.com.listacarros.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import br.com.listacarros.R;

public class LoadImageUtils {

    public static void load(Context context, String url, ImageView imageView){
        Glide.with(context).load(url)
                .placeholder(context.getResources().getDrawable(R.drawable.ic_car_default))
                .centerCrop()
                .into(imageView);
    }
}
