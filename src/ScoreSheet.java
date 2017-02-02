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
			for (int x = 0; x < 12; x++){ // 12 FRAMES BECAUSE WE NEED TO HANDLE A 10th FRAME STRIKE.
				frames.add(new Frame());
			}
		}
		currentFrameIndex = 0;
	}
	
	// Calling this method throws the ball once.
	// Starts at the first frame, first throw. Calling this method multiple times will move to different frames.
	public void scoreThrow(int knockedDownPins){
		
		// make sure the current frame is the one we want to be modifying
		currentFrame = frames.get(currentFrameIndex);
		currentFrame.throwBall(knockedDownPins);
		
		if (currentFrame.state == 3){ // a strike
			// score
			currentFrameIndex++;
			return;
			
		} else if (currentFrame.throwNum == 2){ // is a second throw
			currentFrameIndex++;
		}
		
		
		// call the score method
		scoreCalculator();
	}
	
	// Calculates the current score
	public int scoreCalculator(){

		switch(currentFrame.state){
		case -1:
			System.out.println("Error -- state = -1");
			break;
		case 0:
			System.out.println("pending");
			break;
		case 1:
			currentFrame.frameScore = currentFrame.throw1 + currentFrame.throw2;
			break;
		
		// Spare: The spare's frame's score = following frame's score + 10
		case 2:
			currentFrame.frameScore = 
				currentFrame.throw1 + currentFrame.throw2 + frames.get(currentFrameIndex+1).frameScore;
			break;
			
		// Strike: The strike's frame's score (ex. "frame 1") = frame 2's score + frame 3's score + 10;
		case 3:
			currentFrame.frameScore = 
					currentFrame.throw1 + currentFrame.throw2 + 
					frames.get(currentFrameIndex+1).frameScore + frames.get(currentFrameIndex+2).frameScore;
			break;
		default:
			System.out.println("PROBLEM: state < -1 || state > 3");
			break;
		}
		
		
		currentFrame.frameScore = currentFrame.throw1 + currentFrame.throw2;
		
		// iteration of the frames
		currentFrameIndex++;
		
		return 0;
		
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


