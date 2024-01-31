//Name: Antonio Rodriguez
//Class: CSC 300-01
//Project: 7
//Orignal Author: Malcolm McCullough 
//Description: This project will recieve an inputed expression and using 
//			   stacks, it will return the result 
//


import java.io.*;
import java.util.*;


public class InFixExpressionEvaluator {
	//public variables & Stacks  
	public static boolean debugMode = false;
	public static Stack<Integer> ValueStack = new Stack<Integer>();
	public static Stack<Character> OperatorStack = new Stack<Character>();
	public static Stack<Integer> ValueStackTemp = new Stack<Integer>();
	public static Stack<Character> OperatorStackTemp = new Stack<Character>();
	
	
	public static void main(String[] args) {
        Token token;
        lex tr = new lex();
		
        System.out.print("Starting Expression Evaluation Program\n\n");
        System.out.print("Enter Expression: ");

        token = tr.getNextToken();

        while (token.equalsType(TokenType.QUIT) == false) {
            /* check first Token on Line of input */
            if (token.equalsType(TokenType.HELP)) {
                printCommands();
                tr.clearToEoln();
            } else if (token.equalsType(TokenType.ERROR)) {
                System.out.print("Invalid Input - For a list of valid commands, type ?\n");
                tr.clearToEoln();
            } 
			//if 'd' is entered debuging mode will turn on 
			else if (token.equalsType(TokenType.DEBUG)) {
                System.out.print("Debugging Mode On");
				debugMode = true;
                tr.clearToEoln();
			}else if (token.equalsType(TokenType.EOLN)) {
                System.out.print("Blank Line - Do Nothing\n");
                /* blank line - do nothing */
            } else {
                process(token, tr);
            }

            System.out.print("\nEnter Expression: ");
            token = tr.getNextToken();
        }

        System.out.print("Quitting Program\n");
        return;
    }

