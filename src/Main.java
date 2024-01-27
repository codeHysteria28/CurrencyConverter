import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {
    static JFrame frame = new JFrame();
    static JComboBox<String> jc1, jc2;
    static JLabel from,to, currency1, currency2;
    static JButton convert;
    static JTextField t1, t2;
    static Api api = new Api();
    static CurrencyRates cr;
    static Timer timer;

    public static void comboBox(){
        // get the values and save them to an array
        Double[] valuesArray = cr.getData().values().toArray(new Double[0]);

        // get the keys and save them to an array
        String[] keysArray = cr.getData().keySet().toArray(new String[0]);

        // create JPanel with GridBagLayout
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // create labels from combo boxes
        from = new JLabel("From:");
        to = new JLabel("To:");
        from.setFont(new Font("Default", Font.BOLD, 12));
        to.setFont(new Font("Default", Font.BOLD, 12));

        // default values when app loads
        currency1 = new JLabel(String.valueOf(valuesArray[0]));
        currency2 = new JLabel(String.valueOf(valuesArray[0]));

        // create a combo box 1 and 2
        jc1 = new JComboBox<>(keysArray);
        jc2 = new JComboBox<>(keysArray);

        // combo listeners lambda
        jc1.addActionListener(e -> {
            String selectedKey = (String) jc1.getSelectedItem();
            Double currencyValue = cr.getData().get(selectedKey);
            currency1.setText(String.valueOf(currencyValue));
        });

        jc2.addActionListener(e -> {
            // get selectedKey
            String selectedKey = (String) jc2.getSelectedItem();
            // get value from CurrencyRates Map according to selectedKey
            Double currencyValue = cr.getData().get(selectedKey);
            currency2.setText(String.valueOf(currencyValue));
        });

        // button to do the conversion and it's actionListener
        convert = new JButton("Convert");

        // create a time that triggers after 2ms
        timer = new Timer(2000, e -> {
            t1.setText("");
            t1.setBackground(Color.WHITE);
            timer.stop();
        });

        // convert currency
        convert.addActionListener(e -> {
            // formula for converting currency
            // a * b = c
            // A = money you have in the currency you wish to convert
            // B = exchange rate
            // C = money you have after applying the exchange rate

            String t1Val = null;
            String selectedKey = (String) jc2.getSelectedItem();
            Double currencyValue = cr.getData().get(selectedKey);
            Double conversionResult = null;

            // check if t1 is not empty
            if(t1.getText().trim().isEmpty()){
                t1.setText("Add value !");
                t1.setBackground(Color.RED);
                timer.start();
            }else{
                t1Val = t1.getText();
                double t1ValNum = Double.parseDouble(t1Val);
                conversionResult = t1ValNum * currencyValue;
                t2.setText(String.valueOf(conversionResult));
            }

            System.out.println(conversionResult);
        });

        // text fields to enter currency
        t1 = new JTextField("");
        t2 = new JTextField("");
        // prevent editing and focus the t2 field
        t2.setEditable(false);
        t2.setFocusable(false);

        // create panels for each combo box and its label + convert button
        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(currency1, BorderLayout.NORTH);
        p1.add(jc1, BorderLayout.CENTER);
        p1.add(t1, BorderLayout.SOUTH);

        JPanel p2 = new JPanel(new BorderLayout());
        p2.add(currency2, BorderLayout.NORTH);
        p2.add(jc2, BorderLayout.CENTER);
        p2.add(t2, BorderLayout.SOUTH);

        JPanel p3 = new JPanel();
        p3.add(convert);

        // add combo boxes, labels and JPanel to frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,15,0);
        p.add(from, gbc);
        gbc.gridy = 1;
        p.add(p1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        // space between p1 and p2 JPanels
        gbc.insets = new Insets(0,30,15,0);
        p.add(to, gbc);
        gbc.gridy = 1;
        p.add(p2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth =2;
        gbc.insets = new Insets(15,0,0,0);
        p.add(p3, gbc);

        frame.add(p);
    }

    public static void generateUI(){
        cr = api.getApi();
//        CurrencyTable ct = new CurrencyTable();
//
//        // adding table to the frame®
//        frame.add(ct.getScrollPane());

        // frame title
        frame.setTitle("Currency Converter");

        // call combobox
        comboBox();

        // frame size
        frame.setSize(500,500);

        // set frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args){
        generateUI();
    }
}
