/**
 * ProjectName: EpidemicLeaving
 * FileName: UserDTO.java
 * PackageName: club.gmcy.epidemicleaving.dto
 * Date: 2023-09-24 17:03
 * copyright(c)
 */
package club.gmcy.epidemicleaving.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Version: V1.0
 * @Author: ccs
 * @ClassName: UserDTO
 * @PackageName: club.gmcy.epidemicleaving.dto
 * @Data: 2023-09-24 17:03
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private String username;
    private String password;
}
