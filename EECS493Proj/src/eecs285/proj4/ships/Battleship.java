package eecs285.proj4.ships;

import eecs285.proj4.util.Constants;

public class Battleship extends AbstractShip{

  public Battleship(){
    super();
    setSize(Constants.BATTLESHIP_SIZE);
  }
  
  public Battleship(int inStart, int endStart){
    super(inStart, endStart);
    setSize(Constants.BATTLESHIP_SIZE);
  }

}