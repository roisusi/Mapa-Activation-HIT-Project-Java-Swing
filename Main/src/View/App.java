package View;

import javax.swing.*;

public class App {
    public static void main(String[] args)
    {
        //for multithreading
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomePage();
            }
        });

    }
}