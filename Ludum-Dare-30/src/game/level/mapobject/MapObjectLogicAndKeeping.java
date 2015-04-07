package game.level.mapobject;

import java.beans.Transient;

import game.level.LevelMap;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MapObjectLogicAndKeeping extends MapObjectLogic {

	private int[] in;
	private boolean islocked;

	public MapObjectLogicAndKeeping(int id, int targetID, boolean inverted, boolean power, int... in) {
		super(id, targetID, inverted, power);
		this.in = in;
		this.name = "& K";
	}

	public MapObjectLogicAndKeeping() {
		in = new int[0];
		this.name = "& K";
	}

	public boolean update(LevelMap map) {
		boolean b = true;
		for (int i = 0; i < in.length; i++) {
			if (!map.getMapObject(in[i]).getPower()) {
				b = false;
			}
		}
		if (b) {
			islocked = true;
		}
		return islocked;
	}

	public int inputCount() {
		return in.length;
	}

	@Transient
	public int[] getInputs() {
		return in;
	}

	public boolean moreInputs() {
		return true;
	}

	public void setInput(int index, int id) {
		if (index == in.length) {
			int[] newIn = new int[in.length + 1];
			for (int i = 0; i < in.length; i++) {
				newIn[i] = in[i];
			}
			newIn[in.length] = id;
			in = newIn;
		} else if (id == 0 || id == -1) {
			int[] newIn = new int[in.length - 1];
			for (int i = 0; i < newIn.length; i++) {
				newIn[i] = i < index ? in[i] : in[i + 1];
			}
			in = newIn;
		} else {
			in[index] = id;
		}
	}

	public int[] getIn() {
		return in;
	}

	public void setIn(int[] in) {
		this.in = in;
	}

}
