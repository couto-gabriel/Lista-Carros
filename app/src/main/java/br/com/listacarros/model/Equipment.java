package br.com.listacarros.model;

public class Equipment {

    private int equipmentId;
    private String description;
    private String category;
    private int vehicleId;

    public int getEquipmentId() {

        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {

        this.equipmentId = equipmentId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
