import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: lyq
 * @createDate: 17/4/2023
 * @version: 1.0
 */

public class testShuZiMaLi {
//    public static void main(String[] args) {
//        Scanner scanner=new Scanner(System.in);
//        float h=scanner.nextFloat();
//        int n =scanner.nextInt();
//        float returnHeight=0;
//        float distance=0;
//        //write your code here......
//        if(n==0){
//            returnHeight=0;
//            distance=h;
//            System.out.println(String.format("%.3f", returnHeight)+" "+String.format("%.3f", distance));
//        }else{
//            distance=h;
//            returnHeight= (float) (h/(Math.pow(2,n)));
//            for (int i = 1; i <=n-1 ; i++) {
//                returnHeight= (float) (h/Math.pow(2,i));
//                distance+=returnHeight*2;
//                //100+50+50+25+25
//            }
//            returnHeight= (float) (h/Math.pow(2,n));
//            System.out.println(String.format("%.3f", returnHeight)+" "+String.format("%.3f", distance));
//        }
//        //输出格式为：System.out.println(String.format("%.3f", h)+" "+String.format("%.3f", sum));
//
//
//    }

    //    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String line = scanner.nextLine();
//        Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
//        //write your code here......
//        char[] arrs = line.toCharArray();
//        for (int i = 0; i < arrs.length; i++) {
//            if(Character.isLetter(arrs[i])){
//                if(map.containsKey(arrs[i])){
//                    map.put(arrs[i],map.get(arrs[i])+1);
//                }else{
//                    map.put(arrs[i],1);
//                }
//            }
//        }
//        Set<Map.Entry<Character, Integer>> entrys = map.entrySet();
//        for (Map.Entry<Character, Integer> entry : entrys) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
//    }
    static HashSet<List<Integer>> set=new HashSet<>();
//    static List<List<Integer>> res = new LinkedList<>();
    static LinkedList<Integer> track = new LinkedList<>();
    private static void backTrack(int[] c, int start) {
        set.add(new LinkedList<>(track));
        for (int i = start; i <c.length ; i++) {
            track.addLast(c[i]);
            backTrack(c,i+1);
            track.removeLast();
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }
        int target = 0;
        for (int i = 0; i < n; i++) {
            target += a[i];
        }

        /**
         * 2 6
         * 30 39
         * 15 29 42 1 44 1
         */

//        for (int i = 0; i < m; i++) {
//            backTrack(b, i);
//        }
        int[] c=new int[]{15,29,42,1,44,1};
        backTrack(c, 0);
        System.out.println(set);
//        List<Integer> temp=new LinkedList<>();
//        for (List<Integer> re : res) {
//            temp.addAll(re);
//        }
//        int[] result=new int[temp.size()];
//        for (int i = 0; i < temp.size(); i++) {
//            result[i]=temp.get(i);
//        }
//        for (int i = 0; i < result.length-1; i++) {
//            System.out.println(result[i]+" "+result[result.length-1]);
//        }
//        System.out.println(res);
    }
}
