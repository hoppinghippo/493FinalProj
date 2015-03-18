package eecs285.proj4.ships;

import eecs285.proj4.util.Constants;

public class Aircraft extends AbstractShip{

  public Aircraft(){
    super();
    setSize(Constants.AIRCRAFT_SIZE);
  }
  
  public Aircraft(int inStart, int endStart){
  	super(inStart, endStart);
  	setSize(Constants.AIRCRAFT_SIZE);
  }

}
