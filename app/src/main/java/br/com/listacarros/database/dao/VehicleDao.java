package br.com.listacarros.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.listacarros.model.Vehicle;

@Dao
public interface VehicleDao {

    @Insert
    void insert(Vehicle vehicle);

    @Query("SELECT * FROM Vehicle")
    List<Vehicle> getList();

    @Update
    void update(Vehicle vehicle);

    @Delete
    void delete(Vehicle vehicle);
}
