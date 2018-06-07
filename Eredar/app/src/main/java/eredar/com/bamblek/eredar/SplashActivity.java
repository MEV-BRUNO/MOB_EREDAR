package eredar.com.bamblek.eredar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    Context context;
    SharedPreferences sp;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_splash);
        layout.setBackground(getResources().getDrawable(R.drawable.background_mdss01));

        sp = getSharedPreferences("eredar_pref", MODE_PRIVATE);
        if(!sp.contains("initialized")){
            i = new Intent(context, KorisnikActivity.class);
        }else{
            i = new Intent(SplashActivity.this, NavActivity.class);

        }

        new Thread(new Runnable() {
            public void run() {
                windSetup();
            }
        }).start();
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */


            @Override
            public void run() {
                startActivity(i);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


    public void windSetup() {
        // gornja boja menija
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
     //   window.setStatusBarColor((getResources().getColor(R.color.windColor)));
     //   window.setStatusBarColor((getResources().getColor(R.color.windColor)));

    }

}
