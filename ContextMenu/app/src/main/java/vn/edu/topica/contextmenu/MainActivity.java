package vn.edu.topica.contextmenu;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button btnThang,btnVy,btnNam;
    Button btnSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnThang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSelected=btnThang;
            }
        });
        btnThang.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnSelected=btnThang;
                return false;
            }
        });

        btnVy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSelected=btnVy;
            }
        });
        btnVy.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnSelected=btnVy;
                return false;
            }
        });

        btnNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSelected=btnNam;
            }
        });
        btnNam.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnSelected=btnNam;
                return false;
            }
        });
    }




    private void addControls() {
        btnThang= (Button) findViewById(R.id.btnThang);
        btnVy= (Button) findViewById(R.id.btnVy);
        btnNam= (Button) findViewById(R.id.btnNam);

        registerForContextMenu(btnNam);
        registerForContextMenu(btnVy);
        registerForContextMenu(btnThang);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.contextmenu_main,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mnuInDam){
            String tempString="SƠN TÙNG MTPP";
            SpannableString spanString = new SpannableString(tempString);
            spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
            btnSelected.setText(spanString);

            btnSelected.setTypeface(null,Typeface.BOLD);
        }
        else if(item.getItemId()==R.id.mnuMau){
            btnSelected.setBackgroundColor(Color.RED);
        }
        else
            btnSelected.setVisibility(View.INVISIBLE);
        return super.onContextItemSelected(item);
    }
}
