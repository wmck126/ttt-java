/*
 * Assignment: Tic-Tac-Toe
 * TTTMcKinley.java
 * Ward McKinley
 * File created: 10/06/2023
*/

import java.util.Scanner;

public class TTTMcKinley {
    public static void main(String[] args){
        //main application
        Scanner input = new Scanner(System.in);
        //initialize and print board to start
        String[][] board = initBoard();
        System.out.println("Let's begin!");
        gameBoard(board);

        //main app logic
        while(true){
            int isWinner = checkWinner(board);
            //your turn
            yourTurn(board, input);

            isWinner = checkWinner(board); //checks to see if your move wins
            if (isWinner == 1){
                gameBoard(board);
                System.out.println("X wins");
                break;
            } else if (isWinner == 0){
                gameBoard(board);
                System.out.println("Draw");
                break;
            }

            machineTurn(board);
            isWinner = checkWinner(board); //checks to see if machine wins
            if (isWinner == -1){ 
                gameBoard(board);
                System.out.println("O wins");
                break;
            }

            gameBoard(board); //displays board
        }
    }

    public static String[][] initBoard(){
        //initialize 3x3 board with empty spaces 
        String[][] board = new String[3][3];
        for(int row= 0; row< 3; row++){
            for (int column= 0; column< 3; column++){
                board[row][column] = " ";
            }
        }
        return board;
    }

    public static void gameBoard(String[][] board){
        //print each space in the board, if middle don't include | on either side
        for(int row= 0; row< 3; row++){
            for (int column= 0; column< 3; column++){
                if (column== 1){
                    System.out.print(" " + board[row][column] + " ");
                } else {
                    System.out.print("| " + board[row][column] + " |");
                }
                
            }
            System.out.println();
        }
    }

    
    public static int checkWinner(String[][] board){
        //draw == 0
        //x win = 1
        //o win = -1
        //no win = 2

        //test X winning conditions
        if(board[0][0].equals("X")){ //test winning conditions if 0,0 is X (row, column, diagnol)
            if(board[0][1].equals("X") && board[0][2].equals("X")){
                return 1;
            } else if(board[1][0].equals("X") && board[2][0].equals("X")){
                return 1;
            } else if(board[1][1].equals("X") && board[2][2].equals("x")){
                return 1;
            }
        } else if(board[0][2].equals("X")){ //test at 0,2 column and diagnol
            if(board[1][1].equals("X") && board[2][0].equals("X")){
                return 1;
            } else if(board[1][2].equals("X") && board[2][2].equals("X")){
                return 1;
            }
        } else if(board[1][0].equals("X") && board[1][1].equals("X") && board[1][2].equals("X")){ //test middle row
            return 1;
        } else if(board[2][0].equals("X") && board[2][1].equals("X") && board[2][2].equals("X")){ //test bottom row
            return 1;
        } else if(board[0][1].equals("X") && board[1][1].equals("X") && board[2][1].equals("X")){//test middle column
            return 1;
        }

        //Test O winning conditions 
        if(board[0][0].equals("O")){ //test winning conditions if 0,0 is O (row, column, diagnol)
            if(board[0][1].equals("O") && board[0][2].equals("O")){
                return -1;
            } else if(board[1][0].equals("O") && board[2][0].equals("O")){
                return -1;
            } else if(board[1][1].equals("O") && board[2][2].equals("O")){
                return -1;
            }
        } else if(board[0][2].equals("O")){ //test at 0,2 column and diagnol
            if(board[1][1].equals("O") && board[2][0].equals("O")){
                return -1;
            } else if(board[1][2].equals("O") && board[2][2].equals("O")){
                return -1;
            }
        } else if(board[1][0].equals("O") && board[1][1].equals("O") && board[1][2].equals("O")){ //test middle row
            return -1;
        } else if(board[2][0].equals("O") && board[2][1].equals("O") && board[2][2].equals("O")){ //test bottom row
            return -1;
        } else if(board[0][1].equals("O") && board[1][1].equals("O") && board[2][1].equals("O")){//test middle column
            return -1;
        }

        //test if board is full - draw
        for (int row = 0; row < 3; row++){
            for (int column = 0; column < 3; column++){
                if (board[row][column].indexOf(" ") > -1){
                    return 2; //no win
                }
            }
            if (row == 2){ //if reached [2][2] and still no space is found, return 0
                return 0; //draw
            }
        }
        return 2;
    }


    public static void yourTurn(String[][] board, Scanner input){
        System.out.println("Please enter coordinate of your location (x)");
        int coordinateX = input.nextInt();
        int coordinateY = input.nextInt();
        boolean spotTaken = true;

        //limit handling
        if (coordinateX > 2 || coordinateX < 0){
            System.out.println("Input an X coordinate between 0 - 2");
        }
        if (coordinateY > 2 || coordinateY < 0){
            System.out.println("Input a Y coordinate between 0 - 2");
        }

        //check to see if space is occupied, if not then let player put piece there.
        while (spotTaken == true){
            if (board[coordinateX][coordinateY].equals("X") || board[coordinateX][coordinateY].equals("O")){
                System.out.println("That spot is already taken!");
                spotTaken = true;
                System.out.println("Please enter coordinate of your location (x)");
                coordinateX = input.nextInt();
                coordinateY = input.nextInt();
            } else if (coordinateX <= 2 && coordinateX >= 0 && coordinateY >= 0 && coordinateY <= 2){ //if w/in accepted range
                board[coordinateX][coordinateY] = "X";
                spotTaken = false;
            }
        }
    }



    public static void machineTurn(String[][] board){
        int randomX = (int)(Math.random() * 3);
        int randomY = (int)(Math.random() * 3);
        boolean foundSpot = false;

        do {
            if (board[randomX][randomY].equals(" ") && !board[randomX][randomY].equals("X")){ //if a space is empty and not occupied
                board[randomX][randomY] = "O";
                foundSpot = true;
            } else { //else, reroll 
                randomX = (int)(Math.random() * 3);
                randomY = (int)(Math.random() * 3);
            }
        } while (foundSpot == false);
    }
}
