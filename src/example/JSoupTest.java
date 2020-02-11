package example;


import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import api.Graph;
import api.Util;
import pa1.Crawler;
import pa1.Index;
import pa1.Page;
import pa1.WebGraph;

/**
 * Example illustrating basic usage of the jsoup library.
 * This example finds all links in a given url and 
 * identifies links that should be ignored (according to 
 * Util.ignoreLink) and those that
 * link to non-html documents.
 */
public class JSoupTest
{

  public static void main(String[] args) throws IOException
  {
	  //This seems to be working, but ordering of ranks isnt right
	  Crawler testCrawl = new Crawler("http://web.cs.iastate.edu/~smkautz", 2, 40);
	  //PrintStream o = new PrintStream(new File("Graph.txt"));
	  
//	  x.createPage();
	  
	  Graph<String> graph;
	  
	  graph = testCrawl.crawl();
	  
	  //System.setOut(o); 
      System.out.println(graph.toString());
	  
	 // System.out.println(graph.toString());
	  //Index x= new Index(graph.vertexDataWithIncomingCounts());
	  //x.makeIndex();
	  //System.out.println(graph.toString());
	  //System.out.println(x.searchWithOr("Kyrie", "computer").toString());
	   
	 Index temp = new Index(graph.vertexDataWithIncomingCounts());
	 
	 temp.makeIndex();
	 
	 //System.out.println("Indegree: " + temp.getIndegree(0));
	 
	 temp.search("steven");
	 
	 //temp.searchWithOr("steve", "science");
	  
	 //System.out.println(graph.toString());
	  
	 //System.out.println(graph.toString());
	  
	 //System.out.println(graph.vertexDataWithIncomingCounts());
	 
	 //System.out.println(temp.toString());
	  
	  
    
//    String url = "http://web.cs.iastate.edu/~smkautz";
//    //String url ="http://www.cs.iastate.edu/";
//   // String url = "https://en.wikipedia.org/wiki/Gouraud_shading";
//      
//    System.out.println("Fetching " + url);
//    Document doc = Jsoup.connect(url).get();    
//    
//    // get the links from the document text
//    Elements links = doc.select("a[href]");
//    ArrayList<String> test = new ArrayList<>();
//    
//    for (Element link : links)
//    {
//      // get the href in the form of an absolute url
//      String v = link.attr("abs:href");
//      //System.out.println("Found: " + v);
//      
//      // make sure it's a non-bookmark link with a valid MIME type
//      Document temp = null;
//      if (!Util.ignoreLink(url, v))
//      {
//        try
//        {
//          temp = Jsoup.connect(v).get();
//          System.out.println("Found: " + v);
//        }
//        catch (UnsupportedMimeTypeException e)
//        {
//          System.out.println("Unsupported Mime Type");
//        } 
//        catch (HttpStatusException  e)
//        {
//          System.out.println("--invalid link, do nothing");
//        }
//      }
//      else
//      {
//        System.out.println("--ignore");
//      }
//
//    }
//    
//
//    System.out.println();
//    
//    
//    System.out.println("Full document body text excluding links:");
//    String text = doc.body().text();
//    System.out.println(text);
//    //System.out.println(test.toArray().toString());
  }

}
