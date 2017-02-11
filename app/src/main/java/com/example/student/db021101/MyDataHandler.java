package com.example.student.db021101;


import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by student on 2017/2/11.
 */

public class MyDataHandler extends DefaultHandler {
    boolean isTitle = false;
    boolean isLink = false;
    boolean isItem = false;
    ArrayList<String> MyTitle = new ArrayList();
    ArrayList<String> MyLink = new ArrayList();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("title"))
        {
            isTitle = true;
        }
        if (qName.equals("link"))
        {
            isLink = true;
        }
        if (qName.equals("item"))
        {
            isItem = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equals("title"))
        {
            isTitle = false;
        }
        if (qName.equals("link"))
        {
            isLink = false;
        }
        if (qName.equals("item"))
        {
            isItem = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (isTitle && isItem)
        {
            String title = new String(ch).substring(start, start + length);
            MyTitle.add(title);
            Log.d("NET", title);
        }
        if (isLink && isItem)
        {
            String link = new String(ch).substring(start, start + length);
            MyLink.add(link);
            Log.d("NET", link);
        }
    }
}
