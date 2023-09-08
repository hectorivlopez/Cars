package model;

import controller.WindowController;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        WindowController.app = app;
        WindowController.start();
    }
}
