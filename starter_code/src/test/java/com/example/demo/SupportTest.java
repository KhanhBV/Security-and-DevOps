package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SupportTest {
        protected MockMvc mockMvc;
        @Autowired
        WebApplicationContext webApplicationContext;

        protected void configTest() {
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        }

        protected String mappingData(Object object) throws JsonProcessingException {
            ObjectMapper objMapper = new ObjectMapper();
            return objMapper.writeValueAsString(object);
        }
}
