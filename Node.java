/*
Aristeidis Panagiotou 4754
Theofanis Georgakis 4644
Hlias Varthalitis 4637
*/

public class Node {
    private int val; //-1(O), 0(draw), 1(X)
    private Node parent;
    private int turn = 1;
    private Node[] children;
    private int[][] board;

    public Node(int M, int N, Node parent){
        this.children = new Node[N];
        this.parent = parent;
        this.board = new int[M][N];
    }

    public Node[] getChildren(){
        return this.children;
    };
    
    public void updateBoard(int[][] array, int turn){ //update the board of this node
        this.turn = turn;
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board[0].length; j++){
                this.board[i][j] = array[i][j];
            }
        }
    }

    public int getTurn(){
        return this.turn;
    }

    public void setTurn(int turn){
        this.turn = turn;
    }    

    public int[][] getBoard(){
        return this.board;
    }
    
    public int getValue(){
        return this.val;
    }

    public void setValue(int val){
        this.val = val;
    }

    public Node getParent(){
        return this.parent;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public int hasChildren(){	//returns amount of children
        int counter = 0;
        for(int i = 0; i < this.children.length; i++){
            if(this.children[i] != null){
                counter ++;
            }
        }
        return counter;
    }

    public Node getChild(int i){
        return this.children[i];
    }
    
    public void setChild(int i, Node child){
        this.children[i] = child;
    }
   
    public int indexChild(Node child){	//returns index of a child, -1 
        for(int i = 0; i < this.children.length; i++){
            if(this.children[i] == child){
                return i;
            }
        }
        return -1;
    }

    public void printBoard(){
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board[0].length; j++){
                if(this.board[i][j] == 0){
                    System.out.print(" ~ ");
                }else{
                    if(this.board[i][j] == 1){
                        System.out.print(" X ");
                    }else{
                        System.out.print(" O ");
                    }
                }
            }
            System.out.print("\n");
        }
    }
}