/**
 * ProjectName: SpringBootDemo
 * FileName: LogUtils.java
 * PackageName: com.esaysc.demo.utils
 * Date: 2023-08-30 17:02
 * copyright(c)
 */
package club.gmcy.epidemicleaving.utils;

/**
 * @Version: V1.0
 * @Author: ccs
 * @ClassName: LogUtils
 * @PackageName: com.esaysc.demo.utils
 * @Data: 2023-08-30 17:02
 * @Description: 日志工具类
 */
public class LogUtils {
    // 为文字添加左右环绕下划线
    public static void underline(String str){
        String[] strings = str.split("");
        for (int i = 0; i < 50 - strings.length; i++) {
            System.out.print("-");
        }
        System.out.print(" " + str + " ");
        for (int i = 0; i < 50 - strings.length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    // 为文字添加左右环绕 自定义符合
    public static void underline(String str, String symbol){
        System.out.println();
        String[] strings = str.split("");
        for (int i = 0; i < 50 - strings.length; i++) {
            System.out.print(symbol);
        }
        System.out.print(" " + str + " ");
        for (int i = 0; i < 50 - strings.length; i++) {
            System.out.print(symbol);
        }
        System.out.println();
        System.out.println();

    }
}