    public static void process(Token token, lex tr) {
        
		int result = 0;
		/**********************************************/
        /* Declare both stack head pointers here      */
		
		int topValue = 0;	
		char topOp = '0';
		
        /* Loop until the expression reaches its End */
        while (token.equalsType(TokenType.EOLN) == false) {
            /* The expression contains an OPERATOR */
            if (token.equalsType(TokenType.OPERATOR)) {
				
                /* make this a debugMode statement */
				if (debugMode == true){
					System.out.print("OP: " + token.getOperator() + ", ");
				}	
	
                // add code to perform this operation here				
				
				//If the current operator is ')', it will go back and solve what is inside the paranthesis until it reaches '('
				if (token.getOperator() == ')'){								
					while(topOp != '('){
						//pushes all values from orignal stack into temporary stack until it reaches '('
						if(!ValueStack.empty()){
							ValueStackTemp.push(ValueStack.pop());
						}
						if(!ValueStack.empty()){
							ValueStackTemp.push(ValueStack.pop());
						}
						OperatorStackTemp.push(OperatorStack.pop());
						//reset topOp
						topOp = OperatorStack.peek();
					}
					char temp;
					//Solve the expression(s) in the paranthesis
					while(!OperatorStackTemp.empty()){
						char currentOp = OperatorStackTemp.pop();;
						int val1 = ValueStackTemp.pop();
						//if there is no other value in the stack, the expressions in the paranthesis are complete
						if (ValueStackTemp.empty()){
							ValueStackTemp.push(val1);
							break;
						}
						int val2 = ValueStackTemp.pop();
						//if '+' or '-' is the operator, check if the next operator is not '*' or '/'
						//this is to make sure PEMDAS is followed 
						if((currentOp == '+' || currentOp == '-') && !OperatorStackTemp.empty()){
							if(OperatorStackTemp.peek() == '*' || OperatorStackTemp.peek() == '/' ){
								temp = OperatorStackTemp.pop();
								int val3 = ValueStackTemp.pop();
								//pass the third and second value along with new operator 
								val2 = eval(val3, val2, temp);
							}
						}
						result = eval(val2, val1, currentOp);
						//push result back onto the stack
						ValueStackTemp.push(result);
					}
					//push the last remaining value from ValueStackTemp back to the orignal, ValueStack
					ValueStack.push(ValueStackTemp.pop());
					
				}
				//if no end paranthesis it will simply continue to add
				else{ 
					//pushing the current token into the appropriate stack
					OperatorStack.push(token.getOperator());	
					//setting the top value of the stack by using peek()
					topOp = OperatorStack.peek();	  							
				}
			}

            /* The expression contain a VALUE */
            else if (token.equalsType(TokenType.VALUE)) {
                /* make this a debugMode statement */
				if (debugMode == true){	
					System.out.print("Val: " + token.getValue() + ", ");
				}
                // add code to perform this operation here
				//pushing the current token into the appropriate stack
				ValueStack.push(token.getValue());
				//setting the top value of the stack by using peek()
				topValue = ValueStack.peek();								
            }

            /* get next token from input */
            token = tr.getNextToken();
        }

        /* The expression has reached its end */

        // add code to perform this operation here
		System.out.print("\n");
		//Pushing the remaining values and operators after the paranthesis, into a temporary stack
		while(!OperatorStack.empty()){
			OperatorStackTemp.push(OperatorStack.pop());
		}
		while(!ValueStack.empty()){
			ValueStackTemp.push(ValueStack.pop());
		}
		//Working through the remaining expression
		while(!OperatorStackTemp.empty()){
			char currentOp = OperatorStackTemp.pop();
			char temp;
			//remove any paranthesis
			if(currentOp == '('){
				if(OperatorStackTemp.empty()){				
					break;
				}
				currentOp = OperatorStackTemp.pop();
			}
			//setting the values for the eval() method 
			int val1 = ValueStackTemp.pop();
			//if the rest of the value stack is empty, the expression has been solved 
			if(ValueStackTemp.empty()){
				ValueStackTemp.push(val1);
				break;
			}
			int val2 = ValueStackTemp.pop();
			//if '+' or '-' is the operator, check if the next operator is not '*' or '/'
			//this is to make sure PEMDAS is followed
			if((currentOp == '+' || currentOp == '-') && !OperatorStackTemp.empty()){
				if(OperatorStackTemp.peek() == '*' || OperatorStackTemp.peek() == '/' ){
					temp = OperatorStackTemp.pop();
					int val3 = ValueStackTemp.pop();
					//pass the third and second value along with new operator 
					val2 = eval(val3, val2, temp);
				}
			}
			result = eval(val2, val1, currentOp);
			//push result back onto the stack
			ValueStackTemp.push(result);
		}
		//Printing the result of the equation
		System.out.println("Result of equation: " + result);		 
    }

	// eval() method that recieves 2 values and an operator
	//matched the correct operator and returns the appropriate result 
	public static int eval(int v1, int v2, char cOp){
		int r = 0;
		if (cOp == '+'){
			r = v1 + v2;
		}
				
		if (cOp == '-'){
			r = v2 - v1;
		}
				
		if (cOp == '/'){
			r = v2 / v1;
		}
				
		if (cOp == '*'){
			r = v1 * v2;
		}
		return r;
	}




    /**************************************************************/
    /*                                                            */
    /*  The Code below this point should NOT need to be modified  */
    /*  for this program.   If you feel you must modify the code  */
    /*  below this point, you are probably trying to solve a      */
    /*  more difficult problem that you are being asked to solve  */
    /*                                                            */
    /**************************************************************/

    public static void printCommands() {
        System.out.print("The commands for this program are:\n\n");
        System.out.print("q - to quit the program\n");
        System.out.print("? - to list the accepted commands\n");
		System.out.print("d - to enter degubbing mode \n");
        System.out.print("or any infix mathematical expression using operators of (), *, /, +, -\n");
    }
}

class lex {
    private BufferedReader br;
    private String inline;
    private boolean needline;
    private int pos;

    // initialize the lex class to read from Standard Input
    public lex() {
        // set to read from Standard Input
        br = new BufferedReader(new InputStreamReader(System.in));
        inline = "";
        pos = 0;
        needline = true;
    }

    // Force the next getNextToken to read in a line of input
    public void clearToEoln() {
        needline = true;
    }

