/**
 * ProjectName: SpringBootNew
 * FileName: UserMapper.java
 * PackageName: club.gmcy.sweb.mapper
 * Date: 2023-09-24 14:49
 * copyright(c)
 */
package club.gmcy.persist.mapper;

import club.gmcy.persist.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Version: V1.0
 * @Author: ccs
 * @ClassName: UserMapper
 * @PackageName: club.gmcy.sweb.mapper
 * @Data: 2023-09-24 14:49
 * @Description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
