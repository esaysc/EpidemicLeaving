package club.gmcy.epidemicleaving.service.impl;

import club.gmcy.epidemicleaving.dto.UserDTO;
import club.gmcy.epidemicleaving.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Test
    void login() {
        log.info("业务层测试: 登录");
        UserDTO userDTO = new UserDTO("one", "123456");
        boolean login = userService.login(userDTO);
        log.info("result => 登录{}", login ? "成功" : "失败");
    }
}
