package oneClass;

/**
 * @description: 简单选择排序
 * @author: lyq
 * @createDate: 3/8/2022
 * @version: 1.0
 */
public class SimpleChoiceSort {
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49};
//        simpleChoiceSort(a);
        simpleChoiceSortFuXi(a);
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void simpleChoiceSort(int[] a) {
        int min, temp, i, j;
        for (i = 0; i < a.length; i++) {
            min = i;
            for (j = i; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (min != i) {
                temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }

        }
    }

    /**
     * 简单排序的复习
     * @param a
     */
    public static void simpleChoiceSortFuXi(int[] a){
        int min;
        for (int i = 0; i < a.length; i++) {
            min=i;
            for (int j = i+1; j <a.length ; j++) {
                if(a[j]<a[min]){
                    min=j;
                }
            }
            if(min!=i){
                int temp=a[i];
                a[i]=a[min];
                a[min]=temp;
            }
        }
    }
}
