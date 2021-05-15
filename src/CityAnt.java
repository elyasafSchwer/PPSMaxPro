import java.util.HashMap;
import java.util.Map;

import org.jgrapht.graph.DefaultWeightedEdge;

public class CityAnt extends City {
	HashMap<DefaultWeightedEdge, Double> ants;
	
	public CityAnt(City city) {
		super(city);
		this.ants = new HashMap<DefaultWeightedEdge, Double>();
		for(DefaultWeightedEdge e : city.getGraph().edgeSet()){
			this.ants.put(e, 1.0);
		}
	}
	public double getAntOf(DefaultWeightedEdge e){
		return ants.get(e);
	}
	public void updateAnts(){
		for(Map.Entry<DefaultWeightedEdge, Double> entry : ants.entrySet()) {
			entry.setValue(entry.getValue() - 0.05);
		}
	}
	public void updateAntsByPath(AntPath result) {
		double all_length_div_all_weight = result.getLength() / result.getWeight();
		for(DefaultWeightedEdge e : result.getEdgeList()){
			ants.put(e, result.getGraph().getEdgeWeight(e) * all_length_div_all_weight);
		}
	}
}
