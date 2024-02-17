import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Alien extends MovingThing
{
  private int speed;
  private Image image;
  private String direction2 = "RIGHT";

  public Alien()
  {
    this(50,10,30,30,0);
  }

  public Alien(int x, int y)
  {
    //add code here
    this(x,y, 30, 30, 1);
  }

  public Alien(int x, int y, int s)
  {
    //add code here
    this(x,y, 30, 30, s/2);
  }

  // all ctors call this ctor
  public Alien(int x, int y, int w, int h, int s)
  {
    super(x, y, w,h);
    speed=s/2;
    try
    {
      URL url = getClass().getResource("alien.jpg");
      image = ImageIO.read(url);
    }
    catch(Exception e)
    {
      //feel free to do something here
      System.out.println("Nah");
    }
  }

  public void setSpeed(int s)
  {
    //add code
    speed = s;
  }

  public int getSpeed()
  {
    return speed;
  }

  public String getD()
  {
    return direction2;
  }

  public void setD(String hhh)
  {
    direction2 = hhh;
  }

  public void move(String direction)
  {
    //add code here
    if(direction.equals("RIGHT"))
		{
			setX(getX() + speed);
      direction2 = "RIGHT";
		}
    
		if(direction.equals("LEFT"))
		{
			setX(getX() - speed);
      direction2 = "LEFT";
		}
    
		if(direction.equals("DOWN"))
		{
			setY(getY() + speed);
      direction2 = "DOWN";
		}
  }

  public void draw( Graphics window )
  {
    window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
  }

  public String toString()
  {
    return super.toString() +" "+ getSpeed();
  }
}
