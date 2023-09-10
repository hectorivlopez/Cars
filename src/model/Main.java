package model;

import controller.WindowController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        App app = new App();
        WindowController.app = app;
        WindowController.start();
    }
}
