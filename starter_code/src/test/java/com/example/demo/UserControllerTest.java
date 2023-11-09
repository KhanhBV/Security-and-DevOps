package com.example.demo;

import com.example.demo.model.requests.CreateUserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {
    protected MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void configTest() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mappingData(Object object) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        return objMapper.writeValueAsString(object);
    }

    @Test
    public void findUserByIdSuccessTest() throws Exception {
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/id/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, rs.getResponse().getStatus());
    }

    @Test
    public void findUserByIdInvalidTest() throws Exception {
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/id/11").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(404, rs.getResponse().getStatus());
    }

    @Test
    public void findUserByUserNameSuccessTest() throws Exception {
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/khanh").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, rs.getResponse().getStatus());
    }

    @Test
    public void findUserByUserNameInvalidFailTest() throws Exception {
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/No name").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertNotNull(rs.getResponse().getContentAsString());
    }

    @Test
    public void createUserSuccess() throws Exception {
        CreateUserRequest rq = new CreateUserRequest();
        rq.setUsername("khanhbv");
        rq.setPassword("khanhbui");
        rq.setConfirmPassword("khanhbui");

        String js = mappingData(rq);
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/create").contentType(MediaType.APPLICATION_JSON_VALUE).content(js)).andReturn();
        assertEquals(200, rs.getResponse().getStatus());
    }

    @Test
    public void createUserFailWithPassInvalid() throws Exception {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("khanhbv");
        request.setPassword("khanh");
        request.setConfirmPassword("khanh");

        String js = mappingData(request);
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/create").contentType(MediaType.APPLICATION_JSON_VALUE).content(js)).andReturn();
        assertEquals(400, rs.getResponse().getStatus());
    }

    @Test
    public void createUserWithConfirmPassNotMatch() throws Exception {
        CreateUserRequest rq = new CreateUserRequest();
        rq.setUsername("khanh");
        rq.setPassword("khanhbui");
        rq.setConfirmPassword("khanhvan");

        String js = mappingData(rq);
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/create").contentType(MediaType.APPLICATION_JSON_VALUE).content(js)).andReturn();
        assertEquals(400, rs.getResponse().getStatus());
    }
}
