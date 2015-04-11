package client493.proj4.util;

public class ImagePathFactory 
{

  public static String createImagePath(String type)
  {
	switch(type)
	{
	  case "Aircraft":
		  return "eecs285/images/Aircraft.jpg";
	  case "Battleship":
		  return "eecs285/images/Battleship.jpg";
	  case "Cruiser":
		  return "eecs285/images/Cruiser.jpg";
	  case "Destroyer":
		  return "eecs285/images/Destroyer.jpg";
	  case "Submarine":
		  return "eecs285/images/Submarine.jpg";
	  default:
		  return null;
	}
  }
	
	
  
}