    // Return the next Token from the input line
    public Token getNextToken() {
        // get a new line of input from user
        if (needline) {
            try {
                inline = br.readLine(); 
            } catch (IOException ioe) {
                System.out.println("Error in reading");
                return new Token(TokenType.EOF);
            }
            if (inline == null) { // End of File Encoutered - Should never be the case when reading
                //   from standard input: System.in
                return new Token(TokenType.EOF);
            }

            inline = inline + " "; // add a space at end to help deal with digit calculation
            needline = false;
            pos = 0;
        }

        // skip over any white space characters in the beginning of the input
        while (pos < inline.length() && Character.isWhitespace(inline.charAt(pos))) {
            pos++;
        }

        // check for the end of the current line of input
        if (pos >= inline.length()) { // at end of line
            needline = true;
            return new Token(TokenType.EOLN);
        }

        // Get the next character from the input line
        char ch = inline.charAt(pos);
        pos++;

        // check if 'q' or 'Q' was entered ==> QUIT Token
        if ('q' == ch || 'Q' == ch) {
            return new Token(TokenType.QUIT);
        }

        // check if "?" was entered ==> HELP Token
        if ('?' == ch) {
            return new Token(TokenType.HELP);
        }
		 
		 // check if "d" was entered ==> DEBUG Token
        if ('d' == ch) {
            return new Token(TokenType.DEBUG);
        }

        // check for Operator values of: + - * / ( )  ==> OPERATOR Token
        if (('+' == ch)
                || ('-' == ch)
                || ('*' == ch)
                || ('/' == ch)
                || ('(' == ch)
                || (')' == ch)) {
            Token t = new Token();
            t.setToOperator(ch);
            return t;
        }

        // check for a number  ==> VALUE Token
        if (Character.isDigit(ch)) {
            int number = Character.digit(ch, 10);
            ch = inline.charAt(pos);
            pos++;
            while (Character.isDigit(ch)) {
                number = number * 10 + Character.digit(ch, 10);
                ch = inline.charAt(pos);
                pos++;
            }
            pos--; // since number calcuation check one character after the last digit
            Token t = new Token();
            t.setToValue(number);
            return t;
        }

        // Input in not valid if code get to this part ==> ERROR Token
        return new Token(TokenType.ERROR);
    }
}

// Enumarated Type specifying all of the Tokens
enum TokenType {
    ERROR,
    OPERATOR,
    VALUE,
    EOLN,
    QUIT,
    HELP,
    EOF,
	DEBUG
}

// Class to hold the Token information
class Token {
    private TokenType type;
    private char op; // using '$' as undefined/error
    private int val; // using -999 as undefined/error

    // Default to initialize to the ERROR TokenType
    public Token() {
        type = TokenType.ERROR;
        op = '$';
        val = -999;
    }

    // Initialize to a specific TokenType
    public Token(TokenType t) {
        type = t;
        op = '$';
        val = -999;
    }

    // Set to a specific TokenType
    public void setToType(TokenType t) {
        type = t;
        op = '$';
        val = -999;
    }

    // Set to a OPERATOR TokenType with specific operator value
    public void setToOperator(char c) {
        type = TokenType.OPERATOR;
        op = c;
        val = -999;
    }

    // Set to a VALUE TokenType with a specific numeric value
    public void setToValue(int v) {
        type = TokenType.VALUE;
        op = '$';
        val = v;
    }

    // return true if the Current Token is of the given TokenType
    public boolean equalsType(TokenType t) {
        if (type == t) return true;
        else return false;
    }

    // return true if the Current Token is of the OPERATOR TokenType
    //     and contains the given operator character
    public boolean equalsOperator(char c) {
        if (type == TokenType.OPERATOR && op == c) return true;
        else return false;
    }

    // Return the Operator for the current Token
    //   verify the current Token is of the OPERATOR TokenType
    public char getOperator() {
        if (type == TokenType.OPERATOR) return op;
        else return '$'; // using $ to indicate an error value
    }

    // Return the Value for the current Token
    //   verify the current token is of the value TokenType
    public int getValue() {
        if (type == TokenType.VALUE) return val;
        else return -999; // using -999 to indicate an error value
    }
}