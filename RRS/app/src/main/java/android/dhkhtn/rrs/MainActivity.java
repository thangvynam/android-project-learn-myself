package android.dhkhtn.rrs;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapterMainSubjects;
    ListView myMainListView;
    Context context;
    SingleItem selectedNewsItem;
    // hard-coding main NEWS categories (TODO: use a resource file)
    String [][] myUrlCaptionMenu = {
            {"https://www.npr.org/rss/rss.php?id=1001", "Top Stories"},
            {"https://www.npr.org/rss/rss.php?id=1003", "U.S. News"},
            {"https://www.npr.org/rss/rss.php?id=1004", "World News"},
            {"https://www.npr.org/rss/rss.php?id=1006", "Business"},
            {"https://www.npr.org/rss/rss.php?id=1007", "Health & Science"},
            {"https://www.npr.org/rss/rss.php?id=1008", "Arts & Entertainment"},
            {"https://www.npr.org/rss/rss.php?id=1012", "Politics & Society"},
            {"https://www.npr.org/rss/rss.php?id=1021", "People & Places"},
            {"https://www.npr.org/rss/rss.php?id=1057", "Opinion"}
    };
    //define convenient URL and CAPTIONs arrays
    String [] myUrlCaption = new String[myUrlCaptionMenu.length];
    String [] myUrlAddress = new String[myUrlCaptionMenu.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < myUrlCaptionMenu.length; i++) {
            myUrlAddress[i] = myUrlCaptionMenu[i][0];
            myUrlCaption[i] = myUrlCaptionMenu[i][1];
        }
        context = getApplicationContext();
        this.setTitle("NPR Headline News\n" + niceDate());
// user will tap on a ListView’s row to request category’s headlines
        myMainListView = (ListView) this.findViewById(R.id.myListView);
        myMainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> _av, View _v,
                                    int _index, long _id) {
                String urlAddress = myUrlAddress[_index];
                String urlCaption = myUrlCaption[_index];
//create an Intent to talk to activity: ShowHeadlines
                Intent callShowHeadlines = new Intent(MainActivity.this,
                        ShowHeadlines.class);
                Bundle myData = new Bundle();
                myData.putString("urlAddress", urlAddress);
                myData.putString("urlCaption", urlCaption);
                callShowHeadlines.putExtras(myData);
                startActivity(callShowHeadlines);
            }
        });
// fill up the Main-GUI’s ListView with main news categories
        adapterMainSubjects = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myUrlCaption); //android's default myUrlCaption);
        myMainListView.setAdapter(adapterMainSubjects);
    }

        public static String niceDate() {
            SimpleDateFormat sdf = new SimpleDateFormat("EE MMM d, yyyy ", Locale.US);
            return sdf.format(new Date() );
        }
    }
