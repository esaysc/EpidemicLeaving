/**
 * ProjectName: SpringBootNew
 * FileName: LoggerController.java
 * PackageName: club.gmcy.sweb.controller
 * Date: 2023-09-22 15:23
 * copyright(c)
 */
package club.gmcy.epidemicleaving.controller;

import club.gmcy.epidemicleaving.dto.UserDTO;
import club.gmcy.epidemicleaving.mapper.UserMapper;
import club.gmcy.epidemicleaving.service.UserService;
import club.gmcy.epidemicleaving.utils.JwtUtils;
import club.gmcy.epidemicleaving.utils.Result;
import club.gmcy.epidemicleaving.utils.StringUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Version: V1.0
 * @Author: ccs
 * @ClassName: LoggerController
 * @PackageName: club.gmcy.sweb.controller
 * @Data: 2023-09-22 15:23
 * @Description:
 */
@RestController
@Slf4j
public class LoggerController {
    @Resource
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK) // 状态码 200
    public Result login(@RequestBody UserDTO userDTO){
        log.info("控制层操作 => 登录");
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        log.info("username => {}", username);
        log.info("password => {}", password);
        if(StringUtil.isEmpty(username) || StringUtil.isEmpty(password)){
            return Result.error();
        } else if (userService.login(userDTO)) {
            String token = JwtUtils.generateToken(userDTO.getUsername());
            System.out.println("token => " + token);
            Integer userId = userService.selectIdByUsername(username);
            return Result.ok().data("token",token).data("username",username).data("userId",userId);
        } else{
            return Result.error();
        }
    }
}
