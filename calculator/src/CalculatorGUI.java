import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CalculatorGUI extends JFrame {

    private JFrame mainFrame;
    private JPanel controlPanel;

    public CalculatorGUI() {
        renderWindowComponents();
        createCalculatorButtons();
    }

    public void renderWindowComponents() {
        mainFrame = new JFrame("Calculator");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    public void createCalculatorButtons() {
        //TODO: Create the buttons
        /**
         +---------------------+
         | 7  |  8  |  9  | /  |
         |---------------------|
         | 4  |  5  |  6  | *  |
         |---------------------|
         | 1  |  2  |  3  | +  |
         |---------------------|
         | .  |  0  |  =  | -  |
         +---------------------+
         */
        // ^ what other buttons do we need in the initial copy?
    }
}
