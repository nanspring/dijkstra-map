import java.util.Scanner;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String continueGame;
		do{
		System.out.println("Please input the name of the map.");
		System.out.println("======================================");
		String mapName=scanner.next();
		Graph path=new Graph(mapName);
		System.out.println("What would you like to see?");
		System.out.println("======================================");
		System.out.println("1. Shortest path");
		System.out.println("2. Minimum spanning tree");
		System.out.println("======================================");
		System.out.println("Type 1 if you like to see the shortest path. Otherwise type 2");
		String choice=scanner.next();	
		DrawGraph graph;
		if(choice.equals("1")){
			ShortestPath sp=new ShortestPath(path);
			System.out.println("Please type the name of the starting intersectoin");
			System.out.println("======================================");
			String start1=scanner.next();
			System.out.println("Please type the name of the ending intersection");
			System.out.println("======================================");
			String end1=scanner.next();
			int start=path.stringToIndex.get(start1);
			int end=path.stringToIndex.get(end1);
			sp.dijkstra(start1);
			Intersection i2=sp.intersectionData.get(start1);
			Intersection i6=sp.intersectionData.get(end1);
			sp.printWholePath(start, end);
			System.out.println("Distance is "+sp.distance[i6.index]+" mile");
			graph=new DrawGraph(sp.path,start,end,path,choice);
		}else{
			MST mst=new MST(path);
			graph=new DrawGraph(path,choice,mst);
		}
		JFrame frame=new JFrame();

		frame.add(graph);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("======================================");
		System.out.println("Do you want to test another map?");
		System.out.println("Type yes to continue. Otherwise type no to quit the program");
		System.out.println("======================================");
		continueGame=scanner.next();
		}while(continueGame.equals("yes"));
		System.out.println("Thank! Hope to see you soon!");



	}
}
