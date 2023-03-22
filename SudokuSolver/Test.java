import java.util.Scanner;

public class Test {
   public static void main(String[] args){
      Scanner scan = new Scanner(System.in);
      System.out.print("Input the size of the board: ");
      int size = scan.nextInt();
      System.out.println();
      System.out.print("Input the difficulty of the board (e - easy, m - medium, h - hard): ");
      char c = scan.next().charAt(0);
      scan.close();
      SudokuBoard board = new SudokuBoard(size, c);
      board.print();
      long startTime = System.nanoTime();
      SudokuSolver.solveSudoku(board);
      long endTime = System.nanoTime();
      long durationInNano = endTime - startTime;
      double durationInMilli = durationInNano / 1e6;
      board.print();
      System.out.println();
      System.out.println("Duration: " + durationInMilli + " ms");
  }
}