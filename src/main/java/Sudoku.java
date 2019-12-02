import java.util.*;

class Sudoku {
    // just in case you need tis method for testing
    public static void main(String[] args) {
        char[][] puzzle = SudokuP.puzzle();
        solve(puzzle);
    }

    // print out one solution of the given puzzle
    // accepted parameter(s): 2D char array representing a sudoku board
    public static void solve(char[][] puzzle) {
        if(puzzle == null || puzzle.length == 0)
            return;
        if (solveSudoku(puzzle)) {
          for (int j = 0; j < puzzle.length; j++) {
               for (int m = 0; m < puzzle[j].length; m++) {
                        System.out.print(puzzle[j][m]);
               }
               System.out.println("");
          }
        } else {
            System.out.print("This puzzle is not solvable.");
        }
    }



    // solve a given sudoku puzzle board
    // additionally, return true if solvable; otherwise return false
    // accepted parameter(s): a 2D char array representing a sudoku board
    // return type: boolean
    // NOTE: you can assume that only valid sudoku board will be given as parameters to this method
    public static boolean solveSudoku(char[][] puzzle){
        if (canSolve(0, 0, puzzle)) {
          return true;
        }
        return false;
    }



    // check if a given sudoku puzzle board is valid or not
    // return true if valid; otherwise return false
    // accepted parameter(s): a 2D char array representing a sudoku board
    // return type: boolean
    public static boolean check(char[][] puzzle) {
      // Checks all vertical and horizontal positions
      for (int j = 0; j < 9; j++) {
        if (isParticallyValid(puzzle, j, 0, j, 8) && isParticallyValid(puzzle, 0, j, 8, j)) {
          continue;
        } else {
          return false;
        }
      }
      for (int l = 0; l <= 6; l += 3) {
        if (isParticallyValid(puzzle, l, 0, l + 2, 2) && isParticallyValid(puzzle, l, 3, l + 2, 5) && isParticallyValid(puzzle, l, 6, l + 2, 8)) {
          continue;
        } else {
          return false;
        }
      }
      return true;
  }



    // check if the specified area of the given sudoku board is valid
    // valid - following the 3 rules of sudoku
    // accepted parameters: puzzle - standing for a sudoku board in the representation of a 2D char array
    //                      four integers x1, y1, x2, y2
    //                      (x1, y1) stands for the top left corner of the area (inclusive)
    //                       x1 - row index; y1 - column index
    //                      (x2, y2) stands for the bottom right corner of the area (inclusive)
    //                       x2 - row index; y2 - colum index
    // return data type: boolean
    // if the specified area is valid, return true; otherwise false
    // e.g.1, isParticallyValid(puzzle,0,0,0,8) is used to check the 1st row of puzzle
    // e.g.2, isParticallyValid(puzzle,0,0,8,0) is used to check the 1st column of puzzle
    // e.g.3, isParticallyValid(puzzle,0,0,2,2) is used to check the top left 3*3 area
    // NOTE that this method will only be applied to every row, every column, and every 3*3 small areas (9 small areas in total)
    public static boolean isParticallyValid(char[][] puzzle, int x1, int y1,int x2,int y2){
             ArrayList<Character> list2 = new ArrayList<Character>();
             for (int row = x1; row <= x2; row++) {
                 for (int column = y1; column <= y2; column++) {
                     if (puzzle[row][column] == '.') {
                         continue;
                     } else {
                         if (list2.size() > 0) {
                             for (int i = 0; i < list2.size(); i++) {
                                 if (puzzle[row][column] == list2.get(i)) {
                                     return false;
                                 }
                             }
                         }
                         list2.add(puzzle[row][column]);
                     }
                 }
             }
             return true;
         }



    // check whether putting a digit c at the position (x, y) in a given sudoku board
    // will make the board invalid
    // accepted parameters: puzzle - standing for a sudoku board in the representation of a 2D char array
    //                      two integers x, y
    //                      x - row index; y - column index
    //                      c - a digit in the form of char to put at (x, y)
    // return data type: boolean
    // if putting c in puzzle is a valid move, return true; otherwise false
    public static boolean isSpotValid(char[][] puzzle, int row, int col, char c){
      if (puzzle[row][col] != c) {
        puzzle[row][col] = c;
        if (isParticallyValid(puzzle, 0, col, 8, col) && isParticallyValid(puzzle, row, 0, row, 8)) {
          if (row < 3) {
            if (col < 3) {
              if (isParticallyValid(puzzle, 0, 0, 2, 2)) {
                puzzle[row][col] = '.';
                return true;
              }
            } else if (col < 6) {
              if (isParticallyValid(puzzle, 0, 3, 2, 5)) {
                puzzle[row][col] = '.';
                return true;
              }
            } else {
              if (isParticallyValid(puzzle, 0, 6, 2, 8)) {
                puzzle[row][col] = '.';
                return true;
              }
            }
          } else if (row < 6) {
            if (col < 3) {
              if (isParticallyValid(puzzle, 3, 0, 5, 2)) {
                puzzle[row][col] = '.';
                return true;
              }
            } else if (col < 6) {
              if (isParticallyValid(puzzle, 3, 3, 5, 5)) {
                puzzle[row][col] = '.';
                return true;
              }
            } else {
              if (isParticallyValid(puzzle, 3, 6, 5, 8)) {
                puzzle[row][col] = '.';
                return true;
              }
            }
          } else {
            if (col < 3) {
              if (isParticallyValid(puzzle, 6, 0, 8, 2)) {
                puzzle[row][col] = '.';
                return true;
              }
            } else if (col < 6) {
              if (isParticallyValid(puzzle, 6, 3, 8, 5)) {
                puzzle[row][col] = '.';
                return true;
              }
            } else {
              if (isParticallyValid(puzzle, 6, 6, 8, 8)) {
                puzzle[row][col] = '.';
                return true;
              }
            }
          }
    } else {
      puzzle[row][col] = '.';
      return false;
    }
  }
    return true;
  }



    // you are welcome to add more methods
    private static boolean canSolve(int row, int col, char[][] puzzle) {
      if (col == puzzle[row].length) {
        col = 0;
        row++;

        if (row == puzzle.length) {
          return true;
        }
      }
      if (puzzle[row][col] != '.') {
        return canSolve(row, col + 1, puzzle);
      }
      for (int value = 1; value <= puzzle.length; value++) {
        char charToPlace = (char) (value + '0');
        if (isSpotValid(puzzle, row, col, charToPlace)) {
          puzzle[row][col] = charToPlace;
          if (canSolve(row, col + 1, puzzle)) {
            return true;
          }
          puzzle[row][col] = '.';
        }
      }
      return false;
    }
}
