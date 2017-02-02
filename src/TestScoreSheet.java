import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import java.util.LinkedList;

public class TestScoreSheet{

	ScoreSheet s = new ScoreSheet();

	@Test
	public void testOneThrow(){
		//frame 1
		s.scoreThrow(5);
		assertTrue (s.currentFrame.frameScore == 5);
		assertTrue(s.score == 5);
	}

	@Test
	public void testTwoThrows(){
		//frame 1
		s.scoreThrow(3);
		//check score for this frame
		assertTrue(s.currentFrame.frameScore == 8);
		//ScoreSheet needs an overall score var
		assertTrue(s.score == 8);
	}
	
	@Test
	public void testThreeThrows(){
		//frame 2
		s.scoreThrow(7);
		//score for this frame is 7
		assertTrue(s.currentFrame.frameScore == 7);
		//score for the game is 15
		assertTrue(s.score == 15);
	}
	
	@Test
	public void testSpareCountsNextFrameScore(){
		
		//frame 2, a spare is thrown
		s.scoreThrow(3);
		
		//we have decided that until the next frame finishes, the score remains 10
		assertTrue(s.currentFrame.frameScore == 10);
		//since we just give 10 at first the overall score is 18
		assertTrue(s.score == 18);
				
		//frame 3
		//start the next frame
		s.scoreThrow(4);
		s.scoreThrow(1);
		
		//score for spare frame should be updated 
		//UPDATE: per the directions spare frame is updated with the ENTIRE FRAMES next score not just the next throw
		assertTrue(s.frames.get(s.currentFrameIndex-1).frameScore == 15);
		//make sure that the current frame score is still 5
		assertTrue(s.currentFrame.frameScore == 5);
		
		//overall score should be updated to reflect the spare 
		//+5 for the next frame and +5 added to the spare
		assertTrue(s.score == 28);
	}

	@Test
	public void testStrikeMovesToNextFrame(){
	//frame 4, a strike is thrown
	s.scoreThrow(10);
	
	//checking that after a strike is thrown, the second throw is not given 
	//by checking current frame is increased to 5 
	assertTrue(s.currentFrameIndex == 5);
	
	}
	
	@Test
	public void testStrikeCountsNextFrameScores(){
		//frame 5
		s.scoreThrow(5);
		s.scoreThrow(4);
		
		//frame 6
		s.scoreThrow(1);
		s.scoreThrow(1);
		
		//per the directions, counting the next two frames scores instead of just 1
		
		//test the previous frame, make sure 11 was added to the strike frame 
		assertTrue(s.frames.get(s.currentFrameIndex-1).frameScore == 21);
		
		//overall score is now 60 to account for 28 (prev total score) + 21 (strike frame score)+ 11 (next two frames) 
		assertTrue(s.score == 60);
	}

	@Test
	public void testSpareOnLastFrame(){		
		//frame 7
		s.scoreThrow(1);
		s.scoreThrow(1);
		
		//frame 8
		s.scoreThrow(1);
		s.scoreThrow(1);
		
		//frame 9
		s.scoreThrow(1);
		s.scoreThrow(1);
		
		//frame 10
		//spare is just treated as a score of 10 since there are no more frames
		s.scoreThrow(5);
		s.scoreThrow(5);
		
		//TODO change to reflect actual expected score
		assertTrue(s.score == 76);
		
	}
	
	//new game
	ScoreSheet s2 = new ScoreSheet();
	@Test
	public void testStrikeOnLastFrames(){
		//test throwing a strike on the 8th, 9th, and 10th frames
		//1
		s.scoreThrow(1);
		s.scoreThrow(1);
		//2
		s.scoreThrow(1);
		s.scoreThrow(1);
		//3
		s.scoreThrow(1);
		s.scoreThrow(1);
		//4
		s.scoreThrow(1);
		s.scoreThrow(1);
		//5
		s.scoreThrow(1);
		s.scoreThrow(1);
		//6
		s.scoreThrow(1);
		s.scoreThrow(1);
		//7
		s.scoreThrow(1);
		s.scoreThrow(1);
		//8, strike
		s.scoreThrow(10);
	
		//9, strike
		s.scoreThrow(10);

		//10 strike
		s.scoreThrow(10);

		
	}

	@Test
	public void testThrowOn11thFrame(){
		//ensure throwing on the 11th frame is not allowed (in some form)
	}

}
