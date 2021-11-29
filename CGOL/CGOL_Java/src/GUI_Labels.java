import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class GUI_Labels {
    public static void main(String[] args){
        //Frame
        JFrame jFrame = new JFrame(); // Creates the frame for out GUI
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Action performed when close button is clicked.


        // Creating Label, text and image.
        JLabel jLabel = new JLabel(); // Creates a label where you can add images and text.
        jLabel.setText("Conways game of Life"); // Sets the text of your label
        ImageIcon imageIcon = new ImageIcon("CGOL_Icon.jpg"); // Create an image Icon
        jLabel.setIcon(imageIcon); // Adds an image Icon to your label
        jLabel.setFont(new Font("Arial", Font.PLAIN, 20)); // stylize text
        jLabel.setBounds(0,0, 500, 500);

        JLabel jLabel2 = new JLabel();
        jLabel2.setText("Hello");
        jLabel2.setIcon(imageIcon);
        jLabel2.setBounds(500,0,500,500);
        jLabel2.setBackground(Color.CYAN);
        jLabel2.setOpaque(true);
        jFrame.add(jLabel2);
        // Create a border for our label
        Border border = BorderFactory.createLineBorder(Color.blue, 3);
        jLabel.setBorder(border);


        // Alignment
        jLabel.setVerticalTextPosition(SwingConstants.TOP); // Set vertical position of your *text*
        jLabel.setHorizontalTextPosition(SwingConstants.CENTER); // set horiziontal position of text.
        jLabel.setHorizontalAlignment(SwingConstants.CENTER); // set horizontal alignment of label
        jLabel.setVerticalAlignment(SwingConstants.CENTER); // set vertical alignment of label

        // Coloring label.
        jLabel.setBackground(Color.black); // sets background color of your label, labels usually take up as much space as possible.
        jLabel.setForeground(Color.white); // Changes color of text..?
        jLabel.setOpaque(true); // makes color changes to label visible.

        jFrame.setSize(1800,1800); // Set the size of our frame.
        jFrame.setLayout(null); // Makes adjustments to label bounds visible.
        jFrame.add(jLabel); // Adds our jLabel to the frame.
        //jFrame.pack(); // Resizes window to contain all elements, make sure to add after adding all components.
        jFrame.setVisible(true); // Allows the frame to be seen.

    }
}