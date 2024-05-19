package oneClass;

/**
 * @description: 计算阶乘然后累加起来
 * @author: lyq
 * @createDate: 29/7/2022
 * @version: 1.0
 */
public class factorial {
    public static void main(String[] args) {
//        factorialSum(10);
    int m[]={10,2,6,8,4,1,3};
        choiceSort(m);
    }


    //计算阶乘之和的方法
    public static void factorialSum(int n) {
        int sum = 1; //用来进行累加的变量，并且是最后需要输出的变量
        for (int i = 1; i <= n; i++) {
            sum *= i;
        }
        System.out.println(sum);
    }

    //选择排序的算法
    public static void choiceSort(int[] m){
        int temp=0;
        for (int i = 0; i < m.length; i++) {
            for (int j=i+1;j<m.length;j++){
                if(m[j]<m[i]){
                    //交换位置
                    temp=m[i];
                    m[i]=m[j];
                    m[j]=temp;

                }
            }
        }
        for (int i : m) {
            System.out.print(i+",");
        }
    }
}
