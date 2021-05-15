
public class Timer {
	long startMilli;
	public static long LIMITMILISECOND = 480000;
//	public static long LIMITMILISECOND = 100;
	public Timer() {
		startMilli = System.currentTimeMillis();
	}
	public boolean timeOut() {
		return (System.currentTimeMillis() - startMilli) >= LIMITMILISECOND;
	}
	public long getTime() {
		return System.currentTimeMillis() - startMilli;
	}
}
