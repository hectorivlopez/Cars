package model;

import controller.WindowController;
import view.Window;

import java.util.LinkedList;


public class App {
    private LinkedList<Car> way;
    private LinkedList<Car> carsLeft;
    private LinkedList<Car> carsRight;
    private String carsPassing;
    private Object mutexLeft;
    private Object mutexRigth;
    private LinkedList<Car> waitList;
    public Car lastCar;
    private Notifier notifier;

    public App() {
        this.way = new LinkedList<Car>();
        this.carsLeft = new LinkedList<Car>();
        this.carsRight = new LinkedList<Car>();
        this.carsPassing = "left";
        this.mutexLeft = new Object();
        this.mutexRigth = new Object();

    }

    public void start() {
        if(this.carsLeft.isEmpty() && !this.carsRight.isEmpty()) {
            this.carsPassing = "right";
        }
        this.notifier = new Notifier(this, WindowController.window.screen);
        notifier.start();
    }

    public void addCar(String side) {
        if(this.way.isEmpty()) {
            this.carsPassing = side;
        }
        Car newCar;
        if(side.equals("left")) {
            newCar = new Car(this.carsLeft.size() + 1, side, -100, 100);
            this.carsLeft.add(newCar);
            /*if(this.carsPassing.equals("left")) {
                this.way = this.carsLeft;
            }*/
            CarThread newCarThread = new CarThread(newCar, this, mutexLeft, WindowController.window.screen);
            newCarThread.start();
        }
        else  {
            newCar = new Car(this.carsRight.size() + 1, side, WindowController.window.screen.getWidth(), -28);
            this.carsRight.add(newCar);
            /*if(this.carsPassing.equals("right")) {
                this.way = this.carsRight;
            }*/
            CarThread newCarThread = new CarThread(newCar, this, mutexRigth, WindowController.window.screen);
            newCarThread.start();
        }
    }

    public void removeCar(Car car) {
        this.way.remove(car);
        WindowController.updateLabels();

        if(this.way.isEmpty()) {
            if(this.carsPassing.equals("left")) {
                this.carsPassing = "right";
                synchronized (this.mutexRigth) {
                    this.mutexRigth.notify();;
                }
            }
            else {
                this.carsPassing = "left";
                synchronized (this.mutexLeft) {
                    this.mutexLeft.notify();;
                }
            }
        }

    }

    public LinkedList<Car> getWay() {
        return way;
    }

    public void setWay(LinkedList<Car> way) {
        this.way = way;
    }

    public LinkedList<Car> getCarsLeft() {
        return carsLeft;
    }

    public void setCarsLeft(LinkedList<Car> carsLeft) {
        this.carsLeft = carsLeft;
    }

    public LinkedList<Car> getCarsRight() {
        return carsRight;
    }

    public void setCarsRight(LinkedList<Car> carsRight) {
        this.carsRight = carsRight;
    }



    public String getCarsPassing() {
        return carsPassing;
    }

    public void setCarsPassing(String carsPassing) {
        this.carsPassing = carsPassing;
    }

    public Object getMutexLeft() {
        return mutexLeft;
    }

    public void setMutexLeft(Object mutexLeft) {
        this.mutexLeft = mutexLeft;
    }

    public Object getMutexRigth() {
        return mutexRigth;
    }

    public void setMutexRigth(Object mutexRigth) {
        this.mutexRigth = mutexRigth;
    }

    public LinkedList<Car> getWaitList() {
        return waitList;
    }

    public void setWaitList(LinkedList<Car> waitList) {
        this.waitList = waitList;
    }

    public Car getLastCar() {
        return lastCar;
    }

    public void setLastCar(Car lastCar) {
        this.lastCar = lastCar;
    }

    public Notifier getNotifier() {
        return notifier;
    }

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }
}
