
public class Result {
	private double success_probabilitie;
	private long time;
	public Result(double fail_probabilitie, Timer timer) {
		this.success_probabilitie = 1 - fail_probabilitie;
		this.time = timer.getTime();
	}
	public double getSuccessProbabilitie() {
		return success_probabilitie;
	}
	public long getTime() {
		return time;
	}
}
