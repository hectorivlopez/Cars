package model;

import view.AnimatedScreen;



public class CarThread extends Thread {
    private Car car;
    private App app;
    private final Object mutex;
    private AnimatedScreen screen;

    public CarThread(Car car, App app, Object mutex, AnimatedScreen screen) {
        this.car = car;
        this.app = app;
        this.mutex = mutex;
        this.screen = screen;
    }

    public void run() {
        System.out.println("Thread " + this.car.side + " " + this.car.name + " started at " + System.currentTimeMillis());
        synchronized (this.mutex) {
            System.out.println("Thread " + this.car.side + " " + this.car.name + " waiting...");
            try {
                this.mutex.wait();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        System.out.println("Thread " + this.car.side + " " + this.car.name + " executing...");
        synchronized (this.mutex) {
            this.app.getWay().add(this.car);
        }
        if(this.car.side.equals("left")) {
            this.app.getCarsLeft().remove(this.car);
            while (this.car.x < this.screen.getWidth() ) {
                this.car.x++;

                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else {
            this.app.getCarsRight().remove(this.car);
            while (this.car.x > -100) {
                this.car.x--;

                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("Thread " + this.car.side + " " + this.car.name + " terminated at " + System.currentTimeMillis());
        synchronized (this.mutex) {
            app.removeCar(this.car);
        }
    }
}
