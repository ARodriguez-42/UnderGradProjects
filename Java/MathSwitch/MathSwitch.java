import java.util.Scanner;
import java.util.function.BinaryOperator;
import java.lang.Math;

interface MathInterface {
	public static <T> T figure(T op1, T op2, BinaryOperator <T> op) {
		return op.apply(op1, op2); //delegate to the op
	}
}

class MathSwitch {
	public static void main(String[] args) {
		char op;
		double num1;
		double num2; 
		double val;
		Scanner input = new Scanner(System.in);
		System.out.println("Choose an operator: +, -, *, /, %, or ^ (Or end-of-file to exit) ");
		do {
			op = input.next().charAt(0);
			System.out.println("Enter first number");
			num1 = input.nextDouble();
			System.out.println("Enter second number");
			num2 = input.nextDouble();
			switch (op) {
				case '+':
					val = MathInterface.figure(num1, num2, (a, b) -> a + b);
					System.out.println(num1 + " + " + num2 + " = " + val);
					break;
				case '-':
					val = MathInterface.figure(num1, num2, (a, b) -> a - b);
					System.out.println(num1 + " - " + num2 + " = " + val);
					break;
				case '*':
					val = MathInterface.figure(num1, num2, (a, b) -> a * b);
					System.out.println(num1 + " * " + num2 + " = " + val);
					break;
				case '/':
					val = MathInterface.figure(num1, num2, (a, b) -> a / b);
					System.out.println(num1 + " / " + num2 + " = " + val);
					break;
				case '%':
					val = MathInterface.figure(num1, num2, (a, b) -> a % b);
					System.out.println(num1 + " % " + num2 + " = " + val);
					break;
				case '^':
					val = MathInterface.figure(num1, num2, (a, b) -> Math.pow(a, b));
					System.out.println(num1 + " ^ " + num2 + " = " + val);
					break;
				default:
					System.out.println("Invalid operator!");
					break;
			}
			// ask users to enter operator
			System.out.println("Choose an operator: +, -, *, /, %, ^");
		} while (input.hasNext());
	input.close();
	}
}