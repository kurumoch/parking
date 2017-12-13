package models;

public class Vehicle {

    private VehicleType type;
    private int parkingTime;
    private boolean isParking;

    public Vehicle(){

    }

    private void setParkingTime(int secs){
        this.parkingTime = secs;
    }

    private void setType(VehicleType type){
        this.type = type;
    }

    private void setIsParking(boolean isParking){
        this.isParking = isParking;
    }

    private int getParkingTime(){
        return parkingTime;
    }

    private VehicleType getType(){
        return type;
    }

    private boolean isParking(){
        return isParking;
    }


}
