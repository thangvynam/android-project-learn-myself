package android.dhkhtn.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RightFragment extends android.app.Fragment implements FragmentCallbacks {

    TextView txtName;
    ImageButton btnFirst,btnNext,btnPrevious,btnLast;
    Context context=null;
    MainActivity main;
    String positionItem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout v= (LinearLayout) inflater.inflate(R.layout.fragment_right, container, false);
        txtName = (TextView) v.findViewById(R.id.txtName);
        btnFirst= (ImageButton) v.findViewById(R.id.btnFirst);
        btnNext= (ImageButton) v.findViewById(R.id.btnNext);
        btnPrevious= (ImageButton) v.findViewById(R.id.btnPrevious);
        btnLast= (ImageButton) v.findViewById(R.id.btnLast);

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RIGHT-FRAG", "0");
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s =positionItem;
                int temp = Integer.parseInt(s);
                temp+=1;
                main.onMsgFromFragToMain("RIGHT-FRAG", Integer.toString(temp));
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s =positionItem;
                int temp = Integer.parseInt(s);
                temp-=1;
                main.onMsgFromFragToMain("RIGHT-FRAG", Integer.toString(temp));
            }
        });
        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RIGHT-FRAG", "8");
            }
        });


        // Inflate the layout for this fragment
        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity(); // use this reference to invoke main callbacks
            main = (MainActivity) getActivity();
        } catch (IllegalStateException e) {
            throw new IllegalStateException(
                    "MainActivity must implement callbacks");
        }
    }

    public static RightFragment newInstance(String strArg) {
        RightFragment fragment = new RightFragment();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onMsgFromMainToFragment(String strValue) {
        String[] temp = strValue.split("&");
        positionItem=temp[0];
        txtName.setText(temp[1]);
    }
}
