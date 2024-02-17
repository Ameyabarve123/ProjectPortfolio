import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Ship extends MovingThing 
{
  private int speed;
  private Image image;
  private int health = 3;

  public Ship()
  {
    this(150,150,100,100,0);
  }

  public Ship(int x, int y)
  {
    //add code here
    this(x,y,100,100,10);
    
  }

  public Ship(int x, int y, int s)
  {
    //add code here
    this(x,y,100,100,s);
  }

  // all ctors call this ctor
  public Ship(int x, int y, int w, int h, int s)
  {
    super(x, y, w, h);
    speed=s;
    try
    {
      URL url = getClass().getResource("ship.jpg");
      image = ImageIO.read(url);
    }
    catch(Exception e)
    {
      //feel free to do something here
      System.out.println("Nah");
    }
  }

  public boolean didCollide(Alien al) {
		if (getX() + getWidth()-30 >= al.getX() && getX() <= al.getX() + al.getWidth()-30
				&& getY() + getHeight()-30 >= al.getY()
				&& getY() <= al.getY() + al.getHeight()-30) {
      System.out.println("Ship-Alien Collision: " + getX() + " " + getY());
			return true;
		} else {
			return false;
		}
	}

  // public int gg(List<Ammo> alienShotss)
  // {
  //   int xx = 0;
  //   if(OuterSpace.health > 0)
  //   {
  //     for(int i = 0; i < alienShotss.size(); i++)
  //     {
  //       if(alienShotss.size() > 0)
  //       {
  //         for(int j = 0; j < alienShotss.size(); j++)
  //         {
  //           if(alienShotss.get(j).didCollide(OuterSpace.ship))
  //             {
  // 							// OuterSpace.health--;
  //               xx++;
  // 							// i = 0;
  // 							return xx;
  //             }
  //         }
  //       }
  //     }
  //   }
  //   return 0;
  // }
  

  
  public void setHealth()
  {
    health--;
    
  }

  public int getHealth()
  {
    return health;
  }

  
  public void setSpeed(int s)
  {
    //add more code
    speed = s;
  }

  public int getSpeed()
  {
    return speed;
  }

  public void move(String direction)
  {
    //add code here
    //add code to move Ship, Alien, etc.
    
    if (direction.equals("LEFT"))
    {
      setX(getX() - speed);
    }

    
    if(direction.equals("RIGHT"))
    {
      setX(getX() + speed);
    }

    if(direction.equals("UP"))
    {
      setY(getY() - speed);
    }

    if(direction.equals("DOWN"))
    {
      setY(getY() + speed);
    }
  }

  public void draw( Graphics window )
  {
    window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
  }

  public String toString()
  {
    return super.toString() + " " + getSpeed();
  }
}
