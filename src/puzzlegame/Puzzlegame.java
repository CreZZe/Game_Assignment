package puzzlegame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Puzzlegame extends JFrame {

    private JPanel p = new JPanel();
    private JPanel k = new JPanel();
    private JPanel b = new JPanel();
    
    private JButton nyttSpel = new JButton("Nytt Spel");
    private JButton sluta = new JButton("Avsluta");
        
    int kartBredd = 4;
    int kartHöjd = 4;
    public JButton[][] spelplan = new JButton[kartBredd][kartHöjd];

    List<JButton> buttons = new ArrayList<>();

    public Puzzlegame() {
        Handler handler = new Handler(); 
        
        int tal = 0;
        
        for (int y = 0; y < kartHöjd; y++) //egentligen behövs bara en längd/höjd då det är en kvadrat
        {
            for (int x = 0; x < kartBredd; x++) //kan ju här även skapa knapparna vilket även gör den delen dynamisk
            {
                if (tal == (kartBredd * kartHöjd - 1)) {
                    buttons.add(new JButton(" ")); 
                }
                else 
                    buttons.add(new JButton((tal + 1) + ""));
                
                spelplan[x][y] = buttons.get(tal);
                buttons.get(tal).addActionListener(handler);
                k.add(buttons.get(tal));
                tal++;
            }
        }
        
        shuffle(spelplan);
        
        p.add(nyttSpel);
        p.add(sluta);
        k.setLayout(new GridLayout(kartHöjd, kartBredd)); // (y,x) dock av ngn anleding
        
        add(p, BorderLayout.NORTH); //denna ger oss platsen "högst upp" på vår panel
        add(k, BorderLayout.CENTER);

        sluta.addActionListener(handler);
        nyttSpel.addActionListener(handler);

        setSize(380, 400);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    
    private class Handler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton clickedButton = (JButton) e.getSource();

            // undersök vilken knapp användaren har tryckt på
            for (int y = 0; y < kartHöjd; y++) {

                for (int x = 0; x < kartBredd; x++) {
                    if (spelplan[x][y].equals(clickedButton)) {
                        if (x > 0) // kan kolla åt vänster
                            if (spelplan[x - 1][y].getText().equals(" "))//Blank är till vänster                            
                                changePos(x, y, x-1, y);

                        if (x < kartBredd - 1) // kan kolla åt höger
                            if (spelplan[x + 1][y].getText().equals(" "))//Blank är till höger                            
                                changePos(x, y, x+1, y);

                        if (y > 0) // kan kolla uppåt
                            if (spelplan[x][y - 1].getText().equals(" ")) //Blank är ovanför
                                changePos(x, y, x, y-1);

                        if (y < kartHöjd - 1) // kan kolla nedåt
                            if (spelplan[x][y + 1].getText().equals(" ")) //Blank är nedanför                            
                                changePos(x, y, x, y+1);
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
    }
    
    public void changePos(int x1, int y1, int x2, int y2) {
        String tempBtnName = spelplan[x1][y1].getText();
        spelplan[x1][y1].setText(spelplan[x2][y2].getText());
        spelplan[x2][y2].setText(tempBtnName);
        
        checkIfGameWon();
    }

    public void checkIfGameWon() {
        int tal = 0;
        int score = 0;
        for (int y = 0; y < kartHöjd; y++) {
            for (int x = 0; x < kartBredd; x++) //kan ju här även skapa knapparna vilket även gör den delen dynamisk
            {
                if (tal == (kartHöjd * kartBredd - 1))
                    if (spelplan[x][y].getText().equals(" "))
                        score++;
                
                if (spelplan[x][y].getText().equals((tal + 1) + ""))
                    score++;
                
                tal++;
                if (score == 16)
                    JOptionPane.showMessageDialog(null, "Grattis, du vann!");
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

/*
    Lägga till antalet omstarter
    Lägga till antalet flytt
    Lägga till bakgrundsmusik?
*/
