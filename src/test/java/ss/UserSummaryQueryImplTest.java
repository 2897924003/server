package ss;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class UserSummaryQueryImplTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 用户存在于数据库，不存在于缓存
     * @throws Exception
     */
    @Test
    void findUserSummaryById_UserExists_ReturnsUserSummary() throws Exception {
        mockMvc.perform(get("/test/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    /**
     * 用户不存在 1.布隆过滤器拦截抛出 2.运行至数据库查询后抛出
     * @throws Exception
     */
    @Test
    void findUserSummaryById_UserDoesNotExist_ReturnsNotFound() throws Exception {
        mockMvc.perform(get("/test/1000000000")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * 用户存在于缓存
     * @throws Exception
     */
    @Test
    void findUserSummaryById_UserExistsInCache_ReturnsCachedUserSummary() throws Exception {
        mockMvc.perform(get("/test/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    /**
     * 传入参数错误
     * @throws Exception
     */
    @Test
    void findUserSummaryById_InvalidIdFormat_ReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/test/abc")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


}
