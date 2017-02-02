import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import java.util.LinkedList;

public class TestScoreSheet{

	ScoreSheet s = new ScoreSheet();

	@Test
	public void testOneThrow(){
		s.scoreThrow(5);
		assertTrue (s.currentFrame.frameScore == 5);
		assertTrue(s.score == 5);
	}

	@Test
	public void testTwoThrows(){
		s.scoreThrow(3);
		//check score for this frame
		assertTrue(s.currentFrame.frameScore == 8);
		//ScoreSheet needs an overall score var
		assertTrue(s.score == 8);
	}
	
	@Test
	public void testThreeThrows(){
		s.scoreThrow(7);
		//score for this frame is 7
		assertTrue(s.currentFrame.frameScore == 7);
		//score for the game is 15
		assertTrue(s.score == 15);
	}
	
	@Test
	public void testSpareCountsNextFrameScore(){
		s.scoreThrow(3);
		
		//this could be changed, not sure if we just add to the 10 later or if
		//it is just considered unknown until the next ball is thrown
		assertTrue(s.currentFrame.frameScore == 10);
		assertTrue(s.score == 18);
		
		//confirm that we are in a "spare" state
		assertTrue(s.currentFrame.frameScore == 2);
		
		//start the next frame
		s.scoreThrow(4);
		
		//score for spare frame should be updated
		//TODO
		assertTrue(s.currentFrame-1.frameScore == 14);
		//score for this frame should still be 4
		assertTrue(s.currentFrame.frameScore == 4);
		
		//overall score should be updated to reflect the spare
		assertTrue(s.score == 26);
		s.scoreThrow(4);
	}

	@Test
	public void testStrikeMovesToNextFrame(){
	s.scoreThrow(10);
	//current frame is 4
	assertTrue(s.currentFrame.frameScore == 3);
	//not sure how to test if a second throw is allowed... 
	//test if an exception is thrown if we try another throw?
	}
	
	@Test
	public void testStrikeCountsNextFrameScores(){
		//frame 5
		s.scoreThrow(5);
		s.scoreThrow(4);
		
		//TODO test the previous frame, make sure 9 was added to the strike frame 
		assertTrue(s.currentFrame-1.frameScore == 19);
		assertTrue(s.score == 58);
	}

	@Test
	public void testSpareOnLastFrame(){
		//is the expected result that we are given another throw
		//or that it is just treated as a score of 10?
		
	}
	
	@Test
	public void testStrikeOnLastFrames(){
		//test throwing a strike on the 8th, 9th, and 10th frames
		
	}

	@Test
	public void testThrowOn11thFrame(){
		//ensure throwing on the 11th frame is not allowed (in some form)
	}

}
