package eredar.com.bamblek.eredar;

        import android.content.Intent;
        import android.content.SharedPreferences;
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
        import android.widget.Button;
        import android.widget.EditText;

        import eredar.com.bamblek.eredar.klase.SystemBarTintManager;

public class KorisnikActivity extends AppCompatActivity {

    private EditText mail;
    private EditText pass;
    private Button prijava;
    SharedPreferences sp;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korisnik);
        mail = (EditText) findViewById(R.id.mailEdit);
        pass = (EditText) findViewById(R.id.passEdit);


        prijava  = (Button) this.findViewById(R.id.prijavaBtn);
        prijava.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(KorisnikActivity.this, NavActivity.class);
                startActivity(i);
                sp = getSharedPreferences("eredar_pref", MODE_PRIVATE);
                ed = sp.edit();
                ed.putBoolean("initialized", true);
                ed.putString("mail", mail.getText().toString());
                ed.putString("pass", pass.getText().toString());
                ed.commit();
                finish();
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#007a50\">" +
                getResources().getString(R.string.title_activity_korisnik) + "</font>"));
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


}


