package com.sid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

public class sid extends Activity {
    /** Called when the activity is first created. */
	 public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	    private ProgressDialog mProgressDialog;
	public String FileName = "";
    public String FileURL = "";
    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        /* Create a new TextView to display the parsingresult later. */
        tv = new TextView(this);
        tv.setText("This is the parsing program...");


        try {
             /* Create a URL we want to load some xml-data from. */
             URL url = new URL("http://www.pragyan.org/12/updates.xml");

             url.openConnection();
             /* Get a SAXParser from the SAXPArserFactory. */
             SAXParserFactory spf = SAXParserFactory.newInstance();
             SAXParser sp = spf.newSAXParser();

             /* Get the XMLReader of the SAXParser we created. */
             XMLReader xr = sp.getXMLReader();
 			/* Create a new ContentHandler and apply it to the XML-Reader*/ 
 			ExampleHandler myExampleHandler = new ExampleHandler();
 			xr.setContentHandler(myExampleHandler);
 			
             /* Parse the xml-data from our URL. */
              xr.parse(new InputSource(url.openStream()));
             /* Parsing has finished. */

             /* Our ExampleHandler now provides the parsed data to us. */
            
             List<ParsedExampleDataSet> parsedExampleDataSet = 
					myExampleHandler.getParsedData();
             ParsedExampleDataSet dataItem ;
           //  FileURL= (parsedExampleDataSet).getfileurl();
            /* Iterator j;
             j = parsedExampleDataSet.iterator();
             
             while(j.hasNext()){

                  dataItem = (ParsedExampleDataSet) j.next();
                  tv.append("\n" + dataItem.getname());
                  tv.append("\n" + dataItem.gettype());
                  tv.append("\n" + dataItem.getdescription());*/
                  FileName = "http://www.pragyan.org/11/cms/uploads/iconman/0000000702_innovation.png";
                 FileURL = "http://www.pragyan.org/11/cms/uploads/iconman/0000000702_innovation.png";
                  startDownload();
               //   }
           //  tv.setText(parsedExampleDataSet.toString());

             
                

        } catch (Exception e) {
             /* Display any Error to the GUI. */
             tv.setText("Error: " + e.getMessage());

        }
        /* Display the TextView. */
        this.setContentView(tv);
   }
    private void startDownload(){
        new DownloadFileAsync().execute(FileURL);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("Downloading file..");
                mProgressDialog.setIndeterminate(false);
                mProgressDialog.setMax(100);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(true);
                mProgressDialog.show();
                return mProgressDialog;
            default:
                return null;
        }
    }


    class DownloadFileAsync extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(DIALOG_DOWNLOAD_PROGRESS);
        }


        @Override
        protected String doInBackground(String... aurl) {

            try {
                File root = Environment.getExternalStorageDirectory();
                URL u = new URL(FileURL);
                HttpURLConnection c = (HttpURLConnection) u.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.connect();

                int lenghtOfFile = c.getContentLength();

                FileOutputStream f = new FileOutputStream(new File(root + "/download/", FileName));

                InputStream in = c.getInputStream();

                byte[] buffer = new byte[1024];
                int len1 = 0;
                long total = 0;

                while ((len1 = in.read(buffer)) > 0) {
                    total += len1; //total = total + len1
                    publishProgress("" + (int)((total*100)/lenghtOfFile));
                    f.write(buffer, 0, len1);
                }
                f.close();
            } catch (Exception e) {
                Log.d("Downloader", e.getMessage());
            }

            return null;

        }


        protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC",progress[0]);
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
       }

       @Override
       protected void onPostExecute(String unused) {
           dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
           tv.setText("MotherFucker");
       }

    }

}