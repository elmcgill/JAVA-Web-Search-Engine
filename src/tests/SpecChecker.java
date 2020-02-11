package tests;

import api.Graph;
import api.TaggedVertex;
import pa1.Crawler;
import pa1.Index;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import speccheck.SpecCheck;
import speccheck.SpecCheckTest;








public class SpecChecker
{
  public static void main(String[] args) {
    boolean testOnly = (args.length > 0);







    
    try {
      SpecCheck.test(SpecChecker.class, "pa1", 2);
    }
    catch (Throwable e) {
      
      System.out.println(e);
    } 
  }




  
  public void initializationError() { System.out.println("foo"); }





  
  private static final String[] words = new String[] { 
      "play", 
      "sky", 
      "country", 
      "flies", 
      "costs", 
      "chicken", 
      "soup", 
      "twocourse", 
      "will", 
      "link", 
      "ice", 
      "cream", 
      "good", 
      "long", 
      "meal", 
      "fly", 
      "hovercraft", 
      "extra", 
      "stand", 
      "favorite", 
      "vanilla" };

  
  private static final String[] nonwords = new String[] {
      "chicken!", 
      "the", 
      "CHICKEN"
    };

  
  private static Index index;
  
  private static final String[] pages = new String[] {
      "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/a.html", 
      "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/b.html", 
      "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/c.html", 
      "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/d.html", 
      "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/i.html", 
      "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/j.html", 
      "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/e.html", 
      "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/f.html", 
      "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/g.html", 
      "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/h.html"
    };
  private static final int[] ranks = new int[] {
      3, 2, 2, 2, 1, 1, 1, 1, 1, 1 }; private static String[] g1Vertices;
  private static int[][] g1Edges;
  
  static  {
    ArrayList<TaggedVertex<String>> args = new ArrayList<>();
    for (int i = 0; i < pages.length; i++)
    {
      args.add(new TaggedVertex(pages[i], ranks[i]));
    }
    
    index = new Index(args);
    index.makeIndex();

    
    g1Vertices = 
      new String[] {
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/a.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/b.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/c.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/d.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/i.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/j.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/e.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/f.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/g.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/h.html"
      };
    g1Edges = 
      new int[][] {
        { 1, 2, 3
        }, { 2, 4, 5
        }, { 1, 3, 6, 7
        }, { 0, 8, 9
        
        },
        {}, {}, new int[1], {}, {}, {}
      };


    
    g1Incoming = 
      new int[][] {
        { 3, 6
        }, { 0, 2
        }, { 0, 1
        }, { 0, 2
        }, { 1
        }, { 1
        }, { 2
        }, { 2
        }, { 3
        }, { 3 }
      };

    
    g1Scores = new int[] { 3, 2, 2, 2, 1, 1, 1, 1, 1, 1 };
  }
  private static int[][] g1Incoming;
  private static int[] g1Scores;
  
  @Before
  public void setup() {}
  
  private String seed = "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/a.html";

  
  @Test
  @SpecCheckTest(order = 20, msg = "")
  public void testGraph1() {
    Crawler c = new Crawler(this.seed, 100, 100);
    Graph<String> g = c.crawl();
    checkVertices("With Crawler(seed, 100, 100), graph should have correct vertices. ", g);
  }



  
  @Test
  @SpecCheckTest(order = 20, msg = "")
  public void testGraph2() {
    Crawler c = new Crawler(this.seed, 100, 100);
    Graph<String> g = c.crawl();
    checkVertices("Correct previous errors first: With Crawler(seed, 100, 100), graph should have correct vertices. ", g);
    checkNeighborLists("With Crawler(seed, 100, 100), graph should have correct neighbor lists. ", g);
  }


  
  @Test
  @SpecCheckTest(order = 21, msg = "")
  public void testGraph3() {
    Crawler c = new Crawler(this.seed, 100, 100);
    Graph<String> g = c.crawl();
    checkVertices("Correct previous errors first: With Crawler(seed, 100, 100), graph should have correct vertices. ", g);
    
    checkIncomingLists("With Crawler(seed, 100, 100), graph should have correct incoming lists. ", g);
  } @Test
  @SpecCheckTest(order = 22, msg = "")
  public void testIndex1() {
    byte b;
    int i;
    String[] arrayOfString;
    for (i = (arrayOfString = words).length, b = 0; b < i; ) { String s = arrayOfString[b];
      
      List<TaggedVertex<String>> result = index.search(s);
      if (result == null || result.size() == 0)
      {
        Assert.fail("Index should have a nonempty entry for word \"" + s + "\"");
      }
      b++; }
    
    for (i = (arrayOfString = nonwords).length, b = 0; b < i; ) { String s = arrayOfString[b];
      
      List<TaggedVertex<String>> result = index.search(s);
      if (result != null && result.size() != 0)
      {
        Assert.fail("Index should not have a nonempty entry for word \"" + s + "\"");
      }
      b++; }
  
  }
  
