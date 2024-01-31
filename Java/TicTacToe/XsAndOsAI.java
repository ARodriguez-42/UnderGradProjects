public class XsAndOsAI extends XsAndOs {

	public XsAndOsAI(){
		super();
	}
/** The heuristic evaluation function for the current board
 * @Return +100, +10, +1 for EACH 3-, 2-, 1-in-a-line for computer.
 * -100, -10, -1 for EACH 3-, 2-, 1-in-a-line for opponent.
 * 0 otherwise
 */
	public int evaluate(char p) {
		int score = 0;
		// Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
		score += evaluateLine(p, 0, 0, 0, 1, 0, 2); // row 0 				[0][0], [0][1], [0][2]
		score += evaluateLine(p, 1, 0, 1, 1, 1, 2); // row 1 				[1][0], [1][1], [1][2]
		score += evaluateLine(p, 2, 0, 2, 1, 2, 2); // row 2 				[2][0], [2][1], [2][2]
		score += evaluateLine(p, 0, 0, 1, 0, 2, 0); // col 0 				[0][0], [1][0], [2][0]
		score += evaluateLine(p, 0, 1, 1, 1, 2, 1); // col 1 				[0][1], [1][1], [2][1]
		score += evaluateLine(p, 0, 2, 1, 2, 2, 2); // col 2 				[0][2], [1][2], [2][2]
		score += evaluateLine(p, 0, 0, 1, 1, 2, 2); // diagonal				[0][0], [1][1], [2][2]
		score += evaluateLine(p, 0, 2, 1, 1, 2, 0); // alternate diagonal	[0][2], [1][1], [2][0]
		return score;
	}
 /**
 * The heuristic evaluation function for the given line of 3 cells
 * @Return +100, +10, +1 for 3-, 2-, 1-in-a-line for current player.
 * -100, -10, -1 for 3-, 2-, 1-in-a-line for opponent.
 * 0 otherwise
 */
	private int evaluateLine(char p, int row1, int col1, int row2, int col2, int row3, int col3) {
		int score = 0;
		char myMarck = (p == 'X') ? 'X' : 'O'; // if p==X return X else return O
		char opMark = (p == 'X') ? 'O' : 'X';

		// First cell
		if (board[row1][col1] == myMarck) {
			score = 1;
		} 
		else if (board[row1][col1] == opMark) {
			score = -1;
		}

		// Second cell
		if (board[row2][col2] == myMarck) {
			if (score == 1) { // cell1 is myMarck
				score = 10;
			} 
			else if (score == -1) { // cell1 is opMark
				return 0;
			} 
			else { // cell1 is empty
				score = 1;
			}
		} 
		else if (board[row2][col2] == opMark) {
			if (score == -1) { // cell1 is opMark
				score = -10;
			} 
			else if (score == 1) { // cell1 is myMarck
				return 0;
			} 
			else { // cell1 is empty
				score = -1;
			}
		}

		// Third cell
		if (board[row3][col3] == myMarck) {
			if (score > 0) { // cell1 and/or cell2 is myMarck
				score *= 10;
			} 
			else if (score < 0) { // cell1 and/or cell2 is opMark
				return 0;
			} 
			else { // cell1 and cell2 are empty
				score = 1;
			}
		} 
		else if (board[row3][col3] == opMark) {
			if (score < 0) { // cell1 and/or cell2 is opMark
				score *= 10;
			} 
			else if (score > 1) { // cell1 and/or cell2 is myMarck
				return 0;
			} 
			else { // cell1 and cell2 are empty
				score = -1;
			}
		}
		return score;
	}
 /** iteratively score for all possible moves (cells with ' '))
 * with method evaluate; storing all scores in a int [][] array
 * then search though that array for the best move
 * use the row and colum of best score from the array
 * and call playMove(p,r,c)
*/
	public void AI_Move(char p) {
		int best = -999; // best is best move found so far - start with really low value
		int bestr = 0; // row for current result
		int bestc = 0; // col for current result
		int scores [][] = new int[3][3];			//array to hold scores for each move 
		for ( int a = 0; a < 3; a++ ){               // set all scores for each postion to zero
			for ( int b = 0; b < 3; b++ ){
				scores [a][b] = -10000;
			}
		}
		// score all posible moves
		//evaluate(p);
		for ( int r = 0; r < 3; r++ ){               //rows 
			for ( int c = 0; c<3; c++ ){		//columns 
				if (board[r][c] == ' '){
					scores [r][c] = evaluate(p);
				}
			}
		}
	// for ( . . for( . . . if( . . .
		for ( int g = 0; g < 3; g ++ ){               
			for ( int h = 0; h < 3; h++ ){
				if (scores[g][h] > best){
					best = scores[g][h];
					bestr = g;
					bestc = h;
				}
			}
		}
					
	// find best move
	// for ( . . for( . . . if( . . .

	// move
		playMove(p, bestr, bestc);
	}
}