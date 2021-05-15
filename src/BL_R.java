import java.util.ArrayList;
import java.util.Stack;

public class BL_R extends Heuristics{

	public Result alg(City city, double money, int source){
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
//			System.out.println("pop: " + father);
			ArrayList<Path> neighbors = PathTools.get_neighbors(father, money);
//			System.out.println("neighbors: " + neighbors);
			// result update when father enter the stack. if father optimal now, result_fail == father_fail.
			if(neighbors.isEmpty() && result.getFailProbabilitie() == father.getFailProbabilitie()){
				threshold = Math.min(threshold, father.getLength());
//				System.out.println("threshold now : " + threshold);
				stack.removeIf(path -> path.getLength() > father.getLength());
			}
			for(Path neighbor : neighbors){
				if(neighbor.getLength() <= threshold){
					stack.push(neighbor);
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
