import java.util.LinkedList;

//-1=error, 0=pending, 1=normal, 2=spare, 3=strike

public class ScoreSheet {
	public int score; // total score of the game
	public int currentFrameIndex; // index of the current frame, alongside 'frames'
	public Frame currentFrame;
	public LinkedList<Frame> frames; // list of frames
	
	// Constructor: When the scoresheet is first created, these variables are set.
	public ScoreSheet() {
		System.out.println("\n=CREATING NEW SCORESHEET=");
		score = 0;
		
		// Take note of the variable's values that the frame constructor sets.
		frames = new LinkedList<Frame>();{
			System.out.print("INIT: Creating frames... ");
			for (int x = 0; x < 12; x++){ // 12 FRAMES BECAUSE WE NEED TO HANDLE A 10th FRAME STRIKE. Just roll with it.
				System.out.print(x + ", ");
				frames.add(new Frame());
			}
			System.out.println();
		}
		currentFrameIndex = 0;
	}
	
	// Calling this method throws the ball once.
	// First time it is called, it throws at the first frame, frame's first throw. 
	// Calling this method multiple times will move to different frames and throws.
	public void scoreThrow(int knockedDownPins){
		if (currentFrameIndex >= 10) throw new IllegalStateException();
		System.out.println("Throwing ball, knocks down " + knockedDownPins + " pins");
		// make sure the current frame is the one we want to be modifying
		currentFrame = frames.get(currentFrameIndex);
		
		// Throw the ball down the lane.
		currentFrame.throwBall(knockedDownPins);
		
		
		// a strike
		if (currentFrame.state == 3){ 
			currentFrame = frames.get(++currentFrameIndex);
			scoreUpdater();
			// Because the score is updated sequentially,
			// we need to call this twice for strikes/spares to be calculated properly.
			scoreUpdater();
			
		// a spare; and assumed to be a second throw	
		} else if (currentFrame.state == 2){ 
			currentFrame = frames.get(++currentFrameIndex);
			scoreUpdater();
			scoreUpdater();
		
		// Second throw of a 'normal frame' (ie not a strike, spare, or error)
		} else if (currentFrame.state == 1){ 
			currentFrame = frames.get(++currentFrameIndex);
			scoreUpdater();
			scoreUpdater();
		
		// First throw of a 'normal frame'. <10 pins are knocked down.
		} else if (currentFrame.state == 0) { 
			scoreUpdater();

		// -1 or other	
		} else {
			System.out.println("We got problems");
		}
		
		
		// call the score method
		
	}
	
	// Calculates the current score, and moves the currentFrame and currentFrameIndex to the next frame.
	private void scoreUpdater(){
		
		
		int totalScore = 0; // total score. Variable may be able to be consolidated.
		int frameIndex = 0; // Index of the frame f 
		for (Frame f : frames){
			
			switch(f.state){
			case -1:
				System.out.println("Error -- state == -1");
				break;
			// First throw, not a strike or error.
			case 0:
				f.frameScore = f.throw1 + f.throw2;
				break;
				
			// Second throw, not a spare or error
			case 1:
				f.frameScore = f.throw1 + f.throw2;
				break;
			
			// Spare: The spare's frame's score = following frame's score + 10
			case 2:
				// TODO: Hardcode scoring on 10th frame, then change frame count
				f.frameScore = 
					10 + frames.get(frameIndex+1).frameScore;
				break;
				
			// Strike: The strike's frame's score (ex. "frame 1") = frame 2's score + frame 3's score + 10;
			case 3:
				// TODO: Hardcode scoring on 9th and 10th frames, then change frame count
				f.frameScore =
					10 + 
					frames.get(frameIndex+1).frameScore + frames.get(frameIndex+2).frameScore;
				
				if (f.frameScore >= 30) f.frameScore = 30; //terrible, but our logic was recursively adding the strikes too much
				
				break;
			default:
				break;
			}
			
			System.out.println("UPDATE: INDX [" + frameIndex + "] ST [" + f.state + 
					"] FRMSCR [" + f.frameScore + "]");
			
			// iteration of the frames
			frameIndex++;
			totalScore += f.frameScore;
		}
		
		score = totalScore;
		
	}
	
	
	
	// score should go in here :)
	public class Frame {
		public int frameScore; // set by the ScoreCalculator method
		public int pins;
		public int state;
		public int throwNum; // 1st or second throw?
		
		public int throw1;
		public int throw2;
	
		public Frame(){
			pins = 10;
			throwNum = 1;
			state = 0;
			frameScore = 0;
			throw1 = 0;
			throw2 = 0;
		}		
		
		public int throwBall(int pinsKnockedDown){				
			
			
			
			
			if (throwNum == 1){
				pins -= pinsKnockedDown;
				throwNum++;
				
				if (pins <= 0){
					throw1 = 10;
					return state = 3;
				} else { // not a strike
					throw1 += pinsKnockedDown;
					return state = 0;
				}
				
			} else if (throwNum == 2){
				if (state == 3){
					throw2 = 0; // This is making assumptions!
					return 3;
				}
				
				pins -= pinsKnockedDown;
				if (pins <= 0){ // spare
					throw2 += pinsKnockedDown;
					return state = 2;
				} else { // "normal"
					throw2 += pinsKnockedDown;
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


