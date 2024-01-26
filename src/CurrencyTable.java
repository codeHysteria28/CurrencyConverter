import javax.swing.*;
import java.util.Map;

public class CurrencyTable {
    JTable j;
    JScrollPane sp;

    Api api = new Api();
    CurrencyRates apiData = api.getApi();

    CurrencyTable(){
        Map<String, Double> data = apiData.getData();
        String[] columnNames = { "Currency", "Value" };

        // convert Map to 2D array, so it can be accepted as parameter for JTable
        Object[][] arrayData = new Object[data.size()][2];
        int i = 0;

        for(Map.Entry<String,Double> entry : data.entrySet()){
            arrayData[i][0] = entry.getKey();
            arrayData[i][1] = entry.getValue();
            i++;
        }

        // initialize the JTable
        j = new JTable(arrayData, columnNames) {
            // prevent cells and rows to be editable after click
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        j.setBounds(30, 40, 200,300);

        // add JTable to JScrollPane
        sp = new JScrollPane(j);
    }

   public JScrollPane getScrollPane(){
        return sp;
   }
}
