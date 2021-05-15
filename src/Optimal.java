import java.util.ArrayList;
import java.util.Stack;

public class Optimal extends Heuristics{

	public Result alg(City city, double money, int source){
		Timer timer = new Timer();
		Path result = new Path(city, source, money);
		Stack<Path> stack = new Stack<Path>();
		stack.push(result);
		while(!stack.isEmpty()){
			if(timer.timeOut()) {
				return null;
			}
			Path father = stack.pop();
//			System.out.println("pop: " + father);
			ArrayList<Path> neighbors = PathTools.get_neighbors(father, money);
//			System.out.println("neighbors :" + neighbors);
			for(Path path : neighbors){
				stack.push(path);
				if(path.getFailProbabilitie() < result.getFailProbabilitie()){
//					System.out.println("result now :" + path);
					result = path;
				}
			}
		}
		return new Result(result.getFailProbabilitie(), timer);
	}

}
