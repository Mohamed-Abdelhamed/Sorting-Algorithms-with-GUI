package sortinggui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SortingGUI extends JFrame {

    JButton selection;
    JButton Merge;
    JButton Quick;
    long startTime = 0;
    long endTime = 0;
    JFrame frame1 = new JFrame();

    public SortingGUI() {
        frame1.setLayout(null);
        frame1.setSize(600, 600);
        frame1.setVisible(true);

        selection = new JButton("Selection Sort");
        Merge = new JButton("Merge Sort");
        Quick = new JButton("Quick Sort");

        frame1.add(selection);
        frame1.add(Merge);
        frame1.add(Quick);

        selection.setBounds(150, 50, 300, 30);
        Merge.setBounds(150, 200, 300, 30);
        Quick.setBounds(150, 350, 300, 30);

        selection.addActionListener(new ButtonWatcher());
        Merge.addActionListener(new ButtonWatcher());
        Quick.addActionListener(new ButtonWatcher());
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class ButtonWatcher implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Object buttonPressed = ae.getSource();

            if (buttonPressed.equals(selection)) {
                JFrame frame = new JFrame("Selection sort");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                startTime = System.nanoTime();
                frame.setContentPane(new Selectionsort());
                frame.pack();
                frame.setVisible(true);
                endTime = System.nanoTime();
               // JOptionPane.showMessageDialog(null, "Time Taken : " + (endTime - startTime));

            }
            if (buttonPressed.equals(Merge)) {
                JFrame frame = new JFrame("Merge sort");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                startTime = System.nanoTime();
                frame.setContentPane(new MergeSort());
                frame.pack();
                frame.setVisible(true);
                endTime = System.nanoTime();
                //JOptionPane.showMessageDialog(null, "Time Taken : " + (endTime - startTime));

            }
            if (buttonPressed.equals(Quick)) {
                JFrame frame = new JFrame("Quick sort");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                startTime = System.nanoTime();
                frame.setContentPane(new QuickSort());
                frame.pack();
                frame.setVisible(true);
                endTime = System.nanoTime();
                //JOptionPane.showMessageDialog(null, "Time Taken : " + (endTime - startTime));

            }

        }
    }

    public static void main(String[] args) {

        SortingGUI g = new SortingGUI();

    }
}
