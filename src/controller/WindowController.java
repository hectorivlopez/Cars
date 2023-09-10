package controller;

import model.*;
import view.Window;

import java.io.IOException;

public class WindowController {
    public static Window window;
    //public static AnimateCar animation;
    public static App app;
    public static Object mutex = new Object();

    public static void start() {
        window = new Window();
        //animation = new AnimateCar(window.screen);
       // animation.start();
        /*Car[] cars = new Car[10];
        CarThread[] carsT = new CarThread[10];
        for(int i = 0; i < 10; i++) {
            Car newCar = new Car(i);
            cars[i] = newCar;
            CarThread newCarThread = new CarThread(newCar, mutex, window.screen);
            carsT[i] = newCarThread;
            newCarThread.start();
        }


         */
        Repaint cosa = new Repaint(window.screen);
        cosa.start();
    }

    public static void startAnimation() {
        app.start();
    }

    public static void addCarLeft() throws IOException {
        app.addCar("left");
        updateLabels();

    }

    public static void addCarRight() throws IOException {
        app.addCar("right");
        updateLabels();

    }

    public static void updateLabels() {
        int carsLeftAmount = app.getCarsLeft().size();
        if(app.getCarsPassing().equals("left")) {
            carsLeftAmount += app.getWay().size();
        }
        int carsRightAmount = app.getCarsRight().size();
        if(app.getCarsPassing().equals("right")) {
            carsRightAmount += app.getWay().size();
        }
        window.carsLeftLabel.setText("Izquierda: " + String.valueOf(carsLeftAmount));
        window.carsRightLabel.setText("Derecha: " + String.valueOf(carsRightAmount));
    }
}
