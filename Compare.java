import java.util.Comparator;

public class Compare<Integer> implements Comparator<Integer>{
	public double distance[];
public Compare(double distance[]){
	this.distance=distance;
}
	
	
	@Override
	public int compare(Integer a, Integer b) {
		if(distance[(int) a]<distance[(int) b]){
			return -1;
		}else if(distance[(int)a]>distance[(int)b]){
			return 1;
		}else{
		return 0;
		}
	}

}
