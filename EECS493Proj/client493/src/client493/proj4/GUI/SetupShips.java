package client493.proj4.GUI;

import client493.proj4.ships.*;
import client493.proj4.player.*;
import client493.proj4.util.Constants;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class SetupShips extends JFrame {
  
  private DefaultListModel<String> shipListModel;
  private JList<String> shipList;
  private JScrollPane shipScrollPane;
  // private JTextArea shipTextArea;
  private int shipIndex;
  private String selected;
  
  private int playersReady;
  
  private int buttonIndex;
  private final ArrayList<ButtonPanel> buttonPanels = 
                                                   new ArrayList<ButtonPanel>();
  private Player player;  
  
  private JButton clear;
  private JButton ready;
  private JButton random;

  //ship control
  private JButton rotate;
  private JButton set;
  
  private JLabel search = new JLabel("", SwingConstants.LEFT);
  
  public SetupShips(String title, Player inPlayer){
    super(title + " - " + inPlayer.getName());
    
    playersReady = 0;
    shipIndex = -1;
    buttonIndex = -1;
    selected = "";
    player = inPlayer;
    
    //create ship list
    shipListModel = addShips();
    shipList = new JList<String>(shipListModel);
    
    setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
    
    JLabel shipLabel = new JLabel("Available ships", SwingConstants.LEFT);
    shipLabel.setHorizontalAlignment(SwingConstants.LEFT);
    shipLabel.setFont(new Font("Overlock-Regular", Font.PLAIN, 16));
    shipLabel.setForeground(Color.WHITE);
    
    JLabel gridLabel = new JLabel("Ship placement", SwingConstants.LEFT);
    gridLabel.setHorizontalAlignment(SwingConstants.LEFT);
    gridLabel.setFont(new Font("Overlock-Regular", Font.PLAIN, 16));
    gridLabel.setForeground(Color.WHITE);
    
    search.setHorizontalAlignment(SwingConstants.LEFT);
    search.setText("Searching for players online... 0/4 players ready.");
    search.setOpaque(false);
    search.setFont(new Font("Overlock-Regular", Font.PLAIN, 18));
    search.setForeground(Color.WHITE);
    
    shipList.addMouseListener(new MouseListener());
    shipScrollPane = new JScrollPane(shipList);
    shipScrollPane.setMinimumSize(new Dimension(183, 279));
    shipScrollPane.setMaximumSize(new Dimension(183, 279));
    shipScrollPane.setPreferredSize(new Dimension(183, 279));
    // shipTextArea = new JTextArea(183, 279);
    // Font overlock = new Font("Overlock-Regular", Font.PLAIN, 13);
    // shipTextArea.setFont(overlock);
    // shipTextArea.add(shipScrollPane);
    
    clear = new JButton("Clear");
    clear.setFont(new Font("Sans Serif", Font.PLAIN, 13));
    clear.addActionListener(new ClearListener());
    
    random = new JButton("Random");
    random.setFont(new Font("Sans Serif", Font.PLAIN, 13));
    random.addActionListener(new RandomListener());
    
    ready = new JButton("Ready");
    ready.setFont(new Font("Sans Serif", Font.PLAIN, 13));
    ready.addActionListener(new ReadyListener());
    ready.setEnabled(false);
    
    JPanel buttonGrid = new JPanel();
    buttonGrid.setLayout(new GridLayout(10,10));
    char letter = 'A';
    for(int i = 0; i < 10; ++i){
      for(int j = 0; j < 10; ++j){
        ButtonPanel bp = new ButtonPanel(letter, j);
        buttonPanels.add(bp);
        buttonGrid.add(bp);
      }
      ++letter;
    }
    
    Box shipListBox = new Box(BoxLayout.Y_AXIS);
    JPanel shipLabelPanel = new JPanel();
    shipLabelPanel.setOpaque(false);
    shipLabelPanel.add(shipLabel);
    shipListBox.add(shipLabelPanel);
    shipListBox.add(shipScrollPane);
    
    Box gridBox = new Box(BoxLayout.Y_AXIS);
    JPanel gridLabelPanel = new JPanel();
    gridLabelPanel.setOpaque(false);
    gridLabelPanel.add(gridLabel);
    gridBox.add(gridLabelPanel);
    gridBox.add(buttonGrid);
    
    Box topBox = new Box(BoxLayout.X_AXIS);
    topBox.add(shipListBox);
    topBox.add(Box.createRigidArea(new Dimension(25, 0)));
    topBox.add(gridBox);
    
    Box leftBox = new Box(BoxLayout.Y_AXIS);
    JPanel searchPanel = new JPanel();
    searchPanel.setOpaque(false);
    searchPanel.add(search);
    leftBox.add(topBox);
    leftBox.add(Box.createRigidArea(new Dimension(0, 15)));
    leftBox.add(searchPanel);
    
    Box buttonBox = new Box(BoxLayout.Y_AXIS);
    buttonBox.add(clear);
    buttonBox.add(random);
    buttonBox.add(ready);
    clear.setAlignmentX(CENTER_ALIGNMENT);
    random.setAlignmentX(CENTER_ALIGNMENT);
    ready.setAlignmentX(CENTER_ALIGNMENT);
    
    Box frameBox = new Box(BoxLayout.X_AXIS);
    frameBox.add(leftBox);
    frameBox.add(Box.createRigidArea(new Dimension(20, 0)));
    frameBox.add(buttonBox);
    
    String imagePath = "client493/images/blueseadeep.jpg";
    JLabel background = new JLabel(new ImageIcon(getClass()
                                                 .getClassLoader()
                                                 .getResource(imagePath)));
    background.add(frameBox);
    background.setLayout(new FlowLayout());
    setContentPane(background);    
  }
  
  public DefaultListModel<String> addShips(){
    shipListModel = new DefaultListModel<String>();
    shipList = new JList<String>(shipListModel);
    shipListModel.addElement("Aircraft");
    shipListModel.addElement("Battleship");
    shipListModel.addElement("Cruiser");
    shipListModel.addElement("Destroyer");
    shipListModel.addElement("Submarine");
    return shipListModel;
  }
  
  public class ReadyListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
      if(e.getSource() == ready){
        boolean ocean[] = player.getPlayerOcean();
        for(int j = 0; j < 100; ++j){
          if(buttonPanels.get(j).getBackground().equals(Constants.RED)){
            ocean[j] = true;
            buttonPanels.get(j).setBackground(Constants.GREY);
          }
        }
        player.getAircraft().setLocations();
        player.getBattleship().setLocations();
        player.getCruiser().setLocations();
        player.getSubmarine().setLocations();
        player.getDestroyer().setLocations();
        invalidate();
        validate();
        repaint();
        clear.setEnabled(false);
        random.setEnabled(false);
        ready.setEnabled(false);        
        try
        {
          PlayerSocket playerSocket = player.getSocket();
          playerSocket.sendPlayerReady();
        }
        catch(NullPointerException excep){
          JOptionPane.showMessageDialog(null,
                                        "Could not find the server.",
                                        "Error!",
                                        JOptionPane.ERROR_MESSAGE);
          search.setText("Could not find any players... " + 
                         "please connect to a server." );
        }       
      }
    }
  }
  
  public void clearBoard()
  {
    for(int i = 0; i < 100; ++i)
    {
    buttonPanels.get(i).setBackground(Constants.DARK_BLUE);
        buttonPanels.get(i).setEnabled(true);
    }
    shipListModel.removeAllElements();
    shipListModel.addElement("Aircraft");
    shipListModel.addElement("Battleship");
    shipListModel.addElement("Cruiser");
    shipListModel.addElement("Destroyer");
    shipListModel.addElement("Submarine");
  }
  
  public class ClearListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if(e.getSource() == clear)
      {
        ready.setEnabled(false);
        clearBoard();
        boolean[] ocean = player.getPlayerOcean();
        for(int i = 0; i < 100; ++i)
        {
          ocean[i] = false;
        }
        player.getAircraft().resetLocations();
        player.getBattleship().resetLocations();
        player.getCruiser().resetLocations();
        player.getSubmarine().resetLocations();
        player.getDestroyer().resetLocations();
      }
      invalidate();
      validate();
      repaint();
    }
  }
  
  //for random
  public void placeShip(int ship, boolean[] ocean, int start, int end)
  {
    if(ship == 0)
    {
    Aircraft aircraft = player.getAircraft();
        aircraft.setStartIndex(start);
        aircraft.setEndIndex(end);
        aircraft.setLocations();
        int[] locations = aircraft.getLocations();
        for(int i = 0; i < aircraft.getSize(); ++i)
        {
          ocean[locations[i]] = true;
        }
      }
    else if(ship == 1)
    {
        Battleship battleship = player.getBattleship();
        battleship.setStartIndex(start);
        battleship.setEndIndex(end);
        battleship.setLocations();
        int[] locations = battleship.getLocations();
        for(int i = 0; i < battleship.getSize(); ++i)
        {
          ocean[locations[i]] = true;
        }
      }
    else if(ship == 2)
    {
        Cruiser cruiser = player.getCruiser();
        cruiser.setStartIndex(start);
        cruiser.setEndIndex(end);
        cruiser.setLocations();
        int[] locations = cruiser.getLocations();
        for(int i = 0; i < cruiser.getSize(); ++i)
        {
          ocean[locations[i]] = true;
        }
      }
    else if(ship == 3)
    {
        Submarine submarine = player.getSubmarine();
        submarine.setStartIndex(start);
        submarine.setEndIndex(end);
        submarine.setLocations();
        int[] locations = submarine.getLocations();
        for(int i = 0; i < submarine.getSize(); ++i)
        {
          ocean[locations[i]] = true;
        }
      }
    else if(ship == 4)
    {
        Destroyer destroyer = player.getDestroyer();
        destroyer.setStartIndex(start);
        destroyer.setEndIndex(end);
        destroyer.setLocations();
        int[] locations = destroyer.getLocations();
        for(int i = 0; i < destroyer.getSize(); ++i)
        {
          ocean[locations[i]] = true;
        }
      }
  }
  
  public void placeAllShipsRandom(){

    clearBoard();
    shipListModel.removeAllElements();
    boolean[] ocean = player.getPlayerOcean();
    for(int i = 0; i < 100; ++i){
      ocean[i] = false;
    }
    player.getAircraft().resetLocations();
    player.getBattleship().resetLocations();
    player.getCruiser().resetLocations();
    player.getSubmarine().resetLocations();
    player.getDestroyer().resetLocations();
    
    int direction = 0;
    int index = -1;
    boolean setback = true;
    int sizeArray[] = {5, 4, 3, 3, 2};
    int size = 0;

    for(int i = 0; i < 5; ++i){
      setback = true;
      while(setback){
        
        size = sizeArray[i];        
        index = (int) (Math.random() * 99);
        direction = (int)(Math.random() * 4) + 1; //get a random direction, 1 = left, 2 = right, 3 = down, 4 = up
        
        if(direction == 1){
          int indexDivide = index / 10;
          int left = index - size + 1;
          setback = false;
          if(left < 0){
            setback = true;
          }
          else if(!ocean[left]){
            for(int y = 0; y < size; ++y){
              if(ocean[index - y]){
                setback = true;
              }
            }
            if(left / 10 != indexDivide){
              setback = true;
            }
            if(!setback){
              placeShip(i, ocean, index, left);
            }
          }
          else{
            setback = true;
          }
        }
        
        else if(direction == 2){
          int indexDivide = index / 10;
          int right = index + size - 1;
          setback = false;
          if(right / 10 > 9){
            setback = true;
          }
          
          else if(!ocean[right]){
            for(int y = 0; y < size; ++y){
              if(ocean[index + y]){
                setback = true;
              }
            }
            if(right / 10 != indexDivide){
              setback = true;
            }
            if(!setback){
              placeShip(i, ocean, index, right);
            }
          }
          else{
            setback = true;
          }
        }
        
        else if(direction == 3){
          int down = index + ((size - 1) * 10);
          setback = false;
          if(down / 10 > 9){
            setback = true;
          }
          else if(!ocean[down]){
            for(int y = 0; y < size; ++y){
              if(ocean[index + (y * 10)]){
                setback = true;
              }
            }
            if(!setback){
              placeShip(i, ocean, index, down);
            }
          }
          else{
            setback = true;
          }
        }
        
        else{
          int up = index - ((size - 1) * 10);
          setback = false;
          if(up < 0){
            setback = true;
          }
          else if(!ocean[up]){
            for(int y = 0; y < size; ++y){
              if(ocean[index - (y * 10)]){
                setback = true;
              }
            }
            if(!setback){
              placeShip(i, ocean, index, up);
            }
          }
          else{
            setback = true;
          }
        }
      }
    }  
  }
  
  public class RandomListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
      if(e.getSource() == random){
        ready.setEnabled(true);
        clearBoard();
        placeAllShipsRandom();
        boolean ocean[] = player.getPlayerOcean();
        for(int i = 0; i < 100; ++i){
          if(ocean[i]){
            buttonPanels.get(i).setEnabled(false);
            buttonPanels.get(i).setBackground(Constants.RED);
          }
        }
        invalidate();
        validate();
        repaint();
      }
    }
  }
  
  public class MouseListener extends MouseAdapter{
    public void mouseClicked(MouseEvent e){
      if(e.getClickCount() == 1){
        shipIndex = shipList.locationToIndex(e.getPoint());
      }
      else if (e.getClickCount() == 2)
      {
        JList<String> templist = (JList)e.getSource();
        //doubleClick = new DoubleClickShipDialog(templist.getSelectedValue());
      }
    }
  }
  
  public class ButtonPanel extends JPanel implements ActionListener{
    
    private JButton button;
    
    public ButtonPanel(char letter, int number){
      setBorder(BorderFactory.createLineBorder(Constants.LIGHT_BLUE));
      setBackground(Constants.DARK_BLUE);
      button = new JButton(letter + String.valueOf(number));
      button.setForeground(Constants.WHITE);
      button.setBorder(null);
      button.setBorderPainted(false);
      button.setMargin(new Insets(0,0,0,0));
      button.addActionListener(this);
      add(button);
    }
    
    public void reset(){
      for(int i = 0; i < 100; ++i){
        if(!buttonPanels.get(i).getBackground().equals(Constants.RED)){
          buttonPanels.get(i).setBackground(Constants.DARK_BLUE);
        }
      }
    }
    
    public void setIndices(String selected, int start, int end){
      AbstractShip ship;
      if(selected == "Aircraft"){
        ship = player.getAircraft();
      }
      else if(selected == "Cruiser"){
        ship = player.getCruiser();
      }
      else if(selected == "Battleship"){
        ship = player.getBattleship();
      }
      else if(selected == "Destroyer"){
        ship = player.getDestroyer();
      }
      else{
        ship = player.getSubmarine();
      }
      ship.setStartIndex(start);
      ship.setEndIndex(end);
    }
    
    public void actionPerformed(ActionEvent e){
      
      JButton buttoning = ((JButton) e.getSource());
      String position = buttoning.getActionCommand();
      
      int i = position.charAt(0) - 'A' + 1;
      int j = Integer.parseInt(position.substring(1));
      buttonIndex = (10 * (i - 1)) + j;
      int size = 0;
      if(shipIndex > -1){
        selected = shipListModel.elementAt(shipIndex);
      }
      else{
        return;
      }
      if(selected == "Aircraft"){
        size = Constants.AIRCRAFT_SIZE;
      }
      else if(selected == "Cruiser"){
        size = Constants.CRUISER_SIZE;
      }
      else if(selected == "Battleship"){
        size = Constants.BATTLESHIP_SIZE;
      }
      else if(selected == "Destroyer"){
        size = Constants.DESTROYER_SIZE;
      }
      else if(selected == "Submarine"){
        size = Constants.SUBMARINE_SIZE;
      }
      else{
        return;
      }
      
      if(button == buttoning){  
        //button is valid ship end spot
        if(buttonPanels.get(buttonIndex)
                       .getBackground()
                       .equals(Constants.GREEN))
        {
          if(buttonIndex + size - 1 < 100 &&
             buttonPanels.get(buttonIndex + size - 1)
                         .getBackground()
                         .equals(Constants.GREEN))
          {
            setIndices(selected, buttonIndex, buttonIndex + size - 1);
            for(int r = 0; r < size; ++r)
            {
              buttonPanels.get(buttonIndex + r).setBackground(Constants.RED);
            }
            invalidate();
            validate();
            repaint();
          }
          
          else if(buttonIndex - size + 1 >= 0 &&
                  buttonPanels.get(buttonIndex - size + 1)
                              .getBackground()
                              .equals(Constants.GREEN))
          {
            setIndices(selected, buttonIndex, buttonIndex - size + 1);
            for(int r = 0; r < size; ++r){
              buttonPanels.get(buttonIndex - r).setBackground(Constants.RED);
            }
            invalidate();
            validate();
            repaint();
          }
          
          else if(buttonIndex + (size - 1) * 10 < 100 &&
                  buttonPanels.get(buttonIndex + (size - 1) * 10)
                              .getBackground()
                              .equals(Constants.GREEN))
          {
            setIndices(selected, buttonIndex, buttonIndex + (size - 1) * 10);
            for(int r = 0; r < size * 10; r += 10){
              buttonPanels.get(buttonIndex + r).setBackground(Constants.RED);
            }
            invalidate();
            validate();
            repaint();
          }
          
          else if(buttonIndex - (size - 1) * 10 >= 0 &&
                  buttonPanels.get(buttonIndex - (size - 1) * 10)
                              .getBackground()
                              .equals(Constants.GREEN))
          {
            setIndices(selected, buttonIndex, buttonIndex - (size - 1) * 10);
            for(int r = 0; -size * 10 < r; r -= 10){
              buttonPanels.get(buttonIndex + r).setBackground(Constants.RED);
            }
            invalidate();
            validate();
            repaint();
          }
          
          for(int z = 0; z < 100; ++z){
            if(!(buttonPanels.get(z)
                             .getBackground()
                             .equals(Constants.GREY)) &&
               !(buttonPanels.get(z)
                             .getBackground()
                             .equals(Constants.RED)))
            {
              buttonPanels.get(z).setBackground(Constants.DARK_BLUE);
              buttonPanels.get(z).setEnabled(false);
            }
          }
          invalidate();
          validate();
          repaint();
          
          for(int x = 0; x < shipListModel.getSize(); ++x){
            @SuppressWarnings("unused")
            String test = shipListModel.elementAt(x);
            if(shipListModel.elementAt(x) == selected){
              shipListModel.remove(x);
              selected = "";
              shipIndex = -1;
            }
          }
        }
        
        else{
          reset();
          
          if(!buttonPanels.get(buttonIndex)
                          .getBackground()
                          .equals(Constants.RED)){
            buttonPanels.get(buttonIndex).setBackground(Constants.GREEN);
            button.setForeground(Constants.WHITE);
            invalidate();
            validate();
            repaint();
          }
          
          int indexDivide = buttonIndex / 10;
          int left = buttonIndex - size + 1;
          int right = buttonIndex + size - 1;
          int down = buttonIndex + ((size - 1) * 10);
          int up = buttonIndex - ((size - 1) * 10);
          
          boolean setback = false;
          if(left < 0){
            setback = true;
          }
          
          else if(!buttonPanels.get(left)
                               .getBackground()
                               .equals(Constants.RED)){
            for(int y = 0; y < size; ++y){
              if(buttonPanels.get(buttonIndex - y)
                             .getBackground()
                             .equals(Constants.RED)){
                setback = true;
              }
            }
            if(left / 10 != indexDivide){
              setback = true;
            }
            if(!setback){
              buttonPanels.get(left).setBackground(Constants.GREEN);
              invalidate();
              validate();
              repaint();
            }
          }
          
          setback = false;
          if(right / 10 > 9){
            setback = true;
          }
          
          else if(!buttonPanels.get(right)
                               .getBackground()
                               .equals(Constants.RED)){
            for(int y = 0; y < size; ++y){
              if(buttonPanels.get(buttonIndex + y)
                             .getBackground()
                             .equals(Constants.RED)){
                setback = true;
              }
            }
            if(right / 10 != indexDivide){
              setback = true;
            }
            if(!setback){
              buttonPanels.get(right).setBackground(Constants.GREEN);
              invalidate();
              validate();
              repaint();
            }
          }
          
          setback = false;
          if(down / 10 > 9){
            setback = true;
          }
          else if(!buttonPanels.get(down)
                               .getBackground()
                               .equals(Constants.RED)){
            for(int y = 0; y < size; ++y){
              if(buttonPanels.get(buttonIndex + (y * 10))
                             .getBackground()
                             .equals(Constants.RED)){
                setback = true;
              }
            }
            if(!setback){
              buttonPanels.get(down).setBackground(Constants.GREEN);
              invalidate();
              validate();
              repaint();
            }
          }
          
          setback = false;
          if(up < 0){
            setback = true;
          }
          else if(!buttonPanels.get(up)
                               .getBackground()
                               .equals(Constants.RED)){
            for(int y = 0; y < size; ++y){
              if(buttonPanels.get(buttonIndex - (y * 10))
                              .getBackground()
                              .equals(Constants.RED)){
                setback = true;
              }
            }
            if(!setback){
              buttonPanels.get(up).setBackground(Constants.GREEN);
              invalidate();
              validate();
              repaint();
            }
          }
        }
      }
      if(shipListModel.isEmpty()){
        ready.setEnabled(true);
      }
    }
  }
  
  public void getReadyPlayers(){
    for(int i = 0; i < 4; ++i){
      if(player.getSocket().receivePlayerReady()){
        ++playersReady;
        search.setText("Searching for players online... " + playersReady +
                       "/4 players ready.");
      }
    }
    System.out.println("gets to 1");
    PlayerSocket playerSocket = player.getSocket();
    boolean[] playerOcean = player.getPlayerOcean();
    boolean shipPlacement = false;
    for(int i = 0; i < 100; ++i){
      shipPlacement = playerOcean[i];
      playerSocket.sendShipPlacement(shipPlacement);
    }
    System.out.println("gets to 2");
    player.getSocket().sendPlayerName(player.getName());
    String p2Name = "";
    String p3Name = "";
    String p4Name = "";
    for(int i = 0; i < 4; ++i){
      String playerName = player.getSocket().receivePlayerName();
      int playerNumber = player.getSocket().receivePlayerNumber();
      if(playerNumber == 2){
        p2Name = playerName;
      }
      else if(playerNumber == 3){
        p3Name = playerName;
      }
      else if(playerNumber == 4){
        p4Name = playerName;
      }
    }
    System.out.println("gets to 3");
    OceanGUI ocean = new OceanGUI("Ship Wars", player, p2Name, p3Name, p4Name);
    ocean.pack();
    ocean.setVisible(true);
    ocean.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    dispose();
  }
  
}