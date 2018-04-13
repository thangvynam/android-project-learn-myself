package vn.edu.topica.eventdatabingding;

import vn.edu.topica.models.Temperature;

/**
 * Created by DELL on 8/26/2017.
 */

public interface MainActivityContact  {
    public interface Presenter
    {
        void onShowData(Temperature temperature);
    }
    public interface View
    {
        void showData(Temperature temperature);
    }
}
