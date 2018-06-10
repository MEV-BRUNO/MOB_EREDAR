package eredar.com.bamblek.eredar;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import eredar.com.bamblek.eredar.klase.Prijava;
import eredar.com.bamblek.eredar.klase.SystemBarTintManager;

public class DetaljiActivity extends AppCompatActivity {

    TextView datumView;
    TextView naslovView;
    TextView opisView;
    TextView napomenaView;
    TextView odgovorenaView;
    private LocationManager locationManager;
    private String json;
    private int id;
    private Prijava p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        id = (int) intent.getLongExtra("id", 0);
        try {
            new NetworkAsyncGet().execute(new URL("https://api.myjson.com/bins/11foii"));
        }catch (Exception e){

        }

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#007a50\">" +
                "Detalji" + "</font>"));
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{0xffc6ebe1, 0xFFffffff});
        gd.setCornerRadius(0f);
        getSupportActionBar().setBackgroundDrawable(gd);

        // derfiniranje boje trake zaslona
        SystemBarTintManager tintManager;
        setTranslucentStatus(true);
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.text);

        // navigacija
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp); // ikona za povratak
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setTranslucentStatus(boolean on) {
        // TODO Auto-generated method stub

        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class NetworkAsyncGet extends android.os.AsyncTask<java.net.URL, Void, String> {
        private String ret = null;
        protected String doInBackground(URL... urls) {

            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(urls[0].toString());
                HttpResponse response = httpClient.execute(httpGet);
                ret = org.apache.http.util.EntityUtils.toString(response.getEntity(), "UTF-8");
            } catch (Exception e) {
                String s = e.toString();
            }
            return ret;
        }

        @Override
        protected void onPostExecute(String result) {
            naslovView = (TextView) findViewById(R.id.naslovView);
            datumView = (TextView) findViewById(R.id.datumView);
            opisView = (TextView) findViewById(R.id.opisView);
            napomenaView = (TextView) findViewById(R.id.napomenaView);
            odgovorenaView = (TextView) findViewById(R.id.odgovorenoView);

            try{
                JSONArray ja = new JSONArray(ret);
                ArrayList<Prijava> prijave = Prijava.fromJson(ja);
                for(int i=0;i<prijave.size();i++){
                    if (prijave.get(i).getId()==id){
                        p=prijave.get(i);
                        break;
                    }
                }
                naslovView.setText(p.getNaslov());
                datumView.setText(new SimpleDateFormat("dd.MM.yyyy").format(p.getZaprimljena()));
                opisView.setText(p.getOpis());
                napomenaView.setText(p.getNapomena());
                odgovorenaView.setText("Odgovoreno: "+new SimpleDateFormat("dd.MM.yyyy").format(p.getZavrsena()));
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

}
