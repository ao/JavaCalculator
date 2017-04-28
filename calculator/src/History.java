import javax.swing.*;

/**
 * Store history of previous calculations
 */

public class History {

    private Object store;
    private DefaultListModel<String> m;
    private JList<String> p;

    public void History() {

    }

    public void lsPointer(DefaultListModel _m, JList _p) {
        m = _m;
        p = _p;
    }

    public void addToHistory(String str) {
        ((DefaultListModel)p.getModel()).addElement(str);
    }

}
