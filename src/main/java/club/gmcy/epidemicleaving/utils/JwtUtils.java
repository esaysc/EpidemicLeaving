package club.gmcy.epidemicleaving.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/04/12:38
 * @FileName: JwtUtils
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: JwtUtils
 * @author: ccs
 * @Date: 2022/12/4 12:38
 * @Version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "el.jwt")
public class JwtUtils {
    private String header;
    // 过期时间 7天
    private static long expire = 604800;
    // 32位密钥
    private static String secret = "gmcylbcfrevilog";
    // 生成token
    public static String generateToken(String username){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * expire);
        return Jwts.builder()
            .setHeaderParam("type","JWT")
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512,secret)
            .compact();

    }
    // 解析token
    public static Claims getClaimsByToken(String token){
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();
    }
    public static boolean verify(String token) {
        return TokenUtils.verify(token,secret);
    }




}
