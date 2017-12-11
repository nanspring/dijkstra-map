import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

import javax.swing.JFrame;

public class ShortestPath {

	public static Map<String,Intersection> intersectionData;
	public static Map<String, Road> roadData;
	public Map<Integer,String>indexToString;
	public int vertexCount;
	public Map<String,Integer>stringToIndex;
	public Map<Integer,Intersection>findIntersect;
	Comparator<? super Integer> compare;
	private PriorityQueue<Integer> priority;
	private Stack<Integer> pathPrint;
	public double distance[];
	public int path[],indexPath[];
	Map<Integer, ArrayList<Integer>> nodes;
	public ShortestPath(Graph graph){
		nodes=graph.nodes;
		stringToIndex=graph.stringToIndex;
		indexToString=graph.indexToString;
		vertexCount=graph.vertexCount;
		distance=graph.distance;
		path=new int[vertexCount];
		roadData=graph.roadData;
		findIntersect=graph.findIntersect;
		intersectionData=graph.intersectionData;
		compare=new Compare(distance);
		pathPrint=new Stack<Integer>();
		priority=new PriorityQueue<Integer>(vertexCount,compare);
	}


	public void dijkstra(String intersectionname){
		int s=stringToIndex.get(intersectionname);
		for(int i=0;i<vertexCount;i++){		 
			distance[i]=Integer.MAX_VALUE;
		}
		distance[s]=0;
		priority.add(s);
		while(!priority.isEmpty()){		
			s=priority.poll();
			(findIntersect.get(s)).visited=true;
			for(int k=0;k<nodes.get(findIntersect.get(s).index).size();k++){
				int w=nodes.get(findIntersect.get(s).index).get(k);
				if((findIntersect.get(w).visited)==false){
					Road road=new Road(findIntersect.get(s),findIntersect.get(w));
					if(distance[w]>(distance[s]+road.weigh)){					
						distance[w]=distance[s]+road.weigh;
						path[w]=s;						 
						priority.add(w);
					}
				}
			}
		}
	}
	public void printPath(int start, int end){
		if(start==end){
			return;
		}else{

			pathPrint.push(end);

			printPath(start,path[end]);

		}
	}

	public void printWholePath(int start, int end){
		printPath(start,end);
		pathPrint.push(start);
		while(pathPrint.isEmpty()==false){
			System.out.println(indexToString.get((pathPrint.pop())));
		}
	}


	

}
