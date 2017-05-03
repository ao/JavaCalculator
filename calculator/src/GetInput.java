import java.awt.GridLayout;
import javax.swing.*;

public class GetInput {

    /**
     * Show message
     * @param title
     * @param message
     * @param buttonType
     */
    public void showMessage(String title, String message, int buttonType) {
        JOptionPane myOptionPane = new JOptionPane();
        myOptionPane.setMessage(message);
        myOptionPane.setMessageType(buttonType);
        JDialog d = myOptionPane.createDialog(null, title);
        d.setVisible(true);        
    }

    /**
     * Get number
     * @return int
     */
    public int getNumber() {
        int retVal = 0;
        JTextField field1 = new JTextField("1");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Please enter a value"));
        panel.add(field1);
        int result = JOptionPane.showConfirmDialog(null, panel, "Test",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                retVal = Integer.parseInt(field1.getText());
            } catch (ArithmeticException e1) {
                showMessage("Invalid number","You can not divide by zero", JOptionPane.PLAIN_MESSAGE);
                e1.printStackTrace();
            } catch (Exception e2) {
                showMessage("Invalid number","You have to input a valid number", JOptionPane.PLAIN_MESSAGE);
                e2.printStackTrace();
            }
            
        } else
            System.out.println("Cancelled");

        return retVal;
    }


}    
