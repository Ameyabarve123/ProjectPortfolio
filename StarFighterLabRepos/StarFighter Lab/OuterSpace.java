import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;


public class OuterSpace extends Canvas implements KeyListener, Runnable
{
  public static Ship ship;
	private boolean fired = false;
  private Ammo ammo;
  private boolean shoot = false;

  private boolean resumer = false;

  private boolean reloadHelper = false;

  private int pause = 0;


  private boolean yesQuestion;

  private boolean alien;

  private boolean gameOnn = true;

  public static int health = 3;


  
  private int score = 0;
  private int uhHuhHoney = 0;
   
  private AlienHorde horde;
  private Bullets shots;
  private Bullets alienShots;
  
  

  private boolean[] keys;
  private BufferedImage back;

  public OuterSpace()
  {
    
    setBackground(Color.black);
    keys = new boolean[7];
    alien = true;
    ship = new Ship(350, 350, 100, 100, 3);
    yesQuestion = true;
    shots = new Bullets();
    alienShots = new Bullets();
    
    
		ammo = new Ammo((ship.getX() + ship.getWidth() / 2) - 5, ship.getY() - 5, 2);
    horde = new AlienHorde(11);
    if(yesQuestion)
    {
      horde.createHorde(115, 35, 30, 30, 3);
      yesQuestion = false;
    }
    
    

    int xoXo = 115;
    int yoYo = 35;
    
    
    this.addKeyListener(this);
    new Thread(this).start();

    setVisible(true);
  }

  public void update(Graphics window)
  {
    if(!gameOnn)
      donezo(window);
    else if(horde.size() == 0)
      wowzer(window);
    else if(resumer)
      pauseWindow(window);
    else
      paint(window);
  }

  public void pauseWindow(Graphics window)
  {
    window.setColor(Color.GREEN);
    window.setFont(new Font(Font.SANS_SERIF, 30, 30));
    window.drawString("PAUSED", 15, 250);
    window.drawString("Press p to resume", 15, 300);
   

    if(keys[5])
    {
      resumer = false;
    }
  }

  public void wowzer(Graphics window)
  {
    window.setColor(Color.GREEN);
    window.setFont(new Font(Font.SANS_SERIF, 30, 30));
    window.drawString("YOU WIN", 15, 250);
    window.drawString("Press r to restart", 15, 300);
    window.setFont(new Font(Font.SANS_SERIF, 80, 80));
    window.drawString("Score: " + score, 15, 80);

    if(keys[5])
    {
      score = 0;
      horde.setScore0();
      gameOnn = true;
      yesQuestion = true;
      if(yesQuestion)
      {
        horde.createHorde(115, 35, 30, 30, 3);
        yesQuestion = false;
      }
      alienShots = new Bullets();
    }
  }


  public void donezo(Graphics window)
  {
      window.setColor(Color.RED);
      window.setFont(new Font(Font.SANS_SERIF, 30, 30));
      window.drawString("YOU LOST :)", 15, 250);
      window.drawString("Press r to restart", 15, 300);

      window.setFont(new Font(Font.SANS_SERIF, 80, 80));
      window.drawString("Score: " + score, 15, 80);
    
    if(keys[5])
    {
      horde.setScore0();
      score = 0;
      gameOnn = true;
      health = 3;
      yesQuestion = true;
      horde = new AlienHorde(11);
      if(yesQuestion)
      {
        horde.createHorde(115, 35, 30, 30, 3);
        yesQuestion = false;
      }
      shots = new Bullets();
      alienShots = new Bullets();
      score = 0;
    }
  }
  
  public void paint( Graphics window )
  {
    //set up the double buffering to make the game animation nice and smooth
    
    Graphics2D twoDGraph = (Graphics2D)window;
    
    //take a snap shop of the current screen and same it as an image
    //that is the exact same width and height as the current screen
    if (back==null)
      back = (BufferedImage)(createImage(getWidth(),getHeight()));

    //create a graphics reference to the back ground image
    //we will draw all changes on the background image
    Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0, 0, 800, 600);
    
		

    
    graphToBack.setColor(Color.BLACK);
    graphToBack.fillRect(0,0,getWidth(), getHeight());

    ship.draw(graphToBack);
    
    horde.draw(graphToBack);
    
    
  
