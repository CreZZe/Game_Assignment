package puzzlegame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Puzzlegame extends JFrame implements ActionListener {

    private JPanel p = new JPanel();
    private JPanel k = new JPanel();
    
    private JButton nyttSpel = new JButton("Nytt Spel"); // 1-15 + 1 blank
    private JButton sluta = new JButton("Avsluta");
    private JLabel victory = new JLabel("Du vann!");


    
    
    int size = 4;
    public JButton[][] spelplan = new JButton[size][size];


    public Puzzlegame() {

        createAndShowUI();
        initComponents();
       
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {        
        JButton clickedButton = (JButton) e.getSource();
        
        p.remove(victory); //tar bort victory efter ett klick oavsett knapp
        p.repaint(); //målar om panelen p annars fastnar "du vann-texten"
        
        // undersök vilken knapp användaren har tryckt på

        for (int y = 0; y < size; y++) {

            for (int x = 0; x < size; x++) {
                if (spelplan[x][y].equals(clickedButton)) {
                    if (spelplan[x][y].getText().equals("-")) {
                        return;
                    }
                    // gör att inget händer vid tryck på vår blanka knapp
                    
                    if (x > 0) // kan kolla åt vänster
                    {
                        if (spelplan[x - 1][y].getText().equals("-")) {
                            changePos(x - 1, y, x, y);
                        }
                    }
                    if (x < size - 1) // kan kolla åt höger
                    {
                        if (spelplan[x + 1][y].getText().equals("-")) {
                            changePos(x + 1, y, x, y);
                        }
                    }
                    if (y > 0) // kan kolla uppåt
                    {
                        if (spelplan[x][y - 1].getText().equals("-")) {
                            changePos(x, y, x, y - 1);
                        }
                    }
                    if (y < size - 1) // kan kolla nedåt
                    {
                        if (spelplan[x][y + 1].getText().equals("-")) {
                            changePos(x, y, x, y + 1);
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
        
        if (gameOver()) {
            System.out.println("YOU WON!");
            p.add(victory);
        }
    }
    
    private void createAndShowUI() {
        this.setLocation(700, 300); //orienterar framen mot mitten
        this.setResizable(false);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        
    }

    private void initComponents() 
    {
        int talet = 1; //då börjar texten på knapprutorna på 1->
        //byggarbetsplats
        int maxTal = (size * size) - 1;
        for (int y = 0; y < size; y++)
        {
            for (int x = 0; x < size; x++)
            {
                // Exit condition för loopen
                if (talet > maxTal) {
                    break;
                }

                JButton newButton = new JButton(Integer.toString(talet));
                spelplan[x][y] = newButton;
                newButton.addActionListener(this);
                k.add(newButton);
                talet++;
            }
        }
        //om man vill lägga in blank sist
        JButton emptyButton = new JButton("-");
        spelplan[size - 1][size - 1] = emptyButton;
        emptyButton.addActionListener(this);
        k.add(emptyButton);

        //Nu körs en pre-game-shuffle (om det är önskvärt)
        shuffle(spelplan);

        p.add(nyttSpel);
        p.add(sluta);
        sluta.addActionListener(this);
        nyttSpel.addActionListener(this);
        
        k.setLayout(new GridLayout(size, size)); // (y,x) dock av ngn anleding

        add(p, BorderLayout.NORTH); //denna ger oss platsen "högst upp" på vår panel
        add(k, BorderLayout.CENTER);
    }

    public boolean gameOver() {
        int spelplansStorlek = size*size;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++)
            {
                String buttonText = spelplan[x][y].getText();
                int tal = y*size + x + 1;
                
                if (buttonText.equals("-")) {
                    if (tal != spelplansStorlek) {
                        return false;
                    }

                } else {

                    if (tal != Integer.parseInt(buttonText)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void shuffle(JButton[][] a) {
        Random random = new Random();

        for (int i = a.length - 1; i > 0; i--) //4x x 4y atm
        {
            for (int j = a[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);
                
                changePos(i, j, m, n);
            }
        }
    }
    public void changePos(int x1, int y1, int x2, int y2) {
        String tempBtnName = spelplan[x1][y1].getText();
        spelplan[x1][y1].setText(spelplan[x2][y2].getText());
        spelplan[x2][y2].setText(tempBtnName);
    }


    public static void main(String[] args) {
        Puzzlegame pg = new Puzzlegame();
    }

}