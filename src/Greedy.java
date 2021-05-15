import java.util.ArrayList;
import java.util.Collections;

public class Greedy extends Heuristics{

	public Result alg(City city, double money, int source){
		System.out.println("GREEDY: ");
		Timer timer = new Timer();
		Path result = new Path(city, source, money);
		System.out.println("start: " + result);
		ArrayList<Path> neighbors = PathTools.get_neighbors(result, money);
		while(neighbors.size() > 0){
			if(timer.timeOut()) {
				return null;
			}
			System.out.println("neighbors: " + neighbors);
			result = Collections.max(neighbors);
			System.out.println("result now: " + result);
			neighbors = PathTools.get_neighbors(result, money);
		}
		System.out.println("GREEDY RESULT: " + result + "\n");
		return new Result(result.getFailProbabilitie(), timer);
	}

}
