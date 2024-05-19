package fiveClass;

/**
 * @description:
 * @author: lyq
 * @createDate: 25/2/2023
 * @version: 1.0
 */
public class randomSort {
//    //得到和基准相等的区域的下标范围
//    public static int[] getEqualArea(int[] array, int l, int r) {
//        if (array == null || array.length < 2) {
//            return new int[]{-1, -1};
//        }
//        if (l == r) { //数组中只有一个数，范围就是自己的下标
//            return new int[]{l, r};
//        }
//        int less = l - 1;//小于区域的右边界
//        int more = r;//大于区域的左边界,默认让最后一个数在大于区域中
//        int index = l;//当前的索引位置
//        while (index < more) { //当前索引的位置不能和大于区域的左边界碰上
//            if(array[index]==array[r]){
//                //如果当前索引的数等于基准的话，索引+1
//                index++;
//            }else if(array[index]<array[r]){
//                //如果当前索引的位置小于基准的话
//                swap(array,less+1,index);//交换小于区域右边界后一个数和当前索引的数
//                less++;//小于区域扩大一个
//                index++;//索引后移一位
//            }else{
//                //如果当前索引位置大于基准的话
//                swap(array,index,more-1);//交换索引和大于区域的前一个位置
//                more--;//大于区域前移一个
//            }
//        }
//        //基准之前的数都保证了左右以及相等的位置，只需要将基准复位,基准和大于区域的左边界交换
//        swap(array,more,r);
//        return new int[]{less+1,more};
//    }
//    public static void swap(int[] arr,int l,int r){
//        int temp=arr[l];
//        arr[l]=arr[r];
//        arr[r]=temp;
//    }
//
//    public static int getStandard(int[] array, int left, int right) {
//        int standard = array[0];//基准
////        int n=array.length-1;
//        int l = left;//左指针
//        int r = right;//右指针
//        while (l < r) {
//            while (l < r && array[r] > standard) {
//                r--;
//            }
//            array[l] = array[r];
//            l++;
//            while (l < r && array[l] <= standard) {
//                l++;
//            }
//            array[r] = array[l];
//            r--;
//        }
//        array[l] = standard;
//        return l;
//    }
//
//    public static void quicksort(int[] array) {
//        if(array==null || array.length<2){
//            return;
//        }
//        process(array,0,array.length-1);
//    }
//
//    private static void process(int[] array, int l, int r) {
//        if(l>=r){
//            return;
//        }
//        //为了真正满足时间复杂度为nlogn，我们要保证每个数作为基准的概率相等，所以我们随机选择一个数作为基准
//        swap(array,l+(int) (Math.random()*(r-l+1)),r);
//        int[] area = getEqualArea(array, l, r);
//        process(array,l,area[0]-1);
//        process(array,area[1]+1,r);
//    }

    //快排
    public static void quicksort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        //现等概率随机取得一个数作为基准，然后得到相等的区间
        swap(arr,l+(int) (Math.random()*(r-l+1)),r);
        int[] area = getEqualArea(arr, l, r);
        process(arr, l, area[0] - 1);
        process(arr, area[1] + 1, r);
    }

    private static int[] getEqualArea(int[] arr, int l, int r) {
        if(arr==null || arr.length<2){
            return new int[]{-1,-1};
        }
        if(l==r){
            return new int[]{l,r};
        }
        int less = l - 1;//小于区域的右边界
        int more = r;//大于区域的左边界
        int index = l;//当前索引位置
        while (index < more) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, less + 1, index);
                less++;
                index++;
            } else {
                swap(arr, index, more - 1);
                more--;
            }
        }
        //将基准复位
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] test = new int[]{6, 5, 8, 1, 3, 10, 4};
        int[] test02 = new int[]{2, 2, 2, 2, 2, 2, 2};
        quicksort(test02);
        for (int i = 0; i < test02.length; i++) {
            if (i == test02.length - 1) {
                System.out.print(test02[i]);
            } else {
                System.out.print(test02[i] + ",");
            }
        }
    }
}
