package com.example.student.db021101;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
    MyDataHandler dataHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataHandler = new MyDataHandler();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String udn_url = "https://udn.com/rssfeed/news/1";
                    URL url = new URL(udn_url);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    InputStream inputStream = conn.getInputStream();
                    ByteArrayOutputStream result = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) != -1) {
                        result.write(buffer, 0, length);
                    }
                    String str = result.toString("UTF-8");
                    // Log.d("NET", str);

                    SAXParserFactory spf = SAXParserFactory.newInstance();
                    SAXParser sp = spf.newSAXParser();
                    XMLReader xr = sp.getXMLReader();
                    xr.setContentHandler(dataHandler);
                    xr.parse(new InputSource(new StringReader(str)));

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                /*

                double fullSize = conn.getContentLength(); // 總長度
                byte[] buffer = new byte[64]; // buffer ( 每次讀取長度)
                int readSize = 0; // 當下讀取長度
                double sum = 0;
                */
            }
        }.start();
    }
}
