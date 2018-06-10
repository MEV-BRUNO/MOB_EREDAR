package eredar.com.bamblek.eredar.klase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import eredar.com.bamblek.eredar.R;

public class PrijavaAdapter extends ArrayAdapter<Prijava> {
    public PrijavaAdapter(Context context, ArrayList<Prijava> prijave) {
        super(context, 0, prijave);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Prijava prijava = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pregled_adapter, parent, false);
        }
        // Lookup view for data population
        TextView tvNaziv = (TextView) convertView.findViewById(R.id.nazivView);
        TextView tvLokacija = (TextView) convertView.findViewById(R.id.lokacijaView);
        TextView tvDatum = (TextView) convertView.findViewById(R.id.datumView);
        // Populate the data into the template view using the data object
            tvNaziv.setText(prijava.getNaslov());
            tvLokacija.setText(prijava.getAdresa());
            tvDatum.setText(new SimpleDateFormat("dd.MM.yyyy").format(prijava.getZaprimljena()));

        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public boolean hasStableIds () { return true; }

    @Override
    public long getItemId (int position) { return getItem(position).getId(); }
}