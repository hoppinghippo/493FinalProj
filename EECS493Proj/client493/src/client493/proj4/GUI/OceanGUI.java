package client493.proj4.GUI;

import client493.proj4.Dialogs.WinningDialog;
import client493.proj4.GUI.ButtonGrid;
import client493.proj4.GUI.ButtonGrid.ButtonPanel;
import client493.proj4.player.Player;
import client493.proj4.player.PlayerSocket;
import client493.proj4.util.Constants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

@SuppressWarnings("serial")
public class OceanGUI extends JFrame{
	
  private JDialog winner;
  
  private static Player player;
  private static JButton fire = new JButton();
  
  private static ButtonGrid p1Map = new ButtonGrid();
  private static ButtonGrid p2Map = new ButtonGrid();
  private static ButtonGrid p3Map = new ButtonGrid();
  private static ButtonGrid p4Map = new ButtonGrid();
  
  public JPanel buttons = new JPanel();
  
  private static JLabel p1Name = new JLabel("Player One");
  private static JLabel p2Name = new JLabel("Player Two");
  private static JLabel p3Name = new JLabel("Player Three");
  private static JLabel p4Name = new JLabel("Player Four");
  
  private static JLabel p1Ships = new JLabel("Ships Remaining: 5");
  private static JLabel p2Ships = new JLabel("Ships Remaining: 5");
  private static JLabel p3Ships = new JLabel("Ships Remaining: 5");
  private static JLabel p4Ships = new JLabel("Ships Remaining: 5");
  
  private int shipsRemaining1;
  private int shipsRemaining2;
  private int shipsRemaining3;
  private int shipsRemaining4;
  
  public JTextArea textArea;
  
  private static JButton shield;
  private static JButton DoubleShots;
  private static JButton hidemisses;
  
  private String p1;
  private String p2;
  private String p3;
  private String p4;
  
