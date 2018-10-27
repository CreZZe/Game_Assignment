package puzzlegame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

public class Puzzlegame extends JFrame {

    private JPanel p = new JPanel();
    private JPanel k = new JPanel();

    private JButton nyttSpel = new JButton("Nytt Spel");
    private JButton sluta = new JButton("Avsluta");
    private JButton ett = new JButton("1");
    private JButton två = new JButton("2");
    private JButton tre = new JButton("3");
    private JButton fyra = new JButton("4");
    private JButton fem = new JButton("5");
    private JButton sex = new JButton("6");
    private JButton sju = new JButton("7");
    private JButton åtta = new JButton("8");
    private JButton nio = new JButton("9");
    private JButton tio = new JButton("10");
    private JButton elva = new JButton("11");
    private JButton tolv = new JButton("12");
    private JButton tretton = new JButton("13");
    private JButton fjorton = new JButton("14");
    private JButton femton = new JButton("15");
    private JButton blank = new JButton("-");

    List<JButton> buttons = new ArrayList<>();
    List<List<JButton>> buttons2 = new ArrayList<>();

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
        Collections.shuffle(buttons);

        p.add(nyttSpel);
        p.add(sluta);

        k.setLayout(new GridLayout(4, 4));

        int a = 0;
        for (int i = 0; i < 4; i++) {
            buttons2.add(new ArrayList());
            for (int j = 0; j < 4; j++) {
                buttons2.get(i).add(buttons.get(a));
                buttons.get(a).addActionListener(handler);
                a++;
            }
        }

        for (List<JButton> bList : buttons2) {
            for (JButton b : bList) {
                k.add(b);
            }
        }

        add(p, BorderLayout.NORTH);
        add(k, BorderLayout.CENTER);

        sluta.addActionListener(handler);

        setMinimumSize(new Dimension(400, 400));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public class EventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < buttons2.size(); i++) {
                for (int j = 0; j < buttons2.get(i).size(); j++) {

                    if (buttons2.get(i).get(j) == blank) {

                        //System.out.println(e.getActionCommand());

                        //System.out.println("i = " + i + " : j = " + j);

                        
                        

                                    
                        try {
                            if (e.getSource() == buttons2.get(i).get(j - 1)) {
                                System.out.println("Blank är till höger");

//                                changePosition(i, j - 1, i, j);
                            }
                        } catch (Exception error) {}

                        try {
                            if (e.getSource() == buttons2.get(i).get(j + 1)) {
                                System.out.println("Blank är till vänster");

//                                changePosition(i, j + 1, i, j);
                            }
                        } catch (Exception error1) {}

                        try {
                            if (e.getSource() == buttons2.get(i - 1).get(j)) {
                                System.out.println("Blank är under");

//                                changePosition(i - 1, j, i, j);
                            }
                        } catch (Exception error2) {}

                        try {
                            if (e.getSource() == buttons2.get(i + 1).get(j)) {
                                System.out.println("Blank är över");

//                                changePosition(i + 1, j, i, j);
                            }
                        } catch (Exception error3) {}
                    }
                }
            }

            if (e.getSource() == sluta) {
                System.exit(0);
            }

            //enums 2d array?
        }

        void changePosition(int firstBlockX, int firstBlockY, int secondBlockX, int secondBlockY) {
//            JButton tempButton = buttons2.get(firstBlockX).get(firstBlockY);
//            buttons2.get(firstBlockX).set(firstBlockY, buttons2.get(secondBlockX).get(secondBlockY));
//            buttons2.get(secondBlockX).set(secondBlockY, tempButton);
//            
//            JButton button = (JButton) tempButton;
//            Container parent = button.getParent();
//            parent.remove(button);
//            parent.revalidate();
//            parent.repaint();
            
            
            //Göra om och använda getComponents()?
        }
    }

    public static void main(String[] args) {
        Puzzlegame pg = new Puzzlegame();
    }
}
