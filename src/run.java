import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Vector;

public class run {

	public static Vector<Integer> getTestList(String test_file) throws IOException{
		Vector<Integer> result = new Vector<Integer>();
		FileReader fr = new FileReader(new File(test_file));
		BufferedReader br = new BufferedReader(fr);
		while(br.ready()){
			result.add(Integer.parseInt(br.readLine()));
		}
		br.close();
		fr.close();
		return result;
	}
	
	public static void printTestToFiles(City city, Vector<Integer> test, String alg_name, Heuristics heur) throws IOException{
		FileWriter fw_pro = new FileWriter(new File(alg_name + "_result_pro.csv"));
		FileWriter fw_time = new FileWriter(new File(alg_name + "_result_time.csv"));
		fw_pro.write(alg_name + "\n");
		fw_time.write(alg_name + "\n");
		
		HashSet<Integer> over_time_tests = new HashSet<Integer>();
		double cost = 7200;
		while(cost <= 7200){
			StringBuilder sb_pro = new StringBuilder(""+cost+",");
			StringBuilder sb_time = new StringBuilder(""+cost+",");
			for (Integer v : test) {
				Result result = null;
				if(!over_time_tests.contains(v)) {
					result = heur.alg(city, cost, v);
					if(result == null) {
						over_time_tests.add(v);
					}
				}
				sb_pro.append(((result == null) ? "null" : result.getSuccessProbabilitie()) +",");
				sb_time.append(((result == null) ? "null" : result.getTime()) +",");
			}
			fw_pro.write(sb_pro.toString() + "\n");
			fw_time.write(sb_time.toString() + "\n");
			cost += 100;
		}
		
		fw_pro.close();
		fw_time.close();
	}
	
	public static void main(String[] args) throws IOException {
		City city = new City("graph_with_distances.csv", "graph_with_probabilities_m1200.csv");
		Vector<Integer> test = getTestList("test100ver.csv");
		printTestToFiles(city, test, "GREEDY", new Greedy());
		printTestToFiles(city, test, "BL_G", new BL_G());
		
	}

}
