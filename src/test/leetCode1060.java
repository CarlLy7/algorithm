package test;

/**
 * @description:
 * @author: lyq
 * @createDate: 4/3/2023
 * @version: 1.0
 */
public class leetCode1060 {
    //[4,9,12,15] index=5   0 4 2 2  index=1
    public static int[] getCount(int[] arr){
        int len= arr.length;
        int[] res=new int[len];
        res[0]=0;
        for (int i =1 ; i < len; i++) {
            res[i]=arr[i]-arr[i-1]-1;
        }
//        for (int re : res) {
//            System.out.print(re+",");
//        }
        return res;
    }
    public static void main(String[] args) {
        int[] test=new int[]{4,9,12,15};
        int ans=getHiatus(test,5);
        System.out.println(ans);
    }

    private static int getHiatus(int[] test, int index) {
        int[] count = getCount(test);
        for (int j = 1; j < count.length; j++) {
            if(index<=count[j]){
                return test[j-1]+index;
            }else{
                index-=count[j];
            }
        }
        if(index>0){
            return test[test.length-1]+index;
        }
        return 0;
    }
}
