import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

public class Graph {
	public static Map<String,Intersection> intersectionData;
	public static Map<String, Road> roadData;
	public static int vertexCount,edgeCount;
	public double distance[];
	public Map<Integer, ArrayList<Integer>> nodes = new HashMap<Integer, ArrayList<Integer>>();
	public Map<Integer,String>indexToString;
	public Map<Integer,Intersection>findIntersect;
	public Map<String,Integer>stringToIndex;

	public Graph(String filename){
		intersectionData=new HashMap<String,Intersection>();
		roadData=new HashMap<String,Road>();
		indexToString=new HashMap<Integer,String>();
		findIntersect=new HashMap<Integer,Intersection>();
		stringToIndex=new HashMap<String,Integer>();
		fileRead(filename);
		distance=new double[vertexCount];
	}

	public void fileRead(String filename){
		try{
			FileReader file=new FileReader((filename));
			BufferedReader readfile=new BufferedReader(file);
			String line;
			int index = 0;
			ArrayList<Integer> tmp;
			while((line=readfile.readLine())!=null){
				String[] temp2=line.split("\\s+");
				if(temp2[0].equals("i")){
					double y=Double.valueOf(temp2[2]);
					double x=Double.valueOf(temp2[3]);
					Intersection intersect=new Intersection(x,y,index);
					intersectionData.put(temp2[1], intersect);
					indexToString.put(index, temp2[1]);
					findIntersect.put(index, intersect);
					stringToIndex.put(temp2[1], index);
					index += 1;
				}
				if(temp2[0].equals("r")){

					Road road=new Road(intersectionData.get(temp2[3]),intersectionData.get(temp2[2]));
					Road road2=new Road(intersectionData.get(temp2[2]),intersectionData.get(temp2[3]));
					road.name=temp2[1];
					road2.name=temp2[1];

					if (nodes.get(intersectionData.get(temp2[3]).index) == null){
						tmp = new ArrayList<Integer>();
						tmp.add(intersectionData.get(temp2[2]).index);
						nodes.put(intersectionData.get(temp2[3]).index, tmp);
					}
					else{
						nodes.get(intersectionData.get(temp2[3]).index).add(intersectionData.get(temp2[2]).index);
					}

					if (nodes.get(intersectionData.get(temp2[2]).index) == null){
						tmp = new ArrayList<Integer>();
						tmp.add(intersectionData.get(temp2[3]).index);
						nodes.put(intersectionData.get(temp2[2]).index, tmp);
					}
					else{
						nodes.get(intersectionData.get(temp2[2]).index).add(intersectionData.get(temp2[3]).index);
					}
					roadData.put(temp2[1], road);
					roadData.put(temp2[1] + "undirected",road2);

				}

			}
			vertexCount=intersectionData.size();
			edgeCount=roadData.size();
			readfile.close();


		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}catch(IOException e){
			System.out.println("error");
		}
	}

	public void printRoad(Map<String,Road> roadData){
		for(String key:roadData.keySet()){
			System.out.println(key);
		}
	}



}
