package model;

import view.AnimatedScreen;



public class CarThread extends Thread {
    private Car car;
    private App app;
    private Object mutex;
    private AnimatedScreen screen;
    private Boolean waiting;
    private int vel;

    public CarThread(Car car, App app, Object mutex, AnimatedScreen screen) {
        this.car = car;
        this.app = app;
        this.mutex = mutex;
        this.screen = screen;
        this.waiting = false;
        this.vel = 5;
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
        // ------------------------------ Advance to waiting ------------------------------
        if(this.app.getCarsPassing().equals("right") && this.car.side.equals("left")) {
            this.waiting = true;
            System.out.println("Car left avancing to wait");
            int wayPos;
            synchronized (this.app.getMutexRigth()) {
                this.app.getLeftWay().add(this.car);
                wayPos = this.app.getLeftWay().indexOf(this.car);
            }
            this.app.getCarsLeft().remove(this.car);
            this.mutex = this.app.getMutexWait();

            while (this.car.x < 385 - 160 - (160 * wayPos)) {
                this.car.x++;

                try {
                    sleep(this.vel);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if(this.app.getCarsPassing().equals("left") && this.car.side.equals("right")) {
            this.waiting = true;
            System.out.println("Car right avancing to wait");
            int wayPos;
            synchronized (this.app.getMutexLeft()) {
                this.app.getRightWay().add(this.car);
                wayPos = this.app.getRightWay().indexOf(this.car);
            }
            this.app.getCarsRight().remove(this.car);
            this.mutex = this.app.getMutexWait();

            while (this.car.x > 615 + (160 * wayPos)) {
                this.car.x--;

                try {
                    sleep(this.vel);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if(this.waiting) {
            if( !this.app.getCarsPassing().equals(this.car.side)) {
                synchronized (this.mutex) {
                    System.out.println("Thread " + this.car.side + " " + this.car.name + " waiting...");
                    try {
                        this.mutex.wait();

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            if(this.car.side.equals("left")) {
                this.mutex = this.app.getMutexLeft();
            }
            else {
                this.mutex = this.app.getMutexRigth();
            }
        }


        // ------------------------------ Advance to terminate ------------------------------



        System.out.println("Thread " + this.car.side + " " + this.car.name + " executing...");
        if(!this.waiting) {

            synchronized (this.mutex) {
                this.app.getWay().add(this.car);
            }
        }

        if(this.car.side.equals("left")) {
            this.app.getCarsLeft().remove(this.car);
            while (this.car.x < this.screen.getWidth() ) {
                this.car.x++;

                try {
                    sleep(this.vel);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else {
            this.app.getCarsRight().remove(this.car);
            while (this.car.x > -150) {
                this.car.x--;

                try {
                    sleep(this.vel);
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
