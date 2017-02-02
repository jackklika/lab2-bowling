import java.util.LinkedList;


public class ScoreSheet {
	public int score;
	
	public ScoreSheet {
		LinkedList<Frame> frames = new LinkedList<Frame>();{
			for (int x = 0; x < 10; x++){
				frames.add(new Frame());
			}
		}
	}
	
	// At the beginning of each frame, 10 pins are reset. 
	// a frame may knock down a max of 10 pins
	
	public void throwBall(int knockedDownPins){
		// check what frame it's at
		
		// check the throws. If there was a first throw that was a strike, go to the next frame.
		//    go through the whole list
		// call the score method, go through all the frames
		
	}
	
	// Calculates the current score
	public int scoreCalculator(){
		
	}
	
	
	
	
	public class Frame {
		public int frameScore;
		public int pins;
		public int state;
		
		public Frame(){
			pins = 10;
			
		}
		
		public class tThrow {
			
			public int throwNum;
			
			// num is which throw it is, ie 1 or 2
			public tThrow(){
				throwNum = 1;
				state = 0;
			}
			
			public int throwBall(int pinsKnockedDown){				
				pins -= pinsKnockedDown;
				if (throwNum == 1){
					if (pins <= 0){
						state = 3;
						return 3;
					} else {
						state = 0;
						return 0;
					}
				} else if (throwNum == 2){
					if (pins <= 0){
						state = 2;
						return 2;
					} else {
						state = 1;
						return 1;
					}
					
				} else {
					System.out.println("Error: throwNum != 1 || 2");
					state = -1;
					return -1; // returns the state of the throw --
					//-1=error, 0=pending, 1=normal, 2=spare, 3=strike
				}
				
				
			}
			
		}
		
	}

	
}


