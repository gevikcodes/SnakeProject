//Programmer: Gevik Ohanian 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.util.Random;

import java.awt.event.KeyEvent; 
import java.util.HashSet;
public class Game_Boundry extends JPanel implements ActionListener
{
    static final int screen_width = 600; 
    static final int screen_height = 600; 
    static final int unitSize = 15; 
    static final int gameUnits = (screen_width * screen_height)/(unitSize);
    static final int Delay = 75; 
    final int x [] = new int [gameUnits] ;//Holding the x coordiantes as well as the body part of the snake 
    final int y []  = new int [gameUnits]; 
    int body_parts = 5; 
    int pointsEaten = 0;
    int pointX; //Origin of the point on x for items for the snake to eat 
    int pointY; //Origin of the point on y for items forthe snake to eat on the y axis
    char direction = 'R'; //Begin the snake position moving right >>>>> 
    boolean running = false; 
    Timer timer ; 
    Random random; 
    
    Game_Boundry()
    {
        random = new Random(); 
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
        this.setFocusable(true); 
        this.addKeyListener(new MyKeyAdapater());
        start_game(); 
        
        
        
        
    }
    public void start_game()
    {
        newApple(); 
        running = true; 
        timer = new Timer(Delay,this); 
        timer.start(); 
        
        
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g)
    {
        if(running){
            for(int i =0; i < screen_height/unitSize; i++)
            {
                 g.drawLine(i*unitSize, 0, i*unitSize, screen_height);
                 g.drawLine(0, i*unitSize, screen_width,i*unitSize);
            }
                
        
          
        
        
     
             g.setColor(Color.green);
             g.fillOval(pointX, pointY, unitSize, unitSize);
              for(int i =0; i < body_parts; i++) //Incrementation for whenever the snake scores a point so it becomes bigger 
        {
                if(i ==0)
                 {
                     g.setColor(Color.RED);
                     g.fillRect(x[i], y[i], unitSize, unitSize);
                  }
                else 
                 {
                     g.setColor(new Color(45, 180, 0)); 
                     g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                     g.fillRect(x[i], y[i], unitSize, unitSize);
                }
        }
               g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 30)); 
        FontMetrics metrics = getFontMetrics(g.getFont()); 
        g.drawString("SCORE: " + pointsEaten, (screen_width - metrics.stringWidth("SCORE: " + pointsEaten)) / 2, g.getFont().getSize());
        }
        else 
        {
            gameOver(g); 
        }
            
    }

    public void newApple()
    {
        pointX= random.nextInt((int)(screen_width/unitSize)) * unitSize; //Generate random apple drop 
        pointY = random.nextInt((int)(screen_height/unitSize)) * unitSize;
        
    }
    public void movement()
    {
        for(int i = body_parts; i > 0; i--)
        {
            x[i] = x[i-1]; 
            y[i] = y[i-1]; 
            
        }
        switch (direction)
        {
            case 'U' : 
                y[0]= y[0] - unitSize; 
               break; 
            case 'D' : 
                y[0]= y[0] + unitSize; 
                break; 
            case 'L' : 
                x[0]= x[0] - unitSize;    
                break; 
            case 'R': 
                x[0] = x[0] + unitSize; 
        }
              
    }
    public void checkFood()
    {
        if((x[0] == pointX) && (y[0] == pointY))
        {
            body_parts++; 
            pointsEaten++; 
            newApple(); 
        }
    }
    public void check_item_eaten()
    {
        //Checks for colliion of body 
        for(int i = body_parts; i > 0; i--) 
        {
            if((x[0]) == x [i] && (y[0] == y[i]))
            {
                running = false; 
            }
        }
        if(x[0] < 0)
        {
            running = false; 
        }
        if(x[0] > screen_width)
        {
            running = false; 
        }
         if(y[0] < 0)
        {
            running = false; 
        }
        if(y[0] > screen_height)
        {
            running = false; 
        }
        if(!running)
        {
            timer.stop(); 
            
        }
    }
    public void gameOver(Graphics g)
    {
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 30)); 
        FontMetrics metrics1 = getFontMetrics(g.getFont()); 
        g.drawString("SCORE: " + pointsEaten, (screen_width - metrics1.stringWidth("SCORE: " + pointsEaten)) / 2, g.getFont().getSize());
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 30)); 
        FontMetrics metrics2 = getFontMetrics(g.getFont()); 
        g.drawString("GAME OVER :(", (screen_width - metrics2.stringWidth("GAME OVER :(")) / 2, screen_height / 2);
          
    }
   

    public void actionPerformed(ActionEvent e)
    {
        if(running == true)
        {
            movement(); 
            checkFood();
            check_item_eaten(); 
           
        }
        repaint(); 
            
    }
    public class MyKeyAdapater extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch(e.getKeyCode()) 
            {
                case KeyEvent.VK_LEFT: 
                    if(direction != 'R')
                    {
                        direction = 'L'; 
                    }
                    break;
                  case KeyEvent.VK_RIGHT: 
                    if(direction != 'L')
                    {
                        direction = 'R'; 
                    }
                    break;
                       case KeyEvent.VK_UP: 
                    if(direction != 'D')
                    {
                        direction = 'U'; 
                    }
                    break;
                       case KeyEvent.VK_DOWN: 
                       {
                           if(direction != 'U')
                           {
                               direction = 'D'; 
                           }
                           break; 
                       }
            }
        }
    }

}
