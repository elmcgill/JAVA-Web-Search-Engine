package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import pa1.WebGraph;
import pa1.WebNode;
/**
class GraphTest {

	@Test
	void test() {
		WebNode x = new WebNode("A", 0);
		
		x.addInc(1);
		x.addInc(2);
		x.addOut(3);
		
		System.out.println("Incoming " + x.getIncoming());
		System.out.println("Outgoing " + x.getOutgoing());
		
		ArrayList<Integer> check = new ArrayList<Integer>();
		check.add(1);
		check.add(2);
		
		System.out.println("Index: " + x.getIndex());
		System.out.println("Name: " + x.getName());
		
		assertEquals(x.getIndex(), 0);
		assertEquals(x.getName(), "A");
		assertEquals(x.getIncoming(), check);
		
	}
	
	@Test
	void test2() {
		WebGraph graph = new WebGraph();
		
		WebNode n = new WebNode("A", 0);
		WebNode n2 = new WebNode("B", 1);
		WebNode n3 = new WebNode("C", 2);
		WebNode n4 = new WebNode("D", 3);
		
		graph.addNode(n);
		graph.addNode(n2);
		graph.addNode(n3);
		graph.addNode(n4);
		
		n.addOut(n2.getIndex());
		n.addOut(n3.getIndex());
		
		n2.addInc(n.getIndex());
		n3.addInc(n.getIndex());
		
		n2.addOut(n4.getIndex());
		n3.addOut(n4.getIndex());
		
		n4.addInc(n2.getIndex());
		n4.addInc(n3.getIndex());
	//	n4.addInc(n4.getIndex()); self loop
		
	
		System.out.println("Vertexes: " + graph.vertexData());
		System.out.println("A's incoming nodes: " + n.getIncoming());
		System.out.println("B's incoming nodes: " + n2.getIncoming());
		System.out.println("C's incoming nodes: " + n3.getIncoming());
		System.out.println("D's incoming nodes: " + n4.getIncoming());
		
		System.out.println("--------");

		for(int i = 0; i < graph.vertexDataWithIncomingCounts().size(); i++) {
			System.out.println(graph.vertexDataWithIncomingCounts().get(i).getVertexData());
			System.out.println(graph.vertexDataWithIncomingCounts().get(i).getTagValue());
			
		
		}
		System.out.println("--------");
		
		
		System.out.println(graph.getNeighbors(0));
		System.out.println(graph.getNeighbors(1));
		System.out.println(graph.getNeighbors(2));
		System.out.println(graph.getNeighbors(3));
		
		try {
		System.out.println(graph.getNeighbors(7));
		}
		catch(Exception A) {
			System.out.println("Not found");
		}
		
		
		
		
		
	}
	
	

}
**/