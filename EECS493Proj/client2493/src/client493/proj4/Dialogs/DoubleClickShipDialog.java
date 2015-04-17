package client493.proj4.Dialogs;
//The Dialog that appears when you double click the ship



import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client493.proj4.util.*;

///JDIALOG THAT PRINTS OUT PICTURE AND STATS OF DOUBLE CLICKED SHIP 
//***if is sunk need to change the image path to S_Aircraft
public class DoubleClickShipDialog extends JDialog
{
  private JLabel myicon;
  private JLabel image_label;
  public DoubleClickShipDialog(String type)
  {
    setTitle(type);
    String imagePath;
    
    JPanel Pan = new JPanel();
    
    imagePath = ImagePathFactory.createImagePath(type);
    
    if(imagePath == null){ }
    else
    {
      myicon = new JLabel(new ImageIcon(getClass()
                .getClassLoader()
                .getResource(imagePath)));
      myicon.setLayout(new FlowLayout());
      add(myicon);
	  setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
	  setMinimumSize(new Dimension(400,400));
	  setContentPane(myicon);
	  pack();
	  setVisible(true);
    }
  }
}
