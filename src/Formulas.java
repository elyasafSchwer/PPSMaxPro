
public class Formulas {
	
	public static double getLocalFailProbabilitie(double[][] store, double money){
		double result = 0;
		for (int i = 0; i < store[0].length; i++) {
			if(store[1][i] > money){
				result += store[0][i];
			}
		}
		return result;
	}
}
