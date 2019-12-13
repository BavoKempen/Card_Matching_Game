package view;

import javax.swing.*;

public class RunMemoryGame {
    public static void main (String[] args){

        SwingUtilities.invokeLater(new Runnable() { //To make it more robust, asynchronously queues the constructor on
            // event dispatching thread. Thank you YouTube!

            public void run() {
                new MainFrame(); //create new class of view.MainFrame type defined in respective class where the rest can be added

            }
        });
    }
}
