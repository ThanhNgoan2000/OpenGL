package opL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Graph {
Map<Integer, List<Integer>> adjList = null;
public Graph() {
	adjList = new HashMap<Integer, List<Integer>>();
}
public abstract void addEdge(Integer u, Integer v) ;
	

}
