/*
Aristeidis Panagiotou 4754
Theofanis Georgakis 4644
Hlias Varthalitis 4637
*/

import  java.util.Stack;

public class DFSTree{
    private Node root;
    private Node current;
    private Node child;

    public DFSTree(int M, int N, int K){
        this.root = new Node(M, N, null);
    }
    
    public Node generateTree(int M, int N, int K){
        Stack<Node> nodeStack = new Stack<Node>();  //a stack is used to create the tree using DFS
        c4Array board = new c4Array(M, N, K);   //we use this board to play a turn and determine whether a victory or a draw is reached
        nodeStack.push(this.root);  //push the root first
        while(!nodeStack.isEmpty()){    //if the stack is empty then we've explored every node of the tree
            current = nodeStack.pop();  //examine the current node
            board.updateBoard(current.getBoard(), current.getTurn());   //update tree's board with the one from the current node
            board.checkWin();   //check if victory or draw and set according values to the terminal node
            if(board.getVictory()){
                if(board.getTurn() % 2 == 0){
                    current.setValue(1);
                }else{
                    current.setValue(-1);
                }
            }
            else if(board.getDraw()){
                current.setValue(0);
            }else{  //if not terminal node  
                for(int j = 0; j < N; j++){ //max number of moves to examine is N
                    if(board.canPlay(j)){  //check if a move is available
                        child = new Node(M, N, current);
                        current.setChild(j, child); //set current node to child j and push it to stack
                        nodeStack.push(child);
                        board.play(j); //play column j in tree board
                        child.updateBoard(board.getBoard(), board.getTurn());   //update child board with tree board
                        board.undo(j);  //reset the tree board so that we can examine next child
                    }
                }
            }
        }
        return this.root;
    }
}