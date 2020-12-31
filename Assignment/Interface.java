/* 
    [Interface.java]
    [Author: Ayden Khairis]
    [Class: COMP2230]
*/

import java.util.*;

public class Interface{

	private int turn = 0;
	private String name = "tricycle-C3282229";
	private String isready = "readyok";
	private Board b = new Board();
	private AI connect4 = new AI(b);
	private int round = 0;

	private void run() {
		String input;
		String[] inputSplit;
		Scanner console = new Scanner(System.in);
		while(true) {
			input = console.nextLine();
			inputSplit = input.split(" ");
			switch(inputSplit[0]) {
				case "name":
					System.out.println(name);
					break;
				case "isready":
					System.out.println(isready);
					break;
				case "position":
					if (inputSplit.length > 2) {
						lastMove(inputSplit[2]);
					}
					else {
						lastMove("Error");
					}
					break;
				case "go":
					System.out.println(move());
					break;
				case "quit":
					System.out.println("quitting");
					System.exit(0);
			}
		}
	}

	private void lastMove(String input){
		String last = "";
		if (round == 0) {
			if (input.equalsIgnoreCase("Error")) {
				turn = 1;
			}	
			else {
				turn = 2;
				last += input.charAt(input.length()-1);
				int output = Integer.parseInt(last);
				b.placeMove(output, 2);
			}

		}
		
		if (round > 0) {
			if (input.length() >= 1) {
				last += input.charAt(input.length()-1);
				int output = Integer.parseInt(last);
				b.placeMove(output, 2);
			}
		}

		round++;
	}

	private String move(){
		int value = 0;
		value = value();
		int column = -1;
		if (round > 3){
			column = column();
		}
		if (round <= 3){
			column = 3;
		}
		b.placeMove(column, 1);
		return "bestmove " +column+ " " +value;
	}

	private int column(){
		return connect4.getMove();
	}

	private int value(){
		int value = 0;
		value = connect4.evaluateBoard(b);
		return value;
	}

	public static void main(String[] args) {
        Interface ie = new Interface();
        ie.run();
    }
}