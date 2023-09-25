/**
 * ProjectName: EpidemicLeaving
 * FileName: UserImpl.java
 * PackageName: club.gmcy.epidemicleaving.service.impl
 * Date: 2023-09-24 17:05
 * copyright(c)
 */
package club.gmcy.epidemicleaving.service.impl;

import club.gmcy.epidemicleaving.dto.UserDTO;
import club.gmcy.epidemicleaving.entity.User;
import club.gmcy.epidemicleaving.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import static com.baomidou.mybatisplus.extension.toolkit.Db.getOne;

/**
 * @Version: V1.0
 * @Author: ccs
 * @ClassName: UserImpl
 * @PackageName: club.gmcy.epidemicleaving.service.impl
 * @Data: 2023-09-24 17:05
 * @Description:
 */
public class UserImpl implements UserService {
    private QueryWrapper<User> queryWrapper = new QueryWrapper<>();

    @Override
    public boolean login(UserDTO userDTO) {
        queryWrapper.clear();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        // 查询满足条件的对象类实体
        User one = getOne(queryWrapper);
        // 如果有一条记录则代表 该用户存在
        // 返回真 代表该用户存在
        return one != null;
    }
}
