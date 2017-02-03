import java.awt.Frame;
import java.util.LinkedList;


public class Main {
	LinkedList<Frame> frames = new LinkedList<Frame>();{
		for (int x = 0; x < 10; x++){
			frames.add(new Frame());
		}
	}
}
