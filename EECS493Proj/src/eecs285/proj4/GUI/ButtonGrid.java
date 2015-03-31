package eecs285.proj4.GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import eecs285.proj4.util.Constants;

@SuppressWarnings("serial")
public class ButtonGrid extends JPanel{

  private static final int N = 10;
  private final List<ButtonPanel> panels = new ArrayList<ButtonPanel>();

  public ButtonGrid(){
    setLayout(new GridLayout(N, N));
    char letter = 'A';
    for(int i = 0; i < N; ++i){
      for(int j = 0; j < N; ++j){
        ButtonPanel bp = new ButtonPanel(letter, j);
        panels.add(bp);
        this.add(bp);
      }
      ++letter;
    }
  }
  //comment for git
  class ButtonPanel extends JPanel implements ActionListener{
    
    JButton button;

    public ButtonPanel(char letter, int i){
      setBorder(BorderFactory.createLineBorder(new Color(25, 130, 170)));
      setBackground(new Color(5, 50, 75));
      button = new JButton(letter + String.valueOf(i));
      button.setForeground(Color.white);
      button.setBorder(null);
      button.setBorderPainted(false);
      button.setMargin(new Insets(0,0,0,0));
      button.addActionListener(this);
      add(button);
    }
        
    public void actionPerformed(ActionEvent event){
      JButton button = ((JButton) event.getSource());
      String position = button.getActionCommand();
      int i = position.charAt(0) - 'A' + 1;
      int j = Integer.parseInt(position.substring(1));
      int index = (10 * (i - 1)) + j;
      ButtonPanel bp = panels.get(index);
      Color background = bp.getBackground();
      if(background == Constants.DARK_BLUE){
        bp.setBackground(Constants.YELLOW);
        button.setForeground(Constants.BLACK);
      }
      else if(background == Constants.GREY ||
              background == Constants.WHITE ||
              background == Constants.RED ||
              background == Constants.DARKER_BLUE){
      }
      else{
        bp.setBackground(Constants.DARK_BLUE);
        button.setForeground(Constants.WHITE);
      }
    }
    
    public JButton getButton(){
      return button;
    }
    
  }
  
  public void setPanelColor(int index, Color background, Color text){
    ButtonPanel bp = panels.get(index);
    bp.setBackground(background);
    bp.button.setForeground(text);
  }
  
  public boolean checkPanelColor(int index, Color background){
    ButtonPanel bp = panels.get(index);
    if(bp.getBackground() == background){
      return true;
    }
    else{
      return false;
    }
  }
  
  public List<ButtonPanel> getButtonPanels(){
    return panels;
  }
  
}   