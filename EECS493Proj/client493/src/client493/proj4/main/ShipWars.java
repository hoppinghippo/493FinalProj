package client493.proj4.main;

import client493.proj4.Dialogs.WinningDialog;
import client493.proj4.GUI.OceanGUI;
import client493.proj4.GUI.WelcomeGUI;
import client493.proj4.player.Player;

import client493.proj4.GUI.*;
import java.awt.Dimension;

import javax.swing.WindowConstants;

public class ShipWars{
  public static void main(String [] args){
    WelcomeGUI welcome = new WelcomeGUI("Ship Wars");
    welcome.pack();
    welcome.setVisible(true);
    welcome.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
	
	/*TROY'S Testing
	Player player = new Player("Boo", "0.0.0.0");
    
    final SetupShips setup = new SetupShips("ShipWars Matchmaking", player);
    setup.pack();
    setup.setVisible(true);
    setup.setMinimumSize(new Dimension(668, 396));
    setup.setMaximumSize(new Dimension(668, 396));
    setup.setPreferredSize(new Dimension(668, 396));
    setup.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    */
	  
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