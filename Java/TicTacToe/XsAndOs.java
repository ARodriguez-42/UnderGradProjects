//
//    XsAndOs.java
//
// The orginal header :
//
// The Irish have a game called  (in Gallic) or Xs And Os for the rest of us.  
// The English call it , but you might know it by another name.   
// It is a game for two players, X and O, who take turns marking the spaces in a 3Ã—3 grid.
// The player who succeeds in placing three of their marks in a diagonal, horizontal, 
// or vertical row is the winner.   
//
// author: Malcolm
// creation date: Nov 2019
//
// The file has been corrupted, you need to rebuild it 
//       use the specifications given 
//       and recreate the missing/currupted code 

public class XsAndOs {
   // Instance Variables
    protected char[][] board;      		// 2-dimensional array for the board
    private int turns;				// after 9 turns the game must be over

   // Constructors
    public XsAndOs() {		
		board = new char[3][3];                   // create th borad
		turns = 0;                                // set turns to zero 

		for ( int r=0; r<3; r++ ){               // for three rows
			for ( int c=0; c<3; c++ ){              // for three coluns 
				board[r][c] = ' ';    
			}
		}
   }       

      // Accessor Methods					


      // Do we have a winner 
      // check for same char (p) in a row 
      // in a column or either diagonal
      // p is either 'O' or 'X'	  									
    public boolean isWinner( char p ) {				// Do we have a winner
	 // for each row 										
        for(int i = 0; i<3; i++){ 					// i	check each row 
            if(board[i][0] == p){           	    // 0   
                if(board[i][1] == p){             	// 1	p 	p 	p
                    if(board[i][2] == p){          	// 2   
                        return true; 
					}
				}
			}
        }
         // for each column                        
        for(int i = 0; i<3; i++){            		// i  0  1  2
            if(board[0][i] == p){             		//       p
                if(board[1][i] == p){               //       p
                    if(board[2][i] == p){           //       p
                        return true;           
					}
				}
			}
         }
	 //  or diagonal left to right down
        if (board[0][0] == p){               		// p
			if (board[1][1] == p){                 	//    p
				if (board[2][2] == p){         		//       p
					return true ;
				}	
			}
        }

	 // for diagonal lef right to left up  
        if(board[0][2] == p){              		//       p
            if(board[1][1] == p){               //    p
                if(board[2][0] == p){           // p
					return true;
				}
			}
		}
      
      return false;
   }

   // isFull 
   // returns true if turns equals 9 other wise false 
    public boolean isFull() {	
        if (turns  == 9){
            return true;
		}
		else{
			return false ;
		}
	}
	
	
	
   // a tie or a 'cat game' - a cat can never catch his own tail
   // if 'O' not a winner and 'X" not Winner" 
   // and if there are not more spaces avalible
   // then return true otherwise return false
   public boolean isCat() {				
        if(isWinner('O')) 
		  return false;
        if(isWinner('X')) 
		  return false;
        for( int r=0; r < 3 ; r++ )
            for ( int c = 0 ; c <3; c++ )
				if( board[r][c] == ' ') 
					return false;                
		return true;	
   }

   // if square [r][c] is on the board 
   // r and c both have to betweeen zero or two clusivelyr 
   public boolean isValid( int r, int c ) {
      return  ( 0 <= r && r <= 2 && 0 <= c && c <= 2 );
   }

   // return number of turn taken so far
   public int numTurns() {
      return turns;
   }

   // return marker at borad[r][c]
   // if r and or c are invalid returns '@'
   public char playerAt( int r, int c ) {
		if ( isValid(r,c) )
			return board[r][c];
		else
			return '@';
   }

   // diplay the board to the user(s)
   public void displayBoard() {
      System.out.println("  0  " + board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
      System.out.println("    --+-+--");
      System.out.println("  1  " + board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
      System.out.println("    --+-+--");
      System.out.println("  2  " + board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
      System.out.println("     0 1 2 ");
   }

   // Modifiers
   // if row r and column c is free (space) 
   // then put may marker (p) there
   // and increament turns 
	public void playMove( char p, int r, int c ) {
		if(playerAt(r,c) == ' '){
			board[r][c] = p;
			turns++;
		}
	}
}