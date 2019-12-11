import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GamePanel extends JPanel {

    private ImageIcon cardIconList[];

    public GamePanel(){
        this.setSize(200,500);
        this.setLayout(new GridLayout(4,4));
        this.cardIconList = readInIcons();

        ImageIcon backIcon = this.cardIconList[8];
        ControlCards controlCards = new ControlCards();


        int cardsToAdd[] = new int[16];
        for (int i = 0; i<8;i++){
            cardsToAdd[2*i] =i;
            cardsToAdd[2*i+1]=i;
        }

        //randomize
        randomizeCardArray(cardsToAdd);
        for(int i=0;i<cardsToAdd.length;i++){
            int num = cardsToAdd[i];
            CardClass newCard = new CardClass(controlCards, this.cardIconList[num], backIcon, num);
            add(newCard);
        }
        return;

    }

    private void randomizeCardArray(int[] t) {
        Random randomizer = new Random();
        for (int i=0; i<t.length;i++){
            int d=randomizer.nextInt(t.length);
            //swap
            int s=t[d];
            t[d] = t[i];
            t[i] = s;

        }
    }

    private ImageIcon[] readInIcons() {

        ImageIcon[] icon = new ImageIcon[9];
        for (int i = 0; i < 9; i++){
            String imageName = "Files/shoes/"+i+".jpg";
            icon[i] = new ImageIcon(imageName);
        }
        return icon;
    }


}
