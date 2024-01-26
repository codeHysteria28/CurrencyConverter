import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static JFrame frame = new JFrame();
    static JComboBox<String> jc1, jc2;
    static JLabel from,to, currency1, currency2;

    public static void comboBox(){
        // fetch the data from api
        Api api = new Api();
        CurrencyRates cr = api.getApi();

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

        // combo listeners
        jc1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedKey = (String) jc1.getSelectedItem();
                Double currencyValue = cr.getData().get(selectedKey);
                currency1.setText(String.valueOf(currencyValue));
            }
        });

        jc2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get selectedKey
                String selectedKey = (String) jc1.getSelectedItem();
                // get value from CurrencyRates Map according to selectedKey
                Double currencyValue = cr.getData().get(selectedKey);
                currency2.setText(String.valueOf(currencyValue));
            }
        });

        // create panels for each combo box and its label
        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(currency1, BorderLayout.NORTH);
        p1.add(jc1, BorderLayout.SOUTH);

        JPanel p2 = new JPanel(new BorderLayout());
        p2.add(currency2, BorderLayout.NORTH);
        p2.add(jc2, BorderLayout.SOUTH);

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

        frame.add(p);
    }

    public static void generateUI(){
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
