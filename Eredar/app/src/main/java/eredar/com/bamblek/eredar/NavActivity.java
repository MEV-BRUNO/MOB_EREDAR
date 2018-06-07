package eredar.com.bamblek.eredar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import eredar.com.bamblek.eredar.klase.ControlApp;
import eredar.com.bamblek.eredar.klase.SystemBarTintManager;

public class NavActivity extends AppCompatActivity
        implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences settings;
    Context context;
    String dialogType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#00f00f00"));
        //toolbar.setElevation(0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(0);
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{0xffd6e1ef, 0xFFffffff});
        gd.setCornerRadius(0f);
        getSupportActionBar().setBackgroundDrawable(gd);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);




        drawer.setDrawerListener(toggle);
        // transparetnost sijene kod menija
        drawer.setScrimColor(getResources().getColor(android.R.color.transparent));

        //drawer.setScrimColor(Color.parseColor("#aadddddd"));
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

        // def. app

        Button bottonPrijava = (Button) findViewById(R.id.idButton1);
        Button bottonPregled = (Button) findViewById(R.id.idButton2);
        Button bottonStatistika = (Button) findViewById(R.id.idButton3);
        Button bottonProjekt = (Button) findViewById(R.id.idButton4);


        bottonPrijava.setOnClickListener(this);
        bottonPregled.setOnClickListener(this);
        bottonStatistika.setOnClickListener(this);
        bottonProjekt.setOnClickListener(this);


       // TextView tw=(TextView)findViewById(R.id.textView2);
       // tw.setText(getScreenResolution(context));

        // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //  getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00f00000")));

        // boja
        SystemBarTintManager tintManager;
        setTranslucentStatus(true);
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.text);

        // provjera postavki
        // test language
/*        settings = getApplicationContext().getSharedPreferences("prijavljen", 0);
        if (!settings.contains("prijavljen")){
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("prijavljen", "ne");
            editor.putString("user", "");
            editor.putString("password", "");
            editor.commit();
        }
        user=settings.getString("user", null);
        pass=settings.getString("password", null);*/

        settings = getSharedPreferences("eredar_pref",0);
        NavigationView nav = (NavigationView) findViewById(R.id.nav_view);
        View headerView = nav.getHeaderView(0);
        TextView side_mail = (TextView) headerView.findViewById(R.id.side_mail);
        side_mail.setText(settings.getString("mail", "NULL"));


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        // test internet connection is avaliable
        if (!isNetworkAvailable(context)) {
            // code here
            noConnectionMessage();
            //Toast.makeText(IzbornikActivity.this, controlApp.selectMessageMain(lan,1) ,
            //        Toast.LENGTH_LONG).show();
            return;
        }
        switch (v.getId()) {

            case R.id.idButton1://slanje prijave
                dialogType = "update";
                context.startActivity(new Intent().setClass(context,
                        PrijavaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case R.id.idButton2://pregled
                context.startActivity(new Intent().setClass(context,
                        PregledActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case R.id.idButton3://statistika
                    context.startActivity(new Intent().setClass(context,
                            StatistikaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case R.id.idButton4://o projektu
                context.startActivity(new Intent().setClass(context,
                        OprojektuActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;

            /*
            case R.id.idButton2://pregled
                context.startActivity(new Intent().setClass(context,
                        GeolokacijaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
                */

        }
    }



    // test connection
    public boolean isNetworkAvailable(final Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    public void noConnectionMessage() {

        String messTitle = "Nema veze s internetom";
        String mess = "Molim vas da uključite wifi ili mobilne podatke.";
        String buttonOK = "Zatvori";
        // dialog
        final Dialog dialog = new Dialog(NavActivity.this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.popup_message);
        // Set dialog title
        TextView textView1 = (TextView) dialog.findViewById(R.id.labTitle);
        textView1.setText(messTitle);
        TextView textView2 = (TextView) dialog.findViewById(R.id.labMessage);
        textView2.setText(mess);
        Button b1 = (Button) dialog.findViewById(R.id.button1); // Done
        b1.setText(buttonOK);
        b1.setOnClickListener(new View.OnClickListener() { // OK
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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

    private static String getScreenResolution(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        float density = context.getResources().getDisplayMetrics().density;
        String dens="";
        if (density >= 4.0) {
            dens= "xxxhdpi";
        }
        if (density >= 3.0) {
            dens= "xxhdpi";
        }
        if (density >= 2.0) {
            dens= "xhdpi";
        }
        if (density >= 1.5) {
            dens= "hdpi";
        }
        if (density >= 1.0) {
            dens= "mdpi";
        }
        return "{" + width + "," + height + "\n density: }"+dens;
    }



}
