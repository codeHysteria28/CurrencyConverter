import javax.swing.*;

public class Main {
    public static JFrame frame = new JFrame();

    Main(){
        frame.setTitle("Currency Converter");
        CurrencyTable ct = new CurrencyTable();

        // adding table to the frame
        frame.add(ct.getScrollPane());

        // frame size
        frame.setSize(500,500);

        // set frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new Main();
    }
}
