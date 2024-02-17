import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Bullets
{
  private List<Ammo> ammo;

  public Bullets()
  {
    ammo = new ArrayList<Ammo>(0);
  }

  public void add(Ammo al)
  {
    ammo.add(al);
  }

  //post - draw each Ammo
  public void draw(Graphics window)
  {
    if (ammo.size() > 0) 
    {
			for (Ammo a : ammo) 
      {
				a.draw(window);
        
			}
		}
  }

            
          

  public void move()
  {
    if (ammo.size() > 0) 
    {
			for (Ammo a : ammo) 
      {
				a.move("UP");
			}
		}
  }

  public void moveDown()
  {
    if (ammo.size() > 0) 
    {
			for (Ammo a : ammo) 
      {
				a.move("DOWN");
			}
		}
  }
  
  // remove any Ammo which has reached the edge of the screen
  public void cleanUpEdges()
  {
    if (ammo.size() > 0) 
    {
			for (int i = 0; i < ammo.size(); i++) 
      {
				if (!ammo.get(i).isAlive()) 
        {
					ammo.remove(i);
					i = 0;
				}
			}
		}
  }

  public List<Ammo> getList()
  {
    return ammo;
  }

  public Ammo getAmmo(int pos) 
  {
		return ammo.get(pos);
	}

  public int getSize() 
  {
		return ammo.size();
	}


  public String toString()
  {
    return "";
  }
}
