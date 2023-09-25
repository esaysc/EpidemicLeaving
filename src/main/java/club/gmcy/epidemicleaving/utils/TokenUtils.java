/**
 * ProjectName: SpringBootDemo
 * FileName: TokenUtils.java
 * PackageName: com.esaysc.demo.utils
 * Date: 2023-08-30 15:40
 * copyright(c)
 */
package club.gmcy.epidemicleaving.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Version: V1.0
 * @Author: ccs
 * @ClassName: TokenUtils
 * @PackageName: com.esaysc.demo.utils
 * @Data: 2023-08-30 15:40
 * @Description: jjwt 封装
 */
public class TokenUtils {
    public  static SignatureAlgorithm DefaultAlgorithm= SignatureAlgorithm.HS256;  // 默认加密算法
    public  SignatureAlgorithm algorithm=SignatureAlgorithm.HS256; // 加密算法

    /**
     * @Title: getField_map
     * @Author: ccs
     * @Date: 2023-08-30 15:47
     * @Param: [obj]
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     * @Throws:
     * @Description: 获取属性名和属性的映射表
     */
    public static Map<String,Object> getField_map(Object obj) {
        /*
         getDeclaredFields:可以获取本类所有的字段，包括private的，
                           但是不能获取继承来的字段。 (注： 这里只能获取到private的字段，
                           但并不能访问该private字段的值,除非加上setAccessible(true))
                 getFields:只能获取public的，包括从父类继承来的字段
         */
        HashMap<String, Object> map = new HashMap<>();
        //  getDeclaredFields() 返回 Field 对象的一个数组，该数组包含此 Class 对象所表示的类或接口所声明的所有字段（包括私有成员）。
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f :
                fields) {
            /**
             * 作用于方法上，method.setAccessible(true);
             * 作用于属性上，field.setAccessible(true);
             *
             * 将此对象的 accessible 标志设置为指示的布尔值。
             * 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
             * 值为 false 则指示反射的对象应该实施 Java 语言访问检查;
             * 实际上setAccessible是启用和禁用访问安全检查的开关,并不是为true就能访问为false就不能访问 ；
             *
             * 由于JDK的安全检查耗时较多.所以通过setAccessible(true)的方式关闭安全检查就可以达到提升反射速度的目的
             */
            f.setAccessible(true);
            try {
                map.put(f.getName(),f.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return map;
    }

    public ArrayList<ArrayList<Object>> getField_list(Object obj){
        ArrayList<ArrayList<Object>> lists = new ArrayList<>();
        ArrayList<Object> names = new ArrayList<>();
        ArrayList<Object> values = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f :
                fields) {
            try {
                f.setAccessible(true);
                names.add(f.getName());
                f.setAccessible(true);
                values.add(f.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        lists.add(names);
        lists.add(values);
        return lists;
    }
    public String createTokenWithObj(Object obj,String secretFieldName, String subjectFieldName, String idFieldName, long lastingTime) throws Exception {
        Map<String, Object> fields = TokenUtils.getField_map(obj);
        String secret = null;
        String subject = null;
        String id = null;

        if(fields.containsKey(secretFieldName)){
            secret = (String)fields.get(secretFieldName);
        }
        if(secret==null){
            throw new Exception("you need to assign the secret value!");
        }

        if(fields.containsKey(subjectFieldName)){
            subject = (String) fields.get(subjectFieldName);
        }
        if(subject==null){
            throw new Exception("you need to assign the subject value!");
        }

        if(fields.containsKey(idFieldName)) {
            id = (String) fields.get(idFieldName);
        }
        if(id==null){
            id = UUID.randomUUID().toString();
        }
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        System.out.println("时间 => " + now + " " + date);
        JwtBuilder j = Jwts.builder();
        // 封装信息在token中
        j.setClaims(fields);
        j.setId(id); // 设置Id jwt的唯一标识，设置为一个不重复的值，主要用来作为一次性token，从而回避重放攻击，一般是用户Id
        j.setIssuedAt(date); // 设置签发时间
        j.setIssuer(subject); // 设置签发人，一般为用户名

        // 设置 签名使用的签名算法 和 签名使用的密钥
        if(this.algorithm!=null) {
            j.signWith(this.algorithm,secret);
        }else{
            j.signWith(TokenUtils.DefaultAlgorithm,secret);
        }

        // 设置token过期时间
        if(lastingTime>=0){
            date = new Date(lastingTime+now);
            j.setExpiration(date);
        }
        return j.compact();
    }
    public static String createToken(String secret, String subject, String id, long lastingTime) throws Exception {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        JwtBuilder j = Jwts.builder();
        j.setId(UUID.randomUUID().toString());
        j.setIssuedAt(date);
        if(subject!=null){
            j.setIssuer(subject);
        }else {
            throw new Exception("you need to assign the subject value!");
        }
        if(secret==null){
            throw new Exception("you need to assign the secret value!");
        }
        j.signWith(TokenUtils.DefaultAlgorithm,secret);
        if (lastingTime >= 0) {
            date = new Date(lastingTime+now);
            j.setExpiration(date);
        }
        return j.compact();
    }

    public static Claims parseToken(String token, String secret){
        if(secret==null){
            return null;
        }
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    public static boolean verify(String token, String secret) {
        Claims claims = parseToken(token, secret);
        return !claims.isEmpty();
    }
    /**
     * @Title: refreshToken
     * @Author: ccs
     * @Date: 2023-08-30 16:43
     * @Param: [token, time, secret]
     * @Return: java.lang.String
     * @Throws:
     * @Description: 刷新令牌
     */
    public static String refreshToken(String token, long time, String secret) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        JwtBuilder j = Jwts.builder();
        j.setClaims(claims);
        j.setIssuer(claims.getIssuer());
        j.setId(claims.getId());
        long now = System.currentTimeMillis();
        Date nowDate = new Date(now);
        j.setIssuedAt(nowDate);
        if(time>=0){
            j.setExpiration(new Date(now+time));
        }else {
            j.setExpiration(new Date(now));
        }
        return j.compact();

    }

    public static void main(String[] args) throws Exception {
        String secret = "asdfgjlhjfaskfjasdfjasdf";
        long expire = 604800;
        String token = createToken(secret, "aa", null, 604800);
        System.out.println("token => " + token);
        Claims claims = parseToken(token,secret);
        System.out.println(claims);
    }




}