  private boolean hide = false;
  private boolean doubleshot = false;
  private boolean shld = false;
  
  
  public OceanGUI(String title, Player inPlayer,
                  String inp2, String inp3, String inp4){
    super(title + " - " + inPlayer.getName());
    player = inPlayer;
    p1 = inPlayer.getName();
    p2 = inp2;
    p3 = inp3;
    p4 = inp4;
    shipsRemaining1 = 5;
    shipsRemaining2 = 5;
    shipsRemaining3 = 5;
    shipsRemaining4 = 5;
    
    
  
    
    JPanel oceans = new JPanel(new GridLayout(1,3));
    
    JPanel p1Container = new JPanel();
    JPanel p2Container = new JPanel();
    JPanel p3Container = new JPanel();
    JPanel p4Container = new JPanel();
    JPanel gifContainer = new JPanel();
  
    JPanel right = new JPanel();
    
    JPanel p1ShipsPanel = new JPanel();
    JPanel p2ShipsPanel = new JPanel();
    JPanel p3ShipsPanel = new JPanel();
    JPanel p4ShipsPanel = new JPanel();
        
    String radarPath = "client493/images/radar.gif";
    JLabel radar = new JLabel(new ImageIcon(getClass()
                                            .getClassLoader()
                                            .getResource(radarPath)));
    
    p1Container.setLayout(new BoxLayout(p1Container, BoxLayout.Y_AXIS));
    p2Container.setLayout(new BoxLayout(p2Container, BoxLayout.Y_AXIS));
    p3Container.setLayout(new BoxLayout(p3Container, BoxLayout.Y_AXIS));
    p4Container.setLayout(new BoxLayout(p4Container, BoxLayout.Y_AXIS));
    p1Container.setOpaque(false);
    p2Container.setOpaque(false);
    p3Container.setOpaque(false);
    p4Container.setOpaque(false);
    
//    p1Container.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 10));
    p2Container.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
    p3Container.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
    p4Container.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
    
    p1Name.setText(player.getName());
    p2Name.setText(p2);
    p3Name.setText(p3);
    p4Name.setText(p4);
    
    p1Name.setFont(new Font("Overlock-Regular", Font.PLAIN, 14));
    p1Name.setForeground(Color.RED);
    p2Name.setFont(new Font("Overlock-Regular", Font.PLAIN, 14));
    p2Name.setForeground(Color.WHITE);
    p3Name.setFont(new Font("Overlock-Regular", Font.PLAIN, 14));
    p3Name.setForeground(Color.WHITE);
    p4Name.setFont(new Font("Overlock-Regular", Font.PLAIN, 14));
    p4Name.setForeground(Color.WHITE);
    
    p1Ships.setFont(new Font("Overlock-Regular", Font.PLAIN, 14));
    p1Ships.setForeground(Color.RED);
    p2Ships.setFont(new Font("Overlock-Regular", Font.PLAIN, 14));
    p2Ships.setForeground(Color.WHITE);
    p3Ships.setFont(new Font("Overlock-Regular", Font.PLAIN, 14));
    p3Ships.setForeground(Color.WHITE);
    p4Ships.setFont(new Font("Overlock-Regular", Font.PLAIN, 14));
    p4Ships.setForeground(Color.WHITE);
    
    Dimension mapSize = new Dimension(320,320);
    Dimension playerMapSize = new Dimension(320,320);
    p1Map.setMinimumSize(playerMapSize);
    p1Map.setMaximumSize(playerMapSize);
    p1Map.setPreferredSize(mapSize);
    p2Map.setMinimumSize(mapSize);
    p2Map.setMaximumSize(mapSize);
    p2Map.setPreferredSize(mapSize);
    p3Map.setMinimumSize(mapSize);
    p3Map.setMaximumSize(mapSize);
    p3Map.setPreferredSize(mapSize);
    p4Map.setMinimumSize(mapSize);
    p4Map.setMaximumSize(mapSize);
    p4Map.setPreferredSize(mapSize);
    
    p1Container.add(p1Map);
    p1Container.add(p1ShipsPanel);
    p1ShipsPanel.setLayout(new BoxLayout(p1ShipsPanel, BoxLayout.X_AXIS));
    p1ShipsPanel.add(p1Name);
    p1ShipsPanel.add(Box.createHorizontalGlue());
    p1ShipsPanel.add(p1Ships);
    p1ShipsPanel.setSize(100, 100);
    
    p2Container.add(p2Map);
    p2Container.add(p2ShipsPanel);
    p2ShipsPanel.setLayout(new BoxLayout(p2ShipsPanel, BoxLayout.X_AXIS));
    p2ShipsPanel.add(p2Name);
    p2ShipsPanel.add(Box.createHorizontalGlue());
    p2ShipsPanel.add(p2Ships);
    
    p3Container.add(p3Map);
    p3Container.add(p3ShipsPanel);
    p3ShipsPanel.setLayout(new BoxLayout(p3ShipsPanel, BoxLayout.X_AXIS));
    p3ShipsPanel.add(p3Name);
    p3ShipsPanel.add(Box.createHorizontalGlue());
    p3ShipsPanel.add(p3Ships);
    
    p4Container.add(p4Map);
    p4Container.add(p4ShipsPanel);
    p4ShipsPanel.setLayout(new BoxLayout(p4ShipsPanel, BoxLayout.X_AXIS));
    p4ShipsPanel.add(p4Name);
    p4ShipsPanel.add(Box.createHorizontalGlue());
    p4ShipsPanel.add(p4Ships);
    
    p1ShipsPanel.setOpaque(false);
    p2ShipsPanel.setOpaque(false);
    p3ShipsPanel.setOpaque(false);
    p4ShipsPanel.setOpaque(false);
    
    Dimension labelSize = new Dimension(300,15);
    p1ShipsPanel.setMinimumSize(labelSize);
    p1ShipsPanel.setMaximumSize(labelSize);
    p1ShipsPanel.setPreferredSize(labelSize);
    p2ShipsPanel.setMinimumSize(labelSize);
    p2ShipsPanel.setMaximumSize(labelSize);
    p2ShipsPanel.setPreferredSize(labelSize);
    p3ShipsPanel.setMinimumSize(labelSize);
    p3ShipsPanel.setMaximumSize(labelSize);
    p3ShipsPanel.setPreferredSize(labelSize);
    p4ShipsPanel.setMinimumSize(labelSize);
    p4ShipsPanel.setMaximumSize(labelSize);
    p4ShipsPanel.setPreferredSize(labelSize);
    
   // oceans.add(p1Container);
    oceans.add(p2Container);
    oceans.add(p3Container);
    oceans.add(p4Container);
    oceans.setOpaque(false);
   // oceans.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 10));
    
    gifContainer.setLayout(new BoxLayout(gifContainer, BoxLayout.Y_AXIS));
    gifContainer.setOpaque(false);
    gifContainer.add(radar);
    //gifContainer.setBorder(BorderFactory.createEmptyBorder(95, 0, -5, 39));
    
    fire.setFont(new Font("Impact", Font.BOLD, 70));
    fire.setForeground(Color.WHITE);
    fire.setBackground(Color.RED);
   
   // fire.setContentAreaFilled(false);
    fire.setBorderPainted(false);
    FireListener thelistener = new FireListener();
    fire.addActionListener(thelistener);
   //.setAlignmentY(LEFT_ALIGNMENT);
    fire.setCursor(new Cursor(Cursor.HAND_CURSOR));
   // buttons.add(new Box(BoxLayout.X_AXIS));
   // buttons.add(fire);
   // buttons.setAlignmentY(RIGHT_ALIGNMENT);
//   buttons.setOpaque(false);
    fire.setText("FIRE");
 

   // right.add(gifContainer);
  //  right.add(Box.createRigidArea(new Dimension(100,300)));
    right.setLayout(new GridLayout(2, 1, 15, 15));
   
   // right.setBorder(BorderFactory.createEmptyBorder(20, -70, 0, 0));
    right.setOpaque(false);
   
    
    
    JPanel finalb = new JPanel();
    finalb.setLayout(new GridLayout(1, 3, 20, 20));
    
    Box backgroundFrame = new Box(BoxLayout.Y_AXIS);
    
    
    
    JLabel powerups = new JLabel("             Power Ups");
    powerups.setForeground(Constants.WHITE);
    powerups.setFont(new Font("Overlock-Regular", Font.BOLD, 22));
    shield = new JButton("SHIELD");
    DoubleShots = new JButton("DOUBLE AMMO");
    hidemisses = new JButton("HIDE MISSES");
    shield.setFont(new Font("Bebas", Font.BOLD, 16));
    shield.setForeground(new Color(30, 144, 255));
    shield.setOpaque(false);
    shield.setBorderPainted(true);
    shield.setContentAreaFilled(false);
    DoubleShots.setFont(new Font("Bebas", Font.BOLD, 16));
    DoubleShots.setForeground(new Color(30, 144, 255));
    DoubleShots.setOpaque(false);
    DoubleShots.setBorderPainted(true);
    DoubleShots.setContentAreaFilled(false);
    hidemisses.setFont(new Font("Bebas", Font.BOLD, 16));
    hidemisses.setForeground(new Color(30, 144, 255));
    hidemisses.setOpaque(false);
    hidemisses.setBorderPainted(true);
    hidemisses.setContentAreaFilled(false);
    hidemisses.addActionListener(new HideWhites());
    DoubleShots.addActionListener(new DoubleAmo());
    shield.addActionListener(new Shielding());
    JPanel bot = new JPanel();
    bot.setLayout(new GridLayout(4,1));
    bot.setSize(100, 10);
    bot.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    bot.add(powerups);
    bot.add(shield);
    bot.add(DoubleShots);
    bot.add(hidemisses);
  
 

    
    
    bot.setOpaque(false);
    backgroundFrame.add(oceans);
  //  backgroundFrame.add(rightWrapper);
    backgroundFrame.setOpaque(false);
    
    
    Dimension textSize = new Dimension(10,10);
    textArea = new JTextArea(20, 20);

    textArea.setEditable(false);
    textArea.setBackground(Constants.BLACK);
    textArea.setForeground(Constants.RED);
  //  textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    textArea.append("Initiating game...");
    textArea.setMaximumSize(textSize);
    textArea.setMinimumSize(textSize);
    textArea.setSize(textSize);
    JPanel textHolder = new JPanel();
    textHolder.setSize(30, 30);
    textHolder.add(textArea);
    textHolder.setBackground(Color.DARK_GRAY);
   // textWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    finalb.add(textHolder);
    finalb.add(p1Container);
    right.add(bot);
    right.add(fire);
    finalb.add(right);
    finalb.setOpaque(false);
    


    
    String imagePath = "client493/images/metal.jpg";
    JLabel background = new JLabel(new ImageIcon(getClass()
                                                 .getClassLoader()
         .getResource(imagePath)));
    
 //   JLabel background = new JLabel();
    backgroundFrame.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    background.setLayout(new FlowLayout());
    background.add(backgroundFrame);
    background.add(finalb);
    //setContentPane(background);
    JScrollPane hold = new JScrollPane(background);
    hold.setSize(1600, 1600);
    add(hold);
   
    
  //  background.setLayout(new FlowLayout());
    
   
    
    boolean[] ocean = player.getPlayerOcean();
    
    for(int i = 0; i < 100; ++i){
      if(ocean[i]){
        p1Map.setPanelColor(i, Constants.GREY, Constants.WHITE);
      }
      else{
        p1Map.setPanelColor(i, Constants.DARKER_BLUE, Constants.WHITE);
      }
    }
    
    
    
    
  }
  
