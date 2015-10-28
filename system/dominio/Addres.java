package krys.threer.system.dominio;

/**
 * Created by Krys on 24/10/2015.
 */
public class Addres {

    private String street, number, burgh, city, state, country;
    private double latitude, longitude;


    public Addres(String street, String number, String burgh, String city, String state, String country,double latitude, double longitude){

        this.street = street;
        this.number = number;
        this.burgh = burgh;
        this.city = city;
        this.state = state;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public Addres(String burgh,String city, String state, String country){

        this.burgh = burgh;
        this.city = city;
        this.state = state;
        this.country = country;

    }

    public Addres(String burgh,String city, String state, String country,double latitude, double longitude){

        this.burgh = burgh;
        this.city = city;
        this.state = state;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBurgh() {
        return burgh;
    }

    public void setBurgh(String burgh) {
        this.burgh = burgh;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
