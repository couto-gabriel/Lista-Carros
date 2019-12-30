package br.com.listacarros.database.converter;


import androidx.room.TypeConverter;

import java.util.ArrayList;

import br.com.listacarros.model.Equipment;

public class Converters {

    @TypeConverter
    public Equipment convertEquipmentListToObject(ArrayList<Equipment> list){
        Equipment equipment = null;

        for(Equipment e: list){
            equipment = e;
        }
        return equipment;
    }

    @TypeConverter
    public String convertPhototListToObject(ArrayList<String> list){
        String photo = null;

        for(String p: list){
            photo = p;
        }
        return photo;
    }
}
