import java.util.LinkedList;

//-1=error, 0=pending, 1=normal, 2=spare, 3=strike

public class ScoreSheet {
	public int score; // total score of the game
	public int currentFrameIndex; // index of the current frame, alongside 'frames'
	public Frame currentFrame;
	public LinkedList<Frame> frames; // list of frames
	
	public ScoreSheet() {
		score = 0;
		frames = new LinkedList<Frame>();{
			for (int x = 0; x < 10; x++){
				frames.add(new Frame());
			}
		}
		currentFrameIndex = 1;	
	}
	
	// At the beginning of each frame, 10 pins are reset. 
	// a frame may knock down a max of 10 pins
	
	// Should only throw the ball;
	public void scoreThrow(int knockedDownPins){
		
		
		// check the throws. If there was a first throw that was a strike, go to the next frame.
		currentFrame = frames.get(currentFrameIndex);
		
		if (currentFrame.throwBall(knockedDownPins) == 3){ // is it a strike?
			// score
			currentFrameIndex++;
			return;
		} else if (currentFrame.throwNum == 2){
			currentFrameIndex++;			
		}
		
		
		// call the score method
		scoreCalculator();
	}
	
	// Calculates the current score
	public int scoreCalculator(){
		
		// iteration of the frames
		
		return 0;
		
	}
	
	
	
	
	public class Frame {
		public int frameScore;
		public int pins;
		public int state;
		public int throwNum;
		
		public Frame(){
			pins = 10;
			throwNum = 1;
			state = 0;
		}		
		
		public int throwBall(int pinsKnockedDown){				
			pins -= pinsKnockedDown;
			if (throwNum == 1){
				if (pins <= 0){
					return state = 3;
				} else { // not a strike
					throwNum++;
					return state = 0;
				}
			} else if (throwNum == 2){
				if (pins <= 0){
					return state = 2;
				} else {
					return state = 1;
				}
				
			} else {
				System.out.println("Error: throwNum != 1 || 2");
				return state = -1; // returns the state of the throw --
				//-1=error, 0=pending, 1=normal, 2=spare, 3=strike
			}
			
				
			
			
		}
		
	}

	
}


