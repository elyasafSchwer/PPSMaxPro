import java.util.ArrayList;

public class ACO extends Heuristics{
	
	public Result alg(City city, double money, int source){
		Timer timer = new Timer();
		CityAnt city_ant = new CityAnt(city);
		AntPath result = iteration(1, city_ant, money, source);
		for (int i = 2; i <= 50; i++) {
			if(timer.timeOut()) {
				return null;
			}
			AntPath new_path = iteration(i, city_ant, money, source);
			city_ant.updateAnts(); // ant-- for each ant
			if(result.getFailProbabilitie() > new_path.getFailProbabilitie()){
				result = new_path;
				city_ant.updateAntsByPath(result);
			}
		}
		return new Result(result.getFailProbabilitie(), timer);
	}
	
	private AntPath iteration(int i, CityAnt city_ant, double money, int source){
//		System.out.println("ITERATION " + i);
		AntPath new_path = new AntPath(city_ant, source, money);
//		System.out.println("start: " + new_path);
		ArrayList<AntPath> neighbors = PathTools.get_neighbors(city_ant, new_path, money);
		while(neighbors.size() > 0){
//			System.out.println("neighbors: " + neighbors);
			new_path = choose_path_acoording_spv_hbp(neighbors);
//			System.out.println("new_path: " + new_path);
			neighbors = PathTools.get_neighbors(city_ant, new_path, money);
		}
		return new_path;
	}
	
	private AntPath choose_path_acoording_spv_hbp(ArrayList<AntPath> neighbors) {
		double sum = 0;
		for (AntPath antPath : neighbors) {
			sum += antPath.getS_P_v() * antPath.getH_v_p();
			antPath.setValue(sum);
		}
		double choose = Math.random() * sum;
		for (AntPath antPath : neighbors) {
			if(choose <= antPath.getValue()){
				return antPath;
			}
		}
		return neighbors.get(neighbors.size() - 1);
	}

}
