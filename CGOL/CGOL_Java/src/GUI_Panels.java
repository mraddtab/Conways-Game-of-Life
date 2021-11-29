import javax.swing.*;
import java.awt.*;

public class GUI_Panels {
    public static void main(String[] args) {
        //JPanel = a GUI componenet that functions as a container to hold other componenets (kind of like a div in HTML)
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setBounds(0,0, 250,250);
        redPanel.setLayout(null);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.blue);
        bluePanel.setBounds(250,0,250,250);
        bluePanel.setLayout(null);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.green);
        greenPanel.setBounds(0,250,500,250);
        greenPanel.setLayout(new BorderLayout()); // When using layout manager, the panel will place items to the left horizontally, and center vertically.
                                                  // On default it will append it to the top row center, until it is full.

        JLabel label = new JLabel();
        label.setText("Hi");
        ImageIcon imageIcon = new ImageIcon("CGOL_Icone.jpg");
        label.setIcon(imageIcon);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.RIGHT);
        label.setBounds(0,0,75,75); // Label will move relative to the container it is in and not the Frame itself.

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(750, 750);
        frame.setVisible(true);
        frame.add(redPanel);
        frame.add(bluePanel);
        frame.add(greenPanel);
        greenPanel.add(label);
        redPanel.add(label);
        bluePanel.add(label);
    }
}
