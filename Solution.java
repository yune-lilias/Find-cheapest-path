// PUT YOUR NAME(S) HERE
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {
     static int weightmin = 10;
    /*
     * Complete the 'findCheapestPath' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_STRING_ARRAY edges as parameter.
     */

    public static int findCheapestPath(List<List<String>> edges) {
      weightmin = 10;
        // Write your code here
        HashMap<String, Node> map = new HashMap<>();
        for (List<String> edge: edges) {
            Node a = map.get(edge.get(0)); // Start node;
            if (a == null) {
                a = new Node(edge.get(0));
                map.put(edge.get(0), a);
            }
            Node b = map.get(edge.get(1)); // End node;
            if (b == null) {
                b = new Node(edge.get(1));
                map.put(edge.get(1), b);
            }
            // Add edge
            a.add(new NodeEdge(b, Integer.parseInt(edge.get(2))));       
        }
        
        // TODO: Find path from A to H ...
        return dfs(map.get("A"), map.get("H"));

    }
    public static int dfs(Node a, Node b){
      dfs(a,b,0);
      return weightmin;
    }

    
    public static void dfs(Node a, Node b, int weightSoFar) {
        if(a == null)
        return;
        if(a.value.equals(b.value)){
        if(weightSoFar <= 10&&weightmin>weightSoFar){
          weightmin = weightSoFar;
          return;
        }
        else{
          return;
        }
        }
        else{
          for(NodeEdge next : a.edges)
          dfs(next.end,b,weightSoFar+next.weight);
        }
        //Return true only if you get a==b and weightSoFar<=10
    }

}

class Node {
    String value;
    ArrayList<NodeEdge> edges;
    public Node(String value) {
        edges = new ArrayList<>();
        this.value = value;
    }
    
    public void add (NodeEdge edge) {
        edges.add(edge);
    }
}

class NodeEdge{
    Node end;
    int weight;
    public NodeEdge(Node end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/Users/Admin/Desktop/envr.txt"));
   //     BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(Arrays.asList(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")));
        }

        int result = Result.findCheapestPath(arr);
        System.out.println(result);
   //     bufferedWriter.write(String.valueOf(result));
    //    bufferedWriter.newLine();

        bufferedReader.close();
    //    bufferedWriter.close();
    }
}
