package resurse;

import org.lwjgl.Sys;

public class Timer {

	public static boolean pause = false;
	public static long lFrame, totalTime;
	public static float d = 0, multi = 1;

	public static long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

	public static float getDelta() {
		long time = getTime();
		int delta = (int) (time - lFrame);
		lFrame = getTime();
		return delta * 0.002f;
	}

	public static float Delta() {
		if (pause)
			return 0;
		else
			return d * multi;
	}

	public static float TotalTime() {
		return totalTime;
	}

	public static float Multi() {
		return multi;
	}

	public static void update() {
		d = getDelta();
		totalTime += d;
	}

	public static void newMulti(int s) {
		multi += s;
	}

	public static void Pause() {
		if (pause)
			pause = false;
		else
			pause = true;
	}
}
