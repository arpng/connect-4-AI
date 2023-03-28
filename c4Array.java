/*
Aristeidis Panagiotou 4754
Theofanis Georgakis 4644
Hlias Varthalitis 4637
*/

import java.util.Scanner;

public class c4Array {
    private int M;  //rows
    private int N;  //columns  
    private int K;  //win condition
    private int turn = 1;
    private boolean victory = false;
    private boolean draw = false;
    private int[][] array;
    Scanner sc = new Scanner(System.in);

    public c4Array(int M, int N, int K){
        this.M = M; this.N = N; this.K = K;
        array = new int[M][N];
    }

    public int getTurn(){
        return turn;
    }

    public boolean getVictory(){
        return victory;
    }
    
    public boolean getDraw(){
        return draw;
    }

    public void updateBoard(int[][] array, int turn){
        this.turn = turn;
        for(int i = 0; i < this.array.length; i++){
            for(int j = 0; j < this.array[0].length; j++){
                this.array[i][j] = array[i][j];
            }
        }
        this.victory = false;
        this.draw = false;
    }

    public int[][] getBoard(){
        return this.array;
    }

    private boolean empty(int i, int j){
        if(array[i][j] != 0){    
            return false;
        }
        return true;
    }
    
    private boolean checkHor(int i, int j){
        if(array[i][j] == 0){    //no point in checking if its empty
            return false;
        }
        if(j >= N - (K - 1)){  //check bounds
            return false;
        }
        for(int col = 1; col < K; col++){   //all next 3 elements must be equal 
            if(array[i][j] != array[i][j+col]){
                return false;
            }
        }
        return true;
    }

    private boolean checkVer(int i, int j){
        if(array[i][j] == 0){    //no point in checking if its empty
            return false;
        }
        if(i < K-1){  //check bounds
            return false;
        }
        for(int line = 1; line < K; line++){   //all next 3 elements must be equal
            if(array[i][j] != array[i-line][j]){
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagR(int i, int j){
        if(array[i][j] == 0){    //no point in checking if its empty
            return false;
        }
        if(i < K-1){  //check vertical bounds 
            return false;
        }
        if(j >= N - (K - 1)){  //check horizontal bounds
            return false;
        }
        for(int diag = 1; diag < K; diag++){   //all next 3 elements must be equal
            if(array[i][j] != array[i-diag][j+diag]){
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagL(int i, int j){
        if(array[i][j] == 0){ //no point in checking if its empty
            return false;
        }
        if(i < K-1){  //check vertical bounds
            return false;
        }
        if(j < K-1){  //check horizontal bounds
            return false;
        }
        for(int diag = 1; diag < K; diag++){   //all next 3 elements must be equal
            if(array[i][j] != array[i-diag][j-diag]){
                return false;
            }
        }
        return true;
    }

    public void checkWin(){
        if(turn >= (2*K-1)){ //impossible to win if turn is lower
            for(int i = M-1; i > -1; i--){
                for(int j = 0; j < N; j++){
                        if(checkHor(i, j) || checkVer(i, j) || checkDiagL(i, j) || checkDiagR(i, j)){
                        victory = true;
                        return;
                    }
                }
            }
        }
        if(turn == M*N + 1){ //if max number of turns is reached, then game's a draw
            draw = true;
        }
    }

    public void printArr(){
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(empty(i,j)){
                    System.out.print(" ~ ");
                }else{
                    if(array[i][j] == 1){
                        System.out.print(" X ");
                    }else{
                        System.out.print(" O ");
                    }
                }
            }
            System.out.print("\n");
        }
    }
    
    public boolean canPlay(int j){
        if(empty(0, j)){
            return true;
        }
        return false;
    }
    
    public void play(int j){
        int s;
        int i = M-1;
        
        if(this.turn%2 == 0){
            s = -1; // = ' O ' because its MIN's turn
        }else{
            s = 1; // = ' X ' because its MAX's turn
        }

        while(!empty(i,j)){
            i--;
        }
        
        this.array[i][j] = s;
        this.turn++;
    }

    public void undo(int j){
        int i = 0;
    
        while(empty(i,j)){
            i++;
        }
        
        this.array[i][j] = 0;
        
        this.turn--;

    }
}