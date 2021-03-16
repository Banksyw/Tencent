
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author 王昊坤
 * @Description:
 * @date 2021/3/1617:22
 */


/**
 * 面试官您好，这道题，我当时笔试时的思路是用哈希表将对应的数字和中文汉字存储，将数字分为整数和小数两个数组，分别读取每个数字和根据他们对应序列，拼接字符串。
 * 感觉挨个解析，拼接的方法有点“笨”，但是我暂时没想到其它更好的办法。然后我在网上学习了一些正则表达的用法,参考了网上的一些做法。
 **/
class Trans2RMB {
    public static void main(String[] args) {
        Trans2RMB t2r = new Trans2RMB();
        String s = t2r.splitNum(t2r.roundString(t2r.getNum()));
        // 如果转换过后是一个空串，则不输出屏幕
        if (!"".equals(s)) {
            System.out.println("转换成中文后为：" + s);
        }
        System.out.println("\n---------------------------------------------");
    }

    /**
     * 从命令行接收一个数，在其中调用 checkNum() 方法对其进行
     * 验证，并返回相应的值
     */
    private String getNum() {
        String s = null;
        System.out.println("请输入一个数字（精确到小数点后两位）：");
        // 从命令行输入这个浮点数
        Scanner scanner = new Scanner(System.in);
        s = scanner.next();
        // 判断用户输入是否合法
        // 若合法，返回这个值；若非法返回 "0"
        if (this.checkNum(s)) {
            return s;
        } else {
            return "";
        }
    }

    /**
     * 判断用户输入的数据是否合法，用户只能输入大于零的数字，不能输入其它字符
     */
    private boolean checkNum(String s) {
        // 如果用户输入的数里有非数字字符，则视为非法数据，返回 false
        try {
            float f = Float.valueOf(s);
            // 如果这个数小于零则视为非法数据，返回 false
            if (f < 0) {
                System.out.println("非法数据，请检查！");
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("非法数据，请检查！");
            return false;
        }
    }

    /**
     * 把用户输入的数以小数点为界分割开来，并调用 numFormat() 方法
     * 进行相应的中文金额大写形式的转换
     * 注：传入的这个数应该是经过 roundString() 方法进行了四舍五入操作的
     */
    private String splitNum(String s) {
        // 如果传入的是空串则继续返回空串
        if ("".equals(s)) {
            return "";
        }
        // 以小数点为界分割这个字符串
        int index = s.indexOf(".");
        // 截取并转换这个数的整数部分
        String intOnly = s.substring(0, index);
        String part1 = this.numFormat(1, intOnly);
        // 截取并转换这个数的小数部分
        String smallOnly = s.substring(index + 1);
        String part2 = this.numFormat(2, smallOnly);
        // 把转换好了的整数部分和小数部分重新拼凑一个新的字符串
        String newS = part1 + part2;
        return newS;
    }

    /**
     * 对传入的数进行四舍五入操作
     */
    private String roundString(String s) {
        // 如果传入的是空串则继续返回空串
        if ("".equals(s)) {
            return "";
        }
        // 将这个数转换成 double 类型，并对其进行四舍五入操作
        double d = Double.parseDouble(s);
        // 此操作作用在小数点后两位上
        d = (d * 100 + 0.5) / 100;
        // 将 d 进行格式化
        s = new DecimalFormat("##0.000").format(d);
        // 以小数点为界分割这个字符串
        int index = s.indexOf(".");
        // 这个数的整数部分
        String intOnly = s.substring(0, index);
        // 规定数值的最大长度只能到万亿单位，否则返回 "0"
        if (intOnly.length() > 13) {
            System.out.println("输入数据过大！（整数部分最多13位！）");
            return "";
        }
        // 这个数的小数部分
        String smallOnly = s.substring(index + 1);
        // 如果小数部分大于两位，只截取小数点后两位
        if (smallOnly.length() > 2) {
            String roundSmall = smallOnly.substring(0, 2);
            // 把整数部分和新截取的小数部分重新拼凑这个字符串
            s = intOnly + "." + roundSmall;
        }
        return s;
    }

    /**
     * 把传入的数转换为中文金额大写形式
     */
    private String numFormat(int flag, String s) {
        int sLength = s.length();
        // 货币大写形式
        String bigLetter[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        // 货币单位
        String unit[] = {"元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万"};
        String small[] = {"分", "角"};
        // 用来存放转换后的新字符串
        String newS = "";
        // 逐位替换为中文大写形式
        for (int i = 0; i < sLength; i++) {
            if (flag == 1) {
                // 转换整数部分为中文大写形式（带单位）
                newS = newS + bigLetter[s.charAt(i) - 48] + unit[sLength - i - 1];
            } else if (flag == 2) {
                // 转换小数部分（带单位）
                newS = newS + bigLetter[s.charAt(i) - 48] + small[sLength - i - 1];
            }
        }
        return newS;
    }
}
