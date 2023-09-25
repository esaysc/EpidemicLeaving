/**
 * ProjectName: EpidemicLeaving
 * FileName: UserServiceImpl.java
 * PackageName: club.gmcy.epidemicleaving.service.impl
 * Date: 2023-09-24 17:05
 * copyright(c)
 */
package club.gmcy.epidemicleaving.service.impl;

import club.gmcy.epidemicleaving.dto.UserDTO;
import club.gmcy.epidemicleaving.entity.User;
import club.gmcy.epidemicleaving.mapper.UserMapper;
import club.gmcy.epidemicleaving.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.baomidou.mybatisplus.extension.toolkit.Db.getOne;

/**
 * @Version: V1.0
 * @Author: ccs
 * @ClassName: UserServiceImpl
 * @PackageName: club.gmcy.epidemicleaving.service.impl
 * @Data: 2023-09-24 17:05
 * @Description:
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean login(UserDTO userDTO) {
        log.info("业务层操作: 用户登录");
        queryWrapper.clear();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        // 查询满足条件的对象类实体
        User one = getOne(queryWrapper);
        log.info("one => {} ", one);
        // 如果有一条记录则代表 该用户存在
        // 返回真 代表该用户存在
        return one != null;
    }

    @Override
    public Integer selectIdByUsername(String username) {
        log.info("业务层操作: 根据用户名 返回 用户编号");
        queryWrapper.clear();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        return user.getId();

    }
}
