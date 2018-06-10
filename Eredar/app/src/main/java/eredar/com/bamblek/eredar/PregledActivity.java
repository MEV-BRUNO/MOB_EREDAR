package eredar.com.bamblek.eredar;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import eredar.com.bamblek.eredar.klase.Prijava;
import eredar.com.bamblek.eredar.klase.PrijavaAdapter;
import eredar.com.bamblek.eredar.klase.SystemBarTintManager;

public class PregledActivity extends AppCompatActivity {

    private URL url;
    private PrijavaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<Prijava> prijave = new ArrayList<Prijava>();
        adapter = new PrijavaAdapter(this, prijave);
        ListView listView = (ListView) findViewById(R.id.lvPrijave);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent x = new Intent(PregledActivity.this, DetaljiActivity.class);
                x.putExtra("id",l);
                startActivity(x);
            }
        });

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#007a50\">" +
                getResources().getString(R.string.title_activity_pregled) + "</font>"));
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

        try {

            url =  new URL("https://api.myjson.com/bins/11foii");
            new NetworkAsyncGet().execute(url).toString();
        } catch (Exception e) {
            String s = e.toString();
        }



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
            try{
                JSONArray ja = new JSONArray(ret);
                ArrayList<Prijava> prijave = Prijava.fromJson(ja);
                adapter.addAll(prijave);
            }catch(Exception e){

            }
        }

    }
}
