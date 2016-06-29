package com.example.rglovejoy.npr_rss_feed.rss;

import android.util.Log;

import com.example.rglovejoy.npr_rss_feed.model.Feed;
import com.example.rglovejoy.npr_rss_feed.model.FeedItem;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser {
    public static Feed parseXML(String xmlData) {
        Feed feed;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlData));

            Document doc = dBuilder.parse(is);

            feed = new Feed();
            NodeList topNodeList = doc.getElementsByTagName("channel");
            Element topElement = (Element) topNodeList.item(0);
            feed.title = getElement(topElement, "title");
            feed.link = getElement(topElement, "link");
            feed.description = getElement(topElement, "description");
            feed.lastBuildDate = getElement(topElement, "lastBuildDate");
            feed.language = getElement(topElement, "language");
            feed.copyright = getElement(topElement, "copyright");
            feed.generator = getElement(topElement, "generator");

            NodeList imageNodeList = doc.getElementsByTagName("image");
            Element imageElement = (Element) imageNodeList.item(0);

            feed.image.title = getElement(imageElement, "title");
            feed.image.url = getElement(imageElement, "url");
            feed.image.link = getElement(imageElement, "link");

            NodeList itemNodes = doc.getElementsByTagName("item");
            for (int i = 0; i < itemNodes.getLength(); i++) {
                Element element = (Element) itemNodes.item(i);

                FeedItem feedItem = new FeedItem();

                feedItem.title = getElement(element, "title");
                feedItem.description = getElement(element, "description");
                feedItem.link = getElement(element, "link");
                feedItem.pubDate = getElement(element, "pubDate");
                feedItem.guid = getElement(element, "guid");
                feedItem.encodedContent = getElement(element, "content:encoded");
                feedItem.creator = getElement(element, "dc:creator");

                feed.feedItems.add(feedItem);
            }

            return feed;
        } catch (ParserConfigurationException e) {
            Log.e("XMLParser", "Problem parsing document", e);
        } catch (IOException ioException) {
            Log.e("XMLParser", "Problem with io", ioException);
        } catch (SAXException saxException) {
            Log.e("XMLParser", "Problem with sax", saxException);
        }
        return null;
    }

    private static String getElement(Element e, String tagName) {
        NodeList tagNode = e.getElementsByTagName(tagName);
        Element line = (Element) tagNode.item(0);
        try {
            Node child = line.getFirstChild();
            if (child instanceof CharacterData) {
                CharacterData cd = (CharacterData) child;
                return cd.getData();
            }
        } catch (NullPointerException npe) {
            return "";
        }
        return "";
    }

}
