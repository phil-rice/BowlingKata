We are going to create an function which, given a valid sequence of rolls for one line of American Ten-Pin Bowling, produces the total score for the game. This story is picked because it's about the right size for a couple of hours of Test-Driven Development demonstration.

I'll briefly summarize the scoring for this form of bowling:

* Each game, or "line" of bowling, includes ten turns, or "frames" for the bowler.
* In each frame, the bowler gets up to two tries to knock down all the pins.
* If in two tries, he fails to knock them all down, his score for that frame is the total number of pins knocked down in his two tries.
* If in two tries he knocks them all down, this is called a "spare" and his score for the frame is ten plus the number of pins knocked down on his next throw (in his next turn).
* If on his first try in the frame he knocks down all the pins, this is called a "strike". His turn is over, and his score for the frame is ten plus the simple total of the pins knocked down in his next two rolls.
* If he gets a spare or strike in the last (tenth) frame, the bowler gets to throw one or two more bonus balls, respectively. These bonus throws are taken as part of the same turn. If the bonus throws knock down all the pins, the process does not repeat: the bonus throws are only used to calculate the score of the final frame.
* The game score is the total of all frame scores.

The input to the function is the list of 'how many pins I knocked down for this ball'. The input does not 'know' about frames. An example input would be List(7, 2, 5, 5, 3, 0, 10, 2, 4).

In this input
Frame 1 is (7,2)
Frame 2 is (5,5)
Frame 3 is (3,0)
Frame 4 is (10)
Frame 5 is (2, 4)