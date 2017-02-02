import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;

import java.util.LinkedList;
import ScoreSheet.Frame;


public class TestScoreSheet{

	ScoreSheet s = new ScoreSheet();
	LinkedList<Frame> frames = s.frames;
	
	Frame f1 = frames.get(0);
	@Test
	public void testOneThrow(){
		
		int s1 = f1.throwBall(5);
		assertTrue(f1.score == 5);
		assertTrue(s.score == 5);
	}

	@Test
	public void testTwoThrows(){
		f1.throwBall(3);
		//check score for this frame
		assertTrue(f1.score == 8);
		//ScoreSheet needs an overall score var
		assertTrue(s.score == 8);
	}
	
	Frame f2 = frames.get(1);
	
	@Test
	public void testThreeThrows(){
		f2.throwBall(7);
		//score for this frame is 7
		assertTrue(f2.score == 7);
		//score for the game is 15
		assertTrue(s.score == 15);
	}

	Frame f3 = frames.get(2);
	
	@Test
	public void testSpareCountsNextFrameScore(){
		f2.throwBall(3);
		
		//this could be changed, not sure if we just add to the 10 later or if
		//it is just considered unknown until the next ball is thrown
		assertTrue(f2.score == 10);
		assertTrue(s.score == 18);
		
		//confirm that we are in a "spare" state
		assertTrue(f2.state == 2);
		
		//start the next frame
		f3.throwBall(4);
		
		//score for spare frame should be updated
		assertTrue(f2.score == 14);
		//score for this frame should still be 4
		assertTrue(f3.score == 4);
		
		//overall score should be updated to reflect the spare
		assertTrue(s.score == 26);
		f3.throwBall(4);
	}
	Frame f4 = frames.get(3);
	@Test
	public void testStrikeMovesToNextFrame(){
	f4.throwBall(10);
	assertTrue(f4.state == 3);
	//not sure how to test if a second throw is allowed... 
	//test if an exception is thrown if we try another throw?
	}
	Frame f5 = frames.get(4);
	@Test
	public void testStrikeCountsNextFrameScores(){
		f5.throwBall(5);
		f5.throwBall(4);
		assertTrue(f4.score == 19);
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
