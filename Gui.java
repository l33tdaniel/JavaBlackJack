import javax.swing.*;
import java.awt.*;  
class Gui {
    public Gui(){
    JFrame frame = new JFrame();

    JLabel l1 = new JLabel("Java Blackjack",SwingConstants.CENTER);
    frame.add(l1);

    JButton button1 = new JButton("hit");
    button1.setBounds(100,100,30,40); //x, y, width, height 
    frame.add(button1);

    JButton button2 = new JButton("stand");
    button2.setBounds(200,100,30,40);
    frame.add(button2);
    
    frame.setSize(400,500);
    frame.setLayout(new GridLayout(3,2));
    frame.setVisible(true);
    }
}