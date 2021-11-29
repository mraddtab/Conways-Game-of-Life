import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_MyFrameButton extends JFrame{
    // MyFrame is a subclass of JFrame and behaves exactly like a JFrame
    JButton button = new JButton();
    GUI_MyFrameButton(){
        // Creating and resizing an image icon
        ImageIcon icon = new ImageIcon("pointer.png");
        Image img = icon.getImage() ;
        Image newimg = img.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon( newimg );

        button.setBounds(100,100,250,100); // Set the position and size of the button
        button.addActionListener(e -> System.out.println("poo"));  // Performs a action when button is pressed. e -> func()
        button.setText("Press for poo");
        button.setFocusable(false); // removes clipping from long text
        button.setIcon(icon);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setFont(new Font("Comic Sans", Font.BOLD, 15));
        button.setIconTextGap(15); // set distance between icon and text
        button.setForeground(Color.black);  // text color
        button.setBackground(Color.white); // button color
        button.setBorder(BorderFactory.createEtchedBorder()); // create button border
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit on close
        this.setLayout(null);
        this.setSize(500,500);
        this.setVisible(true);
        this.add(button);
        this.getContentPane().setBackground(Color.BLACK); // Change frame color

    }

}
