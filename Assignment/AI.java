/* 
    [AI.java]
    [Author: Ayden Khairis]
    [Class: COMP2230]
*/


import java.util.*;

public class AI { 
    // Variables
    private Board b;
    private int nextMove = -1;
    private int maxDepth = 12;

    // Constructor
    public AI(Board b){
        this.b = b;
    }

    // Method to check the result of the game
    public int findResult(Board b){
        int ai1Score = 0, ai2Score = 0;
        for(int i=5;i>=0;i--){
            for(int j=0;j<=6;++j){
                if(b.board[i][j]==0) continue;
                
                // Checking right cells 
                if(j<=3){
                    for(int k=0;k<4;++k){ 
                            if(b.board[i][j+k]==1) ai1Score++;
                            else if(b.board[i][j+k]==2) ai2Score++;
                            else break; 
                    }
                    if(ai1Score==4)return 1; else if (ai2Score==4)return 2;
                    ai1Score = 0; ai2Score = 0;
                } 
                
                // Checking cells up
                if(i>=3){
                    for(int k=0;k<4;++k){
                            if(b.board[i-k][j]==1) ai1Score++;
                            else if(b.board[i-k][j]==2) ai2Score++;
                            else break;
                    }
                    if(ai1Score==4)return 1; else if (ai2Score==4)return 2;
                    ai1Score = 0; ai2Score = 0;
                } 
                
                // Checking diagonal up-right
                if(j<=3 && i>= 3){
                    for(int k=0;k<4;++k){
                        if(b.board[i-k][j+k]==1) ai1Score++;
                        else if(b.board[i-k][j+k]==2) ai2Score++;
                        else break;
                    }
                    if(ai1Score==4)return 1; else if (ai2Score==4)return 2;
                    ai1Score = 0; ai2Score = 0;
                }
                
                // Checking diagonal up-left
                if(j>=3 && i>=3){
                    for(int k=0;k<4;++k){
                        if(b.board[i-k][j-k]==1) ai1Score++;
                        else if(b.board[i-k][j-k]==2) ai2Score++;
                        else break;
                    } 
                    if(ai1Score==4)return 1; else if (ai2Score==4)return 2;
                    ai1Score = 0; ai2Score = 0;
                }  
            }
        }
        
        for(int j=0;j<7;++j){
            // Game still in play
            if(b.board[0][j]==0)return -1;
        }
        // Game draw
        return 0;
    }

    // Method to calculate the score of the engine
    int calculateScore(int ai1Score, int moreMoves){   
    int moveScore = 4 - moreMoves;
    if(ai1Score==0)return 0;
    else if(ai1Score==1)return 1*moveScore;
    else if(ai1Score==2)return 10*moveScore;
    else if(ai1Score==3)return 100*moveScore;
    else return 1000;
    }

