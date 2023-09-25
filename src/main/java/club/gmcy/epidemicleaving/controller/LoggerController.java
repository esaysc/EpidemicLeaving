/**
 * ProjectName: SpringBootNew
 * FileName: LoggerController.java
 * PackageName: club.gmcy.sweb.controller
 * Date: 2023-09-22 15:23
 * copyright(c)
 */
package club.gmcy.sweb.controller;

import club.gmcy.sweb.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private LogService logService;
    @GetMapping("/admin/log")
    public String adminLog() {
        log.info("请求到达/admin/log");
        logService.adminLog();
        return "管理员查询了日志";
    }

    @GetMapping("/manager/log")
    public String managerLog() {
        log.info("请求到达/manager/log");
        logService.managerLog();
        return "经理查询了日志";
    }

    @GetMapping("/log/query")
    public String queryList() {
        log.info("请求到达/log/query");
        logService.queryList();
        return "查询了日志";
    }

    @GetMapping("/log/delete")
    public String delete() {
        log.info("请求到达/log/delete");
        logService.delete();
        return "删除日志方法";
    }
}
