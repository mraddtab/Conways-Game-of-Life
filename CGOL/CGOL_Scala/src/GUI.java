import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

import static java.awt.Color.*;
import static java.lang.Thread.sleep;

public class GUI {
    //button and grid setup
    public static Boolean running = false;
    public static JFrame frame = new JFrame();
    public static JPanel topPanel = new JPanel();
    public static JPanel botPanel = new JPanel();
    public static JPanel centerPanel = new JPanel();
    public static JButton[][] buttonGrid = new JButton[50][50];
    public static JButton start = new JButton("Start");
    public static JButton clear = new JButton("Clear");
    public static JButton next = new JButton("Next");
    public static Color ALIVE = green;
    public static Color DEAD = white;
    public static JSlider time = new JSlider(150, 2000 ,2000);

    // Methods.
    public static void clearGrid(){
        for(int i = 0; i < buttonGrid.length; i++){
            for(int j = 0; j < buttonGrid.length; j++){
                buttonGrid[i][j].setBackground(Color.WHITE);
            }
        }
    }
    public static int countNeighbors(JButton[][] grid, int x, int y ) {
        int count = 0;
            if (buttonGrid[x + 1][y - 1].getBackground() == green) {
                count += 1;
            }
            if (buttonGrid[x + 1][y].getBackground() == green) {
                count += 1;
            }
            if (buttonGrid[x + 1][y + 1].getBackground() == green) {
                count += 1;
            }
            if (buttonGrid[x - 1][y - 1].getBackground() == green) {
                count += 1;
            }
            if (buttonGrid[x - 1][y].getBackground() == green) {
                count += 1;
            }
            if (buttonGrid[x - 1][y + 1].getBackground() == green) {
                count += 1;
            }
            if (buttonGrid[x][y - 1].getBackground() == green) {
                count += 1;
            }
            if (buttonGrid[x][y + 1].getBackground() == green) {
                count += 1;
            }
        return count;
    }
    public static void step() {
        int[][] nextGeneration = new int[50][50];
        for (int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid.length; j++) {
                Color state = buttonGrid[i][j].getBackground(); // Color of selected cell
                // Edge Cases
                if (i == 0 || j == 0 || i == buttonGrid.length - 1 || j == buttonGrid.length - 1) {
                    nextGeneration[i][j] = 0;
                }
                //[[GAME LOGIC]
                // If cell is dead and neighbors >= 3, cell becomes alive otherwise stays dead.
                // If cell is alive, and neignbors == 2 or 3, lives on to next generation.
                // If cell is alive and neighbors < 2 or neighbors > 3, dies.
                else {
                    if (state == DEAD && countNeighbors(buttonGrid, i, j) == 3) {
                        nextGeneration[i][j] = 1;
                    }
                    if (state == ALIVE && countNeighbors(buttonGrid, i, j) < 2 || countNeighbors(buttonGrid, i, j) > 3) {
                        nextGeneration[i][j] = 0;
                    }
                    else if (state == ALIVE && countNeighbors(buttonGrid, i, j) == 2 || state == ALIVE && countNeighbors(buttonGrid, i, j) == 3) {
                        nextGeneration[i][j] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < nextGeneration.length; i++) {
            for (int j = 0; j < nextGeneration.length; j++) {
                if (nextGeneration[i][j] == 1) {
                    buttonGrid[i][j].setBackground(green);
                }
                if (nextGeneration[i][j] == 0) {
                    buttonGrid[i][j].setBackground(white);
                }
            }
        }
    }
    public static boolean allDead(){
        int count = 0;
        for (int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid.length; j++) {
                if(buttonGrid[i][j].getBackground() == ALIVE){
                    count++;
                }
            }
        }
        if(count > 0){
            return false;
        }
        else{
            return true;
        }
    }
    public static void wait(int ms) {
        try{
            Thread.sleep(ms);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    //Updates grid when run
    private static void processInBackground(){
        SwingWorker<Void, Void> Worker = new SwingWorker(){
            @Override
            protected Object doInBackground() throws Exception {
                    while (running == true) {
                        if(!allDead()) {
                            GUI.wait(time.getValue());
                            step();
                        }
                        else{
                            running = false;
                            start.setText("Start");
                        }
                    }
                return null;
            }
        };
        Worker.execute();
    }

    public static void main(String[] args) {

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setSize(1200,1200);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Ronald's Game of Life");
        // Setup our Frame


        // Panels
        topPanel.setBackground(new Color(30, 123, 194));
        botPanel.setBackground(new Color(30, 123, 194));
        centerPanel.setBackground(Color.white);
        topPanel.setPreferredSize(new Dimension(100,50));
        botPanel.setPreferredSize(new Dimension(100,100));
        frame.add(topPanel,BorderLayout.NORTH);
        frame.add(botPanel,BorderLayout.SOUTH);
        frame.add(centerPanel);

        // Top Panel
        JLabel header = new JLabel();
        header.setText("Ronald's Game of Life");
        header.setForeground(Color.white);
        header.setFont(new Font("MV Boli", Font.PLAIN, 27));
        topPanel.add(header);

        // Create Grid Panel
        centerPanel.setLayout(new GridLayout(50,50, 1, 1));
        for(int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid.length; j++) {
                JButton button = buttonGrid[i][j] = new JButton("");
                button.setBackground(Color.white);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {   // Changes between green and white
                        if(button.getBackground() == white){
                            button.setBackground(green);
                        }
                        else{
                            button.setBackground(white);
                        }
                    }
                });
                centerPanel.add(buttonGrid[i][j]);
                }
            }



        //Bottom Panel
        botPanel.add(clear);
        botPanel.add(start);
        botPanel.add(next);
        time.setInverted(true);
        botPanel.add(time);

        //Bottom Panel Button/Slider functionality
        //Clear Grid
        clear.addActionListener(e -> clearGrid());
        // Start
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(running == true){
                    running = false;
                    start.setText("Start");
                }
                else{
                    running = true;
                    processInBackground();
                    start.setText("Pause");
                }
            }
        });
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                step();
            }
        });



        frame.pack();

    }


}
