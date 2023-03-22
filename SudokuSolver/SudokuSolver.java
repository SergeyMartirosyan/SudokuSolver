import java.util.Random;

public class SudokuSolver {
    public static void solveSudoku(SudokuBoard board){
        boolean decider = true;
        for (int i = 0; i < board.getSize() * board.getSize(); i++) {
            for (int j = 0; j < board.getSize() * board.getSize(); j++) {
                if(board.getCell(i, j).getValue()!=0){
                    if(!board.checkIfValueIsOk(i, j))
                        decider = false;
                }
            }
        }
        if(decider){
            if(!solveSudoku(board, 0, 0)){
                System.out.println("Unsolvable Sudoku board");
            }
        }
        else{
            System.out.println("Invalid Sudoku board");
        }
    } 

    private static boolean solveSudoku(SudokuBoard board, int i, int j) {
        if (board.getCell(i, j).getValue() == 0) {
            int[] numbers = new int[board.getSize() * board.getSize()];
    
            for (int k = 0; k < numbers.length; k++) {
                numbers[k] = k + 1;
            }

            Random rand = new Random();
            for (int k = 0; k < numbers.length; k++) {
                int index = rand.nextInt(numbers.length - k) + k;
                int temp = numbers[k];
                numbers[k] = numbers[index];
                numbers[index] = temp;
            }
    
            for (int k = 0; k < numbers.length; k++) {
                int value = numbers[k];
                board.getCell(i, j).setValue(value);
                //board.print();
                if (board.checkIfValueIsOk(i, j)) {
                    if (i == board.getSize() * board.getSize() - 1 && j == board.getSize() * board.getSize() - 1)
                        return true;
                    else {
                        if (j < board.getSize() * board.getSize() - 1) {
                            if (solveSudoku(board, i, j + 1)) {
                                return true;
                            }
                        } 
                        else {
                            if (solveSudoku(board, i + 1, 0)) {
                                return true;
                            }
                        }
                    }
                }
            }
    
            board.getCell(i, j).setValue(0);
            return false;
        } 
        else {
            int new_i = i;
            int new_j = j + 1;
            if (new_j == board.getSize() * board.getSize()) {
                new_i++;
                new_j = 0;
            }
            if (new_i == board.getSize() * board.getSize()) {
                return true;
            }
            return solveSudoku(board, new_i, new_j);
        }
    } 
}