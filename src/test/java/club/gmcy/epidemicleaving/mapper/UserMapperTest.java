package club.gmcy.epidemicleaving.mapper;

import club.gmcy.epidemicleaving.entity.User;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

// @RunWith(SpringRunner.class) // 如果是 Junit 4.x 则需要加 @RunWith，是 Jnit 5.x 就不需要加，因为内置了。（使自动注入生效）
@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 作用是使用自定义的数据源，而非使用自动配置的嵌入式内存数据源
//@SpringBootTest
@Slf4j
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01() {
        User user = userMapper.selectById(1);
        log.info("user => {} ", user);

    }
}
