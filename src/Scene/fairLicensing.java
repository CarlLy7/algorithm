package Scene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @description: 公平发牌的算法
 * @author: lyq
 * @createDate: 27/3/2023
 * @version: 1.0
 */
public class fairLicensing {
//    public static void main(String[] args) {
//        List<String> decks=new ArrayList<>();//牌的组合
//        String[] suits=new String[]{"黑桃","红心","方片","梅花"};//黑桃，红心，方片，梅花
//        String[] ranks=new String[]{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
//        for (String suit : suits) {
//            for (String rank : ranks) {
//                decks.add(suit+" "+rank);
//            }
//        }
//        Collections.shuffle(decks,new Random());//随机洗牌，保证每个牌都有相等的概率进行排列
//        List<String>[] hands=new ArrayList[4];//将牌分成四份
//        for (int i = 0; i < hands.length; i++) {
//            hands[i]=new ArrayList<String>();//初始化hands数组
//        }
//        int curPlayer=0;
//        //进行顺序发牌
//        for (String deck : decks) {
//            hands[curPlayer].add(deck);
//            curPlayer=(curPlayer+1)%4;
//        }
//
//        //打印每个玩家的牌
//        for (int i = 0; i < hands.length; i++) {
//            System.out.println("玩家"+i+1+"的牌为:");
//            for (List<String> hand : hands) {
//                System.out.println(hand);
//            }
//            System.out.println();
//        }
//
//    }
public static void main(String[] args) {
    List<String> decks=new ArrayList<>();
    String[] suits=new String[]{"黑桃","红心","方片","梅花"};
    String[] ranks=new String[]{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    for (String suit : suits) {
        for (String rank : ranks) {
            decks.add(suit+" "+rank);
        }
    }
    Collections.shuffle(decks,new Random());
    List<String>[] hands=new ArrayList[4];//分为四份
    for (int i = 0; i < hands.length; i++) {
        hands[i]=new ArrayList<String>();
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
