package com.example.rglovejoy.npr_rss_feed;

import com.example.rglovejoy.npr_rss_feed.model.Feed;
import com.example.rglovejoy.npr_rss_feed.model.FeedItem;
import com.example.rglovejoy.npr_rss_feed.model.Image;
import com.example.rglovejoy.npr_rss_feed.rss.XMLParser;

import junit.framework.TestCase;

import org.junit.Test;

public class XMLParserTest extends TestCase {

    private static final String xmlString =
            "<rss version=\"2.0\" xmlns:npr=\"http://www.npr.org/rss/\" xmlns:nprml=\"http//:api.npr.org/nprml\" xmlns:itunes=\"http://www.itunes.com/dtds/podcase-1.0.dtd\" xmlns:content=\"http://purl.org/rss.1.0/modules/content\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\">" +
            "    <channel>" +
            "        <title>News</title>" +
            "        <link>http://www.npr.org/templates/story/story.php?storyId=1001</link>" +
            "        <description>NPR News</description>" +
            "        <language>en</language>" +
            "        <copyright>Copyright 2015 NPR</copyright>" +
            "        <generator>NPR API RSS Generator 0.94</generator>" +
            "        <lastBuildDate>Mon, 27 Apr 2015 17:04:00 -0400</lastBuildDate>" +
            "        <image>" +
            "            <url>http://media.npr.org/image.png</url>" +
            "            <title>News</title>" +
            "            <link>http://media.npr.org/image.png</link>" +
            "        </image>" +
            "        <item>" +
            "            <title>Title0</title>" +
            "            <description>Description0</description>" +
            "            <pubDate>Mon, 27 Apr 2015 17:04:00 -0400</pubDate>" +
            "            <link>http://www.npr.org/link0</link>" +
            "            <guid>http://www.npr.org/link0</guid>" +
            "            <content:encoded>encoded0</content:encoded>" +
            "            <dc:creator>Alice Peters</dc:creator>" +
            "        </item>" +
            "        <item>" +
            "            <title>Title1</title>" +
            "            <description>Description1</description>" +
            "            <pubDate>Mon, 28 Apr 2015 15:14:00 -0400</pubDate>" +
            "            <link>http://www.npr.org/link1</link>" +
            "            <guid>http://www.npr.org/link1</guid>" +
            "            <content:encoded>encoded1</content:encoded>" +
            "            <dc:creator>Peter Rabbit</dc:creator>" +
            "        </item>" +
            "    </channel>" +
            "</rss>";



    @Test
    public void testParseXML() {
        Feed feed = XMLParser.parseXML(xmlString);
        Image image = feed.image;
        FeedItem item0 = feed.feedItems.get(0);
        FeedItem item1 = feed.feedItems.get(1);

        assertEquals(feed.title, "News");
        assertEquals(feed.copyright, "Copyright 2015 NPR");
        assertEquals(feed.description, "NPR News");
        assertEquals(feed.generator, "NPR API RSS Generator 0.94");
        assertEquals(feed.language, "en");
        assertEquals(feed.lastBuildDate, "Mon, 27 Apr 2015 17:04:00 -0400");

        assertEquals(image.title, "News");
        assertEquals(image.link, "http://media.npr.org/image.png");
        assertEquals(image.url, "http://media.npr.org/image.png");

        assertEquals(item0.title, "Title0");
        assertEquals(item0.description, "Description0");
        assertEquals(item0.pubDate, "Mon, 27 Apr 2015 17:04:00 -0400");
        assertEquals(item0.link, "http://www.npr.org/link0");
        assertEquals(item0.guid, "http://www.npr.org/link0");
        assertEquals(item0.encodedContent, "encoded0");
        assertEquals(item0.creator, "Alice Peters");

        assertEquals(item1.title, "Title1");
        assertEquals(item1.description, "Description1");
        assertEquals(item1.pubDate, "Mon, 28 Apr 2015 15:14:00 -0400");
        assertEquals(item1.link, "http://www.npr.org/link1");
        assertEquals(item1.guid, "http://www.npr.org/link1");
        assertEquals(item1.encodedContent, "encoded1");
        assertEquals(item1.creator, "Peter Rabbit");
    }


}
