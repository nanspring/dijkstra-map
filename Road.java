import java.util.ArrayList;

public class Road implements Comparable<Road>{
public Intersection start;
public Intersection end;
public double weigh;
public String name;

public Road(Intersection start, Intersection end){
	this.start=start;
	this.end=end;
	double x=69.1*(end.x-start.x);
	double y=69.1*(end.y-start.y)*Math.cos(start.x/57.3);
	//weigh=Math.sqrt((end.x-start.x)*(end.x-start.x)-(end.y-start.y)*(end.y-start.y));
	weigh=Math.sqrt(x*x+y*y);
	
}

@Override
public int compareTo(Road o) {
	if(this.weigh>o.weigh){
		return 1;
	}else if(this.weigh<o.weigh){
		return -1;
	}else{
		return 0;
	}
	
}


}
