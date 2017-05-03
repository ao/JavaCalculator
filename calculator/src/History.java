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

    public void History() {

    }

    public void setPointers(DefaultListModel _m, JList _p) {
        m = _m;
        p = _p;
    }

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

    public void addHistoryBlankItem(boolean showBlankItem) {
        if (showBlankItem) {
            ((DefaultListModel)p.getModel()).clear();
            hasHistory = false;
            ((DefaultListModel)p.getModel()).addElement( "History is blank" );
            p.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            p.setSelectionModel(new DisabledItemSelectionModel());
            p.setEnabled(false);
        } else {
            hasHistory = true;
            p.setEnabled(true);
        }
    }

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

    public String getEntry(int index) {
        if (data.size()>=index) {
            String dataItem = data.get(index);
            int iend = dataItem.indexOf(",");
            if (iend != -1) return dataItem.substring(0 , iend);
            else return dataItem;
        } else
            return "";
    }

    public void openDataFile() throws IOException {
        try {
            data = Files.readAllLines(Paths.get(historyFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToDataFile() throws IOException {
        try {
            Files.write(Paths.get(historyFile), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void changeHistoryFile(int numToChange){
        
    }
}

