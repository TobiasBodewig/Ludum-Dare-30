package game.main;

public class Monitoring {

	public final static boolean DISPLAY = false;

	public static void tick() {
		print();
	}

	private static void print() {
		if (DISPLAY) {
			System.out.println("################################");
			// System.out.println("FPS: \t\t\t" + FpsManager.getFps());
			System.out.println("Drawing time:\t\t" + String.format("%02d", draw) + " ms");
			System.out.println("Physics time:\t\t" + String.format("%02d", physics) + " ms");
			System.out.println("Physics-Sleep:\t\t" + String.format("%02d", physicsSleep) + " ms");
			System.out.println("Sleep time:\t\t" + String.format("%02d", sleep) + " ms");
			System.out.println("################################\n\n\n");
		}
	}

	/**
	 * Sleep
	 */
	private static long sleepStart = 0;
	private static long sleepStop = 0;
	private static long sleep = 0;

	public static void startSleep() {
		sleepStart = System.currentTimeMillis();
	}

	public static void stopSleep() {
		sleepStop = System.currentTimeMillis();
		sleep = sleepStop - sleepStart;
	}

	/**
	 * Draw
	 */
	private static long drawStart = 0;
	private static long drawStop = 0;
	private static long draw = 0;

	public static void startDraw() {
		drawStart = System.currentTimeMillis();
	}

	public static void stopDraw() {
		drawStop = System.currentTimeMillis();
		draw = drawStop - drawStart;
	}

	/**
	 * Physics
	 */
	private static long physicsStart = 0;
	private static long physicsStop = 0;
	private static long physics = 0;
	private static long physicsSleep = 0;

	public static void startPhysics() {
		physicsStart = System.currentTimeMillis();
		physicsSleep = physicsStart - physicsStop;
	}

	public static void stopPhysics() {
		physicsStop = System.currentTimeMillis();
		physics = physicsStop - physicsStart;
	}
}
