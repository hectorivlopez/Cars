package model;

import controller.WindowController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;


public class App {
    private LinkedList<Car> way;
    private LinkedList<Car> rightWay;
    private LinkedList<Car> leftWay;
    private LinkedList<Car> carsLeft;
    private LinkedList<Car> carsRight;
    private String carsPassing;
    private Object mutexLeft;
    private Object mutexRigth;
    private Object mutexWait;
    private LinkedList<Car> waitList;
    public Car lastCar;
    private Notifier notifier;
    private int carsLeftAmount;
    private int carsRightAmount;


    public App() throws IOException {

        this.carsLeft = new LinkedList<Car>();
        this.carsRight = new LinkedList<Car>();
        this.carsPassing = "left";
        this.mutexLeft = new Object();
        this.mutexRigth = new Object();
        this.mutexWait = new Object();
        this.rightWay = new LinkedList<Car>();
        this.leftWay = new LinkedList<Car>();
        this.way = leftWay;
        this.carsLeftAmount = 0;
        this.carsRightAmount = 0;
    }

    public void start() {
        this.notifier = new Notifier(this, WindowController.window.screen);
        notifier.start();
    }


    public void addCar(String side) throws IOException {
        if(this.way.isEmpty() && this.carsLeft.isEmpty() && this.carsRight.isEmpty()) {
            this.carsPassing = side;
            if(side.equals("left")) {
                this.way = this.leftWay;
            }
            else {
                this.way = this.rightWay;
            }
        }
        Car newCar;
        if(side.equals("left")) {
            this.carsLeftAmount++;
            newCar = new Car(this.carsLeftAmount, side, -150, 100);
            this.carsLeft.add(newCar);
            /*if(this.carsPassing.equals("left")) {
                this.way = this.carsLeft;
            }*/
            CarThread newCarThread = new CarThread(newCar, this, mutexLeft, WindowController.window.screen);
            newCarThread.start();
        }
        else  {
            this.carsRightAmount++;
            newCar = new Car(this.carsRightAmount, side, WindowController.window.screen.getWidth(), -28);
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
        System.out.println(this.way.size());
        if(this.way.isEmpty()) {
            System.out.println("Change way");
            if(this.carsPassing.equals("left")) {
                this.carsLeftAmount = 0;
                this.carsPassing = "right";
                /* synchronized (this.mutexRigth) {
                    this.mutexRigth.notify();;
                } */
                this.way = this.rightWay;
                System.out.println("Active way: right");
                synchronized (this.mutexWait) {
                    this.mutexWait.notifyAll();
                }
            }
            else {
                this.carsRightAmount = 0;
                this.carsPassing = "left";
                /* synchronized (this.mutexLeft) {
                    this.mutexLeft.notify();;
                } */
                this.way = this.leftWay;
                System.out.println("Active way: left");
                synchronized (this.mutexWait) {
                    this.mutexWait.notifyAll();
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

    public LinkedList<Car> getRightWay() {
        return rightWay;
    }

    public void setRightWay(LinkedList<Car> rightWay) {
        this.rightWay = rightWay;
    }

    public LinkedList<Car> getLeftWay() {
        return leftWay;
    }

    public void setLeftWay(LinkedList<Car> leftWay) {
        this.leftWay = leftWay;
    }

    public Object getMutexWait() {
        return mutexWait;
    }

    public void setMutexWait(Object mutexWait) {
        this.mutexWait = mutexWait;
    }

    public int getCarsLeftAmount() {
        return carsLeftAmount;
    }

    public void setCarsLeftAmount(int carsLeftAmount) {
        this.carsLeftAmount = carsLeftAmount;
    }

    public int getCarsRightAmount() {
        return carsRightAmount;
    }

    public void setCarsRightAmount(int carsRightAmount) {
        this.carsRightAmount = carsRightAmount;
    }


}
