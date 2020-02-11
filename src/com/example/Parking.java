package com.example;

import java.util.HashSet;

public class Parking {
    private int occupiedPlaces;
    private int capacity;

    HashSet<Car> carHashSet = new HashSet<>();

    public Parking(int size) {
        this.capacity = size;
    }

    private int places() {
        return capacity - occupiedPlaces;
    }

  public   synchronized boolean accept(Car car) {

        if (places() > 0) {
            carHashSet.add(car);
            occupiedPlaces++;
            Util.log(car.nameCar + " вьехал в парковку" + "количество занятых мест на парковке " + occupiedPlaces);
            return true;
        }

        return false;
    }

    synchronized boolean leave(Car car) {
        carHashSet.remove(car);
        --occupiedPlaces;
        Util.log(car.nameCar + " выехал из парковки " + "количество мест на парковке " + occupiedPlaces);
        return true;
    }

    public int getOccupiedPlaces() {
        return occupiedPlaces;
    }

    public void setOccupiedPlaces(int occupiedPlaces) {
        this.occupiedPlaces = occupiedPlaces;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public HashSet<Car> getCarHashSet() {
        return carHashSet;
    }

    public void setCarHashSet(HashSet<Car> carHashSet) {
        this.carHashSet = carHashSet;
    }
}


