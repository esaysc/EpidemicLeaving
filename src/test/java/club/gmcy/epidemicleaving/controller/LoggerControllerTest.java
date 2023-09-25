package club.gmcy.epidemicleaving.controller;

import club.gmcy.epidemicleaving.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class LoggerControllerTest {
    @Autowired(required = false)
    MockMvc mockMvc;
    @Test
    void login() throws Exception {
        log.info("控制层测试 => 登录");
        String json = """
                {
                    "username": "one",
                    "password": "123456"
                }
                """;
        String url = "/login";
        // 发送 post 请求
        String result = mockMvc.perform(post(url)
                        .contentType("application/json") // 以 JSON 格式发送请求体
                        .content(json) // 传 JSON
                ).andExpect(status().isOk()) // 200
                .andReturn().getResponse().getContentAsString();
        log.info("result: {}",result);
    }
}
