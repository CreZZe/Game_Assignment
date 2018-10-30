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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Puzzlegame extends JFrame implements ActionListener {

    private JPanel p = new JPanel();
    private JPanel k = new JPanel();
    private JPanel b = new JPanel();
    
    private JButton nyttSpel = new JButton("Nytt Spel");
    private JButton sluta = new JButton("Avsluta");

    // Skapar två listor för att ändra storleken på spelfältet
    String[] kartBreddBox = { "3", "4", "5", "7", "10" };
    String[] kartHöjdBox = { "3", "4", "5", "7", "10" };
    JComboBox kartBreddLista = new JComboBox(kartBreddBox);
    JComboBox kartHöjdLista = new JComboBox(kartHöjdBox);
        
    int kartBredd = 4;
    int kartHöjd = 4;
    public JButton[][] spelplan = new JButton[kartBredd][kartHöjd];

    List<JButton> buttons = new ArrayList<>();

    public Puzzlegame() {

        int tal = 0;

        for (int y = 0; y < kartHöjd; y++) {
            for (int x = 0; x < kartBredd; x++) //kan ju här även skapa knapparna vilket även gör den delen dynamisk
            {
                if (tal == 0)
                    buttons.add(new JButton("-"));
                else 
                    buttons.add(new JButton(tal + ""));
                
                Collections.shuffle(buttons);
                spelplan[x][y] = buttons.get(tal);
                buttons.get(tal).addActionListener(this); //??
                k.add(buttons.get(tal));
                tal++;
                if (spelplan[x][y].getText() == "-") {
                    System.out.println("success");
                }
            }
        }

        tal = 0;
        //byggarbetsplats
        for (int y = 0; y < kartHöjd; y++) //egentligen behövs bara en längd/höjd då det är en kvadrat
        {
            for (int x = 0; x < kartBredd; x++) //kan ju här även skapa knapparna vilket även gör den delen dynamisk
            {
                spelplan[x][y] = buttons.get(tal);
                buttons.get(tal).addActionListener(this); //??
                k.add(buttons.get(tal));
                tal++;
                if (spelplan[x][y].getText() == "-") {
                    System.out.println("success");
                }
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
        k.setLayout(new GridLayout(kartHöjd, kartBredd)); // (y,x) dock av ngn anleding
        
        b.add(kartBreddLista);
        b.add(kartHöjdLista);
        
        add(p, BorderLayout.NORTH); //denna ger oss platsen "högst upp" på vår panel
        add(k, BorderLayout.CENTER);
        add(b, BorderLayout.SOUTH);

        sluta.addActionListener(this);
        nyttSpel.addActionListener(this);
        
        kartBreddLista.setSelectedIndex(1); // Anger vilket alternativ som är aktiverat från start på listorna
        kartHöjdLista.setSelectedIndex(1);
        
        //kartBreddLista.addActionListener(this); Dessa skall användas för att skapa funktionalitet på listorna
        //kartHöjdLista.addActionListener(this);

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();

        // undersök vilken knapp användaren har tryckt på
        int tal = 0;
        for (int y = 0; y < kartHöjd; y++) {

            for (int x = 0; x < kartBredd; x++) {
                if (spelplan[x][y].equals(clickedButton)) {
                    if (spelplan[x][y].getText().equals("-")) {
                        return;
                    }
                    // gör att inget händer vid tryck på vår blanka knapp
                    System.out.println(clickedButton.getText());

                    if (x > 0) // kan kolla åt vänster
                    {
                        if (spelplan[x - 1][y].getText().equals("-")) {
                            System.out.println("blank är till vänster");

                            String tempBtnName = spelplan[x][y].getText();
                            spelplan[x][y].setText(spelplan[x - 1][y].getText());
                            spelplan[x - 1][y].setText(tempBtnName);
                        }
                    }
                    if (x < kartBredd - 1) // kan kolla åt höger
                    {
                        if (spelplan[x + 1][y].getText().equals("-")) {
                            System.out.println("blank är till höger");

                            String tempBtnName = spelplan[x][y].getText();
                            spelplan[x][y].setText(spelplan[x + 1][y].getText());
                            spelplan[x + 1][y].setText(tempBtnName);
                        }
                    }
                    if (y > 0) // kan kolla uppåt
                    {
                        if (spelplan[x][y - 1].getText().equals("-")) {
                            System.out.println("blank är ovanför");

                            String tempBtnName = spelplan[x][y].getText();
                            spelplan[x][y].setText(spelplan[x][y - 1].getText());
                            spelplan[x][y - 1].setText(tempBtnName);
                        }
                    }
                    if (y < kartHöjd - 1) // kan kolla nedåt
                    {
                        if (spelplan[x][y + 1].getText().equals("-")) {
                            System.out.println("blank är nedanför");

                            String tempBtnName = spelplan[x][y].getText();
                            spelplan[x][y].setText(spelplan[x][y + 1].getText());
                            spelplan[x][y + 1].setText(tempBtnName);
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

    public void gameOver(JButton[][] a) {
        for (int y = 0; y < kartHöjd; y++) {
            for (int x = 0; x < kartBredd; x++) //kan ju här även skapa knapparna vilket även gör den delen dynamisk
            {
                //spelplan[x][y] = buttons.get(tal);
            }
        }
    }

    public void shuffle(JButton[][] a) {
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