  public boolean findWinner()
  {
	  if (shipsRemaining1 == 0 && shipsRemaining2 == 0 && shipsRemaining3 == 0 && shipsRemaining4 == 0)
	  {
		  winner = new WinningDialog(true, "tie");
		  dispose();
		  return false; 
	  }
	  else if (shipsRemaining1 == 0 && shipsRemaining2 == 0 && shipsRemaining3 == 0) // Winner is p4
	  {
		  winner = new WinningDialog(false, p4);
		  dispose();
		  return false; 
		  
	  }
	  else if (shipsRemaining1 == 0 && shipsRemaining2 == 0 && shipsRemaining4 == 0) // Winner is p3
	  {
		  winner = new WinningDialog(false, p3);
		  dispose();
		  return false; 
	  }
	  else if (shipsRemaining1 == 0 && shipsRemaining3 == 0 && shipsRemaining4 == 0) /// Winner is p2
	  {
		  winner = new WinningDialog(false, p2);
		  dispose();
		  return false; 
	  }
	  else if (shipsRemaining2 == 0 && shipsRemaining3 == 0 && shipsRemaining4 == 0) // Winner is p1
	  {
		  winner = new WinningDialog(false, p1);
		  dispose();
		  return false; 
	  }
	  return true;
  }
  
  public class Shielding implements ActionListener
  {
	  public void actionPerformed(ActionEvent e)
	  {
		  if (e.getSource() == shield)
		  {
			  // Implement
			  shld = true;
			  shield.setForeground(Color.RED);
			  shield.setEnabled(false);
		  }
	  }
  }
  public class DoubleAmo implements ActionListener
  {
	  public void actionPerformed(ActionEvent e)
	  {
		  if (e.getSource() == DoubleShots)
		  {
			  // Implement
			  doubleshot = true;
			  DoubleShots.setForeground(Color.RED);
			  DoubleShots.setEnabled(false);
		  }
	  }
  }
  public class HideWhites implements ActionListener
  {
	  public void actionPerformed(ActionEvent e)
	  {
		  if (e.getSource() == hidemisses)
		  {
			  // Implement
			  hide = true;
			  hidemisses.setForeground(Color.RED);
			  hidemisses.setEnabled(false);
		  }
	  }
  }
  
