package sortinggui;

import java.awt.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Selectionsort extends JPanel {

    int time = 250;


    int n = Integer.parseInt(JOptionPane.showInputDialog("Please input a value"));
    int arr[] = new int[n];
    private int BOX_WIDTH = n * 50 + 20;
    private static final int BOX_HEIGHT = 480;

    public Selectionsort() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        for (int i = 0; i < arr.length; i++) {
            int index = (int) (Math.random() * arr.length);
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        /*Random rn = new Random();
        for (int i = 0; i < arr.length; i++) {
        int randomInt = rn.nextInt(42) + 1;
        arr[i] = randomInt;
        
        }*/

        Thread gameThread = new Thread() {
            public void run() {
                try {
                    sort(arr);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Selectionsort.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        };
        gameThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {

        // Draw the box
        g.setColor(Color.white);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
        int j = 0;

        for (int i = 0; i < arr.length; i++) {

            g.setColor(Color.black);
            g.drawRoundRect(j, 200, BOX_WIDTH / arr.length, 40, 15, 15); // two lines byrsmo el black lines elly maben el rectangles

            g.setColor(Color.red);
            g.drawRoundRect(j, 200, BOX_WIDTH / arr.length, 40, 15, 15); // w dol bylwno el rectangle nafso a7mar

            g.setColor(Color.black);
            g.drawString("" + arr[i], 0 + j + 3, 225); // byrsmo el arkam 3l rectangles
            j += BOX_WIDTH / arr.length; // by5aly rectangle yetfrd fl screen kolha

        }
    }

    private void sort(int arr[]) throws InterruptedException {
        for (int i = 0; i < arr.length - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                    repaint();
                    Thread.sleep(time);
                }
            }
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
            repaint();
            Thread.sleep(time);
        }

    }

}
