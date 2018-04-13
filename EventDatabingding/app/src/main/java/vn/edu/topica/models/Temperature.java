package vn.edu.topica.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.edu.topica.eventdatabingding.BR;

/**
 * Created by DELL on 8/26/2017.
 */

public class Temperature extends BaseObservable {
    private String celsius;
    private String fahrenheit;

    @Bindable
    public String getCelsius() {
        return celsius;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
        notifyPropertyChanged(BR.celsius);
    }
    @Bindable
    public String getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(String fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public Temperature() {

    }

    public Temperature(String celsius) {

        this.celsius = celsius;

    }
}
