
package puzzlegame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Puzzlegame extends JFrame {

   private JPanel p = new JPanel(); 
   private JPanel k = new JPanel();
   //private JTextField namn  = new JTextField(); //avsluta?
   
   private JButton    nyttSpel = new JButton("Nytt Spel"); // 1-15 16st
//   private JButton    spara = new JButton("Spara");
//   private JButton    skriv = new JButton("Skriv ut");
   private JButton    sluta = new JButton("Avsluta");
   private JButton    ett = new JButton("1");
   private JButton    två = new JButton("2");
   private JButton    tre = new JButton("3");
   private JButton    fyra = new JButton("4");
   private JButton    fem = new JButton("5");
   private JButton    sex = new JButton("6");
   private JButton    sju = new JButton("7");
   private JButton    åtta = new JButton("8");
   private JButton    nio = new JButton("9");
   private JButton    tio = new JButton("10");
   private JButton    elva = new JButton("11");
   private JButton    tolv = new JButton("12");
   private JButton    tretton = new JButton("13");
   private JButton    fjorton = new JButton("14");
   private JButton    femton = new JButton("15");
   private JButton    blank = new JButton("-");   
   
   List<JButton> buttons = new ArrayList<>();
   
   public Puzzlegame() { 
       EventHandler handler = new EventHandler();
       
       buttons.add(ett);
       buttons.add(två);
       buttons.add(tre);
       buttons.add(fyra);
       buttons.add(fem);
       buttons.add(sex);
       buttons.add(sju);
       buttons.add(åtta);
       buttons.add(nio);
       buttons.add(tio);
       buttons.add(elva);
       buttons.add(tolv);
       buttons.add(tretton);
       buttons.add(fjorton);
       buttons.add(femton);
       buttons.add(blank);
    

     //area.setFont(new Font("Monospaced", Font.PLAIN, 12)); 
     //p.setLayout(new GridLayout(1,6)); // y, x i detta fall, försöker jämna fördelning
    //p.add(new JLabel("Filnamn: ", JLabel.CENTER)); //bestämmer placering av text, höger/vänster/center osv
     // samt skapar textrutan genom JLabel
       p.add(nyttSpel);
       p.add(sluta); 
//     namn.addActionListener(this);   
//     öppna.addActionListener(this);  
//     spara.addActionListener(this); 
//     skriv.addActionListener(this);
//     sluta.addActionListener(this);
     
     
     k.setLayout(new GridLayout(4,4));     
     
       for (JButton button : buttons) {
           k.add(button);
           button.addActionListener(handler);
       }
     
     add(p,  BorderLayout.NORTH); //denna ger oss platsen "högst upp" på vår panel
     add(k,  BorderLayout.CENTER);


     sluta.addActionListener(handler);
   
     //pack();
     //setSize(1000, 1000);
     setMinimumSize(new Dimension(400, 400));
     //setMinimumSize();
     //setMaximumSize(1000);
     setVisible(true); 
     setDefaultCloseOperation(EXIT_ON_CLOSE); 
       System.out.println(ett.getLocation());
       System.out.println(fem.getLocation());
       System.out.println(femton.getLocation());
   }
   
   
   
     
     public class EventHandler implements ActionListener {
         @Override
         public void actionPerformed(ActionEvent e) {
             
             
             
             
         for (JButton button : buttons) {
             if (e.getSource() == button) {
                 System.out.println(button.getActionCommand());
                 System.out.println(button.getLocation());
             }
         }
         
         //Om jag klickar på 15 vill jag kontrollera om BLANK är positionerad t.ex. X+90 om höger, X-90 om vänster, Y+90 över eller Y-90 under

     if (e.getSource() == ett) {
         //System.out.println("A");
         //kolla närliggande rutor, x, -x, y, -y för tom space, if yes -> flytta dit
     }   
     /*
     else if (e.getSource() == nyttSpel){
     reShuffle(); //shuffla om alla brickor
     }
     
     
     */
         

     else if (e.getSource() == sluta) {
       System.exit(0);
     }
       
    //enums 2d array?
    }
     }
     

    public static void main(String[] args) {
        Puzzlegame pg = new Puzzlegame();
    }

}
