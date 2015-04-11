package client493.proj4.ships;

import client493.proj4.util.Constants;

public class Submarine extends AbstractShip{

  public Submarine(){
    super();
    setSize(Constants.SUBMARINE_SIZE);
  }
  
  public Submarine(int inStart, int endStart){
    super(inStart, endStart);
    setSize(Constants.SUBMARINE_SIZE);
  }

}