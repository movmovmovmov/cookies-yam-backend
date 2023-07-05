package com.cookies.yam.presentation;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@AutoConfigureMockMvc
public class UserApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
    @BeforeEach
    public void setup() {
        String username = "tester1";
        String password = "pw12341234";
        String createdDate = LocalDateTime.now().format(formatter);
        String modifiedDate = LocalDateTime.now().format(formatter);

        String insertSql = "INSERT INTO USER (username, created_date, modified_date, password) " +
                "VALUES('" + username + "', '" + createdDate + "', '" + modifiedDate +"', '" + password +"')";

        jdbcTemplate.execute(insertSql);
    }

    @AfterEach
    public void cleanup() {
        // 데이터 삭제
        String deleteSql = "DELETE FROM USER WHERE username = 'tester1'";
        jdbcTemplate.execute(deleteSql);
    }

    @Test
    public void testCheckUserName() throws Exception {

        String json = "{\"username\":\"john\"}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/vi/auth/join/check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().string("false"));
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Response Body: " + responseBody);

    }
    @Test
    public void testCategory1Modify() throws Exception {
        String json = "{\"username\":\"tester1\", \"category1_id\": 1, \"id\":1}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/vi/user/category1/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void testCategory2Modify() throws Exception {

        String json = "{\"username\":\"tester1\", \"category2_id\": 1}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/vi/user/category2/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(json);
    }


}
