import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Store history of previous calculations
 */

public class History {

    private Object store;
    private DefaultListModel<String> m;
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

        String str = String.join(",", strings);

        data.add(str);

        ((DefaultListModel)p.getModel()).addElement( str.replace(",", "=") );

        try {
            writeToDataFile();
        } catch (Exception e) {
            e.printStackTrace();
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
    }

    public void clearHistory() {
        data.clear();
        try {
            writeToDataFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((DefaultListModel)p.getModel()).clear();
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
}
