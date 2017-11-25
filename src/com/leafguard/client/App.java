package com.leafguard.client;


import javax.swing.*;

public class App extends JFrame
{
    private App() {
        super("LeafGuard");
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }






    public static void main(String[] args) {

        new App().setVisible(true);
    }

}
