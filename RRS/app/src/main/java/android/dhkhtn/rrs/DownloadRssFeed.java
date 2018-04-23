package android.dhkhtn.rrs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by DELL on 4/17/2018.
 */

public class DownloadRssFeed extends AsyncTask<String, Void, ArrayList<SingleItem>> {
    ShowHeadlines callerContext; //caller class
    String urlAddress;
    String urlCaption;
    ProgressDialog dialog = null;

    public DownloadRssFeed(Context callerContext) {
        this.callerContext = (ShowHeadlines) callerContext;
        dialog = new ProgressDialog(callerContext);
    }

    protected void onPreExecute() {
        this.dialog.setMessage("Please wait\nReading RSS feed ...");
        this.dialog.setCancelable(false); //outside touching doesn't dismiss you
        this.dialog.show();
    }

    @Override
    protected ArrayList<SingleItem> doInBackground(String... params) {
        ArrayList<SingleItem> newsList = new ArrayList<SingleItem>();
        urlAddress = params[0]; // eg. "http://www.npr.org/rss/rss.php?id=1004"
        urlCaption = params[1];
        this.dialog.setMessage("Please wait\nReading RSS feed " +
                urlCaption + "...");
        try {
// try to get connected to RSS source
            URL url = new URL(urlAddress);
            URLConnection connection;
            connection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpConnection.getInputStream();
// define a document builder to work on incoming stream
                DocumentBuilderFactory dbf = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
// make DOM-tree for incoming XML stream
                Document dom = db.parse(in);
// make available all access nodes in the parse tree
                Element treeElements = dom.getDocumentElement();
                newsList.clear();
                NodeList itemNodes = treeElements.getElementsByTagName("item");
                if ((itemNodes != null) && (itemNodes.getLength() > 0)) {
                    for (int i = 0; i < itemNodes.getLength(); i++) {
                        newsList.add(dissectItemNode(itemNodes, i));
                    }// for
                }// if
            }// if
            // time to close. we don't need the connection anymore
            httpConnection.disconnect();
        } catch (Exception e) {
            Log.e("Error>> ", e.getMessage());
        }
        return newsList; //to be consumed by onPostExecute
    }

    @Override
    protected void onPostExecute(ArrayList<SingleItem> result) {
        super.onPostExecute(result);
        callerContext.newsList = result;
        int layoutID = android.R.layout.simple_list_item_1;
        ArrayAdapter<SingleItem> adapterNews =
                new ArrayAdapter<SingleItem>(callerContext, layoutID, result);
        callerContext.myListView.setAdapter(adapterNews);
        dialog.dismiss();
    }
    public SingleItem dissectItemNode(NodeList nodeList, int i){
// disassemble i-th entry in NodeList collection
// get the first child of elements: extract fields:
// title, description, pubData, and link. Put those pieces
// together into a POJO 'SingleItem' object, and return it
        try {
            Element entry = (Element) nodeList.item(i);
            Element title = (Element) entry.getElementsByTagName(
                    "title").item(0);
            Element description = (Element) entry.getElementsByTagName(
                    "description").item(0);
            Element pubDate = (Element) entry.getElementsByTagName(
                    "pubDate").item(0);
            Element link = (Element) entry.getElementsByTagName(
                    "link").item(0);
            String titleValue = title.getFirstChild().getNodeValue();
            String descriptionValue =description.getFirstChild().getNodeValue();
            String dateValue = pubDate.getFirstChild().getNodeValue();
            String linkValue = link.getFirstChild().getNodeValue();
            SingleItem singleItem = new SingleItem( dateValue,
                    titleValue,
                    descriptionValue,
                    linkValue );
            return singleItem;
        } catch (DOMException e) {
            return new SingleItem("", "Error", e.getMessage(), null);
        }
    }//dissectNode
}
