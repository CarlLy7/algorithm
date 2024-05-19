package fourClass;

/**
 * @description: 小和问题：将一个数左边比它小的数的和加起来，最终将每一个数左边比它小的数的和返回
 * 思路：  将一个数组拆成两份，然后左右再继续拆，整体的思想是先计算左边组的小和然后在计算右边组的小和，最后再计算整个的小和。
 *        我们在归并排序比较的时候，如果右边的数小的话不进行求和问题，当左右组的数相等的时候我们将右边组的数放入到辅助数组中，因为不确定后面有多少数是大于左组数的
 * @author: lyq
 * @createDate: 23/2/2023
 * @version: 1.0
 */
public class smallSum {

    public static int getSmallSum(int[] arr) {
        if(arr==null||arr.length<2){
            return 0;
        }
       return process(arr,0,arr.length-1);
    }

    private static int process(int[] arr, int l, int r) {
        if(l==r){
            return 0;
        }
        int m=l+((r-l)>>1);
        //返回左边累加的和+右边累加的和+总的累加的和
        //其中处理左边组和右边组的时候使用递归算法
        return process(arr,l,m)+process(arr,m+1,r)+merge(arr,l,m,r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        int[] temp=new int[r-l+1];//辅助数组
        int i=0;//指向辅助数组
        int ans=0;//最终返回结果
        int p1=l;//指向左组中的第一个位置
        int p2=m+1;//指向右组中的第一个位置
        while(p1<=m && p2<=r){
            //注意当左组和右组的比较的数相等的时候，我们要让右组中的这个数给辅助数组，因为我们不知道右组后面有多少个比左组此时的数大的数据,也不会进行累加
            ans+=arr[p1]<arr[p2]?(r-p2+1)*arr[p1]:0;//右边大于左组中这个数的个数乘以左组中这个数
            temp[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while(p1<=m){
            temp[i++]=arr[p1++];
        }
        while(p2<=r){
            temp[i++]=arr[p2++];
        }
        for (int j = 0; j < temp.length; j++) {
            arr[l+j]=temp[j];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test=new int[]{2,3,6,1,4,6};
        System.out.println(getSmallSum(test));
    }
}
