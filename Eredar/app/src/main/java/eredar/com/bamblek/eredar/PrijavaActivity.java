package eredar.com.bamblek.eredar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.pm.PackageManager;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.Console;
import java.net.URL;
import java.util.ArrayList;

import eredar.com.bamblek.eredar.klase.GPSTracker;
import eredar.com.bamblek.eredar.klase.NetworkAsync;
import eredar.com.bamblek.eredar.klase.SystemBarTintManager;


public class PrijavaActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback,
        View.OnClickListener {

    Context context;
    private static final int CAMERA_REQUEST = 1888;
    private static final int TWO_MINUTES = 1000 * 60 * 2; // vrijeme testiranja geo lokacije mobitela
    Location currentBestLocation;
    private static final int ACCESS_FINE_LOCATION = 0;
    private View mLayout;


    private URL url;
    private Button photoButton;
    private ImageView imageView;
    private Spinner spinnerView;
    private boolean salji = false;
    Bitmap photo;
    // GPSTracker class
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijava);
        mLayout = findViewById(R.id.linija);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);;

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#007a50\">" +
                getResources().getString(R.string.title_activity_prijava) + "</font>"));
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


        setSpinner();

        // get image click event
        this.imageView = (ImageView)this.findViewById(R.id.imageView1);
        photoButton = (Button) this.findViewById(R.id.prijavaBtn);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(salji){
                    try{
                        url =  new URL("http://localhost:57456/AdminRest/GetAllPrijave");
                        String s = new NetworkAsync().execute(url).toString();
                        String u = s;
                    }catch (Exception e){

                    }


                    Intent i = new Intent(PrijavaActivity.this , NavActivity.class);
                    startActivity(i);


                }else{
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }


        /*
                gps = new GPSTracker(PrijavaActivity.this);

                // Check if GPS enabled
                if(gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                } else {
                    // Can't get location.
                    // GPS or network is not enabled.
                    // Ask user to enable GPS/network in settings.
                    gps.showSettingsAlert();
                }
                */
            }
        });
        try {
            // defined turent GPS location
            // Acquire a reference to the system Location Manager
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            // Define a listener that responds to location updates
            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    // Called when a new location is found by the network location provider.
                    makeUseOfNewLocation(location);
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };
            // Register the listener with the Location Manager to receive location updates
            String locationProvider = LocationManager.NETWORK_PROVIDER;
            // Or use LocationManager.GPS_PROVIDER
        //    Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);

        //    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }catch(Exception err){

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

    public void setSpinner() {

        spinnerView =(Spinner)this.findViewById(R.id.spinner);
        /* - punimo sa weba
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
                */
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.vrste_arrays));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerView.setAdapter(dataAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        if (requestCode == ACCESS_FINE_LOCATION) {
            // Request for camera permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.
                Snackbar.make(mLayout, "Camera permission was granted. Starting preview.",
                        Snackbar.LENGTH_SHORT)
                        .show();

            } else {
                // Permission request was denied.
                Snackbar.make(mLayout, "Camera permission request was denied.",
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        }
        // END_INCLUDE(onRequestPermissionsResult)
    }


    void makeUseOfNewLocation(Location location) {
        if ( isBetterLocation(location, currentBestLocation) ) {
            currentBestLocation = location;
        }
    }


    /** Determines whether one Location reading is better than the current Location fix
     * @param location  The new Location that you want to evaluate
     * @param currentBestLocation  The current Location fix, to which you want to compare the new one
     */
    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    /** Checks whether two providers are the same */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }


    /*
    Get image from phone device
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            photoButton.setText("Prijavi");
            salji = true;
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // refresh the instructions image

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageView.setImageBitmap(photo);
        } else {
            imageView.setImageBitmap(photo);
        }
    }

    @Override
    public void onClick(View v) {
        // test internet connection is avaliable
 /*       if (!isNetworkAvailable(context)) {
            // code here
            noConnectionMessage();
            //Toast.makeText(IzbornikActivity.this, controlApp.selectMessageMain(lan,1) ,
            //        Toast.LENGTH_LONG).show();
            return;
        }
        */
        switch (v.getId()) {
            /*
            case R.id.relKontakt:
                context.startActivity(new Intent().setClass(context,
                        KontaktActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case R.id.relStatistika:
                context.startActivity(new Intent().setClass(context,
                        GeolokacijaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case R.id.relPregled:
                context.startActivity(new Intent().setClass(context,
                        PregledActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                break;
            case R.id.relPrijava:
                context.startActivity(new Intent().setClass(context,
                        PrijavaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
*/
        }
    }



}
