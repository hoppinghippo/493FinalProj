package client493.proj4.Dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinningDialog extends JDialog
{
  private JLabel pic;
  private JLabel str;
  private Font mainFont;
  public WinningDialog(boolean tie, String name){
    pic = new JLabel(new ImageIcon(getClass().
        getClassLoader().
        getResource("eecs285/images/gameover.jpg")));
    pic.setLayout(new BorderLayout());
    setContentPane(pic);

    
    if(!tie)
    {
  	  setTitle("Congrats " + name);   
  	  String phrase = name + " is the winner!"; 
  	  str = new JLabel(phrase);
  	  str.setFont(new Font("Monotype Corsiva", Font.ITALIC, 50));
  	  str.setForeground(Color.WHITE);
  	  str.setHorizontalAlignment(JLabel.CENTER);
	 
    }
    else{
      setTitle("Tie"); 
      String phrase = "It's a TIE! Mutual Destuction!"; 
      str = new JLabel(phrase);
      str.setFont(new Font("Monotype Corsiva", Font.ITALIC, 50));
      str.setForeground(Color.WHITE);
      str.setHorizontalAlignment(JLabel.CENTER);
    }

    add(str);
	  pack();
	  setVisible(true);
  }
}
