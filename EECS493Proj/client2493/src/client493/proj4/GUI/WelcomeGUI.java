package client493.proj4.GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

import javax.swing.*;

import client493.proj4.player.Player;

@SuppressWarnings("serial")
public class WelcomeGUI extends JFrame{
  
  private JTextField usernameText = new JTextField("Fleet Commander");
  private JTextField serverText = new JTextField("127.0.0.1");
  
  private JButton connect = new JButton("Connect");
  
  Player player;
  
  boolean ready = false;
  
  public WelcomeGUI(String title){
    super(title);
    
    connect.setFont(new Font("Bebas", Font.PLAIN, 30));
    connect.setForeground(Color.WHITE);
    connect.setOpaque(false);
    connect.setContentAreaFilled(false);
    connect.setBorderPainted(false);
    connect.addActionListener(new ConnectListener());
    connect.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
    JLabel usernameLabel = new JLabel("   Username");
    JLabel serverLabel = new JLabel("   Server IP Address");
    usernameLabel.setFont(new Font("Overlock-Regular", Font.PLAIN, 14));
    usernameLabel.setForeground(Color.WHITE);
    serverLabel.setFont(new Font("Overlock-Regular", Font.PLAIN, 14));
    serverLabel.setForeground(Color.WHITE);
    
    Dimension d = new Dimension(150,30);

    usernameText.setPreferredSize(d);
    serverText.setPreferredSize(d);
    usernameText.setMinimumSize(d);
    serverText.setMinimumSize(d);
    usernameText.setMaximumSize(d);
    serverText.setMaximumSize(d);
    
    JPanel usernameBox = new JPanel();
    usernameBox.setLayout(new BoxLayout(usernameBox, BoxLayout.LINE_AXIS));
    usernameBox.add(usernameText);
    usernameBox.add(usernameLabel);
    usernameBox.add(Box.createHorizontalGlue());
    usernameBox.setAlignmentY(LEFT_ALIGNMENT);
    usernameBox.setOpaque(false);
    JPanel serverBox = new JPanel();
    serverBox.setLayout(new BoxLayout(serverBox, BoxLayout.LINE_AXIS));
    serverBox.add(serverText);
    serverBox.add(serverLabel);
    serverBox.setAlignmentY(LEFT_ALIGNMENT);
    serverBox.setOpaque(false);
    
    Box inputBox = new Box(BoxLayout.Y_AXIS);
    inputBox.add(usernameBox);
    inputBox.add(Box.createRigidArea(new Dimension(0, 5)));
    inputBox.add(serverBox);
    inputBox.add(Box.createVerticalGlue());
    inputBox.setOpaque(false);
    
    JPanel bottomPanel = new JPanel(new BorderLayout());
    bottomPanel.add(inputBox, BorderLayout.LINE_START);
    bottomPanel.add(connect, BorderLayout.LINE_END);
    bottomPanel.setOpaque(false);
    bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
    
    String imagePath = "client493/images/welcomescreen.jpg";
    JLabel background = new JLabel(new ImageIcon(getClass()
                                                 .getClassLoader()
                                                 .getResource(imagePath)));
    background.setLayout(new BorderLayout());
    background.add(bottomPanel, BorderLayout.PAGE_END);
    setContentPane(background);
  }
  
  public class ConnectListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      if(event.getSource() == connect){
        String username = usernameText.getText();
        String server = serverText.getText();
        String regEx = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                       "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                       "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                       "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(server);
        boolean validServer = matcher.matches();
        if(usernameText.getText().length() == 0 || 
           serverText.getText().length()   == 0 ){
          JOptionPane.showMessageDialog(null,
                                        "Please enter a username and IP Address.",
                                        "Invalid Input",
                                        JOptionPane.WARNING_MESSAGE);
        }
        else if(!validServer){
          JOptionPane.showMessageDialog(null,
                                        "Please enter a valid IP Address.",
                                        "Invalid Input",
                                        JOptionPane.WARNING_MESSAGE);
        }
        else{
          player = new Player(username, server);
          
          final SetupShips setup = new SetupShips("ShipWars Matchmaking", player);
          setup.pack();
          setup.setVisible(true);
          setup.setMinimumSize(new Dimension(668, 396));
          setup.setMaximumSize(new Dimension(668, 396));
          setup.setPreferredSize(new Dimension(668, 396));
          setup.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     
          
           ready = true;
          
          setVisible(false);
          class GetReadyRunnable implements Runnable {
            public void run() {
              setup.getReadyPlayers();
            }
          }
          
          GetReadyRunnable getReady = new GetReadyRunnable();
          new Thread(getReady).start(); 
        }
      }
    }
  }
  
  public boolean getReady(){
    return ready;
  }
  
  public Player getPlayer(){
    return player;
  }
  
}