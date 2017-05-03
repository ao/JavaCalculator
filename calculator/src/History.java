import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Store history of previous calculations
 */

public class History {

    private DefaultListModel<String> m;
    private boolean hasHistory = false;
    private JList<String> p;
    private List<String> data = new ArrayList<String>() {};
    private String historyFile = "history.dat";

    public void History() {}

    /**
     * Set the pointers to the shared components
     * @param _m
     * @param _p
     */
    public void setPointers(DefaultListModel _m, JList _p) {
        m = _m;
        p = _p;
    }

    /**
     * Add to the History List
     * @param strings
     */
    public void addToHistory(String... strings) {

        if (hasHistory==false) ((DefaultListModel)p.getModel()).clear();
        addHistoryBlankItem(false);

        String str = String.join(",", strings);

        data.add(str);

        ((DefaultListModel)p.getModel()).addElement( str.replace(",", "=") );

        try {
            writeToDataFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add our `blank` item to the history if need be
     * @param showBlankItem
     */
    public void addHistoryBlankItem(boolean showBlankItem) {
        if (showBlankItem) {
            ((DefaultListModel)p.getModel()).clear();
            hasHistory = false;
            ((DefaultListModel)p.getModel()).addElement( "History is blank" );
            // make sure that only a single section is in place before disabling
            p.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            // disable through a custom class
            p.setSelectionModel(new DisabledItemSelectionModel());
            // disable the list
            p.setEnabled(false);
        } else {
            hasHistory = true;
            p.setEnabled(true);
        }
    }

    /**
     * Load History from our Data file
     */
    public void loadHistory() {
        try {
            openDataFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String str: data) {
            ((DefaultListModel)p.getModel()).addElement( str.replace(",", "=") );
        }

        addHistoryBlankItem( (data.size()==0 ? true : false) );
    }

    /**
     * Clear History and write to file
     */
    public void clearHistory() {
        data.clear();
        try {
            writeToDataFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((DefaultListModel)p.getModel()).clear();
        addHistoryBlankItem(true);
    }

    /**
     * Get history item by index
     * @param index
     * @return
     */
    public String getEntry(int index) {
        if (data.size()>=index) {
            String dataItem = data.get(index);
            int iend = dataItem.indexOf(",");
            if (iend != -1) return dataItem.substring(0 , iend);
            else return dataItem;
        } else
            return "";
    }

    /**
     * Open our Data file
     * @throws IOException
     */
    public void openDataFile() throws IOException {
        try {
            data = Files.readAllLines(Paths.get(historyFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write the history to a file
     * @throws IOException
     */
    public void writeToDataFile() throws IOException {
        try {
            Files.write(Paths.get(historyFile), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Change History File (Currently placeholder)
     * @param numToChange
     */
    public void changeHistoryFile(int numToChange){
        
    }
}

