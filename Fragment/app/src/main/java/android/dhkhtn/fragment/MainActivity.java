package android.dhkhtn.fragment;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements MainCallbacks {

    FragmentTransaction ft;
    LeftFragment leftFragment;
    RightFragment rightFragment;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = new Bundle();
        bundle.putString("dulieu","hello");

        fragmentManager = getSupportFragmentManager();

        ft = getFragmentManager().beginTransaction();
        leftFragment=LeftFragment.newInstance("left-frag");
        ft.replace(R.id.leftFrag, leftFragment);
        ft.commit();

        ft = getFragmentManager().beginTransaction();
        rightFragment=RightFragment.newInstance("right-frag");
        rightFragment.setArguments(bundle);
        ft.replace(R.id.rightFrag, rightFragment);
        ft.commit();





    }


    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {
        if (sender.equals("LEFT-FRAG")) {
            try{
                rightFragment.onMsgFromMainToFragment(strValue);
            }catch(Exception e){
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }

        }
        if (sender.equals("RIGHT-FRAG")) {
            try {
                    // forward blue-data to redFragment using its callback method
                leftFragment.onMsgFromMainToFragment(strValue);
            } catch (Exception e) {

            }
        }
    }

}
