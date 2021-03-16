
/**
 * @author 王昊坤
 * @Description:
 * @date 2021/3/1510:52
 */
public class t2 {
    /**
     * 一. 已知斐波那契数列: 0、1、1、2、3、5、8、13、21、34, 我们定义:
     * y = f(n). 令 f(0) = 0, f(1) = 1, f(n) = f(n - 1) + f(n - 2)（n [表情] 2，n [表情] N*）
     * 求:
     * a. f(20); 要求: 请使用两种方式实现, 并输出结果
     * b. 当 y 小于 100,000 时, 求最大的 n
     * <p>
     * <p>
     * 面试官您好，对于斐波那契数列这道题，我列出4个方法如下并比较：
     * 第一个方法用递归法，比较直观，但是效率比较低下，因为它在计算时，重复计算了大量的节点。
     * 第二个方法使用动态规划，从底向上的推导，每个数都存入dp数组，避免了重复计算，但是多消耗了O(n)的数组空间。
     * 第三个方法也是基于动态规划的递推方程，进一步优化了空间
     * 第四个方法，在网上学来的矩阵幂函数解法
     * 对于b问题，我使用第三种方法，附解在后面
     **/
    public static void main(String[] args) {
        System.out.println(dynamic(20));
        System.out.println(recue(20));
        System.out.println(fib(20));
        int[][] fb = fb(20);
        System.out.println(fb[0][1]);
        System.out.println(fib());
    }

    // 1.递归
    public static int recue(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return recue(n - 1) + recue(n - 2);
    }

    // 2.动态规划
    public static int dynamic(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];  //f(n) = f(n - 1) + f(n - 2) 状态方程
        }
        return dp[n];
    }

    //3.循环推进法
    public static int fib(int n) {
        int a = 0, b = 1, sum;
        for (int i = 0; i < n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    //问题b：当 y 小于 100,000 时, 求最大的 n
    public static int fib() {
        int a = 0, b = 1;
        int count = 0;
        while (a < 10000) {
            int temp = a;
            a = b;
            b = a + temp;
            count++;
        }
        return count;
    }


    //4.矩阵解法
    // 关联矩阵
    private static final int[][] UNIT = {{1, 1}, {1, 0}};
    // 全0矩阵
    private static final int[][] ZERO = {{0, 0}, {0, 0}};

    public static int[][] fb(int n) {
        if (n == 0) {
            return ZERO;
        }
        if (n == 1) {
            return UNIT;
        }
        // n是奇数
        if ((n & 1) == 0) {
            int[][] matrix = fb(n >> 1);
            return matrixMultiply(matrix, matrix);
        }
        // n是偶数
        int[][] matrix = fb((n - 1) >> 1);
        return matrixMultiply(matrixMultiply(matrix, matrix), UNIT);
    }

    public static int[][] matrixMultiply(int[][] m, int[][] n) {
        int rows = m.length;
        int cols = n[0].length;
        int[][] r = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                r[i][j] = 0;
                for (int k = 0; k < m[i].length; k++) {
                    r[i][j] += m[i][k] * n[k][j];
                }
            }
        }
        return r;
    }
}
