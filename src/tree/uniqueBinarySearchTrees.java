package tree;

/**
 * @description: 不同的二叉搜索树 https://leetcode.cn/problems/unique-binary-search-trees/
 * @author: lyq
 * @createDate: 22/5/2023
 * @version: 1.0
 */
public class uniqueBinarySearchTrees {
    //使用备忘录消除重叠子问题
    int[][] memo;
    public int numTrees(int n) {
        memo=new int[n+1][n+1];
        return count(1,n);
    }

    private int count(int low, int high) {
        //如果low>high ，那么说明这个BST是空的，但是也要返回1
        if(low>high){
            return 1;
        }
        if(memo[low][high]!=0){
            return memo[low][high];
        }
        int res=0;
        for (int i = low; i <=high ; i++) {
            //左子树都比根节点的值小
            int leftCount=count(low,i-1);
            //右子树都比根节点的值大
            int rightCount=count(i+1,high);
            res+=leftCount*rightCount;
        }
        memo[low][high]=res;
        return res;
    }


}
