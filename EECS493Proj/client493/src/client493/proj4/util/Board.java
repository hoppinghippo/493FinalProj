package client493.proj4.util;

import static java.lang.System.out;
import java.util.Arrays;

import javax.swing.JTextArea;

public class Board
{
    //Variables
    final int BOARD_SIZE = 10; 
    protected char[][] map;

    
    //This is the constructor
    public Board(){
        map = new char[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE; ++i){
          Arrays.fill(map[i], 'e');
        }
        printBoard();
    }
    
    //Checks if position (x,y) is within board
    protected boolean validSpot(final int x, final int y){
      if(x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE)
        return true;
      return false;
    }
    
    //Checks if can place piece 'p' with (x,y) top left corner
    //checks validity of all parts of each piece
    public boolean validPlace(final int x, final int y, final Piece p){
      if(p.placed)
        return false;
      for(int i = 0; i < p.PIECE_SIZE; ++i){
        for(int j = 0; j < p.PIECE_SIZE; ++j){
          if(p.isPart(i, j)){
            if(!validSpot(x+i, y+j) || map[x+i][y+j] != 'e')
              return false;
          }
        }
      }
      return true;
    }
    
     //Places piece 'p' with (x,y) top left corner
     //does not check if this is a validPlace
     //This function should only be called when server sends status update
    public void placePiece(final int x, final int y, final Piece p){
      for(int i = 0; i < p.PIECE_SIZE; ++i){
        for(int j = 0; j < p.PIECE_SIZE; ++j){
          if(p.isPart(i, j))
            map[x+i][y+j] = p.getColor();
        }
      }
    }

    //For debugging purposes prints map to screen
    public void printBoard(){
      out.println("    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9");
      for(int i = 0; i < BOARD_SIZE; ++i){
        if(i<10)
          out.print(i + " | ");
        else 
          out.print((i-10) + " | ");
        for(int j = 0; j < BOARD_SIZE; ++j){
          switch(map[i][j]){
            case 'b':
              out.print("B ");
              break;
            case 'g':
              out.print("G ");
              break;
            case 'r':
              out.print("R ");
              break;
            case 'y':
              out.print("Y ");
              break;
            case 'e':
              out.print(". ");
              break;
            default:
              out.print(map[i][j] + " ");
              break;
          }
        }
        out.print("\n");
      }
      out.print("\n");
    }
    
    public void printBoard(JTextArea terminal){
      printBoard();
      for(int i = 0; i < BOARD_SIZE; ++i){
        for(int j = 0; j < BOARD_SIZE; ++j){
          switch(map[i][j]){
            case 'b':
              terminal.append("B ");
              break;
            case 'g':
              terminal.append("G ");
              break;
            case 'r':
              terminal.append("R ");
              break;
            case 'y':
              terminal.append("Y ");
              break;
            case 'e':
              terminal.append("_ ");
              break;
            default:
              terminal.append(map[i][j] + " ");
              break;
          }
        }
        terminal.append("\n");
      }
      terminal.append("\n");
      terminal.setCaretPosition(terminal.getText().length() - 1);
    }
}
