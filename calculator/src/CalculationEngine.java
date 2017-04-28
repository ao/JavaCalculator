import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;

/**
 * Calculation Engine
 */

public class CalculationEngine implements Calculate {

    private History hist;

    public CalculationEngine(History _hist) {
        hist = _hist;
    }

    /**
     * Use the ScriptEngineManager to power the Calculator
     */
    public void calculateNow(JTextField mainText){
        String global = mainText.getText();
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            String s = engine.eval(global).toString();
            mainText.setText(s);

            hist.addToHistory(global+"="+s);

        } catch (ScriptException e1) {
            e1.printStackTrace();
        }
    }
}
