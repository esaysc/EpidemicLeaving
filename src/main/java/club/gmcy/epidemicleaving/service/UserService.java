/**
 * ProjectName: EpidemicLeaving
 * FileName: UserService.java
 * PackageName: club.gmcy.epidemicleaving.service
 * Date: 2023-09-24 17:02
 * copyright(c)
 */
package club.gmcy.epidemicleaving.service;

import club.gmcy.epidemicleaving.dto.UserDTO;
import club.gmcy.epidemicleaving.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Version: V1.0
 * @Author: ccs
 * @ClassName: UserService
 * @PackageName: club.gmcy.epidemicleaving.service
 * @Data: 2023-09-24 17:02
 * @Description:
 */
public interface UserService extends IService<User> {
    public boolean login(UserDTO userDTO);
    public Integer selectIdByUsername(String username);
}
