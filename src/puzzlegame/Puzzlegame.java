
package puzzlegame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Puzzlegame extends JFrame implements ActionListener {

   private JPanel p = new JPanel();
  private JPanel k = new JPanel();
  //private JTextField namn  = new JTextField(); //avsluta? hej

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

  
  int kartBredd = 4;
  int kartHöjd = 4;
  public JButton[][] spelplan = new JButton[kartBredd][kartHöjd];


  List<JButton> buttons = new ArrayList<>();
  //List<List<JButton>> buttons2 = new ArrayList<>();


  public Puzzlegame() {



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
  //redan nu inser man att vårat skapande av knappar inte är dynamiskt
  Collections.shuffle(buttons); //tja... man slipper göra en randommetod
  //man kan lösa blank på sistaplats genom att adda den efter shuffle..
  //funkar det efter nästa shuffle dock?

  int tal = 0;

  for (int y = 0; y < kartHöjd; y++)
  {
      for (int x = 0; x < kartBredd; x++) //kan ju här även skapa knapparna vilket även gör den delen dynamisk
      {
          spelplan[x][y] = buttons.get(tal);
          buttons.get(tal).addActionListener(this); //??
          k.add(buttons.get(tal));
          tal++;
          if (spelplan[x][y].getText() == "-")
              System.out.println("success");
      }
  }
  
  //byggarbetsplats
    for (int y = 0; y < kartHöjd; y++) //egentligen behövs bara en längd/höjd då det är en kvadrat
  {
      for (int x = 0; x < kartBredd; x++) //kan ju här även skapa knapparna vilket även gör den delen dynamisk
      {
          spelplan[x][y] = buttons.get(tal);
          buttons.get(tal).addActionListener(this); //??
          k.add(buttons.get(tal));
          tal++;
          if (spelplan[x][y].getText() == "-")
              System.out.println("success");
      }
  }
    
  
System.out.println(spelplan[2][2].getClass().getName()); //vi vil dock kunan fp fram namn
      System.out.println(spelplan[2][3].getText());
      System.out.println(spelplan[2][3].getActionCommand());
      System.out.println(spelplan[0][0].getText());
    //area.setFont(new Font("Monospaced", Font.PLAIN, 12));
    //p.setLayout(new GridLayout(1,6)); // y, x i detta fall, försöker jämna fördelning
   //p.add(new JLabel("Filnamn: ", JLabel.CENTER)); //bestämmer placering av text, höger/vänster/center osv
    // samt skapar textrutan genom JLabel
    p.add(nyttSpel);
    p.add(sluta);
    k.setLayout(new GridLayout(kartHöjd,kartBredd)); // (y,x) dock av ngn anleding


    add(p,  BorderLayout.NORTH); //denna ger oss platsen "högst upp" på vår panel
    add(k,  BorderLayout.CENTER);

    //ett.addActionListener(this);  //en knapp kan tydligen få plular i listeners
//     två.addActionListener(this);
//     tre.addActionListener(this);
//     fyra.addActionListener(this);
    sluta.addActionListener(this);
    nyttSpel.addActionListener(this);

    pack();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
      System.out.println(ett.getLocation());
      System.out.println(fem.getLocation());
      System.out.println(femton.getLocation(null));
  }

    @Override
    public void actionPerformed(ActionEvent e) {
        


    JButton clickedButton = (JButton) e.getSource();

    // undersök vilken knapp användaren har tryckt på
    int tal = 0;
    for (int y = 0; y < kartHöjd; y++)
    {

      for (int x = 0; x < kartBredd; x++)
      {
        if (spelplan[x][y].equals(clickedButton)){
            if(spelplan[x][y].getText().equals("-")) return; 
            // gör att inget händer vid tryck på vår blanka knapp
            System.out.println(clickedButton.getText());
            
            
            if (x > 0) // kan kolla åt vänster
            {
                if (spelplan[x-1][y].getText().equals("-"))
                {
                System.out.println("blank är till vänster");
                
                String tempBtnName = spelplan[x][y].getText();
                spelplan[x][y].setText(spelplan[x-1][y].getText());
                spelplan[x-1][y].setText(tempBtnName);
                }
            }
            if (x < kartBredd-1) // kan kolla åt höger
            {
                if (spelplan[x+1][y].getText().equals("-"))
                {
                System.out.println("blank är till höger");
                
                String tempBtnName = spelplan[x][y].getText();
                spelplan[x][y].setText(spelplan[x+1][y].getText());
                spelplan[x+1][y].setText(tempBtnName);
                }
            }
            if (y > 0) // kan kolla uppåt
            {
                if (spelplan[x][y-1].getText().equals("-"))
                {
                System.out.println("blank är ovanför");
                
                String tempBtnName = spelplan[x][y].getText();
                spelplan[x][y].setText(spelplan[x][y-1].getText());
                spelplan[x][y-1].setText(tempBtnName);
                }
            }
            if (y < kartHöjd-1) // kan kolla nedåt
            {
                if (spelplan[x][y+1].getText().equals("-"))
                {
                System.out.println("blank är nedanför");

                String tempBtnName = spelplan[x][y].getText();
                spelplan[x][y].setText(spelplan[x][y+1].getText());
                spelplan[x][y+1].setText(tempBtnName);
                }
            }
        }
      }
    }



    if (e.getSource() == sluta) {
      System.exit(0);
    }
     if (e.getSource() == nyttSpel) {
      shuffle(spelplan);
    }
}
    
    public void gameOver(JButton[][] a)
    {
        for (int y = 0; y < kartHöjd; y++)
        {
            for (int x = 0; x < kartBredd; x++) //kan ju här även skapa knapparna vilket även gör den delen dynamisk
            {
                spelplan[x][y] = buttons.get(tal);
            }
        }
    }
    public void shuffle(JButton[][] a)
    {
    Random random = new Random();
        
        for (int i = a.length - 1; i > 0; i--) //4x x 4y
        {
            for (int j = a[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1); //till rasmus, är det för att vi hamnar som högst på 0.99 x 4? = 3.99999?? int ger 3?
                int n = random.nextInt(j + 1);

                String tempBtnName = a[i][j].getText(); //väldigt snarlik kod ovan
                a[i][j].setText(a[m][n].getText());
                a[m][n].setText(tempBtnName);       
            }
        }
    }
    
   //enums 2d array?
   

    public static void main(String[] args) {
        Puzzlegame pg = new Puzzlegame();
    }

}
