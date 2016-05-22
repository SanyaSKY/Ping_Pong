package gui;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Canvas;

/**
 * Class represents object, that save and load replay from file
 */
public class Replay {
	/** List of game states */
	private List<State> stateList = null;
	int listIndex = 0;

	public Replay() {
		stateList = new ArrayList<State>();
	}

	public void addState(int posYPadLeft, int posYPadRight, int posXBall, int posYBall, int scorePadLeft,
			int scorePadRight) {
		stateList.add(new State(posYPadLeft, posYPadRight, posXBall, posYBall, scorePadLeft, scorePadRight));
	}

	public void writeToFile() throws IOException {

		FileOutputStream fileOutputStream = new FileOutputStream("replays/last.rpl");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

		for (int i = 0; i < stateList.size(); ++i) {
			objectOutputStream.writeObject(stateList.get(i));
		}

		objectOutputStream.flush();
		objectOutputStream.close();
		fileOutputStream.flush();
		fileOutputStream.close();
	}

	public void readFromFile(String replayName) throws IOException, ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream("replays/" + replayName + ".rpl");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

		while (true) {
			State state;
			try {
				state = (State) objectInputStream.readObject();
			} catch (EOFException e) {
				break;
			}

			if (state == null) {
				break;
			}

			stateList.add(state);
		}

		objectInputStream.close();
		fileInputStream.close();
	}

	public void stopReplay() {
		stateList = new ArrayList<State>();
	}

	/**
	 * Controls game object using values from loaded list
	 */
	public void startReplay(Canvas canvas) {
		try {
			PaddleLeft.setPos(stateList.get(listIndex).getposYPadLeft());
			PaddleRight.setPos(stateList.get(listIndex).getposYPadRight());
			Ball.setPos(stateList.get(listIndex).getPosXBall(), stateList.get(listIndex).getPosYBall());
			PaddleLeft.setScore(stateList.get(listIndex).getscorePadLeft());
			PaddleRight.setScore(stateList.get(listIndex).getscorePadRight());
			listIndex++;
			// Force a redraw
			canvas.redraw();
		} catch (IndexOutOfBoundsException e) {
			return;
		}
	}
}