    if(health > 0 && horde.size() > 0)
    {
      graphToBack.setColor(Color.ORANGE);
		graphToBack.setFont(new Font(Font.SANS_SERIF, 15, 15));
      graphToBack.drawString("Health: " + health, 10, 15);
    graphToBack.drawString("Score: " + score, 90, 15);
      if(pause == 300)
      {

      pause = 0;
      int ranNum = (int) (Math.random() * horde.size());
      for(int jj = 0; jj < horde.size(); jj++)
        {
          alienShots.add(new Ammo(horde.getList().get(ranNum).getX() + (horde.getList().get(ranNum).getWidth()/2), horde.getList().get(ranNum).getY(), 2, Color.RED));
        }
      }
  
      
      if (keys[0])
      {
        if(ship.getX() > 0 - (ship.getWidth() / 2) + 2)
          ship.move("LEFT");
      }
  
      //add code to move Ship, Alien, etc.
      if(keys[1])
      {
        if(ship.getX() < (StarFighter.WIDTH - (ship.getWidth() / 2) - 18))
          ship.move("RIGHT");
      }
  
      if(keys[2])
      {
        if(ship.getY() > 0 - 18)
          ship.move("UP");  
      }
  
      if(keys[3])
      {
        if(ship.getY() < 360)
          ship.move("DOWN");
      }
  
      if(keys[4] && fired && health > 0)
      {
        
        shoot = true;
        shots.add(new Ammo(ship.getX() + 45, ship.getY(), 2, Color.YELLOW));
        
      }

      pause++;
      score = horde.calcHits(shots.getList());          

      int anotherOne = 0;
              for(int j = alienShots.getSize()-1; j >= 0; j--)
              {
                if(alienShots.getList().get(j).didCollide(ship))
                  {
                    anotherOne++;
                    if(health >= 1 && anotherOne == 1)
                      resumer = true;
      							alienShots.getList().remove(j);
                  
                   for(int kji = shots.getSize() - 1; kji >= 0; kji--)
                     {
                       shots.getList().remove(kji);
                     }
                  }
              }

          if(anotherOne >= 1)
          {
            health--;
            anotherOne = 0;
          }
        
          for(int jkjk = horde.size()-1; jkjk >= 0; jkjk--)
            {
              if(ship.didCollide(horde.getList().get(jkjk)))
                 {
                   health--;
                   if(health >= 1)
                     resumer = true;
                   ship.setPos(350, 350);
                   horde.getList().remove(jkjk);
                   for(int kji = alienShots.getSize() - 1; kji >= 0; kji--)
                     {
                       alienShots.getList().remove(kji);
                     }
                   for(int kji = shots.getSize() - 1; kji >= 0; kji--)
                     {
                       shots.getList().remove(kji);
                     }
                 }
            }
          
          
      shots.draw(graphToBack);
      alienShots.draw(graphToBack);
      
      alienShots.moveDown();
      shots.move();

    
      
      if(horde.size() == 0 && uhHuhHoney == 0)
      {
        System.out.println(score);
        uhHuhHoney++;
      }
  }
  else
    {
      for (Alien a : horde.getList())
      {
        a.setSpeed(0);
      }
      ship.setSpeed(0);
    }

    if(health == 0)
    {
      graphToBack.setColor(Color.BLACK);
      graphToBack.fillRect(0,0,getWidth(), getHeight());
      gameOnn = false;
    }

    if(horde.size() == 0)
    {
      graphToBack.setColor(Color.BLACK);
      graphToBack.fillRect(0,0,getWidth(), getHeight());
      for(int i = shots.getSize() - 1; i >= 0; i--)
        {
          shots.getList().remove(i);
        }

      for(int hyx = alienShots.getSize() -1; hyx >= 0; hyx--)
        {
          alienShots.getList().remove(hyx);
        }
    }
    

    horde.move();
      
      

    twoDGraph.drawImage(back, null, 0, 0);
    fired = false;
      
  }


  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      keys[0] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      keys[1] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      keys[2] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      keys[3] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keys[4] = true;
      fired = true;
    }
    if(e.getKeyCode() == KeyEvent.VK_R)
    {
      keys[5] = true;
    }

    if(e.getKeyCode() == KeyEvent.VK_P)
    {
      keys[6] = true;
    }
    
    repaint();
  }

  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      keys[0] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      keys[1] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      keys[2] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      keys[3] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keys[4] = false;
      fired = false;
    }
    if(e.getKeyCode() == KeyEvent.VK_R)
    {
      keys[5] = false;
      reloadHelper = false;
    }
    if(e.getKeyCode() == KeyEvent.VK_P)
    {
      keys[6] = false;
      resumer = false;
    }
    repaint();
  }

  public void keyTyped(KeyEvent e)
  {
    //no code needed here
  }


  public void run()
  {
    try
    {
      while(true)
      {
        Thread.currentThread().sleep(5);
        repaint();
      }
    }catch(Exception e)
    {
    }
  }
}