  public void sendMapShots(ButtonGrid pMap, int playerNumber){
    PlayerSocket playerSocket = player.getSocket();
    List<ButtonPanel> buttonPanels = pMap.getButtonPanels();
    for(ButtonPanel bp : buttonPanels){
      if(bp.getBackground() == Constants.YELLOW){
        String coordinate = bp.getButton().getText();
        int i = coordinate.charAt(0) - 'A' + 1;
        int j = Integer.parseInt(coordinate.substring(1));
        int index = (10 * (i - 1)) + j;
        playerSocket.sendShot(playerNumber, index);
      }
    }
  }
  
  public void sendShots(int count){
	  if (shipsRemaining1 > 0)
	  {
		  player.getSocket().sendCount(count);
		  sendMapShots(p2Map, 2);
		  sendMapShots(p3Map, 3);
		  sendMapShots(p4Map, 4);
	  }
	  else
	  {
		  player.getSocket().sendCount(0);
	  }
    
  }
  
  public void sendShipsRemaining(){
    player.updateShipsRemaining();
    player.getSocket().sendShipsRemaining(player.getShipsRemaining());
  }
  
  public void updateBoard(ButtonGrid pMap, boolean hitOrMiss, int index){
    if(hitOrMiss){
      pMap.getButtonPanels().get(index).setBackground(Constants.RED);
      pMap.getButtonPanels().get(index).getButton().setForeground(Constants.WHITE);
    }
    else{
      pMap.getButtonPanels().get(index).setBackground(Constants.WHITE);
      pMap.getButtonPanels().get(index).getButton().setForeground(Constants.BLACK);
    }
  }
  
