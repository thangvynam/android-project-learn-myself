package vn.edu.topica.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by DELL on 8/25/2017.
 */

public class User extends BaseObservable{
    private String firstName;
    private String lastName;

    @Bindable
    public String getFirstName() {
        return firstName;
    }
    @Bindable
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Bindable
    public String getLastName() {
        return lastName;
    }
    @Bindable
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User() {

    }

    public User(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }
}
