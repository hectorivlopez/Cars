package controller;

import model.*;
import view.Window;

import javax.swing.*;
import java.io.IOException;

public class WindowController {
    public static Window window;
    //public static AnimateCar animation;
    public static App app;
    public static Object mutex = new Object();

    public static void start() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        window = new Window();

        Repaint cosa = new Repaint(window.screen);
        cosa.start();
    }

    public static void startAnimation() {
        app.start();
        window.startBtn.setEnabled(false);
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
