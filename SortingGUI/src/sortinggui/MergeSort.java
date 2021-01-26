package sortinggui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;

public class MergeSort extends JPanel {


    int n = Integer.parseInt(JOptionPane.showInputDialog("Please input a value"));
    int arr[] = new int[n];
    private int BOX_WIDTH = n * 50 + 20;
    private static final int BOX_HEIGHT = 480;
    private static final int sleepTime = 100;

    private int redColumn = -1;
    private int blueColumn = -1;
    private int greenColumnStart = -1;
    private int greenColumnFinish = -1;

    public MergeSort() {

        /*Random rn = new Random();
        for (int i = 0; i < arr.length; i++) {
        int randomInt = rn.nextInt(42) + 1;
        arr[i] = randomInt;
        
        }*/


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
        

        Thread thread = new Thread() {
            public void run() {
                try {
                    sort(arr, 0, arr.length - 1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MergeSort.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        thread.start();
    }

    public void sort(int[] arr, int l, int r) throws InterruptedException {
        if (l < r) {
            int m = (l + r) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public void merge(int[] arr, int l, int m, int r) throws InterruptedException {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }

        Thread.sleep(sleepTime);
        repaint();
        int i = 0, j = 0, k = l;
        redColumn = l;
        blueColumn = m + 1;
        Thread.sleep(2 * sleepTime);
        repaint();
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                redColumn = l + i + 1;
                k++;
                i++;
            } else {
                arr[k] = R[j];
                blueColumn = m + j + 1;
                k++;
                j++;
            }
            Thread.sleep(sleepTime);
            repaint();
        }

        // remaing element in small arrays
        while (i < n1) {
            arr[k] = L[i];
            redColumn = l + i + 1;
            i++;
            k++;
            Thread.sleep(sleepTime);
            repaint();
        }

        while (j < n2) {
            arr[k] = R[j];
            blueColumn = m + j + 1;
            k++;
            j++;
            Thread.sleep(sleepTime);
            repaint();
        }
         // to remove red and blue color
        redColumn = -1;
        blueColumn = -1;
        greenColumnStart = l;
        Thread.sleep(sleepTime);
        repaint();

        // to print green Color in sequence 
        for (int a = 0; a < n1 + n2; a++) {
            greenColumnFinish = l + a;
            Thread.sleep(2*sleepTime);
            repaint();
        }
        // to remove all green Color
        greenColumnStart = -1;
        greenColumnFinish = -1;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Background Color
        setBackground(Color.DARK_GRAY);
        for (int i = 0; i < arr.length; i++) {
            g.setColor(Color.black);
            g.drawRoundRect(i * 50 + 10, 200, BOX_WIDTH / arr.length, 40, 15, 15);
            g.drawString(String.valueOf(arr[i]), i * 50 + 18, 225);
        }
        if ((greenColumnStart != -1) && (greenColumnFinish != -1)) {
            for (int i = greenColumnStart; i <= greenColumnFinish; i++) {
                g.setColor(Color.GREEN);
                g.drawRoundRect(i * 50 + 10, 200,  BOX_WIDTH / arr.length, 40, 15, 15);
            }
        }
        if (redColumn != -1) {
            g.setColor(Color.RED);
            g.drawRoundRect(redColumn * 50 + 10, 200,  BOX_WIDTH / arr.length, 40, 15, 15);
        }
        if (blueColumn != -1) {
            g.setColor(Color.BLUE);
            g.drawRoundRect(blueColumn * 50 + 10, 200,  BOX_WIDTH / arr.length, 40, 15, 15);
        }
    }

}
