import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SudokuBoard {
    private int size;
    private SudokuCell grid[][];

    public SudokuBoard(int array[][]){
        grid = new SudokuCell[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(array[i][j] != 0){
                    SudokuCell r = new SudokuCell(array[i][j], true);
                    grid[i][j] = r;

                }
                else{
                    SudokuCell r = new SudokuCell(array[i][j], false);
                    grid[i][j] = r;
                }
            }
        }
        size = (int) Math.sqrt(array.length);
    }

    public SudokuBoard(int size, char c){
        int arr[][] = new int [size][size];
        SudokuBoard r = new SudokuBoard(arr);
        SudokuSolver.solveSudoku(r);
        Random rand = new Random();
        if(c == 'm'){
        Set<Integer> selectedCells = new HashSet<>();
        int remove_cells = size * size / 2;

        while (selectedCells.size() < remove_cells) {
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);
            int row_col_pair = row * size + col; // create a unique row_col

            if (!selectedCells.contains(row_col_pair)) {
                selectedCells.add(row_col_pair);
                r.getCell(row, col).setValue(0);
            }
        }

        }
        else if (c == 'h') {
        Set<Integer> selectedCells = new HashSet<>();
        int remove_cells = size * size*3 / 4;

        while (selectedCells.size() < remove_cells) {
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);
            int row_col_pair = row * size + col; // create a unique row_col

            if (!selectedCells.contains(row_col_pair)) {
                selectedCells.add(row_col_pair);
                r.getCell(row, col).setValue(0);
            }
        }
        } 
        else {
            Set<Integer> selectedCells = new HashSet<>();
            int remove_cells = size * size / 4;
    
            while (selectedCells.size() < remove_cells) {
                int row = rand.nextInt(size);
                int col = rand.nextInt(size);
                int row_col_pair = row * size + col; // create a unique row_col
    
                if (!selectedCells.contains(row_col_pair)) {
                    selectedCells.add(row_col_pair);
                    r.getCell(row, col).setValue(0);
                }
        }
    }
        this.size = (int) Math.sqrt(size);
        grid = new SudokuCell[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if(r.getCell(i, j).getFixed()){
                SudokuCell t = new SudokuCell(r.getCell(i, j).getValue(), true);
                this.grid[i][j] = t;
                }
                else{
                SudokuCell t = new SudokuCell(r.getCell(i, j).getValue(), false);
                this.grid[i][j] = t;
                }
        }
        }

    }

    public int getSize(){
        return size;
    }

    public SudokuCell getCell(int i, int j){
        return grid[i][j];
    }


    public boolean checkIfValueIsOk(int i, int j){
        for (int k = 0; k < grid.length; k++) {
            if(grid[i][k].getValue() == grid[i][j].getValue()){
                if(j!=k)
                    return false;
                    
            }
            if(grid[k][j].getValue() == grid[i][j].getValue()){
                if(i!=k)
                    return false;
            }
        }

        int row; row = (i / getSize()) * getSize();
        int col; col = (j / getSize()) * getSize();
        
        for (int k = row; k < row + getSize(); k++) {
            for (int k2 = col; k2 < col + getSize(); k2++) {
                if(grid[i][j].getValue() == grid[k][k2].getValue()){
                    if(k != i && k2 != j)
                        return false;
                }
            }
        }
        return true;
    }
    public boolean checkIfIsSolved(){
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid.length; col++) {
                if(!checkIfValueIsOk(row, col) || grid[row][col].getValue() == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public void print(){
        int cellSize = Integer.toString(grid.length).length();
        // print the upper board
        System.out.print("+");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < cellSize; j++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
        for (int i = 0; i < grid.length; i++) {
            if(i % getSize() == 0 && i != 0){
                System.out.print("+");
                for (int i2 = 0; i2 < grid.length; i2++) {
                    for (int j2 = 0; j2 < cellSize; j2++) {
                        System.out.print("-");
                    }
                System.out.print("+");
                }
                System.out.println();
                System.out.print("|");
            }
            else{
                System.out.print("|");
            }
            for (int j = 0; j < grid.length; j++) {
                int currentCellSize = Integer.toString(grid[i][j].getValue()).length();
                if(j % getSize() == getSize()-1 && j != 0){
                    System.out.print(grid[i][j].getValue() + " ".repeat(cellSize - currentCellSize) + "|");
                }
                else{
                    System.out.print(grid[i][j].getValue() + " ".repeat(cellSize - currentCellSize + 1));
                }
            }
        System.out.println();
        }

        System.out.print("+");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < cellSize; j++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }
}