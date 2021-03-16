
import java.util.Arrays;

/**
 * @author 王昊坤
 * @Description:
 * @date 2021/3/1511:33
 */
public class t3 {
    //    已知数列: [6 1 2 7 9 3 4 5 10 8], 请用快速排序, 将该数列从小到大排好, 并输出中间每一轮的排序结果

    /**
     * 面试官您好，关于这道题，您在面试中向我提到，我在循环体内的if(a<b)，判断了多次，能不能删掉，我又测试了一下，还是应该继续保留这个判断
     * 因为如果出现了特殊情况，比如我们传递的本来就是一个排序好的数组{0,1,2,3,4,5}，那么就会因为a>=b跳出循环，如果此时我们不判断就交换的话，数组顺序会被打乱。
     * 另外当时现场敲代码时，第一遍运行报错了，我的电脑太卡，您让我关了共享屏幕排错，后面我断点排出来了，我的下个循环的边界应该是a+1,而当时我可能一时紧张写成了a。
     **/
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 2, 3, 4, 5};
        Arrays.sort(arr);
        sort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }

    public static void sort(int[] arr, int left, int right) {
        //非法数据处理
        int a = left;
        int b = right;
        if (a >= b) {
            return;
        }
        int x = arr[a];
        while (a < b) {
            while (a < b && arr[b] >= x) {
                b--;
            }
            if (a < b) {
                arr[a++] = arr[b];
            }
            while (a < b && arr[a] <= x) {
                a++;
            }
            if (a < b) {
                arr[b--] = arr[a];
            }
        }
        arr[a] = x;
        sort(arr, left, a - 1);
        sort(arr, a + 1, right);  //笔试时紧张，这里写成了a.
    }
}