  public void receiveShots(){
    PlayerSocket playerSocket = player.getSocket();
    int count = playerSocket.receiveCount();
    for(int i = 0; i < count; ++i){
      int playerNumber = playerSocket.receivePlayerNumber();
      boolean hitOrMiss = playerSocket.receiveHitOrMissValue();
      int index = playerSocket.receiveShot();
      ButtonGrid pMap;
      if(playerNumber == 1){
        pMap = p1Map;
        if(hitOrMiss){
          player.updateHits(index);
        }
      }
      else if(playerNumber == 2){
        pMap = p2Map;
      }
      else if(playerNumber == 3){
        pMap = p3Map;
      }
      else{
        pMap = p4Map;
      }
      updateBoard(pMap, hitOrMiss, index);
    }
  }
  
  public int checkShotCount(ButtonGrid pMap){
    int count = 0;
    List<ButtonPanel> buttonPanels = pMap.getButtonPanels();
    for(ButtonPanel bp : buttonPanels){
      if(bp.getBackground() == Constants.YELLOW){
        ++count;
      }
    }
    return count;
  }
  
  public void lockPlayerBoard(int shipsRemaining, int playerNumber){
    if(shipsRemaining == 0){
      if(playerNumber == 1){
        p1Ships.setText("Ships Remaining: " + shipsRemaining);
      }
      else if(playerNumber == 2){
        p2Ships.setText("Ships Remaining: " + shipsRemaining);
      }
      else if(playerNumber == 3){
        p3Ships.setText("Ships Remaining: " + shipsRemaining);
      }
      else if(playerNumber == 4){
        p4Ships.setText("Ships Remaining: " + shipsRemaining);
      }
    }
  }
  
  public void updateShipsRemaining(){
    PlayerSocket playerSocket = player.getSocket();
    for(int i = 0; i < 4; ++i){
      int playerNumber = playerSocket.receivePlayerNumber();
      int shipsRemaining = playerSocket.receiveShipsRemaining();
      if(playerNumber == 1){
        p1Ships.setText("Ships Remaining: " + shipsRemaining);
        shipsRemaining1 = shipsRemaining;
      }
      else if(playerNumber == 2){
        p2Ships.setText("Ships Remaining: " + shipsRemaining);
        shipsRemaining2 = shipsRemaining;
      }
      else if(playerNumber == 3){
        p3Ships.setText("Ships Remaining: " + shipsRemaining);
        shipsRemaining3 = shipsRemaining;
      }
      else if(playerNumber == 4){
        p4Ships.setText("Ships Remaining: " + shipsRemaining);
        shipsRemaining4 = shipsRemaining;
      }
      if(shipsRemaining == 0)
      {
    	  if (playerNumber == 1)
    	  {
    		  setDarker(2);
    		  setDarker(3);
    		  setDarker(4);
    	  }
          setLoserBoard(playerNumber);
      }
    }
  }
  public void setDarker(int playernum)
  {
	  if (playernum == 2)
	  {
		  for (int i = 0; i < 100; ++i)
		  {
			  if(p2Map.getButtonPanels().get(i).getBackground().equals(Constants.DARK_BLUE))
	          {
	            p2Map.setPanelColor(i, Constants.DARKER_BLUE, Constants.WHITE);
	          }
		  }
	  }
	  if (playernum == 3)
	  {
		  for (int i = 0; i < 100; ++i)
		  {
			  if(p3Map.getButtonPanels().get(i).getBackground().equals(Constants.DARK_BLUE))
	          {
	            p3Map.setPanelColor(i, Constants.DARKER_BLUE, Constants.WHITE);
	          }
		  }
	  }
	  if (playernum == 4)
	  {
		  for (int i = 0; i < 100; ++i)
		  {
			  if(p4Map.getButtonPanels().get(i).getBackground().equals(Constants.DARK_BLUE))
	          {
	            p4Map.setPanelColor(i, Constants.DARKER_BLUE, Constants.WHITE);
	          }
		  }
	  }
  }
  