    // Evaluate function which evaluates the board
    public int evaluateBoard(Board b){
      
        int ai1Score = 1;
        int score = 0;
        int blanks = 0;
        int k=0, moreMoves=0;
        for(int i=5;i>=0;--i){
            for(int j=0;j<=6;++j){
                
                if(b.board[i][j]==0 || b.board[i][j]==2) continue; 
                
                if(j<=3){ 
                    for(k=1;k<4;++k){
                        if(b.board[i][j+k]==1)ai1Score++;
                        else if(b.board[i][j+k]==2){ai1Score=0;blanks = 0;break;}
                        else blanks++;
                    }
                     
                    moreMoves = 0; 
                    if(blanks>0) 
                        for(int c=1;c<4;++c){
                            int column = j+c;
                            for(int m=i; m<= 5;m++){
                             if(b.board[m][column]==0)moreMoves++;
                                else break;
                            } 
                        } 
                    
                    if(moreMoves!=0) score += calculateScore(ai1Score, moreMoves);
                    ai1Score=1;   
                    blanks = 0;
                } 
                
                if(i>=3){
                    for(k=1;k<4;++k){
                        if(b.board[i-k][j]==1)ai1Score++;
                        else if(b.board[i-k][j]==2){ai1Score=0;break;} 
                    } 
                    moreMoves = 0; 
                    
                    if(ai1Score>0){
                        int column = j;
                        for(int m=i-k+1; m<=i-1;m++){
                         if(b.board[m][column]==0)moreMoves++;
                            else break;
                        }  
                    }
                    if(moreMoves!=0) score += calculateScore(ai1Score, moreMoves);
                    ai1Score=1;  
                    blanks = 0;
                }
                 
                if(j>=3){
                    for(k=1;k<4;++k){
                        if(b.board[i][j-k]==1)ai1Score++;
                        else if(b.board[i][j-k]==2){ai1Score=0; blanks=0;break;}
                        else blanks++;
                    }
                    moreMoves=0;
                    if(blanks>0) 
                        for(int c=1;c<4;++c){
                            int column = j-c;
                            for(int m=i; m<= 5;m++){
                             if(b.board[m][column]==0)moreMoves++;
                                else break;
                            } 
                        } 
                    
                    if(moreMoves!=0) score += calculateScore(ai1Score, moreMoves);
                    ai1Score=1; 
                    blanks = 0;
                }
                 
                if(j<=3 && i>=3){
                    for(k=1;k<4;++k){
                        if(b.board[i-k][j+k]==1)ai1Score++;
                        else if(b.board[i-k][j+k]==2){ai1Score=0;blanks=0;break;}
                        else blanks++;                        
                    }
                    moreMoves=0;
                    if(blanks>0){
                        for(int c=1;c<4;++c){
                            int column = j+c, row = i-c;
                            for(int m=row;m<=5;++m){
                                if(b.board[m][column]==0)moreMoves++;
                                else if(b.board[m][column]==1);
                                else break;
                            }
                        } 
                        if(moreMoves!=0) score += calculateScore(ai1Score, moreMoves);
                        ai1Score=1;
                        blanks = 0;
                    }
                }
                 
                if(i>=3 && j>=3){
                    for(k=1;k<4;++k){
                        if(b.board[i-k][j-k]==1)ai1Score++;
                        else if(b.board[i-k][j-k]==2){ai1Score=0;blanks=0;break;}
                        else blanks++;                        
                    }
                    moreMoves=0;
                    if(blanks>0){
                        for(int c=1;c<4;++c){
                            int column = j-c, row = i-c;
                            for(int m=row;m<=5;++m){
                                if(b.board[m][column]==0)moreMoves++;
                                else if(b.board[m][column]==1);
                                else break;
                            }
                        } 
                        if(moreMoves!=0) score += calculateScore(ai1Score, moreMoves);
                        ai1Score=1;
                        blanks = 0;
                    }
                } 
            }
        }
        return score;
    } 

    // Minimax function 
    public int minimax(int depth, int turn, int alpha, int beta){
        
        if(beta<=alpha){if(turn == 1) return Integer.MAX_VALUE; else return Integer.MIN_VALUE; }
        int result = findResult(b);
    
        if(result==1)return Integer.MAX_VALUE/2;
        else if(result==2)return Integer.MIN_VALUE/2;
        else if(result==0)return 0;
        
        if(depth==maxDepth)return evaluateBoard(b);
        
        int max=Integer.MIN_VALUE, min = Integer.MAX_VALUE;
                
        for(int j=0;j<=6;j++){
            
            int currentScore = 0;
            
            if(!b.legal_move(j)) continue; 
            
            if(turn==1){
                    b.placeMove(j, 1);
                    currentScore = minimax(depth+1, 2, alpha, beta);
                    
                    if(depth==0){
                        if(currentScore > max)nextMove = j; 
                        if(currentScore == Integer.MAX_VALUE/2){b.undoMove(j);break;}
                    }
                    
                    max = Math.max(currentScore, max);
                    
                    alpha = Math.max(currentScore, alpha);  
            } 
            else if(turn==2){
                    b.placeMove(j, 2);
                    currentScore = minimax(depth+1, 1, alpha, beta);
                    min = Math.min(currentScore, min);
                    
                    beta = Math.min(currentScore, beta); 
            }  
            b.undoMove(j); 
            if(currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) break; 
        }  
        return turn==1?max:min;
    }

    // Method to make a move on the board
    public int getMove(){
        nextMove = -1;
        minimax(0, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return nextMove;
    }
}