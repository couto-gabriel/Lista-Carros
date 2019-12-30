package br.com.listacarros.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.com.listacarros.database.dao.VehicleDao;
import br.com.listacarros.model.Vehicle;

@Database(entities = {Vehicle.class}, version = 2, exportSchema = false)
public abstract class ListaCarrosDatabase extends RoomDatabase {

    public abstract VehicleDao vehicleDao();
}