  @Test
  @SpecCheckTest(order = 23, msg = "")
  public void testSearch1() {
    String[] pages = {
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/c.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/a.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/j.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/e.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/f.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/h.html"
      };
    int[] ranks = { 10, 3, 1, 1, 1, 1 };
    
    List<TaggedVertex<String>> results = index.search("chicken");
    checkResults("search \"chicken\" : ", pages, ranks, results);
  }



  
  @Test
  @SpecCheckTest(order = 24, msg = "")
  public void testSearch2() {
    String[] pages = {
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/a.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/h.html"
      };
    int[] ranks = { 6, 2 };
    
    List<TaggedVertex<String>> results = index.searchWithAnd("chicken", "vanilla");
    checkResults("search \"chicken\" and \"vanilla\" : ", pages, ranks, results);
  }


  
  @Test
  @SpecCheckTest(order = 25, msg = "")
  public void testSearch3() {
    String[] pages = {
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/c.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/b.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/a.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/d.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/j.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/e.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/f.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/h.html" };
    int[] ranks = { 10, 6, 6, 2, 1, 1, 1, 2 };
    
    List<TaggedVertex<String>> results = index.searchWithOr("chicken", "vanilla");
    checkResults("search \"chicken\" or \"vanilla\" : ", pages, ranks, results);
  }


  
  @Test
  @SpecCheckTest(order = 26, msg = "")
  public void testSearch4() {
    String[] pages = {
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/c.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/j.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/e.html", 
        "http://web.cs.iastate.edu/~smkautz/cs311f19/temp/f.html"
      };
    int[] ranks = { 10, 1, 1, 1 };
    
    List<TaggedVertex<String>> results = index.searchAndNot("chicken", "vanilla");
    checkResults("search \"chicken\" and not \"vanilla\" : ", pages, ranks, results);
  }






  
  private void checkResults(String msg, String[] pages, int[] ranks, List<TaggedVertex<String>> results) {
    Map<String, Integer> actual = new HashMap<>();
    Set<String> expected = new HashSet<>(); byte b; int j; String[] arrayOfString;
    for (j = (arrayOfString = pages).length, b = 0; b < j; ) { String s = arrayOfString[b];
      
      expected.add(s); b++; }
    
    for (TaggedVertex<String> tv : results)
    {
      actual.put((String)tv.getVertexData(), Integer.valueOf(tv.getTagValue()));
    }
    
    for (j = (arrayOfString = pages).length, b = 0; b < j; ) { String p = arrayOfString[b];
      
      if (!actual.containsKey(p))
      {
        Assert.fail(String.valueOf(msg) + "Result is missing page " + p); } 
      b++; }
    
    for (String p : actual.keySet()) {
      
      if (!expected.contains(p))
      {
        Assert.fail(String.valueOf(msg) + "Result has unexpected page " + p);
      }
    } 

    
    for (int i = 0; i < pages.length; i++) {
      
      int actualScore = ((Integer)actual.get(pages[i])).intValue();
      int expectedScore = ranks[i];
      Assert.assertEquals(String.valueOf(msg) + "rank for url " + pages[i] + " is incorrect. ", expectedScore, actualScore);
    } 

    
    if (results.size() > 0) {
      
      int current = ((TaggedVertex)results.get(0)).getTagValue();
      boolean first = true;
      for (TaggedVertex<String> tv : results) {
        
        if (first) {
          
          first = false;
          
          continue;
        } 
        int next = tv.getTagValue();
        if (current < next)
        {
          Assert.fail(String.valueOf(msg) + "Results are not ordered largest to smallest rank. ");
        }
        current = next;
      } 
    } 
  }


  
  private void checkVertices(String msg, Graph<String> g) {
    ArrayList<String> v = g.vertexData();
    Assert.assertEquals(String.valueOf(msg) + "Vertex array has wrong size: ", g1Vertices.length, v.size());
    for (int i = 0; i < g1Vertices.length; i++) {
      
      if (!v.contains(g1Vertices[i])) {
        Assert.fail(String.valueOf(msg) + "Graph is missing vertex for " + g1Vertices[i]);
      }
    } 
  }

  
  private void checkNeighborLists(String msg, Graph<String> g) {
    ArrayList<String> v = g.vertexData();
    
    Map<String, Integer> stringToIndex = new HashMap<>();
    Assert.assertEquals(String.valueOf(msg) + "Vertex array has wrong size: ", g1Vertices.length, v.size());
    
    for (int i = 0; i < g1Vertices.length; i++) {
      
      int index = v.indexOf(g1Vertices[i]);
      
      if (index < 0)
      {
        Assert.fail(String.valueOf(msg) + "Graph is missing vertex for " + g1Vertices[i]);
      }

      
      List<Integer> neighbors = g.getNeighbors(index);
      
      Set<String> actual = new HashSet<>();
      for (Iterator<Integer> iterator = neighbors.iterator(); iterator.hasNext(); ) { int j = ((Integer)iterator.next()).intValue();
        
        actual.add(v.get(j)); }
      
      if (neighbors.size() != actual.size())
      {
        Assert.fail(String.valueOf(msg) + "Neighbor list for " + (String)v.get(index) + " has duplicate element. ");
      }

      
      Set<String> expected = new HashSet<>(); byte b; int k, arrayOfInt[];
      for (k = (arrayOfInt = g1Edges[i]).length, b = 0; b < k; ) { int j = arrayOfInt[b];

        
        expected.add(g1Vertices[j]);
        
        b++; }
      
      Assert.assertEquals(String.valueOf(msg) + "Neighbor list for " + (String)v.get(index) + " has incorrect size. ", expected.size(), actual.size());
      for (String s : expected) {
        
        if (!actual.contains(s))
        {
          Assert.fail(String.valueOf(msg) + "Neighbor list for " + (String)v.get(index) + " is missing element " + s + ".");
        }
      } 
      for (String s : actual) {
        
        if (!expected.contains(s))
        {
          Assert.fail(String.valueOf(msg) + "Neighbor list for " + (String)v.get(index) + " has unexpected element " + s + ".");
        }
      } 
    } 
  }







  
  private void checkIncomingLists(String msg, Graph<String> g) {
    ArrayList<String> v = g.vertexData();
    ArrayList<TaggedVertex<String>> tv = g.vertexDataWithIncomingCounts();

    
    Assert.assertEquals(String.valueOf(msg) + "Vertex array has wrong size: ", g1Vertices.length, v.size());
    for (int i = 0; i < g1Vertices.length; i++) {
      
      int index = v.indexOf(g1Vertices[i]);
      if (index < 0)
      {
        Assert.fail(String.valueOf(msg) + "Graph is missing vertex for " + g1Vertices[i]);
      }

      
      List<Integer> neighbors = g.getIncoming(index);
      
      Set<String> actual = new HashSet<>();
      for (Iterator<Integer> iterator = neighbors.iterator(); iterator.hasNext(); ) { int j = ((Integer)iterator.next()).intValue();
        
        actual.add(v.get(j)); }
      
      if (neighbors.size() != actual.size())
      {
        Assert.fail(String.valueOf(msg) + "Incoming list for " + (String)v.get(index) + " has duplicate element. ");
      }
      
      Set<String> expected = new HashSet<>(); byte b; int k, arrayOfInt[];
      for (k = (arrayOfInt = g1Incoming[i]).length, b = 0; b < k; ) { int j = arrayOfInt[b];
        
        expected.add(g1Vertices[j]);
        b++; }
      
      Assert.assertEquals(String.valueOf(msg) + "Incoming list for " + (String)v.get(index) + " has incorrect size. ", expected.size(), actual.size());
      for (String s : expected) {
        
        if (!actual.contains(s))
        {
          Assert.fail(String.valueOf(msg) + "Incoming list for " + (String)v.get(index) + " is missing element " + s + ".");
        }
      } 
      for (String s : actual) {
        
        if (!expected.contains(s))
        {
          Assert.fail(String.valueOf(msg) + "Incoming list for " + (String)v.get(index) + " has unexpected element " + s + ".");
        }
      } 

      
      int excount = g1Scores[i];
      int actCount = ((TaggedVertex)tv.get(index)).getTagValue();
      if (this.seed.equals(v.get(index))) {
        
        if (actCount != excount && actCount + 1 != excount)
        {
          Assert.assertEquals(String.valueOf(msg) + " Incorrect incoming count for " + (String)v.get(index) + ". ", excount, actCount);
        }
      }
      else {
        
        Assert.assertEquals(String.valueOf(msg) + " Incorrect incoming count for " + (String)v.get(index) + ". ", excount, actCount);
      } 
    } 
  }















  
  public static String getModifierDiff(int expected, int actual) {
    String msg = "";
    if (Modifier.isStatic(expected) != Modifier.isStatic(actual)) {
      msg = String.valueOf(msg) + "It should " + (Modifier.isStatic(actual) ? "not " : "") + "be static. ";
    }
    if (Modifier.isPublic(expected) != Modifier.isPublic(actual)) {
      msg = String.valueOf(msg) + "It should " + (Modifier.isPublic(actual) ? "not " : "") + "be public. ";
    }
    if (Modifier.isProtected(expected) != Modifier.isProtected(actual)) {
      msg = String.valueOf(msg) + "It should " + (Modifier.isProtected(actual) ? "not " : "") + "be protected. ";
    }
    if (Modifier.isPrivate(expected) != Modifier.isPrivate(actual)) {
      msg = String.valueOf(msg) + "It should " + (Modifier.isPrivate(actual) ? "not " : "") + "be private. ";
    }
    if (Modifier.isFinal(expected) != Modifier.isFinal(actual)) {
      msg = String.valueOf(msg) + "It should " + (Modifier.isFinal(actual) ? "not " : "") + "be final. ";
    }
    if (Modifier.isInterface(expected) != Modifier.isInterface(actual)) {
      msg = String.valueOf(msg) + "It should " + (Modifier.isInterface(actual) ? "not " : "") + "be interface. ";
    
    }
    else if (Modifier.isAbstract(expected) != Modifier.isAbstract(actual)) {
      msg = String.valueOf(msg) + "It should " + (Modifier.isAbstract(actual) ? "not " : "") + "be abstract. ";
    } 
    
    return msg;
  }
  private static String getTypesList(Class[] types) {
    String list = "";
    if (types.length > 0) {
      list = String.valueOf(list) + types[0].getCanonicalName() + ".class";
      for (int i = 1; i < types.length; i++) {
        list = String.valueOf(list) + ", " + types[i].getCanonicalName() + ".class";
      }
    } 
    return list;
  }
  @SpecCheckTest(order = 0, msg = "Missing class.")
  @Test
  public void testForClasses() throws Exception {
    try {
      Class.forName("pa1.Crawler");
    } catch (ClassNotFoundException e) {
      Assert.fail("A class by the name of pa1.Crawler could not be found. Check case, spelling, and that you created your class in the right package.");
    } 
    try {
      Class.forName("pa1.Index");
    } catch (ClassNotFoundException e) {
      Assert.fail("A class by the name of pa1.Index could not be found. Check case, spelling, and that you created your class in the right package.");
    } 
  }
  @SpecCheckTest(order = 10, msg = "Class does not conform to spec.")
  @Test
  public void testPa1Crawler() throws Exception {
    try {
      Class<?> cls = Class.forName("pa1.Crawler");
      Assert.assertTrue("The modifiers for class pa1.Crawler are not correct. " + getModifierDiff(1, cls.getModifiers()), (1 == cls.getModifiers()));
    } catch (ClassNotFoundException e) {
      Assert.fail("A class by the name of pa1.Crawler could not be found. Check case, spelling, and that you created your class in the right package.");
    } 
    Class<?> cls = Class.forName("pa1.Crawler");
    List<Class<?>> ifaces = Arrays.asList(cls.getInterfaces());
    Field field = null;
    LinkedList<Field> fields = new LinkedList<>(); byte b1; int i; Field[] arrayOfField;
    for (i = (arrayOfField = Class.forName("pa1.Crawler").getDeclaredFields()).length, b1 = 0; b1 < i; ) { Field actual = arrayOfField[b1];
      fields.add(actual); b1++; }
    
    for (Field actual : fields) {
      if (!Modifier.isStatic(actual.getModifiers()))
      {
        Assert.assertTrue("Instance variables must be private (or possibly protected). pa1.Crawler." + actual.getName() + " is not. The only public variables should be specified constants, which must be static and final.", !(!Modifier.isPrivate(actual.getModifiers()) && !Modifier.isProtected(actual.getModifiers())));
      }
    } 
    List<Class<?>> exceptions = null;
    List<Class<?>> outlawedExceptions = null;
    Constructor<?> ctor = null;
    try {
      ctor = Class.forName("pa1.Crawler").getDeclaredConstructor(new Class[] { String.class, int.class, int.class });
    } catch (NoSuchMethodException e) {
      Assert.fail("You need a constructor in class pa1.Crawler taking 3 arguments, having types java.lang.String, int, int.");
    } 
    Assert.assertTrue("The modifiers for constructor pa1.Crawler(java.lang.String, int, int) are not correct. " + getModifierDiff(1, ctor.getModifiers()), (1 == ctor.getModifiers()));
    exceptions = Arrays.asList(ctor.getExceptionTypes());
    outlawedExceptions = Arrays.asList(new Class[0]);
    for (Class<?> exception : exceptions) {
      Assert.assertFalse("The specification requires constructor pa1.Crawler(java.lang.String, int, int) to handle and not throw " + exception.getName() + ".", outlawedExceptions.contains(exception));
    }
    LinkedList<Constructor<?>> ctors = new LinkedList<>(); byte b2; int j; Constructor[] arrayOfConstructor;
    for (j = (arrayOfConstructor = (Constructor[])Class.forName("pa1.Crawler").getDeclaredConstructors()).length, b2 = 0; b2 < j; ) { Constructor<?> actual = arrayOfConstructor[b2];
      ctors.add(actual); b2++; }
    
    try {
      ctor = Class.forName("pa1.Crawler").getDeclaredConstructor(new Class[] { String.class, int.class, int.class });
      ctors.remove(ctor);
    } catch (NoSuchMethodException noSuchMethodException) {}
    for (Constructor<?> actual : ctors) {
      if (Modifier.isPublic(actual.getModifiers()) && (actual.getParameterTypes()).length != 0) {
        Assert.fail(String.format("Constructor %1$s(%2$s) is not in the specification. Any constructors you add should be private (or possibly protected).", new Object[] { actual.getName(), getTypesList(actual.getParameterTypes()).replaceAll(".class", "") }));
      }
    } 
    Method method = null;
    try {
      method = Class.forName("pa1.Crawler").getDeclaredMethod("crawl", new Class[0]);
    } catch (NoSuchMethodException e) {
      Assert.fail("You need a crawl method in class pa1.Crawler taking 0 arguments.");
    } 
    Assert.assertEquals("Your method crawl() in class pa1.Crawler has the wrong return type.", Graph.class, method.getReturnType());
    Assert.assertTrue("The modifiers for method crawl() in class pa1.Crawler are not correct. " + getModifierDiff(1, method.getModifiers()), (1 == method.getModifiers()));
    exceptions = Arrays.asList(method.getExceptionTypes());
    outlawedExceptions = Arrays.asList(new Class[0]);
    for (Class<?> exception : exceptions) {
      Assert.assertFalse("The specification requires method crawl() in class pa1.Crawler to handle and not throw " + exception.getName() + ".", outlawedExceptions.contains(exception));
    }
    LinkedList<Method> methods = new LinkedList<>(); byte b3; int k; Method[] arrayOfMethod;
    for (k = (arrayOfMethod = Class.forName("pa1.Crawler").getDeclaredMethods()).length, b3 = 0; b3 < k; ) { Method m = arrayOfMethod[b3];
      methods.add(m); b3++; }
    
    try {
      method = Class.forName("pa1.Crawler").getDeclaredMethod("crawl", new Class[0]);
      methods.remove(method);
    } catch (NoSuchMethodException noSuchMethodException) {}
    for (Method m : methods) {
      if (!m.isBridge() && !Modifier.isPrivate(m.getModifiers()) && !Modifier.isProtected(m.getModifiers()) && !m.getName().equals("main"))
        Assert.fail(String.format("Method pa1.Crawler.%1$s(%2$s) is not in the specification. Any methods you add should be private (or possibly protected).", new Object[] { m.getName(), getTypesList(m.getParameterTypes()).replaceAll(".class", "") })); 
    } 
  }
  
  @SpecCheckTest(order = 10, msg = "Class does not conform to spec.")
  @Test
  public void testPa1Index() throws Exception {
    try {
      Class<?> cls = Class.forName("pa1.Index");
      Assert.assertTrue("The modifiers for class pa1.Index are not correct. " + getModifierDiff(1, cls.getModifiers()), (1 == cls.getModifiers()));
    } catch (ClassNotFoundException e) {
      Assert.fail("A class by the name of pa1.Index could not be found. Check case, spelling, and that you created your class in the right package.");
    } 
    Class<?> cls = Class.forName("pa1.Index");
    List<Class<?>> ifaces = Arrays.asList(cls.getInterfaces());
    Field field = null;
    LinkedList<Field> fields = new LinkedList<>(); byte b1; int i; Field[] arrayOfField;
    for (i = (arrayOfField = Class.forName("pa1.Index").getDeclaredFields()).length, b1 = 0; b1 < i; ) { Field actual = arrayOfField[b1];
      fields.add(actual); b1++; }
    
    for (Field actual : fields) {
      if (!Modifier.isStatic(actual.getModifiers()))
      {
        Assert.assertTrue("Instance variables must be private (or possibly protected). pa1.Index." + actual.getName() + " is not. The only public variables should be specified constants, which must be static and final.", !(!Modifier.isPrivate(actual.getModifiers()) && !Modifier.isProtected(actual.getModifiers())));
      }
    } 
    List<Class<?>> exceptions = null;
    List<Class<?>> outlawedExceptions = null;
    Constructor<?> ctor = null;
    try {
      ctor = Class.forName("pa1.Index").getDeclaredConstructor(new Class[] { List.class });
    } catch (NoSuchMethodException e) {
      Assert.fail("You need a constructor in class pa1.Index taking 1 argument, having type java.util.List.");
    } 
    Assert.assertTrue("The modifiers for constructor pa1.Index(java.util.List) are not correct. " + getModifierDiff(1, ctor.getModifiers()), (1 == ctor.getModifiers()));
    exceptions = Arrays.asList(ctor.getExceptionTypes());
    outlawedExceptions = Arrays.asList(new Class[0]);
    for (Class<?> exception : exceptions) {
      Assert.assertFalse("The specification requires constructor pa1.Index(java.util.List) to handle and not throw " + exception.getName() + ".", outlawedExceptions.contains(exception));
    }
    LinkedList<Constructor<?>> ctors = new LinkedList<>(); byte b2; int j; Constructor[] arrayOfConstructor;
    for (j = (arrayOfConstructor = (Constructor[])Class.forName("pa1.Index").getDeclaredConstructors()).length, b2 = 0; b2 < j; ) { Constructor<?> actual = arrayOfConstructor[b2];
      ctors.add(actual); b2++; }
    
    try {
      ctor = Class.forName("pa1.Index").getDeclaredConstructor(new Class[] { List.class });
      ctors.remove(ctor);
    } catch (NoSuchMethodException noSuchMethodException) {}
    for (Constructor<?> actual : ctors) {
      if (Modifier.isPublic(actual.getModifiers()) && (actual.getParameterTypes()).length != 0) {
        Assert.fail(String.format("Constructor %1$s(%2$s) is not in the specification. Any constructors you add should be private (or possibly protected).", new Object[] { actual.getName(), getTypesList(actual.getParameterTypes()).replaceAll(".class", "") }));
      }
    } 
    Method method = null;
    try {
      method = Class.forName("pa1.Index").getDeclaredMethod("search", new Class[] { String.class });
    } catch (NoSuchMethodException e) {
      Assert.fail("You need a search method in class pa1.Index taking 1 argument, having type java.lang.String.");
    } 
    Assert.assertEquals("Your method search(java.lang.String) in class pa1.Index has the wrong return type.", List.class, method.getReturnType());
    Assert.assertTrue("The modifiers for method search(java.lang.String) in class pa1.Index are not correct. " + getModifierDiff(1, method.getModifiers()), (1 == method.getModifiers()));
    exceptions = Arrays.asList(method.getExceptionTypes());
    outlawedExceptions = Arrays.asList(new Class[0]);
    for (Class<?> exception : exceptions) {
      Assert.assertFalse("The specification requires method search(java.lang.String) in class pa1.Index to handle and not throw " + exception.getName() + ".", outlawedExceptions.contains(exception));
    }
    try {
      method = Class.forName("pa1.Index").getDeclaredMethod("makeIndex", new Class[0]);
    } catch (NoSuchMethodException e) {
      Assert.fail("You need a makeIndex method in class pa1.Index taking 0 arguments.");
    } 
    Assert.assertEquals("Your method makeIndex() in class pa1.Index has the wrong return type.", void.class, method.getReturnType());
    Assert.assertTrue("The modifiers for method makeIndex() in class pa1.Index are not correct. " + getModifierDiff(1, method.getModifiers()), (1 == method.getModifiers()));
    exceptions = Arrays.asList(method.getExceptionTypes());
    outlawedExceptions = Arrays.asList(new Class[0]);
    for (Class<?> exception : exceptions) {
      Assert.assertFalse("The specification requires method makeIndex() in class pa1.Index to handle and not throw " + exception.getName() + ".", outlawedExceptions.contains(exception));
    }
    try {
      method = Class.forName("pa1.Index").getDeclaredMethod("searchWithAnd", new Class[] { String.class, String.class });
    } catch (NoSuchMethodException e) {
      Assert.fail("You need a searchWithAnd method in class pa1.Index taking 2 arguments, having types java.lang.String, java.lang.String.");
    } 
    Assert.assertEquals("Your method searchWithAnd(java.lang.String, java.lang.String) in class pa1.Index has the wrong return type.", List.class, method.getReturnType());
    Assert.assertTrue("The modifiers for method searchWithAnd(java.lang.String, java.lang.String) in class pa1.Index are not correct. " + getModifierDiff(1, method.getModifiers()), (1 == method.getModifiers()));
    exceptions = Arrays.asList(method.getExceptionTypes());
    outlawedExceptions = Arrays.asList(new Class[0]);
    for (Class<?> exception : exceptions) {
      Assert.assertFalse("The specification requires method searchWithAnd(java.lang.String, java.lang.String) in class pa1.Index to handle and not throw " + exception.getName() + ".", outlawedExceptions.contains(exception));
    }
    try {
      method = Class.forName("pa1.Index").getDeclaredMethod("searchWithOr", new Class[] { String.class, String.class });
    } catch (NoSuchMethodException e) {
      Assert.fail("You need a searchWithOr method in class pa1.Index taking 2 arguments, having types java.lang.String, java.lang.String.");
    } 
    Assert.assertEquals("Your method searchWithOr(java.lang.String, java.lang.String) in class pa1.Index has the wrong return type.", List.class, method.getReturnType());
    Assert.assertTrue("The modifiers for method searchWithOr(java.lang.String, java.lang.String) in class pa1.Index are not correct. " + getModifierDiff(1, method.getModifiers()), (1 == method.getModifiers()));
    exceptions = Arrays.asList(method.getExceptionTypes());
    outlawedExceptions = Arrays.asList(new Class[0]);
    for (Class<?> exception : exceptions) {
      Assert.assertFalse("The specification requires method searchWithOr(java.lang.String, java.lang.String) in class pa1.Index to handle and not throw " + exception.getName() + ".", outlawedExceptions.contains(exception));
    }
    try {
      method = Class.forName("pa1.Index").getDeclaredMethod("searchAndNot", new Class[] { String.class, String.class });
    } catch (NoSuchMethodException e) {
      Assert.fail("You need a searchAndNot method in class pa1.Index taking 2 arguments, having types java.lang.String, java.lang.String.");
    } 
    Assert.assertEquals("Your method searchAndNot(java.lang.String, java.lang.String) in class pa1.Index has the wrong return type.", List.class, method.getReturnType());
    Assert.assertTrue("The modifiers for method searchAndNot(java.lang.String, java.lang.String) in class pa1.Index are not correct. " + getModifierDiff(1, method.getModifiers()), (1 == method.getModifiers()));
    exceptions = Arrays.asList(method.getExceptionTypes());
    outlawedExceptions = Arrays.asList(new Class[0]);
    for (Class<?> exception : exceptions) {
      Assert.assertFalse("The specification requires method searchAndNot(java.lang.String, java.lang.String) in class pa1.Index to handle and not throw " + exception.getName() + ".", outlawedExceptions.contains(exception));
    }
    LinkedList<Method> methods = new LinkedList<>(); byte b3; int k; Method[] arrayOfMethod;
    for (k = (arrayOfMethod = Class.forName("pa1.Index").getDeclaredMethods()).length, b3 = 0; b3 < k; ) { Method m = arrayOfMethod[b3];
      methods.add(m); b3++; }
    
    try {
      method = Class.forName("pa1.Index").getDeclaredMethod("search", new Class[] { String.class });
      methods.remove(method);
    } catch (NoSuchMethodException noSuchMethodException) {}
    try {
      method = Class.forName("pa1.Index").getDeclaredMethod("makeIndex", new Class[0]);
      methods.remove(method);
    } catch (NoSuchMethodException noSuchMethodException) {}
    try {
      method = Class.forName("pa1.Index").getDeclaredMethod("searchWithAnd", new Class[] { String.class, String.class });
      methods.remove(method);
    } catch (NoSuchMethodException noSuchMethodException) {}
    try {
      method = Class.forName("pa1.Index").getDeclaredMethod("searchWithOr", new Class[] { String.class, String.class });
      methods.remove(method);
    } catch (NoSuchMethodException noSuchMethodException) {}
    try {
      method = Class.forName("pa1.Index").getDeclaredMethod("searchAndNot", new Class[] { String.class, String.class });
      methods.remove(method);
    } catch (NoSuchMethodException noSuchMethodException) {}
    for (Method m : methods) {
      if (!m.isBridge() && !Modifier.isPrivate(m.getModifiers()) && !Modifier.isProtected(m.getModifiers()) && !m.getName().equals("main"))
        Assert.fail(String.format("Method pa1.Index.%1$s(%2$s) is not in the specification. Any methods you add should be private (or possibly protected).", new Object[] { m.getName(), getTypesList(m.getParameterTypes()).replaceAll(".class", "") })); 
    } 
  }
}
