package br.com.listacarros.database.builder;

import android.content.Context;

import androidx.room.Room;

import br.com.listacarros.R;
import br.com.listacarros.database.ListaCarrosDatabase;

public class DatabaseBuilder {

    private static ListaCarrosDatabase mDatabaseInstance = null;

    public static ListaCarrosDatabase getInstance(Context context){
        if(mDatabaseInstance == null){
            mDatabaseInstance = Room.databaseBuilder(context, ListaCarrosDatabase.class,
                    context.getResources().getString(R.string.database_name)).allowMainThreadQueries()
                    .build();
        }
        return mDatabaseInstance;
    }

}
