import javax.swing.JFrame; 
public class Frame_Game extends JFrame
{
    //Define a constructor for the object 
    Frame_Game()
    {
       this.add(new Game_Boundry()); 
       this.setTitle("Snake"); 
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setResizable(false);
       this.pack();
       this.setVisible(true);
       this.setLocationRelativeTo(null);
       
       
       
    }
    
}