  public void checkifdead()
  {
	  if(shipsRemaining1==0)
	  {
		  boolean notover = true;
		  while(notover)
		  {
			  player.getSocket().sendNoPower();
			  sendShots(0);
			  receiveShots();
			  sendShipsRemaining();
			  updateShipsRemaining();
			  recievePower();
			  notover = findWinner();
		  }
	  }
  }
  
  public class FireListener implements ActionListener{
	  
    public void actionPerformed(ActionEvent e){
      if(e.getSource() == fire){
        int count = 0;
        count += checkShotCount(p2Map);
        count += checkShotCount(p3Map);
        count += checkShotCount(p4Map);
        if(!doubleshot && count > Constants.MAX_SHOTS ){
          JOptionPane.showMessageDialog(null, "Too many shots. Please select a maximum of " + Constants.MAX_SHOTS + " coordinates to send.", "Too many shots.", JOptionPane.ERROR_MESSAGE);
          return;
        }
        else if (doubleshot && count > (Constants.MAX_SHOTS * 2)){
           JOptionPane.showMessageDialog(null, "Too many shots. Please select a maximum of " + Constants.MAX_SHOTS * 2 + " coordinates to send.", "Too many shots.", JOptionPane.ERROR_MESSAGE);
        }
        else if(count <= Constants.MAX_SHOTS || (doubleshot && count <= 10)){
          fire.setEnabled(true);
          fire.setVisible(false);
          fire.setText("...");
          textArea.append("\n Waiting for other players to fire...\n");
          sendPower(); 
          sendShots(count);
          receiveShots();
          sendShipsRemaining();
          updateShipsRemaining();
          recievePower();
          findWinner();
          checkifdead();
          fire.setVisible(true);
          fire.setText("FIRE");
          fire.setEnabled(false);
        }
      }
    }
  }
  
  public void setLoserBoard(int playerNumber){
    if(playerNumber == 1){
      p1Ships.setText("No Ships Remaining");
      p1Ships.setForeground(Constants.YELLOW);
      for(int i = 0; i < 100; ++i){
    	  Color c = p1Map.getButtonPanels().get(i).getBackground();
    	  p1Map.setPanelColor(i, c, Constants.YELLOW);
      }
    }
    else if(playerNumber == 2){
      p2Ships.setText("No Ships Remaining");
      p2Ships.setForeground(Constants.YELLOW);
      for(int i = 0; i < 100; ++i)
      {
    	  Color c = p2Map.getButtonPanels().get(i).getBackground();
    	  p2Map.setPanelColor(i, c, Constants.YELLOW);
          if(p2Map.getButtonPanels().get(i).getBackground().equals(Constants.DARK_BLUE))
          {
            p2Map.setPanelColor(i, Constants.DARKER_BLUE, Constants.YELLOW);
          }
      }
      
    }
    else if(playerNumber == 3){
      p3Ships.setText("No Ships Remaining");
      p3Ships.setForeground(Constants.YELLOW);
      for(int i = 0; i < 100; ++i)
      {
    	  Color c = p3Map.getButtonPanels().get(i).getBackground();
    	  p3Map.setPanelColor(i, c, Constants.YELLOW);
          if(p3Map.getButtonPanels().get(i).getBackground().equals(Constants.DARK_BLUE))
          {
            p3Map.setPanelColor(i, Constants.DARKER_BLUE, Constants.YELLOW);
          }
      }
    }
    else if(playerNumber == 4){
      p4Ships.setText("No Ships Remaining");
      p4Ships.setForeground(Constants.YELLOW);
      for(int i = 0; i < 100; ++i)
      {
    	  Color c = p4Map.getButtonPanels().get(i).getBackground();
    	  p4Map.setPanelColor(i, c, Constants.YELLOW);
          if(p4Map.getButtonPanels().get(i).getBackground().equals(Constants.DARK_BLUE))
          {
            p4Map.setPanelColor(i, Constants.DARKER_BLUE, Constants.YELLOW);
          }
      }
      
    }
  }
  
