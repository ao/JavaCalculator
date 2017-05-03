/**
 * Application: Calculator
 * Team: WA (Werner & Andrew)
 * Module: OOP-Java / Week5
 * URL: https://elearning.uol.ohecampus.com/webapps/discussionboard/do/conference?action=list_forums&course_id=_1629553_1&nav=group_forum&group_id=_456471_1
 * Git: https://bitbucket.org/_ao/module2_group_code/commits/all
 */

public class Calculator {
    public CalculatorGUI cgui;
    public History hist;

    public Calculator() {
        hist = new History();
        // send our application's history state to the GUI creator
        cgui = new CalculatorGUI(hist);
    }

    /**
     * Main()
     * @param args
     */
    public static void main(String[] args) {
        new Calculator();
    }
}
