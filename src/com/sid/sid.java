package com.sid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class sid extends Activity {
    /** Called when the activity is first created. */
	 public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	    private ProgressDialog mProgressDialog;
	    ImageView myImage;
	    public String Fn;
	    public String Url;
	    public String FileName = "";
    public String FileURL = "";
    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.main);
        /* Create a new TextView to display the parsingresult later. */
        tv = new TextView(this);
       tv.setText("This is the parsing program...");

      //  myImage = (ImageView) findViewById(R.id.imageView1);
       //  myImage.findViewById(R.drawable.icon);
       //  File root = Environment.getExternalStorageDirectory();
         

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
            //ParsedExampleDataSet s=new ParsedExampleDataSet() ;
             List DataSet = myExampleHandler.getData();
           //  tv.setText(DataSet.toString());
            // DataSet dataItem ;
           //  FileURL= (parsedExampleDataSet).getfileurl();
             Iterator j;
             j = DataSet.iterator();
             while(j.hasNext())
              {tv.append(j.next().toString());}
            /* while(j.hasNext()){

                  dataItem = (DataSet) j.next();
                  tv.append("\n" + dataItem.getname());
                  tv.append("\n" + dataItem.gettype());
                  tv.append("\n" + dataItem.getdescription());
                // FileName = "innovation.png";
                // = "http://www.pragyan.org/11/cms/uploads/iconman/0000000702_innovation.png";
                //  Fn=dataItem.getname()+".jpg";
                //  Url=dataItem.getfileurl();
                // startDownload();
                  }*/
        //     tv.setText(DataSet.toString());

             
                

        } catch (Exception e) {
             /* Display any Error to the GUI. */
             tv.setText("Error: " + e.getMessage());

        }
        /* Display the TextView. */
        this.setContentView(tv);
   }
    private void startDownload(){
        new DownloadFileAsync().execute(Fn,Url);
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
                URL u = new URL(aurl[1]);
                HttpURLConnection c = (HttpURLConnection) u.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.connect();

                int lenghtOfFile = c.getContentLength();
                new File(root + "/images/").mkdirs();
              //  FileOutputStream f = new FileOutputStream(new File(root + "/download/", FileName));
                FileOutputStream f = new FileOutputStream(new File(root + "/images/",aurl[0]));
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
           File root = Environment.getExternalStorageDirectory();
           File imgFile = new File(root + "/images/"+FileName);
           //File imgFile = new File(root + "/download/", FileName);
           if(imgFile.exists()){

               Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

              
               myImage.setImageBitmap(myBitmap);

           
           tv.setText("Done downloading"+root);}
           else
           {tv.setText("File doesnt exist");}
          
       }

    }

}