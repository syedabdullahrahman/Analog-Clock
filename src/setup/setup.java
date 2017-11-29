package setup;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import display1.display1;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class setup implements Runnable {

    private String title;
    private int size;
    private display1 display;
    private Thread thread;
    private BufferStrategy buffer;
    private Graphics2D g;

    public setup(String title, int size) {
        this.title = title;
        this.size = size;

    }

    public void init() {
        display1 display1 = new display1(title, size);
    }

    public void draw() {
        buffer = display.canvas.getBufferStrategy();
        if (buffer == null) {
            display.canvas.createBufferStrategy(2);
            return;
        }

        int center = size / 2;
        g = (Graphics2D) buffer.getDrawGraphics();   // draw elements
        g.clearRect(0, 0, size * 10, size * 10);        // size of the outer frame to be initialized
        g.setColor(Color.gray);
        
        // center clock
        g.fillOval(10, 10, size - size / 20, size - size / 20);
        g.setColor(Color.white);
        g.fillOval(60, 60, size - size / 5, size - size / 5);
        
//        g.setColor(Color.LIGHT_GRAY);                      // wkday clock
//        g.fillOval(size/4+size/8, size/4-size/8, size/4, size/4);
//        
//        g.setColor(Color.LIGHT_GRAY);
//        g.fillOval(size/8,size/2-size/8,  size/4,  size/4);   // Date clock
//
//                                   
//        g.setColor(Color.gray);                          // Month clock
//        g.fillOval(size/4-95, size/4+80, size/4, size/4);
//        g.setColor(Color.gray);
//        g.fillOval(size/4-80, size/4+95, size/5+5, size/5+5);
//        
//          g.setColor(Color.gray);                          
//        g.fillOval(3*size/4-95, size/4+80, size/4, size/4);
//        g.setColor(Color.gray);
//        g.fillOval(3*size/4-80, size/4+95, size/5+5, size/5+5);

        int angleX, angleY;
        int radius;
        double position;
        double time = System.currentTimeMillis();

        // small outer lines
        for (int i = 1; i <= 60; i++) {
            position = i / 60.0 * Math.PI * 2;
            radius = center - center / 13;
            angleX = (int) (center + (Math.sin(position) * radius));
            angleY = (int) (center - (Math.cos(position) * radius));
            if (i == 15 || i == 30 || i == 45 || i == 60) {
                radius = center - size / 13;    //
                g.setStroke(new BasicStroke(3));
            } else {
                radius = center - center / 8;
                g.setStroke(new BasicStroke(0));
            }
            int angleW = (int) (center + (Math.sin(position) * radius));
            int angleZ = (int) (center - (Math.cos(position) * radius));
            g.setColor(Color.black);
            g.drawLine(angleW + center / 20, angleZ - center / 20, angleX + center / 20, angleY - center / 20);
        }

        // Hour numbers
        for (int i = 1; i <= 12; i++) {
            position = i / 12.0 * Math.PI * 2;
            radius = center - center / 13 - 10;      //
            angleX = (int) ((center) - 15 + (Math.sin(position) * radius));
            angleY = (int) ((center + center / 20) - 12 - (Math.cos(position) * radius));
            g.setColor(Color.black);
            g.setFont(new Font("calibri", Font.BOLD, 33));
            String a = Integer.toString(i);
            g.drawString(a, angleX, angleY);
        }

        // Hour hand
        radius = center - 150;
        time = ((System.currentTimeMillis() + 21600000) / (60.0 * 12 * 60 * 1000.0) * Math.PI * 2);
        angleX = (int) ((center) + ((Math.sin(time)) * radius));
        angleY = (int) ((center) - ((Math.cos(time)) * radius));
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(12));
        g.drawLine(center - 6, center - 6, angleX, angleY);

        // Minute hand
        radius = center - 70;
        time = (System.currentTimeMillis() + 21600000) / (60.0 * 1000.0 * 60.0) * Math.PI * 2;
        angleX = (int) ((center) + (Math.sin(time) * radius));
        angleY = (int) ((center) - ((Math.cos(time)) * radius));
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(8));
        g.drawLine(center - 6, center - 6, angleX, angleY);
        
            try {

            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(setup.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Second hand
        radius = center - 45;
        //time = (System.currentTimeMillis() + 21600000) / (60.0 * 1000.0) * Math.PI * 2;
        time = Calendar.getInstance().get(Calendar.SECOND)/60.0*Math.PI*2;
       
        angleX = (int) ((center) + (Math.sin(time) * radius));
        angleY = (int) ((center) - ((Math.cos(time)) * radius));

            g.setColor(Color.red);
            g.setStroke(new BasicStroke(0));
            g.drawLine(center - 6, center - 6, angleX, angleY);
            
            // Center small circle in big clock
        g.setColor(Color.gray);
       g.fillOval(center-18, center-18, 20, 20);
       
       
           // Weekday
        center = size/4;
        for(int i=1; i<=7; i++){
            position = i/7.0 * Math.PI*2;
            radius = center-100;
            angleX = (int) (center*2+(Math.sin(position)*radius));
            angleY = (int) (center-(Math.cos(position)*radius));
            g.setColor(Color.black);
            g.setFont(new Font("calibri", Font.BOLD, 14));
            String a = new String();        // Weekday names
            if(i==1){
                a = "SUN";
            }    
            if(i==2){
                a = "MON";
            }    
            if(i==3){
                a = "TUE";
            }    
            if(i==4){
                a = "WED";
            }    
            if(i==5){
                a = "THU";
            }    
            if(i==6){
                a = "FRI";
                g.setColor(Color.blue);
                g.setFont(new Font("courier new", Font.BOLD, 16));
            }    
            if(i==7){
                a = "SAT";
                g.setColor(Color.blue);
                g.setFont(new Font("courier new", Font.BOLD, 16));
            }   
            g.drawString(a, angleX, angleY);
        }
        
        // Weekday hand
        radius = center-120;
        time = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)/7.0*Math.PI*2;
        angleX = (int)((center*2)+(Math.sin(time)*radius));
        angleY = (int)(center-((Math.cos(time))*radius));
        g.setColor(Color.CYAN);
        g.setStroke(new BasicStroke(4));
        g.drawLine(center*2, center, angleX, angleY);
           // Center small circle in week clock
        g.setColor(Color.black);
        g.fillOval(center*2-6, center-6, 10, 10);

         // Date
        for(int i=1; i<=31; i++){
            position = i/31.0 * Math.PI*2;
            radius = center-95;
            angleX = (int) ((center)+(Math.sin(position)*radius));
            angleY = (int) ((2*center)-(Math.cos(position)*radius));
            g.setColor(Color.black);
            g.setFont(new Font("calibri", Font.BOLD, 12));
            String a = Integer.toString(i);
            g.drawString(a, angleX, angleY);
        }
        
        // Date hand
        radius = center-100;
        time = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)/31.0*Math.PI*2;
        angleX = (int)((center)+(Math.sin(time)*radius));
        angleY = (int)((2*center)-((Math.cos(time))*radius));
        g.setColor(Color.CYAN);
        g.setStroke(new BasicStroke(4));
        g.drawLine(center, 2*center, angleX, angleY);
        
        
        // Center small circle in date clock
        g.setColor(Color.black);
        g.fillOval(center-6, 2*center-6, 10, 10);
        
        
        
        // Month 
        for(int i=1; i<=12; i++){
            position = i/12.0 * Math.PI*2;
            radius = center-105;
            angleX = (int) ((3*center)+(Math.sin(position)*radius));
            angleY = (int) ((2*center)-(Math.cos(position)*radius));
            g.setColor(Color.black);
            g.setFont(new Font("calibri", Font.BOLD, 14));
            String a = new String();        // Month names
            if(i==12){
                a = "JAN";
            }    
            if(i==1){
                a = "FEB";
            }    
            if(i==2){
                a = "MAR";
            }    
            if(i==3){
                a = "APR";
            }    
            if(i==4){
                a = "MAY";
            }    
            if(i==5){
                a = "JUN";
            }    
            if(i==6){
                a = "JUL";
            }
            if(i==7){
                a = "AUG";
            }
            if(i==8){
                a = "SEP";
            }
            if(i==9){
                a = "OCT";
            }
            if(i==10){
                a = "NOV";
            }
            if(i==11){
                a = "DEC";
            }
            g.drawString(a, angleX-18, angleY);
        }
        
        
        
        // Month hand
        radius = center-110;
        time = Calendar.getInstance().get(Calendar.MONTH)/12.0*Math.PI*2;
        angleX = (int)((3*center)+(Math.sin(time)*radius));
        angleY = (int)((2*center)-((Math.cos(time))*radius));
        g.setColor(Color.CYAN);
        g.setStroke(new BasicStroke(4));
        g.drawLine(3*center, 2*center, angleX, angleY);
        
        
        // Center small circle in month clock
        g.setColor(Color.black);
        g.fillOval(3*center-6, 2*center-6, 10, 10);
     
        buffer.show();
        g.dispose();
    }
    

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        try {
            thread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        init();
        while (true) {
            draw();
           
        }
    }
}
