package Scene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @description: 公平发牌
 * @author: lyq
 * @createDate: 28/3/2023
 * @version: 1.0
 */
public class fairPai {
    public static void main(String[] args) {
        List<String> decks=new ArrayList<>();
        String[] suits=new String[]{"黑桃","红心","方块","梅花"};
        String[] ranks=new String[]{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        for (String suit : suits) {
            for (String rank : ranks) {
                decks.add(suit+rank);
            }
        }
        Collections.shuffle(decks,new Random());
        List<String>[] hands=new ArrayList[4];
        for (int i = 0; i < hands.length; i++) {
            hands[i]=new ArrayList<>();
        }
        int curPlayer=0;
        for (String deck : decks) {
            hands[curPlayer].add(deck);
            curPlayer=(curPlayer+1)%4;
        }

        for (int i = 0; i < hands.length; i++) {
            System.out.println("玩家"+(i+1)+"的牌为");
            for (List<String> hand : hands) {
                System.out.println(hand);
            }
            System.out.println();
        }


    }
}
