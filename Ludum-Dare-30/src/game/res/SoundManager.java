package game.res;

import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.Clip;

import util.log.Log;

public class SoundManager {

	private SoundManager() {

	}

	private static Map<String, Clip> staticClips;
	private static Map<String, Clip> cacheClips;

	private static String soundPath;

	public static void init() {
		staticClips = new HashMap<>();
		cacheClips = new HashMap<>();
		soundPath = "/music/";
	}

	public static void loadStaticClips() {
		// loadStaticClip("hit", "hit.wav");
	}

	public static void loadStaticClip(String id, String path) {
		staticClips.put(id, loadClip(id, path));
	}

	public static void loadClipInCache(String id, String path) {
		cacheClips.put(id, loadClip(id, path));
	}

	private static Clip loadClip(String id, String path) {
		return ResourceManager.getClip(soundPath + path);
	}

	public static void reloadStaticClip(String id, String path) {
		stop(id);
		staticClips.get(id).close();
		loadStaticClip(id, path);
	}

	public static void clearCache() {
		for (String id : cacheClips.keySet()) {
			stop(id);
		}
		cacheClips.clear();
		Log.info(" Clearing cache.");
	}

	public static void play(String id) {
		play(id, false);
	}

	public static void play(String id, boolean looping) {
		Clip clip = null;
		if (staticClips.containsKey(id)) {
			clip = staticClips.get(id);
		} else if (cacheClips.containsKey(id)) {
			clip = cacheClips.get(id);
		} else {
			Log.warning("Try playing unloaded clip: " + id);
		}
		if (clip != null) {
			clip.stop();
			clip.setFramePosition(0);
			if (looping) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			clip.start();
		}
	}

	public static void stop(String id) {
		if (staticClips.containsKey(id)) {
			staticClips.get(id).stop();
		} else if (cacheClips.containsKey(id)) {
			cacheClips.get(id).stop();
		} else {
			Log.warning("Try stoping unloaded clip: " + id);
		}
	}

	public static void stopAll() {
		for (String id : staticClips.keySet()) {
			stop(id);
		}
		for (String id : cacheClips.keySet()) {
			stop(id);
		}
	}

	public static void close() {
		clearCache();
		for (String id : staticClips.keySet()) {
			stop(id);
			staticClips.get(id).close();
		}
		staticClips.clear();
	}

	public static boolean isLoaded(String id) {
		if (staticClips.containsKey(id)) {
			return true;
		} else if (cacheClips.containsKey(id)) {
			return true;
		}
		return false;
	}
}
