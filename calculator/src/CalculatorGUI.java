/**
 * CalculatorGUI class
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Draw Calculator GUI
 */
public class CalculatorGUI extends JFrame {

    private History hist;
    private JFrame mainFrame;
    private JPanel controlPanel;
    private JTextField mainText;

    private Container mainCalcContainer;

    private CalculationEngine engine;

    private JButton n1;
    private JButton n2;
    private JButton n3;
    private JButton n4;
    private JButton n5;
    private JButton n6;
    private JButton n7;
    private JButton n8;
    private JButton n9;
    private JButton ndiv;
    private JButton nmult;
    private JButton nplus;
    private JButton ndot;
    private JButton n0;
    private JButton nequal;
    private JButton nminus;
    private JButton nclear;
    private JButton nfibonacci;
    private JButton nrand;

    private DefaultListModel model = new DefaultListModel();
    private JList lshist = new JList( model );

    /**
     * Constructor to control flow
     */
    public CalculatorGUI(History _hist) {
        hist = _hist;
        _hist.lsPointer(model, lshist);

        engine = new CalculationEngine(_hist);

        renderWindowComponents();
        createCalculatorButtons();
        addButtonsEventHandlers();
        addKeyboardListeners();
    }


    /**
     * Add to the Calculator display
     * @param whatToAdd
     */
    private void addToCalculationString(String whatToAdd){
        String global = mainText.getText();
        global = global.concat(whatToAdd);
        mainText.setText(global);        
    }

    /**
     * Render Window Components
     */
    public void renderWindowComponents() {
        mainFrame = new JFrame("Calculator");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(2, 1));

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

    /**
     * Create all the Buttons for the Calculator
     */
    public void createCalculatorButtons() {
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

        mainCalcContainer = mainFrame.getContentPane();

        mainText = new JTextField(100);
        Font myFontSize = mainText.getFont().deriveFont(Font.BOLD, 50f);
        mainText.setFont(myFontSize);
        mainCalcContainer.add(mainText, BorderLayout.NORTH);

        mainCalcContainer.add(lshist, BorderLayout.EAST);

        // Initialise each jButton
        n1 = new JButton("1");
        n2 = new JButton("2");
        n3 = new JButton("3");
        n4 = new JButton("4");
        n5 = new JButton("5");
        n6 = new JButton("6");
        n7 = new JButton("7");
        n8 = new JButton("8");
        n9 = new JButton("9");
        ndiv = new JButton("/");
        nmult = new JButton("*");
        nplus = new JButton("+");
        ndot = new JButton(".");
        n0 = new JButton("0");
        nequal = new JButton("=");
        nminus = new JButton("-");
        nclear = new JButton("C");
        nfibonacci = new JButton("FIB");
        nrand = new JButton("Rand()");



        // Add all buttons to the jPanel
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
        controlPanel.add(nfibonacci);
        controlPanel.add(nrand);

        //Remove focus so we don't break the keyboard listener
        n1.setFocusable(false);
        n2.setFocusable(false);
        n3.setFocusable(false);
        n4.setFocusable(false);
        n5.setFocusable(false);
        n6.setFocusable(false);
        n7.setFocusable(false);
        n8.setFocusable(false);
        n9.setFocusable(false);
        ndiv.setFocusable(false);
        nmult.setFocusable(false);
        nplus.setFocusable(false);
        ndot.setFocusable(false);
        n0.setFocusable(false);
        nequal.setFocusable(false);
        nminus.setFocusable(false);
        nclear.setFocusable(false);
        nfibonacci.setFocusable(false);
        nrand.setFocusable(false);

    }

    /**
     * Add ActionListener events to all the buttons
     */
    public void addButtonsEventHandlers() {
        n1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(n1.getText());
            }
        });
   
        n2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(n2.getText());
            }
        });

   
        n3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(n3.getText());
            }
        });
   
        n4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(n4.getText());
            }
        });
   
        n5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(n5.getText());
            }
        });
   
        n6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(n6.getText());
            }
        });
   
        n7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(n7.getText());
            }
        });
   
        n8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(n8.getText());
            }
        });
   
        n9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(n9.getText());
            }
        });

        n0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(n0.getText());
            }
        });
                
        ndiv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(ndiv.getText());
            }
        });
   
        nmult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(nmult.getText());
            }
        });
   
        nminus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(nminus.getText());
            }
        });
        
        ndot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(ndot.getText());
            }
        });
        
        nplus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCalculationString(nplus.getText());
            }
        });      

        nclear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainText.setText("");
            }
        }); 
        
        nequal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                engine.calculateNow(mainText);

            }
        });


        nrand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int max = 99;
                int min = 1;
                Random random = new Random();
                int randomNumber = random.nextInt(max - min + 1) + min;
                addToCalculationString( String.valueOf(randomNumber) );
            }
        });

        mainCalcContainer.add(controlPanel,BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    /**
     * add Keyboard Listeners to all the buttons (and a clear)
     */
    private void addKeyboardListeners() {
        mainFrame.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    System.out.println("Hi");
                }

                switch(e.getKeyCode()) {
                    case KeyEvent.VK_0: case KeyEvent.VK_NUMPAD0:
                        n0.doClick();
                        break;
                    case KeyEvent.VK_1: case KeyEvent.VK_NUMPAD1:
                        n1.doClick();
                        break;
                    case KeyEvent.VK_2: case KeyEvent.VK_NUMPAD2:
                        n2.doClick();
                        break;
                    case KeyEvent.VK_3: case KeyEvent.VK_NUMPAD3:
                        n3.doClick();
                        break;
                    case KeyEvent.VK_4: case KeyEvent.VK_NUMPAD4:
                        n4.doClick();
                        break;
                    case KeyEvent.VK_5: case KeyEvent.VK_NUMPAD5:
                        n5.doClick();
                        break;
                    case KeyEvent.VK_6: case KeyEvent.VK_NUMPAD6:
                        n6.doClick();
                        break;
                    case KeyEvent.VK_7: case KeyEvent.VK_NUMPAD7:
                        n7.doClick();
                        break;
                    case KeyEvent.VK_8: case KeyEvent.VK_NUMPAD8:
                        n8.doClick();
                        break;
                    case KeyEvent.VK_9: case KeyEvent.VK_NUMPAD9:
                        n9.doClick();
                        break;

                    case KeyEvent.VK_PLUS: case KeyEvent.VK_ADD:
                        nplus.doClick();
                        break;
                    case KeyEvent.VK_MINUS: case KeyEvent.VK_SUBTRACT:
                        nminus.doClick();
                        break;
                    case KeyEvent.VK_SLASH: // ?
                        ndiv.doClick();
                        break;
                    case KeyEvent.VK_ASTERISK: case KeyEvent.VK_MULTIPLY:
                        nmult.doClick();
                        break;
                    case KeyEvent.VK_EQUALS: case KeyEvent.VK_ENTER:
                        nequal.doClick();
                        break;
                    case KeyEvent.VK_STOP: // ?
                        ndot.doClick();
                        break;

                    case KeyEvent.VK_ESCAPE:
                        nclear.doClick();
                        break;
                }

            }

            /**
             * We keep these overrides here (for now) to make the KeyListener above happy
             * @param e
             */
            @Override
            public void keyTyped(KeyEvent e) {
            //    throw new UnsupportedOperationException("Not supported yet.");
            }
            @Override
            public void keyReleased(KeyEvent e) {
            //      throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }
}
