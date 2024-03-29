import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
import com.google.gson.Gson;

public class Api {
    private static String apiKey = "fca_live_Wq7feOnimL6iWCr1yfNCErtrTQLdYHRZaWcbqVgn";
    private CurrencyRates cr = null;

    public CurrencyRates getApi(){
        if(cr != null) return cr;

        StringBuilder apiContent = new StringBuilder();

        try{
            String apiUrl = "https://api.freecurrencyapi.com/v1/latest?apikey=" + apiKey;
            URI uri = new URI(apiUrl);
            URL url = uri.toURL();
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            con.disconnect();

            apiContent.append(content);
        }catch (URISyntaxException | IOException e){
            e.printStackTrace();
        }

        Gson gson = new Gson();
        cr = gson.fromJson(apiContent.toString(), CurrencyRates.class);

        return cr;
    }
}
