package client493.proj4.ships;

import client493.proj4.util.Constants;

public class Cruiser extends AbstractShip{

  public Cruiser(){
    super();
    setSize(Constants.CRUISER_SIZE);
  }
  
  public Cruiser(int inStart, int endStart){
    super(inStart, endStart);
    setSize(Constants.CRUISER_SIZE);
  }

}