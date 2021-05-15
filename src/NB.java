import java.util.ArrayList;
import java.util.Stack;

public class NB extends Heuristics{

	public Result alg(City city, double money, int source){
		Timer timer = new Timer();
		Path result = new Path(city, source, money);
		int threshold = Integer.MAX_VALUE;
		Stack<Path> stack = new Stack<Path>();
		stack.add(result);
		while(!stack.isEmpty()){
			if(timer.timeOut()) {
				return null;
			}
			Path father = stack.pop();
//			System.out.println("pop: " + father);
			ArrayList<Path> neighbors = PathTools.get_no_back_neighbors(father, money);
			if(neighbors.isEmpty() && result.getFailProbabilitie() == father.getFailProbabilitie()){
				threshold = Math.min(threshold, father.getLength());
//				System.out.println("threshold now : " + threshold);
				stack.removeIf(path -> path.getLength() > father.getLength());
			}
//			System.out.println("neighbors: " + neighbors);
			for(Path neighbor : neighbors){
				if(neighbor.getLength() <= threshold){
					stack.add(neighbor);
					if(result.getFailProbabilitie() > neighbor.getFailProbabilitie()){
//						System.out.println("result now : " + neighbor);
						result = neighbor;
					}
				}
			}
		}
		return new Result(result.getFailProbabilitie(), timer);
	}

}
