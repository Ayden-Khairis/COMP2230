/* 
    [Board.java]
    [Author: Ayden Khairis]
    [Class: COMP2230]
*/


import java.util.*;

public class Board {
    // Variable
	byte[][] board = new byte[6][7];
    
    // Board function
    public Board() {
        board = new byte[][]{
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},
            {0,0,0,0,0,0,0,},    
        };
    }
        
    // Method to check if a move is legal
    public boolean legal_move(int column){
        return board[0][column]==0;
    }
    
    //Placing a Move on the board
    public boolean placeMove(int column, int player){ 
        if(!legal_move(column)) {System.out.println("Illegal move!"); return false;}
        for(int i=5;i>=0;--i){
            if(board[i][column] == 0) {
                board[i][column] = (byte)player;
                return true;
            }
        }
        return false;
    }
    
    // Method to undo a possible illegal move
    public void undoMove(int column){
        for(int i=0;i<=5;++i){
            if(board[i][column] != 0) {
                board[i][column] = 0;
                break;
            }
        }        
    }

    // Method used to display the board for bug testing
    // Unused when submitting assignment
    public void displayBoard(){
    System.out.println();
    for(int i=0;i<=5;++i){
        for(int j=0;j<=6;++j){
            System.out.print(board[i][j]+" ");
        }
        System.out.println();
    }
    System.out.println();
    }
}