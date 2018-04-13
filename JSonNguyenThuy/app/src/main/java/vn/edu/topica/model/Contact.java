package vn.edu.topica.model;

/**
 * Created by DELL on 8/8/2017.
 */

public class Contact {
    private String Name,City,Country;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public Contact() {

    }

    public Contact(String name, String city, String country) {

        Name = name;
        City = city;
        Country = country;
    }
}
