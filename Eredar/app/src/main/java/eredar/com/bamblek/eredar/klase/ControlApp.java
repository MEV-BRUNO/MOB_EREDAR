package eredar.com.bamblek.eredar.klase;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by brunotrstenjak on 05.12.2017..
 */

public class ControlApp
{
    public ControlApp() {

    }

    public boolean isNetworkAvailable(final Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    public String getMessage(int no) {
        String value = "";
        switch (no) {
            case 0:
                value = "http://mdss.bamblek.com/mdss_hr.php";
                break;
            case 1:
                value = "Aplikacija nema pristup internetu!";
                break;
            case 2:
                value = "Potvrdi";
                break;
            case 3:
                value = "Odustani";
                break;
        }
        return value;
    }

}
