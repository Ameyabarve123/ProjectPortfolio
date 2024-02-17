import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde
{
  private List<Alien> aliens;
  private static int score = 0;
  private int size2 = 0;

  

  public AlienHorde(int size)
  {
    aliens = new ArrayList<Alien>();
    size2 = size;
  }

  public void add(Alien al)
  {
    aliens.add(al);
  }

  public void draw(Graphics window)
  {
    if(aliens.size() > 0)
    {
      for (Alien a : aliens) 
      {
				a.draw(window); 
			}
    }
  }

  public int size()
  {
    return aliens.size();
  }

  public List<Alien> getList()
  {
    return aliens;
  }

  public void move()
  {
    for(Alien a: aliens)
      {
        a.move(a.getD());
        if(a.getX() < 0)
        {
          a.move("RIGHT");
          a.setY(a.getY() + a.getHeight());
        }
        if(a.getX() + a.getWidth() > 800)
        {
          a.move("LEFT");
          a.setY(a.getY() + a.getHeight());
        }
      }
  }

  public void createHorde(int x, int y, int w, int h, int s) 
  {
		int xPos = x;
		for (int i = 0; i < size2; i++) 
    {
			aliens.add(new Alien(x, y, w, h, s));
			if (x >= 600) 
      {
				x = xPos;
				y = y + 10 + h;
			} 
      else 
      {
				x = x + w + 10;
			}
		}
	}

  // calulate if Aliens are hit by shots, if so remove the shot and alien and return the number of hits
  public int calcHits(List<Ammo> shots)
  {
    for(int i = 0; i < aliens.size(); i++)
      {
        if(shots.size() > 0)
        {
          for(int j = 0; j < shots.size(); j++)
          {
            if(shots.get(j).didCollide(aliens.get(i)))
              {
                score += 1;
  							aliens.remove(i);
  							i = 0;
  							break;
              }
          }
        }
      }
    return score;
  }

  public void setScore0()
  {
    score = 0;
  }
  

  public String toString()
  {
    return "";
  }
}