  public void sendPower(){
	  boolean sendHide = false;
	  boolean sendshot = false;
	  boolean sendshld = false; 
	  boolean send = false; 	  
	  if(hide){
		  hide = false;
		  send = true; 
		  sendHide = true;
		  
	  }
	  if(doubleshot){
		  doubleshot = false; 
		  send = true;
		  sendshot = true;
	  }
	  if(shld){
		  shld = false; 
		  send = true; 
		  sendshld = true;
	  }
	  if(send){
		  player.getSocket().sendPowerUp(sendshld, sendHide, sendshot);
	  }
	  else{
		  player.getSocket().sendNoPower();
	  }
  }
  
  public void clearWhites(int playerNumber)
  {
	  if(playerNumber == 1)
	  {
		for(int i = 0; i < 100; ++i)
		{
		  if(p1Map.getButtonPanels().get(i).getBackground().equals(Constants.WHITE))
		  {
			p1Map.setPanelColor(i, Constants.DARK_BLUE, Constants.WHITE);
		  }
		}
	  }
	  else if(playerNumber == 2)
	  {
		  for(int i = 0; i < 100; ++i)
			{
			  if(p2Map.getButtonPanels().get(i).getBackground().equals(Constants.WHITE))
			  {
				p2Map.setPanelColor(i, Constants.DARK_BLUE, Constants.WHITE);
			  }
			}
	  }
	  else if(playerNumber == 3)
	  {
		  for(int i = 0; i < 100; ++i)
			{
			  if(p3Map.getButtonPanels().get(i).getBackground().equals(Constants.WHITE))
			  {
				p3Map.setPanelColor(i, Constants.DARK_BLUE, Constants.WHITE);
			  }
			}
	  }
	  else if(playerNumber == 4)
	  {
		  for(int i = 0; i < 100; ++i)
			{
			  if(p4Map.getButtonPanels().get(i).getBackground().equals(Constants.WHITE))
			  {
				p4Map.setPanelColor(i, Constants.DARK_BLUE, Constants.WHITE);
			  }
			}
     }
  }
  
  public void recievePower(){
	    PlayerSocket playerSocket = player.getSocket();
	    int playerNumber;
	    boolean hide2 = false;
	    boolean shield = false;
	    boolean shot = false; 
	    for(int i = 0; i < 3; i++){
	    	
	    	try{
	    		playerNumber = playerSocket.getInputStream().readInt();
	    		hide2 = playerSocket.getInputStream().readBoolean();
	    		shield = playerSocket.getInputStream().readBoolean();
	    		shot = playerSocket.getInputStream().readBoolean();
	    		
	    		String tempName = "";
  			if(playerNumber == 1)
  			{
  			  tempName = p1;
  			}
  			else if(playerNumber == 2)
  			{
  			  tempName = p2;
  			}
  			else if(playerNumber == 3){
  			  tempName = p3;
  			}
  			else if(playerNumber == 4){
  				tempName = p4;
  			}
	    		
	    		if(hide2){
	    			clearWhites(playerNumber);
	    			String theString = tempName + " hid all of their misses!\n"; 
	    			textArea.append(theString);
	    			
	    		}
	    		if(shield){
	    		  String theString =  tempName + " used their SHEILD powerup!\n" + 
	    												"All attacks to player " + tempName + 
	    												" missed\n."; 
	    		  textArea.append(theString);
	    		}
	    		if(shot){
	    		  String theString = tempName + " used their DOUBLE AMMO powerup!\n" + 
							"player " + tempName + 
							" can use up to 10 shots\n";
	    		  textArea.append(theString);
	    		}
	    	
	    	}catch(IOException e){

	    	}
	    	
	    	
	    }
	  
}
  
  
  
}