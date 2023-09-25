package club.gmcy.persist.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : http://www.chiner.pro
 * @date : 2023-9-24
 * @desc : 用户
 */
@TableName("user")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable,Cloneable{
    /** 用户编号 */
    @TableId
    private Integer id ;
    /** 姓名 */
    private String name ;
    /** 用户名 */
    private String username ;
    /** 密码 */
    private String password ;
    /** 身份证号 */
    private String idCard ;
    /** 电话号 */
    private String phone ;
    /** 性别 */
    private Integer gender ;
    /** 生日 */
    private Date birth ;
    /** 电子邮箱 */
    private String email ;
    /** 危险等级 */
    private String dangerClasses ;
    /** 背景图 */
    private String backgroundImage ;
    /** 头像 */
    private String avatar ;
    /** 注册ip */
    private String createIp ;
    /** 最后登录ip */
    private String lastLoginIp ;
    /** 创建时间 */
    private Date gmtCreate ;
    /** 修改时间 */
    private Date gmtModifyed ;

}
