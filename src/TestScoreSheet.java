import org.junit.*;

import static org.junit.Assert.*;

import java.util.LinkedList;

public class TestScoreSheet{

	
	ScoreSheet s = new ScoreSheet();

	@Test
	public void testOneThrow(){
		//frame 1
		s.scoreThrow(5);
		assertTrue(s.score == 5);
	}

	@Test
	public void testTwoThrows(){
		//frame 1 throw 1
		s.scoreThrow(3);
		//frame 1 throw 2
		s.scoreThrow(5);
		//ScoreSheet needs an overall score var
		assertTrue(s.score == 8);
	}
	
	@Test
	public void testThreeThrows(){
		//frame 1 first throw
		s.scoreThrow(7);
		//frame 1 second throw
		s.scoreThrow(2);
		// frame 2 first throw
		s.scoreThrow(6);
		//score for the game is 15
		assertTrue(s.score == 15);
	}
	
	@Test
	public void testSpareCountsNextFrameScore(){
		
		//frame 2, a spare is thrown
		s.scoreThrow(3);
		s.scoreThrow(7);
		
		//we have decided that until the next frame finishes, the score remains 10
		assertTrue(s.score == 10);
				
		//frame 3
		//start the next frame
		s.scoreThrow(4);
		s.scoreThrow(1);
		
		//score for spare frame should be updated 
		//UPDATE: per the directions spare frame is updated with the ENTIRE FRAMES next score not just the next throw
		assertEquals(20, s.score);
	}

	@Test
	public void testStrikeMovesToNextFrame(){
	//frame 4, a strike is thrown
	s.scoreThrow(10);
	
	//checking that after a strike is thrown, the second throw is not given 
	//by checking current frame is increased to 5 
	assertTrue(s.currentFrameIndex == 1);
	
	}
	
	@Test
	public void testStrikeCountsNextFrameScores(){
		//frame 1
		s.scoreThrow(10);
		
		//frame 2
		s.scoreThrow(5);
		s.scoreThrow(4);
		
		//frame 3
		s.scoreThrow(1);
		s.scoreThrow(1);
		
		//overall score is now 32 to account for (10 + 9 + 2) + 9 + 2
		assertTrue(s.score == 32);
	}

	@Test
	public void testSpareOnLastFrame(){		
		//frame 1
		s.scoreThrow(1);
		s.scoreThrow(1);
		
		//frame 2
		s.scoreThrow(1);
		s.scoreThrow(1);
		
		//frame 3
		s.scoreThrow(1);
		s.scoreThrow(1);
				
		//frame 4
		s.scoreThrow(1);
		s.scoreThrow(1);
		
		//frame 5
		s.scoreThrow(1);
		s.scoreThrow(1);
		
		//frame 6
		s.scoreThrow(1);
		s.scoreThrow(1);
		
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
		
		//score
		assertTrue(s.score == 28);
		
	}
	
	//new game
	ScoreSheet s2 = new ScoreSheet();
	@Test
	public void testStrikeOnLastFrames(){
		//test throwing a strike on the 8th, 9th, and 10th frames
		// frame 1
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
		s.scoreThrow(2);
		
		//score = 15
		
		//8, strike, score = tentatively 10, overall 25
		//are we counting the next two frames as 30 and therefore the frame gets 40?
		//overall 55?
		s.scoreThrow(10);
	
		//9, strike, tentatively 10, overall 65?
		//after next frame the score becomes 20 because there is only one left?
		//overall 75?
		s.scoreThrow(10);

		//10 strike
		//since it is the last frame does not get any bonus?
		//overall 85
		s.scoreThrow(10);
		
		//game score
		assertTrue(s.score == 75);
		//frame 9
		assertTrue(s.frames.get(8).frameScore == 20);
		//frame 8
		assertTrue(s.frames.get(7).frameScore == 30);

		
	}

	@Test
	public void testThrowOn11thFrame(){
		//ensure throwing on the 11th frame is not allowed (in some form)
		try {
			s.scoreThrow(10); // 1st
			s.scoreThrow(10); // 2nd
			s.scoreThrow(10); // 3rd
			s.scoreThrow(10); // 4th
			s.scoreThrow(10); // 5th
			s.scoreThrow(10); // 6th
			s.scoreThrow(10); // 7th
			s.scoreThrow(10); // 8th
			s.scoreThrow(10); // 9th
			s.scoreThrow(10); // 10th
			s.scoreThrow(10); // 11th frame throw - should raise error
			assertFalse("scoreThrow should have raised error",true);
		} catch (RuntimeException e) {
			assertTrue("exception of wrong type: " + e.getClass().getName(), e instanceof IllegalStateException);
		}		
		
	}

}
