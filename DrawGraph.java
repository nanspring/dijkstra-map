import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;

public class DrawGraph extends JPanel{
	public Map<String, Road> roadData;
	public Map<String,Intersection> intersectionData;
	int path[],start,end;
	Stack<Integer> pathStack;
	Graph graph;
	String choice;
	Map<Integer,Intersection>findIntersect;
	MST mst;
	ArrayList<String> result;
	
	public DrawGraph(int path[],int start,int end,Graph graph,String choice){
		
		this.roadData=graph.roadData;
		this.intersectionData=graph.intersectionData;
		this.path=path;
		this.findIntersect=graph.findIntersect;
		this.graph=graph;
		pathStack=new Stack<Integer>();
		this.start=start;
		this.end=end;
		this.choice=choice;
	}
	public DrawGraph(Graph graph,String choice, MST mst){
		
		this.roadData=graph.roadData;
		this.intersectionData=graph.intersectionData;
		this.findIntersect=graph.findIntersect;
		this.graph=graph;
		this.choice=choice;
		this.mst=mst;
		result=mst.result;
	}
	public double findMinX(Map<String,Intersection>intersectionData){
		double minX=(intersectionData.get(intersectionData.keySet().toArray()[0])).x;
		for(String key:intersectionData.keySet()){
			if((intersectionData.get(key)).x<minX){
				minX=intersectionData.get(key).x;
			}
		}
		return minX;
	}
	public double findMinY(Map<String,Intersection>intersectionData){
		double minY=(intersectionData.get(intersectionData.keySet().toArray()[0])).y;
		for(String key:intersectionData.keySet()){
			if(((intersectionData.get(key)).y<minY)){
				minY=intersectionData.get(key).y;
			}
		}
		return minY;
	}
	public double findMaxX(Map<String,Intersection>intersectionData){
		double maxX=(intersectionData.get(intersectionData.keySet().toArray()[0])).x;
		for(String key:intersectionData.keySet()){
			if((intersectionData.get(key)).x>maxX){
				maxX=intersectionData.get(key).x;
			}
		}
		return maxX;
	}
	 public void printPath(int start, int end){
		 if(start==end){
			 return;
		 }else{
			 
			 pathStack.push(end);
			
			 printPath(start,path[end]);
			 
		 }
	 }
	public double findMaxY(Map<String,Intersection>intersectionData){
		double maxY=(intersectionData.get(intersectionData.keySet().toArray()[0])).y;
		for(String key:intersectionData.keySet()){
			if(((intersectionData.get(key)).y>maxY)){
				maxY=intersectionData.get(key).y;
			}
		}
		return maxY;
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		double minX=findMinX(intersectionData);
		double maxX=findMaxX(intersectionData);
		double minY=findMinY(intersectionData);
		double maxY=findMaxY(intersectionData);
		double scaleX=getWidth()/(maxX-minX);
		double scaleY=getHeight()/(maxY-minY);
		for(String key:roadData.keySet()){
			
			double x1=((roadData.get(key).start.x)-minX)*scaleX;
			double y1=((roadData.get(key).start.y)-minY)*scaleY;
			double x2=((roadData.get(key).end.x)-minX)*scaleX;
			double y2=((roadData.get(key).end.y)-minY)*scaleY;
		g2.draw(new Line2D.Double(x1,getHeight()-y1,x2,getHeight()-y2));
		}
		if(choice.equals("1")){
		printPath(start,end);
		pathStack.push(start);
		while(pathStack.size()!=1){
		int temp=pathStack.pop();
		double x3=((findIntersect.get(temp)).x-minX)*scaleX;
		double y3=((findIntersect.get(temp)).y-minY)*scaleY;
		double x4=((findIntersect.get(pathStack.peek())).x-minX)*scaleX;
		double y4=((findIntersect.get(pathStack.peek())).y-minY)*scaleY;
	
		g2.setColor(Color.red);
		g2.draw(new Line2D.Double(x3, getHeight()-y3, x4, getHeight()-y4));
		
		}
		}else if(choice.equals("2")){
			for(int i=0;i<result.size();i++){
				Road road=roadData.get(result.get(i));
				Intersection i1=road.start;
				Intersection i2=road.end;
				double x3=(i1.x-minX)*scaleX;
				double y3=(i1.y-minY)*scaleY;
				double x4=(i2.x-minX)*scaleX;
				double y4=(i2.y-minY)*scaleY;
				g2.setColor(Color.MAGENTA);
				g2.draw(new Line2D.Double(x3, getHeight()-y3, x4, getHeight()-y4));
			}
		}
		
		g2.dispose();
	}
}
