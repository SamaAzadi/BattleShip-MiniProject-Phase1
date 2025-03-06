import java.util.Scanner;

public class BattleShip {

    static final int GRID_SIZE = 10;

    static char[][] player1Grid = new char[GRID_SIZE][GRID_SIZE];

    static char[][] player2Grid = new char[GRID_SIZE][GRID_SIZE];

    static char[][] player1TrackingGrid = new char[GRID_SIZE][GRID_SIZE];

    static char[][] player2TrackingGrid = new char[GRID_SIZE][GRID_SIZE];

    static Scanner scanner = new Scanner(System.in);



    public static void main(String[] args) {

        initializeGrid(player1Grid);
        initializeGrid(player2Grid);
        initializeGrid(player1TrackingGrid);
        initializeGrid(player2TrackingGrid);


        placeShips(player1Grid);
        placeShips(player2Grid);


        boolean player1Turn = true;


        while (!isGameOver()) {
            if (player1Turn) {
                System.out.println("Player 1's turn:");
                printGrid(player1TrackingGrid);
                playerTurn(player2Grid, player1TrackingGrid);
            } else {
                System.out.println("Player 2's turn:");
                printGrid(player2TrackingGrid);
                playerTurn(player1Grid, player2TrackingGrid);
            }
            player1Turn = !player1Turn;
        }

        System.out.println("Game Over!");
    }


    static void initializeGrid(char[][] grid) {
        for(int i=0;i< grid.length;i++)
            for(int j=0;j< grid.length;j++)
            {
                grid[i][j] = '~';
            }
    }


    static void placeShips(char[][] grid) {
        for(int size = 2 ; size <= 5 ; size++){
            boolean horizontal = false;
            int end = 0;
            int row = (int)(Math.random()*10);
            int col = (int)(Math.random()*10);
            if((int)(Math.random()*10)%2 == 0)
            {
                horizontal = true;
            }
            while(true){
                if(canPlaceShip( grid , row , col , size , horizontal))
                {
                    for (int i = 1; i <= size ; i++) {
                        if(horizontal)
                        {
                            grid[row][col] = '*';
                            row++;
                        }else{
                            grid[row][col] = '*';
                            col++;
                        }
                    }
                    end = 1;
                }else{
                    row = (int)(Math.random()*10);
                    col = (int)(Math.random()*10);
                    end = -1;
                }
                if(end == 1) break;
            }
        }
    }


    static boolean canPlaceShip(char[][] grid, int row, int col, int size, boolean horizontal) {
        int counter = 0;

        if(horizontal)
        {
            if(row + size <= 9) {
                for (int i = 1; i <= size; i++) {
                    if (grid[row][col] == '~') {
                        counter++;
                    }
                }
            }
        }else{
            if(col + size <= 9) {
                for (int i = 1; i <= size; i++) {
                    if (grid[row][col] == '~') {
                        counter++;
                    }
                }
            }
        }
        if(counter == size){
            return true;
        }else return false;
    }


    static void playerTurn(char[][] opponentGrid, char[][] trackingGrid) {
        System.out.print("Enter target:");
        String target = scanner.nextLine();
        if(isValidInput(target)){
            int col = target.charAt(0) - 'A';
            int row = target.charAt(1) - '0';
            if(opponentGrid[row][col] == '~'){
                trackingGrid[row][col] = '0';
                System.out.println("Miss!");
            }else{
                trackingGrid[row][col] = 'X';
                opponentGrid[row][col] = '~';
                System.out.println("Hit!");
            }

        }else{
            System.out.println("Invalid input,try again.");
        }
    }


    static boolean isGameOver() {
        if(allShipsSunk(player1Grid)){
            System.out.print("Player1 ");
            return true;
        } else if (allShipsSunk(player2Grid)) {
            System.out.print("Player2 ");
            return true;
        }else return false;
    }


    static boolean allShipsSunk(char[][] grid) {
        boolean allShipsSunk = true;
        for(int i=0;i< grid.length;i++)
            for(int j=0;j< grid.length;j++)
            {
                if(grid[i][j] == '*')
                {
                    allShipsSunk = false;
                }

            }
        return allShipsSunk;
    }


    static boolean isValidInput(String input) {
        if (input.length() == 2 && input.charAt(0) >= 'A' && input.charAt(0) <= 'J'
                && input.charAt(1) >= '0' && input.charAt(1) <= '9') {
            return true;
        }else return false;
    }


    static void printGrid(char[][] grid) {
        System.out.println("  A B C D E F G H I J");
        for(int i=0;i< grid.length;i++){
            System.out.print(i);
            for(int j=0;j< grid.length;j++)
            {
                System.out.print(" ");
                System.out.print(grid[i][j]);
            }
            System.out.println();}

    }

}
