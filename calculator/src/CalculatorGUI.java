import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorGUI extends JFrame {

    private JFrame mainFrame;
    private JPanel controlPanel;
    private JTextField mainText;
    
    public CalculatorGUI() {
        renderWindowComponents();
        createCalculatorButtons();
    }

    private void addToCalculationString(String whatToAdd){
        String global = mainText.getText();
        global = global.concat(whatToAdd);
        mainText.setText(global);        
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
        //controlPanel.setLayout(new FlowLayout());
        controlPanel.setLayout(new GridLayout(0,4));
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
        // ^ we can add the fibonacci etc. later
        Container mainCalcContainer = mainFrame.getContentPane();
        mainText = new JTextField(100);
        Font myFontSize = mainText.getFont().deriveFont(Font.BOLD,50f);
        mainText.setFont(myFontSize);
        mainCalcContainer.add(mainText,BorderLayout.NORTH);

        //Define each jButton
        final JButton  n1 =  new JButton("1");
        final JButton  n2 =  new JButton("2");
        final JButton  n3 =  new JButton("3");
        final JButton  n4 =  new JButton("4");
        final JButton  n5 =  new JButton("5");
        final JButton  n6 =  new JButton("6");
        final JButton  n7 =  new JButton("7");
        final JButton  n8 =  new JButton("8");
        final JButton  n9 =  new JButton("9");
        final JButton  ndiv =  new JButton("/");
        final JButton  nmult =  new JButton("*");
        final JButton  nplus =  new JButton("+");
        final JButton  ndot =  new JButton(".");
        final JButton  n0 =  new JButton("0");
        final JButton  nequal =  new JButton("=");
        final JButton  nminus =  new JButton("-");
        final JButton  nclear =  new JButton("C");
        
        //Add all buttons to the jPanel
        controlPanel.add(n7);
        controlPanel.add(n8);
        controlPanel.add(n9);
        controlPanel.add(ndiv);
        controlPanel.add(n4);
        controlPanel.add(n5);
        controlPanel.add(n6);
        controlPanel.add(nmult);
        controlPanel.add(n1);
        controlPanel.add(n2);
        controlPanel.add(n3);
        controlPanel.add(nplus);
        controlPanel.add(ndot);
        controlPanel.add(n0);
        controlPanel.add(nequal);
        controlPanel.add(nminus);
        controlPanel.add(nclear);

        n1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(n1.getText());
            }
        });
   
        n2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(n2.getText());
            }
        });

   
        n3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(n3.getText());
            }
        });
   
        n4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(n4.getText());
            }
        });
   
        n5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(n5.getText());
            }
        });
   
        n6.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(n6.getText());
            }
        });
   
        n7.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(n7.getText());
            }
        });
   
        n8.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(n8.getText());
            }
        });
   
        n9.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(n9.getText());
            }
        });

        n0.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(n0.getText());
            }
        });
                
        ndiv.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(ndiv.getText());
            }
        });
   
        nmult.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(nmult.getText());
            }
        });
   
        nminus.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(nminus.getText());
            }
        });
        
        ndot.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(ndot.getText());
            }
        });
        
        nplus.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                addToCalculationString(nplus.getText());
            }
        });      

        nclear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                mainText.setText("");
            }
        }); 
        
        nequal.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String global = mainText.getText();
                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                try {
                        String s = engine.eval(global).toString();
                        mainText.setText(s);
                    } catch (ScriptException e1) {
                        e1.printStackTrace();
                    }
            }
        });        
        
        mainCalcContainer.add(controlPanel,BorderLayout.CENTER);
        mainFrame.setVisible(true);  
    }
}
