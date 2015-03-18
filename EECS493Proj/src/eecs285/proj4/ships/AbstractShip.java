package eecs285.proj4.ships;

public abstract class AbstractShip{
  
  // ----------------------------[Data Members]---------------------------------
  private int startIndex;
  private int endIndex;
  int[] locations = new int[5];
  boolean[] hits = new boolean[5];
  private int numHits;
  private int size;

  //----------------------------[Class Methods]---------------------------------
  public AbstractShip(){
    startIndex = -1;
    endIndex = -1;
    numHits = 0;
    size = 0;
    resetHits();
    resetLocations();
  }
  
  public AbstractShip(int inStart, int inEnd){
    startIndex = inStart;
    endIndex = inEnd;
    numHits = 0;
    size = 0;
    resetHits();
    resetLocations();
  }
  
  public void setLocations(){
    
    int start = getStartIndex();
    int end = getEndIndex();
    int min = Math.min(start, end);
    resetLocations();
    resetHits();
    
    // if horizontal
    if(Math.abs(end - start) < 10){
      for(int i = 0; i < size; ++i){
        locations[i] = min;
        hits[i] = false;
        ++min;
      }
    }
    // if vertical
    else{
      for(int i = 0; i < size; ++i){
        locations[i] = min;
        hits[i] = false;
        min += 10;
      }
    }
  }
  
  public int[] getLocations(){
    return locations;
  }
  
  public boolean[] getHits(){
    return hits;
  }

  public void setStartIndex(int inStart){
    startIndex = inStart;
  }

  public int getStartIndex(){
    return startIndex;
  }

  public void setEndIndex(int inEnd){
    endIndex = inEnd;
  }

  public int getEndIndex(){
    return endIndex;
  }

  public boolean getSunk(){
    return (numHits >= size);
  }

  public int getNumHits(){
    return numHits;
  }
  
  public int getSize(){
    return size;
  }
  
  public void setSize(int inSize){
    size = inSize;
  }
  
  public void incrementHits(){
    if(numHits < size){
      ++numHits;
    }
  }
  
  public void resetHits(){
    for(int i = 0; i < 5; ++i){
      hits[i] = false;
    }
  }
  
  public void resetLocations(){
    for(int i = 0; i < 5; ++i){
      locations[i] = -1;
    }
  }
  public void resetIndices(){
	    startIndex = -1;
	    endIndex = -1;
	  }
  
}
