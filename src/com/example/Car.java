package com.example;

public class Car implements Runnable {

    String nameCar;
    private Parking parking;

    private Car(String nameCar, Parking parking) {
        this.nameCar = nameCar;
        this.parking = parking;
    }

    private long tempsTime() {
        long time = 100000;
        return (long) (time * Math.random());
    }

    @Override
    public void run() {
        Util.log(nameCar + " вьезжает в парковку ");

        while (true) {
            try {
                Thread.sleep(tempsTime());
                Util.log(nameCar + " ждать снаружи");
                Thread.sleep(tempsTime());

                entrance();

                Thread.sleep(tempsTime());
                Util.log(nameCar + " есть место");

                parking.leave(this);
                Util.log(nameCar + " покинул парковку");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void entrance() {
        try {
            while (!this.parking.accept(this)) {
                Thread.sleep(tempsTime());
                Util.log(nameCar + " \n" + "пытается вернуться");
            }
        } catch (Exception e) {
            Util.log("Исключение брошенное  " + nameCar + "\n" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int sizeParking = 8;
        int nbCar = 15;
        Parking leParking = new Parking(sizeParking);
        Thread[] car = new Thread[nbCar];
        for (int i = 0; i < nbCar; i++) {
            car[i] = new Thread(new Car(String.format("Car %d ", i), leParking));
            car[i].start();
        }
    }
}

