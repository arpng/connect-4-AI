/*
Aristeidis Panagiotou 4754
Theofanis Georgakis 4644
Hlias Varthalitis 4637
*/

import java.util.Scanner;

public class ask2{
    public int miniMax(Node node, boolean isMax, int N){
        int value;
        int temp;

        if(node.hasChildren() == 0){	//node is terminal so we return its value assigned by the tree
            return node.getValue();
        }
        if(isMax){	// if it's MAX's turn then select the node with the highest value
            value = -2;
            for(int i = 0; i < N; i++){
                if(node.getChild(i) != null){
                    temp = miniMax(node.getChild(i), false, N);
                    if(value < temp){
                        value = temp;
                    }
                }
            }
            node.setValue(value);
            return value;
        }else{	// if it's MIN's turn then select the node with the lowest value
            value = 2;
            for(int i = 0; i < N; i++){
                if(node.getChild(i) != null){
                    temp = miniMax(node.getChild(i), true, N);
                    if(value > temp){
                        value = temp;
                    }
                }
            }
            node.setValue(value);
            return value;
        }
    }
    
    public static void main(String[] args){
        int M;  //rows
        int N;  //columns  
        int K;  //win condition
        Node temp; int val = -2; int nextMove = 0;
        int input;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("NOTE: The way we implement the algorithm is by generating the entire game tree. This has the side effect that values greater than 4 for M and N and greater than 3 for K won't work because Java is running out of heap space on our computer. \nTherefore it is not recommended to beyond the configuration 4-4-3.");
        System.out.print("Give M(rows): "); M = sc.nextInt(); 
        System.out.print("Give N(columns): "); N = sc.nextInt();
        System.out.print("Give K(win condition): "); K = sc.nextInt();
        
        System.out.println("Generating tree...");


        DFSTree tree = new DFSTree(M, N, K);
        Node current = tree.generateTree(M, N, K);
        c4Array finalBoard = new c4Array(M, N, K);
        System.out.println("Done generating tree");

        ask2 item = new ask2();
        item.miniMax(current, true, N);
        System.out.println("Done evaluation of nodes using minimax algorithm");
        System.out.println("Value of root is: " + current.getValue());
        finalBoard.printArr();

        while(finalBoard.getVictory() == false && finalBoard.getDraw() == false){
            if(finalBoard.getTurn() % 2 == 1){
                System.out.println("Player MAX is playing...");
                for(int i = 0; i < N; i++){ //we examine each child so that we can find a node with the highest value
                    if(current.getChild(i) != null){
                        temp = current.getChild(i);
                        if(temp.getValue() > val){
                            val = temp.getValue();
                            nextMove = i;
                        }
                    }
                }
                val = -2; //set to -2 for the next time MAX plays
                finalBoard.play(nextMove);
                current = current.getChild(nextMove);
                finalBoard.printArr();
            }else{
                System.out.println("Player MIN it's your turn. Select column(1-" + M +"):");
                input = sc.nextInt();
                while(!finalBoard.canPlay(input - 1)){ //decremeant input by one so that we are never out of bounds
                    System.out.println("Column is full, select a valid one:");
                    input = sc.nextInt();
                }
                finalBoard.play(input - 1);
                current = current.getChild(input - 1);
                finalBoard.printArr();
            }
            finalBoard.checkWin();
        }
        if(finalBoard.getVictory()){
            if(finalBoard.getTurn() % 2 == 0){
                System.out.println("Player MAX won");
            }else{
                System.out.println("Player MIN won");
            }
        }else{
            System.out.println("It's a draw");
        }
    }
}