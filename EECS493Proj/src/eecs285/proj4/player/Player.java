package eecs285.proj4.player;

import eecs285.proj4.ships.*;
import eecs285.proj4.util.Constants;

public class Player{
  
  private String name;
    
  private boolean ocean[];
  private Aircraft aircraft;
  private Battleship battleship;
  private Cruiser cruiser;
  private Destroyer destroyer;
  private Submarine submarine;
  
  private int shipsRemaining;
  
  private PlayerSocket socket;
  
  public Player(String inName, String inServer){
	  name = inName;
	  aircraft = new Aircraft();
	  battleship = new Battleship();
	  cruiser = new Cruiser();
	  destroyer = new Destroyer();
	  submarine = new Submarine();
	  ocean = new boolean[100];
	  for(int i = 0; i < 99; ++i){
	    ocean[i] = false;
	  }
	  shipsRemaining = 5;
	  
    socket = new PlayerSocket(inServer, Constants.PORT);
  }
  
  public boolean[] getPlayerOcean(){
    return ocean;
  }

  public Aircraft getAircraft(){
	  return aircraft;
  }
  public Battleship getBattleship(){
	  return battleship;
  }
  public Cruiser getCruiser(){
	  return cruiser;
  }
  public Destroyer getDestroyer(){
	  return destroyer;
  }
  public Submarine getSubmarine(){
	  return submarine;
  }
  
  public void setOcean(){
    for(int i : aircraft.getLocations()){
      ocean[i] = true;
    }
    for(int i : battleship.getLocations()){
      ocean[i] = true;
    }
    for(int i : cruiser.getLocations()){
      ocean[i] = true;
    }
    for(int i : destroyer.getLocations()){
      ocean[i] = true;
    }
    for(int i : submarine.getLocations()){
      ocean[i] = true;
    }
  }
  
  public PlayerSocket getSocket(){
    return socket;
  }
  
  public String getName(){
    return name;
  }
  
  public void updateHits(int index){
    int[] locations = aircraft.getLocations();
    boolean[] hits = aircraft.getHits();
    for(int i = 0; i < aircraft.getSize(); ++i){
      if(index == locations[i] && hits[i] == false){
        hits[i] = true;
        aircraft.incrementHits();
        i = -1;
        return;
      }
    }
    locations = battleship.getLocations();
    hits = battleship.getHits();
    for(int i = 0; i < battleship.getSize(); ++i){
      if(index == locations[i] && hits[i] == false){
        hits[i] = true;
        battleship.incrementHits();
        i = -1;
        return;
      }
    }
    locations = cruiser.getLocations();
    hits = cruiser.getHits();
    for(int i = 0; i < cruiser.getSize(); ++i){
      if(index == locations[i] && hits[i] == false){
        hits[i] = true;
        cruiser.incrementHits();
        i = -1;
        return;
      }
    }
    locations = destroyer.getLocations();
    hits = destroyer.getHits();
    for(int i = 0; i < destroyer.getSize(); ++i){
      if(index == locations[i] && hits[i] == false){
        hits[i] = true;
        destroyer.incrementHits();
        i = -1;
        return;
      }
    }
    locations = submarine.getLocations();
    hits = submarine.getHits();
    for(int i = 0; i < submarine.getSize(); ++i){
      if(index == locations[i] && hits[i] == false){
        hits[i] = true;
        submarine.incrementHits();
        i = -1;
        return;
      }
    }
  }
  
  public void updateShipsRemaining(){
	shipsRemaining = 5;
    if(aircraft.getSunk() && shipsRemaining > 0){
      --shipsRemaining;
    }
    if(battleship.getSunk() && shipsRemaining > 0){
      --shipsRemaining;
    }
    if(cruiser.getSunk() && shipsRemaining > 0){
      --shipsRemaining;
    }
    if(destroyer.getSunk() && shipsRemaining > 0){
      --shipsRemaining;
    }
    if(submarine.getSunk() && shipsRemaining > 0){
      --shipsRemaining;
    }
  }
  
  public int getShipsRemaining(){
    return shipsRemaining;
  }
}