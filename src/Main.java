import javax.swing.*;

public class Main {
    public static JFrame frame = new JFrame();

    public static void main(String[] args){
        Api api = new Api();
        CurrencyRates storeApiData = api.getApi();

        System.out.println(storeApiData.getData().get("CZK"));
    }
}
