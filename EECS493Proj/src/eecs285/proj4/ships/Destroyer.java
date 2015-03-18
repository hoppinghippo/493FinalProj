package eecs285.proj4.ships;

import eecs285.proj4.util.Constants;

public class Destroyer extends AbstractShip{

  public Destroyer(){
    super();
    setSize(Constants.DESTROYER_SIZE);
  }
  
  public Destroyer(int inStart, int endStart){
    super(inStart, endStart);
    setSize(Constants.DESTROYER_SIZE);
  }

}