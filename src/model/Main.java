package model;

import controller.WindowController;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        App app = new App();
        WindowController.app = app;
        WindowController.start();
    }
}
