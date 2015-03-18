package eecs285.proj4.main;

import eecs285.proj4.Dialogs.WinningDialog;
import eecs285.proj4.GUI.OceanGUI;
import eecs285.proj4.GUI.WelcomeGUI;
import eecs285.proj4.player.Player;

import javax.swing.WindowConstants;

public class ShipWars{
  public static void main(String [] args){
    WelcomeGUI welcome = new WelcomeGUI("Ship Wars");
    welcome.pack();
    welcome.setVisible(true);
    welcome.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    /*WinningDialog win = new WinningDialog(false, "Dude");
    win.pack();
    win.setVisible(true);*/
    /*
	  Player Joe = new Player("jdkd", "127.0.0.1");
	  OceanGUI bob = new OceanGUI("jsj", Joe, "kss", "aks", "ksska");
	  bob.pack();
	  bob.setVisible(true); */
  }
}