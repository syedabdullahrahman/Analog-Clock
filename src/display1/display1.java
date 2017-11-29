
package display1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;


public class display1 {
    
    private String title;
    private int size;
    private JFrame frame;
   public static Canvas canvas;
    
    public display1(String title,int size)
    {
        this.title=title;
        this.size=size;
        createdisplay1 ();
    }
    public void createdisplay1()
    {
      
            frame = new JFrame(title);
        frame.setSize(size*10, size*10);        // size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(600, 10);        // position of this window in the desktop
        frame.setResizable(true);
        frame.setVisible(true);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(size,size));        // size of the visible outer frame
        canvas.setBackground(new Color(50,50,50));
        frame.add(canvas);
        frame.pack();
        
    }
    
}
