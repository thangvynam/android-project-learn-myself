package android.dhkhtn.rrs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by DELL on 4/17/2018.
 */

public class ShowHeadlines extends Activity {
    ArrayList<SingleItem> newsList = new ArrayList<SingleItem>();
    ListView myListView;
    String urlAddress = "";
    String urlCaption = "";
    SingleItem selectedNewsItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = (ListView)this.findViewById(R.id.myListView);
// find out which intent is calling us
        Intent callingIntent = getIntent();
// grab data bundle holding selected url & caption sent to us
        Bundle myBundle = callingIntent.getExtras();
        urlAddress = myBundle.getString("urlAddress");
        urlCaption = myBundle.getString("urlCaption");
        // update app's top 'TitleBar' (eg. 'NPR - Business Wed April 09, 2
        this.setTitle("NPR - " + urlCaption + " \t" + MainActivity.niceDate());
// clicking on a row shows dialogBox with more info about selected item
        myListView = (ListView)this.findViewById(R.id.myListView);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v,
                                    int index, long id) {
                selectedNewsItem = newsList.get(index);
                showNiceDialogBox(selectedNewsItem, getApplicationContext());
            }
        });
// get stories for the selected news option
        DownloadRssFeed downloader = new DownloadRssFeed(ShowHeadlines.this);
        downloader.execute(urlAddress, urlCaption);
    }//onCreate

    public void showNiceDialogBox(SingleItem selectedStoryItem, Context applicationContext) {
        String title = selectedStoryItem.getTitle();
        String description = selectedStoryItem.getDescription();
        if (title.toLowerCase().equals(description.toLowerCase())){
            description = "";
        }
        try {
//CAUTION: sometimes TITLE and DESCRIPTION include HTML markers
            final Uri storyLink = Uri.parse(selectedStoryItem.getLink());
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
            myBuilder

                    .setTitle(Html.fromHtml(urlCaption) )
                    .setMessage( title + "\n\n" + Html.fromHtml(description) + "\n" )
                    .setPositiveButton("Close", null)
                    .setNegativeButton("More", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichOne) {
                            Intent browser = new Intent(Intent.ACTION_VIEW, storyLink);
                            startActivity(browser);
                        }
                    })//setNegativeButton
                    .show();
        } catch (Exception e) {
            Log.e("Error DialogBox", e.getMessage() );
        }
    }//showNiceDialogBox
}//ShowHead

