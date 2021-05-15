import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class BL_G extends Heuristics{

	public Result alg(City city, double money, int source){
		System.out.println("BLG: ");
		Timer timer = new Timer();
		Path result = new Path(city, source, money);
		int threshold = Integer.MAX_VALUE;
		Stack<Path> stack = new Stack<Path>();
		stack.push(result);
		while(!stack.isEmpty()){
			if(timer.timeOut()) {
				return null;
			}
			Path father = stack.pop();
			System.out.println("pop: " + father);
			ArrayList<Path> neighbors = PathTools.get_neighbors(father, money);
			System.out.println("neighbors: " + neighbors);
			if(neighbors.isEmpty() && result.getFailProbabilitie() == father.getFailProbabilitie()){
				threshold = Math.min(threshold, father.getLength());
				System.out.println("threshold now : " + threshold);
				stack.removeIf(path -> path.getLength() > father.getLength());
			}
			while (!neighbors.isEmpty()){
				Path neighbor = Collections.min(neighbors);
				neighbors.remove(neighbor);
				if(neighbor.getLength() <= threshold){
					stack.push(neighbor);
					if(result.getFailProbabilitie() > neighbor.getFailProbabilitie()){
						System.out.println("result now : " + neighbor);
						result = neighbor;
					}
				}
			}
//			System.out.println(Arrays.toString(stack.stream().map(a->a.getEndVertex()).toArray()));
		}
		System.out.println("BLG RESULT : " + result);
		return new Result(result.getFailProbabilitie(), timer);
	}

}
