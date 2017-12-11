import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class MST {
Graph graph;
int vertexCount,edgeCount;
public static Map<String, Road> roadData;
public static ArrayList<Road> roadList;
public static ArrayList<String> result;
public MST(Graph graph){
	this.graph=graph;
	roadData=graph.roadData;
	roadList=new ArrayList<Road>(roadData.values());
	vertexCount=graph.vertexCount;
	edgeCount=graph.edgeCount;
	result=new ArrayList<String>();
	kuskal();
}
class Set {
int parent,rank;
}
public int find(Set subset[],int index){
	if(subset[index].parent!=index){
		subset[index].parent=find(subset,subset[index].parent);
	}
	return subset[index].parent;
}
public void union(Set subset[],int index1, int index2){
	int parent1=find(subset,index1);
	int parent2=find(subset,index2);
	if(subset[parent1].rank<subset[parent2].rank){
		subset[parent1].parent=parent2;
	}else if(subset[parent1].rank>subset[parent2].rank){
		subset[parent2].parent=parent1;
	}else{
		subset[parent2].parent=parent1;
		subset[parent1].rank++;
	}
}

public void kuskal(){
	
	int e=0;
	Collections.sort(roadList);
	Set subsets[]=new Set[vertexCount];
	for(int i=0;i<vertexCount;i++){
		subsets[i]=new Set();
		subsets[i].parent=i;
		subsets[i].rank=0;
	}
	int index=0;
	while(e<vertexCount-1&&index<edgeCount){
		Road road=roadList.get(index);
		
		int x=find(subsets,road.start.index);
		int y=find(subsets,road.end.index);
		if(x!=y){
			result.add(road.name);
			union(subsets,x,y);
			e++;
		}
		index++;
	}
	for(String name:result){
		System.out.println(name);
	}
}
}
