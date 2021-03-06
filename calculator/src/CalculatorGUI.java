/**
 * CalculatorGUI class
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.math.BigInteger;

/**
 * Draw Calculator GUI
 */
public class CalculatorGUI extends JFrame {

    private History hist;
    private JFrame mainFrame;
    private JPanel controlPanel;
    private JPanel histPanel;
    private JTextField mainText;

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
    private JButton nsqrt;

    private JButton histclear;

    private DefaultListModel model = new DefaultListModel();
    private JList lshist = new JList( model );

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuItem exitMenuItem;
    private JMenuItem helpMenuItem;
    private JMenuItem aboutMenuItem;
    
    private int histIndex;

    /**
     * Constructor to control flow
     * @param _hist
     */
    public CalculatorGUI(History _hist) {
        super("Calculator");

        hist = _hist;
        _hist.setPointers(model, lshist);

        engine = new CalculationEngine(_hist);

        renderWindowComponents();
        createCalculatorButtons();
        addButtonsEventHandlers();
        addKeyboardListeners();

        hist.loadHistory();
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
     * Run Fibonacci
     */
    public void runfibo(){
        GetInput gi = new GetInput();
        int fibnr = gi.getNumber();

        BigInteger fibanswer = engine.calculateFibonacciNow(fibnr);
        
        String stranswer = fibanswer.toString();
        addToCalculationString(stranswer);        
    }

    /**
     * Render Window Components
     */
    public void renderWindowComponents() {
        mainFrame = new JFrame("Calculator");
        mainFrame.setSize(600,400);
        mainFrame.setLayout(new GridLayout(2, 0));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(0,4));
        mainFrame.getContentPane().add(controlPanel);

        histPanel = new JPanel();
        histPanel.setLayout(new GridLayout(0,1));
        mainFrame.getContentPane().add(histPanel);

        mainFrame.setLocationRelativeTo(null);
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
        nrand = new JButton("Rand");
        nsqrt = new JButton("Sqrt");
        histclear = new JButton("Clear History");

        mainText = new JTextField(100);
        Font myFontSize = mainText.getFont().deriveFont(Font.BOLD, 50f);
        mainText.setCaretPosition(0);
        mainText.setText("0");
        mainText.setFont(myFontSize);

        histPanel.add(lshist, BorderLayout.NORTH);

        mainFrame.getContentPane().add(mainText, BorderLayout.NORTH);
        mainFrame.getContentPane().add(histPanel, BorderLayout.CENTER);
        mainFrame.getContentPane().add(controlPanel,BorderLayout.SOUTH);
        mainFrame.getContentPane().add(histclear, BorderLayout.CENTER);


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
        controlPanel.add(nsqrt);

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
        histclear.setFocusable(false);

        menuBar = new JMenuBar();
        helpMenu = new JMenu("Help");
        fileMenu = new JMenu("File");

        exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);

        aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);

        helpMenuItem = new JMenuItem("Help");
        helpMenu.add(helpMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        mainFrame.setJMenuBar(menuBar);
    }

    /**
     * Add ActionListener events to all the buttons
     */
    public void addButtonsEventHandlers() {
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        helpMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GetInput gi = new GetInput();
                gi.showMessage("HELP", "The functions of the calculator should perform as a normal calculator. The Fib button returns the nth number in the Fibonacci sequence, where the user can input n", JOptionPane.OK_OPTION);
            }
        });

        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GetInput gi = new GetInput();
                gi.showMessage("About", "This program was developed by Andrew and Werner for our assignment in weeks 5 and 6", JOptionPane.OK_OPTION);
            }
        });
        
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

        nsqrt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String originalMainTextValue = mainText.getText();
                Double sqrtValue = Math.sqrt( Double.valueOf(mainText.getText()) );
                String s = String.format("%.3f", sqrtValue);
                System.out.println(s);
                
                mainText.setText(s);
                hist.addToHistory("Math.pow("+String.valueOf(originalMainTextValue)+",0.5)", s);
            }
        });

        mainFrame.setVisible(true);

        lshist.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    // Double-click detected
                    histIndex = list.locationToIndex(evt.getPoint());
                    loadFromHistoryItem(histIndex);
                } else if (evt.getClickCount() == 3) {
                    // Triple-click detected
                    histIndex = list.locationToIndex(evt.getPoint());
                    loadFromHistoryItem(histIndex);
                }
            }
        });

        histclear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hist.clearHistory();
            }
        });
        
        nfibonacci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runfibo();
            }
        });
    }

    /**
     * Load an item from the History list
     * @param index
     */
    private void loadFromHistoryItem(int index) {
        mainText.setText( hist.getEntry(index) );
        hist.changeHistoryFile(histIndex);
        
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
