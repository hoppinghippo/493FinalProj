package eecs285.proj4.player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

public class PlayerSocket{
  
  private String ipAddress;
  private int portNumber;
  private Socket socket;
  private DataOutputStream outData;
  private DataInputStream inData;
  
  public PlayerSocket(String inIPAddress, int inPortNumber){
    ipAddress = inIPAddress;
    portNumber = inPortNumber;
    try{
      socket = new Socket(ipAddress, portNumber);
      outData = new DataOutputStream(socket.getOutputStream());
      inData = new DataInputStream(socket.getInputStream());
    }
    catch(IOException excep){}
    
  }
  
  public void sendShipPlacement(boolean shipPlacement){
    try{
      outData.writeBoolean(shipPlacement);
    }
    catch(IOException excep){}
  }
  
  public void sendCount(int count){
    try{
      outData.writeInt(count);
    }
    catch(IOException excep){}
  }
  
  public void sendShot(int playerNumber, int index){
    try{
      outData.writeInt(playerNumber);
      outData.writeInt(index);
    }
    catch(IOException excep){}
  }
  
  public int receiveShot(){
    int index = -1;
    try{
      index = inData.readInt();
    }
    catch(IOException excep){}
    return index;
  }
  
  public void sendHitOrMiss(int id, boolean hitOrMiss, int index){
    try{
      outData.writeInt(id);
      outData.writeBoolean(hitOrMiss);
      outData.writeInt(index);
    }
    catch(IOException excep){}
  }
  
  public boolean receiveHitOrMissValue(){
    boolean hitOrMiss = false;
    try{
      hitOrMiss = inData.readBoolean();
    }
    catch(IOException excep){}
    return hitOrMiss;
  }
  
  public int receiveCount(){
    int count = -1;
    try{
      count = inData.readInt();
    }
    catch(IOException excep){}
    return count;
  }
  
  public int receiveHitOrMissIndex(){
    int index = -1;
    try{
      index = inData.readInt();
    }
    catch(IOException excep){}
    return index;
  }
  
  public int receivePlayerNumber(){
    int playerNumber = -1;
    try{
      playerNumber = inData.readInt();
    }
    catch(IOException excep){}
    return playerNumber;
  }

  public Socket getSocket(){
    return socket;
  }
  
  public void sendPlayerReady(){
    try{
      outData.writeBoolean(true);
    }
    catch(IOException excep){}
  }
  
  public boolean receivePlayerReady(){
    boolean index = false;
    try{
      index = inData.readBoolean();
    }
    catch(IOException excep){}
    catch(NullPointerException excep){
      JOptionPane.showMessageDialog(null,
                                    "Could not find a server.",
                                    "Error!",
                                    JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
    return index;
  }
  
  public void sendShipsRemaining(int shipsRemaining){
    try{
      outData.writeInt(shipsRemaining);
    }
    catch(IOException excep){}
  }
  
  public int receiveShipsRemaining(){
    int shipsRemaining = -1;
    try{
      shipsRemaining = inData.readInt();
    }
    catch(IOException excep){}
    return shipsRemaining;
  }
  
  public void sendPlayerName(String name){
    try{
      outData.writeUTF(name);
    }
    catch(IOException excep){}
  }
  
  public String receivePlayerName(){
    String playerName = "";
    try{
      playerName = inData.readUTF();
    }
    catch(IOException excep){}
    return playerName;
  }
  public DataInputStream getInputStream(){
	  return inData;
  }
  
  
  public void sendPowerUp(boolean hide, boolean white, boolean shield){
	    try{
	      outData.writeBoolean(true);
	      outData.writeBoolean(hide);
	      outData.writeBoolean(white);
	      outData.writeBoolean(shield);
	 
	    }
	    catch(IOException excep){}
  }
  public void sendNoPower(){
	  try{	 
		  boolean nottrue = false; 
		  outData.writeBoolean(nottrue);
	  }catch(IOException e){}
  }
}