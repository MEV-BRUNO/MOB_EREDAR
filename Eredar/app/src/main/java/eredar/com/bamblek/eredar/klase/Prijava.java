package eredar.com.bamblek.eredar.klase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;


public class Prijava {
    private int id;
    private String adresa;
    private int id_grad;
    private int id_korisnik;
    private int id_status_prijava;
    private int id_vrsta_prijava;
    private int id_zaposlenika;
    private double latitude;
    private double longitude;
    private String slika;
    private int slika_sirina;
    private int slika_visina;
    private String napomena;
    private String naslov;
    private String opis;
    private Date proslijedena;
    private int vidljivost;
    private String vrsta_naziv;
    private Date zaprimljena;
    private Date zavrsena;
    private String nazivGrada;

    public Prijava(){}

    public Prijava( String adresa, int id_grad, int id_korisnik, int id_status_prijava, int id_vrsta_prijava, int id_zaposlenika, double latitude, double longitude, String slika, int slika_sirina, int slika_visina, String napomena, String naslov, String opis, Date proslijedena, int vidljivost, String vrsta_naziv, Date zaprimljena, Date zavrsena, String nazivGrada) {
        this.id = id;
        this.adresa = adresa;
        this.id_grad = id_grad;
        this.id_korisnik = id_korisnik;
        this.id_status_prijava = id_status_prijava;
        this.id_vrsta_prijava = id_vrsta_prijava;
        this.id_zaposlenika = id_zaposlenika;
        this.latitude = latitude;
        this.longitude = longitude;
        this.slika = slika;
        this.slika_sirina = slika_sirina;
        this.slika_visina = slika_visina;
        this.napomena = napomena;
        this.naslov = naslov;
        this.opis = opis;
        this.proslijedena = proslijedena;
        this.vidljivost = vidljivost;
        this.vrsta_naziv = vrsta_naziv;
        this.zaprimljena = zaprimljena;
        this.zavrsena = zavrsena;
        this.nazivGrada = nazivGrada;
    }

    public Prijava(JSONObject object){
        try {
            this.id = Integer.parseInt(object.getString("id"));
            this.naslov = object.getString("naslov");
            this.adresa = object.getString("adresa");
            this.opis = object.getString("opis");
            this.napomena = object.getString("napomena");
            this.zaprimljena = new Date(Long.parseLong(object.getString("zaprimljena").replace("/Date(","").replace(")/","")));
            this.zavrsena = new Date(Long.parseLong(object.getString("zavrsena").replace("/Date(","").replace(")/","")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Prijava> fromJson(JSONArray jsonObjects) {
        ArrayList<Prijava> prijave = new ArrayList<Prijava>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                prijave.add(new Prijava(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return prijave;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getId_grad() {
        return id_grad;
    }

    public void setId_grad(int id_grad) {
        this.id_grad = id_grad;
    }

    public int getId_korisnik() {
        return id_korisnik;
    }

    public void setId_korisnik(int id_korisnik) {
        this.id_korisnik = id_korisnik;
    }

    public int getId_status_prijava() {
        return id_status_prijava;
    }

    public void setId_status_prijava(int id_status_prijava) {
        this.id_status_prijava = id_status_prijava;
    }

    public int getId_vrsta_prijava() {
        return id_vrsta_prijava;
    }

    public void setId_vrsta_prijava(int id_vrsta_prijava) {
        this.id_vrsta_prijava = id_vrsta_prijava;
    }

    public int getId_zaposlenika() {
        return id_zaposlenika;
    }

    public void setId_zaposlenika(int id_zaposlenika) {
        this.id_zaposlenika = id_zaposlenika;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public int getSlika_sirina() {
        return slika_sirina;
    }

    public void setSlika_sirina(int slika_sirina) {
        this.slika_sirina = slika_sirina;
    }

    public int getSlika_visina() {
        return slika_visina;
    }

    public void setSlika_visina(int slika_visina) {
        this.slika_visina = slika_visina;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getProslijedena() {
        return proslijedena;
    }

    public void setProslijedena(Date proslijedena) {
        this.proslijedena = proslijedena;
    }

    public int getVidljivost() {
        return vidljivost;
    }

    public void setVidljivost(int vidljivost) {
        this.vidljivost = vidljivost;
    }

    public String getVrsta_naziv() {
        return vrsta_naziv;
    }

    public void setVrsta_naziv(String vrsta_naziv) {
        this.vrsta_naziv = vrsta_naziv;
    }

    public Date getZaprimljena() {
        return zaprimljena;
    }

    public void setZaprimljena(Date zaprimljena) {
        this.zaprimljena = zaprimljena;
    }

    public Date getZavrsena() {
        return zavrsena;
    }

    public void setZavrsena(Date zavrsena) {
        this.zavrsena = zavrsena;
    }

    public String getNazivGrada() {
        return nazivGrada;
    }

    public void setNazivGrada(String nazivGrada) {
        this.nazivGrada = nazivGrada;
    }
}
