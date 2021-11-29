import javax.swing.*;
import java.awt.*;

public class GUI_GridLayout {

    public static void main(String[] args) {
        // Layout Manager = Defines the natural layout for components within a container

        // GridLayout = places components in a grid of cells.
        //              Each component takes all the available space within its cell,
        //              Each cell is the same size

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(new GridLayout(3,3, 0 ,0 ));
        frame.setVisible(true);

        frame.add(new JButton("1"));
        frame.add(new JButton("2"));
        frame.add(new JButton("3"));
        frame.add(new JButton("4"));
        frame.add(new JButton("5"));
        frame.add(new JButton("6"));
        frame.add(new JButton("7"));
        frame.add(new JButton("8"));
        frame.add(new JButton("9"));

    }
}
