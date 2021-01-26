package sortinggui;

import java.awt.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class QuickSort extends JPanel {

    int Pivot;
    int jj;
    int ii;
    int time = 250;
    String ss = "pivot is ";


    int n = Integer.parseInt(JOptionPane.showInputDialog("Please input a value"));
    int arr[] = new int[n];
    private int BOX_WIDTH = n * 50 + 20;
    private static final int BOX_HEIGHT = 480;

    public QuickSort() {
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

        /* Random rn = new Random();
        for (int i = 0; i < arr.length; i++) {
        int randomInt = rn.nextInt(42) + 1;
        arr[i] = randomInt;
        
        }*/
        Thread gameThread = new Thread() {
            public void run() {
                try {

                    sort(arr, 0, arr.length - 1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuickSort.class.getName()).log(Level.SEVERE, null, ex);
                }
                jj = -1;
                ii = -1;
                Pivot = -1;
                ss = "kda a7na 5lsnaa :D";
                repaint();
            }
        };
        gameThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    // Paint background

        // Draw the box
        g.setColor(Color.white);
        g.drawRoundRect(0, 0, BOX_WIDTH, BOX_HEIGHT, 15, 15);
        int j = 0;

        for (int i = 0; i < arr.length; i++) {

            g.setColor(Color.black);
            g.drawRoundRect(j, 200, BOX_WIDTH / arr.length, 40, 15, 15);
            if (i == jj) {
                g.setColor(Color.blue);
            } else if (i == ii) {
                g.setColor(Color.cyan);
            } else if (arr[i] == Pivot) {
                g.setColor(Color.yellow);
            } else {
                g.setColor(Color.red);
            }
            g.drawRoundRect(j, 200, BOX_WIDTH / arr.length, 40, 15, 15);
            g.setColor(Color.BLACK);
            g.drawString("" + arr[i], 0 + j + 3, 225);
            j += BOX_WIDTH / arr.length;
            g.drawString(ss + Pivot, 0, 350);

        }

    }

    private void sort(int[] arr, int l, int r) throws InterruptedException {
        if (l < r) {
            Pivot = arr[l];
            ii = l;
            jj = r;
            while (ii < jj) {
                ii++;
                while (ii <= r && arr[ii] < Pivot) {
                    ii++;
                    repaint();
                    Thread.sleep(time);
                }
                while (jj >= l && arr[jj] > Pivot) {
                    jj--;
                    repaint();
                    Thread.sleep(time);
                }
                if (ii <= r && ii < jj) {
                    swap(arr, ii, jj);
                    repaint();
                    Thread.sleep(time);
                }
            }
            swap(arr, l, jj);
            repaint();
            Thread.sleep(time);
            sort(arr, l, jj - 1);
            sort(arr, jj + 1, r);
        }
    }

    private void swap(int[] arr, int i, int j) {
        if (i >= 0 && j >= 0 && i < arr.length && j < arr.length) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

}
