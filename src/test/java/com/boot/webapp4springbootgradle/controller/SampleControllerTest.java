package com.boot.webapp4springbootgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest {
    @Resource
    MockMvc mockMvc;

    @Resource
    ObjectMapper objectMapper;

    @Test
    public void test_server_time() throws Exception{
        mockMvc.perform(get("/server_time"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test_get_list() throws Exception{
        mockMvc.perform(get("/get_list"))
                .andDo(print())
                .andExpect(status().isOk());
    }

//    @Test
//    public void test_delete_sample_ajax() throws Exception{
//        Map<String, String> map = new HashMap<>();
//        map.put("eno", "4");
//
//        String value = objectMapper.writeValueAsString(map);
//
//        MvcResult result = mockMvc.perform(post("/delete_sample_ajax")
//                                        .contentType(MediaType.APPLICATION_JSON)
//                                        .content(value))
//                                    .andDo(print())
//                                    .andExpect(status().isOk())
//                                    .andExpect(jsonPath("$.result").value("OK"))
//                                    .andReturn();
//        String res = result.getResponse().getContentAsString();
//        System.out.println("res="+res);
//
//    }
}
