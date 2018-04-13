package vn.edu.topica.eventdatabingding;

import vn.edu.topica.models.Temperature;

/**
 * Created by DELL on 8/26/2017.
 */

public class MainAcivityPresenter {
    private MainActivityContact.View view;
    public MainAcivityPresenter(MainActivityContact.View view){
        this.view=view;
    }
    public  void onShowData(Temperature temperature){
        this.view.showData(temperature);
    }
}
