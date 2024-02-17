import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo<T> extends MovingThing implements Collideable<T>
{
  private int speed;


  private boolean alive = true;

  private Color color = Color.GREEN;

  public Ammo()
  {
    this(5,5,5);
  }

  public Ammo(int x, int y)
  {
    //add code
    this(x, y, 5);
  }

  public Ammo(int x, int y, int s)
  {
    //add code
    super(x, y);
    speed = s;
  }

  public Ammo(int x, int y, int s, Color c) 
  {
		super(x, y);
    color = c;
    speed = s;
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

  public void draw(Graphics window)
  {
    //add code to draw the ammo
    window.setColor(color);
		window.fillRect(getX(), getY(), 10, 10);
  }
        
        
  public void move(String direction)
  {
    //add code to draw the ammo
    if (direction.equals("RIGHT")) {
			setX(getX() + speed);		
		}
		if (direction.equals("LEFT")) {
			setX(getX() - speed);
		}
		if (direction.equals("UP")) {
			setY(getY() - speed);
		}
		if (direction.equals("DOWN")) {
			setY(getY() + speed);
		}
  }

  public String toString()
  {
    return super.toString() + " " + getSpeed();
  }



  public boolean didCollide(T b) 
  {
    if(b.getClass().equals(Alien.class))
    {
      Alien al = (Alien)b;
        if (getX() + 10 >= al.getX() && getX() <= al.getX() + al.getWidth() && getY() - 10 >= al.getY() && getY() <= al.getY() + al.getHeight()) 
      {
         System.out.println("Ammo-Alien Collision: " + getX() + " " + getY());
  			alive = false;
  			return true;
  		} 
      else 
      {
  			return false;
  		}
    }
    else if(b.getClass().equals(Ship.class))
    {
      Ship s = (Ship)b;
      if (getX() + 10 >= s.getX() && getX() <= s.getX() + s.getWidth() && getY() - 10 >= s.getY() && getY() <= s.getY() + s.getHeight()) 
    {
			alive = false;
			return true;
		} 
      else 
      {
			return false;
		  }
    }
    return false;
	}


  public boolean didCollideShip(Ship s) 
  {
		if (getX() + 10 >= s.getX() && getX() <= s.getX() + s.getWidth() && getY() - 10 >= s.getY() && getY() <= s.getY() + s.getHeight()) 
    {
			alive = false;
			return true;
		} 
    else 
    {
			return false;
		}
	}
  

	public boolean isAlive() 
  {
		if (getY() < 0) 
    {
			alive = false;
		}
		return alive;
	}

  public boolean getisAlive()
  {
    return alive;
  }

  public void setAlive()
  {
    alive = false;
  }

}
