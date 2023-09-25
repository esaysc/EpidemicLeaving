package club.gmcy.epidemicleaving;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("club.gmcy.epidemicleaving.mapper") // 映射的包路径
public class EpidemicLeavingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpidemicLeavingApplication.class, args);
    }

}
