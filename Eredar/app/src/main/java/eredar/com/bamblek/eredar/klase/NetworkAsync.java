package eredar.com.bamblek.eredar.klase;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URL;

public class NetworkAsync extends AsyncTask<URL, Void, String> {
    protected String doInBackground(URL... urls) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://localhost:57456/AdminRest/GetAllPrijave");
            HttpResponse response = httpClient.execute(httpGet);
            return response.toString();
        }catch(Exception e){
            String s =e.toString();
        }
        return null;
    }

}
