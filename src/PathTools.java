import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class PathTools {
	public static ArrayList<Path> get_neighbors(Path correct_path, double money){
		HashSet<Integer> neighbors_index = correct_path.getNeighbors();
		ArrayList<Path> result = new ArrayList<Path>();
		for (int i : neighbors_index) {
			result.add(new Path(correct_path, i, money));
		}
		remove_all_not_optimal_path(correct_path.getCity(), correct_path, result);
		remove_all_over_cost_path(result, money);
		return result;
	}
	
	public static ArrayList<Path> get_no_back_neighbors(Path correct_path, double money){
		ArrayList<Path> result = get_neighbors(correct_path, money);
		result.forEach(p -> p.remove_back_neighbors());
		return result;
	}
	
	public static void remove_all_not_optimal_path(City city, Path father, List<? extends Path> list){
		Iterator<? extends Path> it = list.iterator();
		while (it.hasNext()) {
			Path path = it.next();
			if(path.getWeight() > father.getWeight() + city.getOptimalDist(father.getEndVertex(), path.getEndVertex())){
				it.remove();
			}
		}
	}
	
	public static void remove_all_over_cost_path(List<? extends Path> neighbors, double money) {
		neighbors.removeIf(path->path.getWeight() > money);
	}
	
	public static ArrayList<AntPath> get_neighbors(CityAnt city_ant, AntPath correct_path, double money){
		HashSet<Integer> neighbors_index = correct_path.getNeighbors();
		ArrayList<AntPath> result = new ArrayList<AntPath>();
		for (int i : neighbors_index) {
			result.add(new AntPath(correct_path, city_ant, i, money));
		}
		remove_all_not_optimal_path(correct_path.getCity(), correct_path, result);
		remove_all_over_cost_path(result, money);
		return result;
	}

}
