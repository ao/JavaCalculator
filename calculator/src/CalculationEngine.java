import java.math.BigInteger;
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

    CalculationEngine() {
        //throw new UnsupportedOperationException("Not supported yet."); 
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
            hist.addToHistory(global,s);

        } catch (ScriptException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /**
     * Calculate the Fibonacci Sequence of a number
     * @param input
     * @return
     */
    public BigInteger calculateFibonacciNow(int input){
        BigInteger fibo = new BigInteger("-1");
        BigInteger num = new BigInteger("0");
        BigInteger num2 = new BigInteger("1");

        //System.out.println("Calculating fibo");

        try {
            if ((input < 0) || (input > 999)){
                fibo = BigInteger.valueOf(-1);
            } else if (input < 3){
                fibo = BigInteger.valueOf(1);
            } else if (input < 1000) {
                for (int loop = 2; loop < input; loop ++) {
                    fibo = num.add(num2);
                    num = num2;
                    num2 = fibo;
                }            
            }            
        } catch (NullPointerException e1) {
            e1.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }

        //System.out.println(fibo);
        return fibo;
    }    
}
