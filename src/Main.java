import java.util.LinkedList;

import ScoreSheet.Frame;


public class Main {
	LinkedList<Frame> frames = new LinkedList<Frame>();{
		for (int x = 0; x < 10; x++){
			frames.add(new Frame());
		}
	}
}
