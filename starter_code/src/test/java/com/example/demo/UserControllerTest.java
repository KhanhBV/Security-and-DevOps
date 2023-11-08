package com.example.demo;

import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class UserControllerTest extends SupportTest {
    @Override
    @Before
    public void configTest() {
        super.configTest();
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

        String js = super.mappingData(rq);
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/create").contentType(MediaType.APPLICATION_JSON_VALUE).content(js)).andReturn();
        assertEquals(200, rs.getResponse().getStatus());
    }

    @Test
    public void createUserFailWithPassInvalid() throws Exception {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("khanhbv");
        request.setPassword("khanh");
        request.setConfirmPassword("khanh");

        String js = super.mappingData(request);
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/create").contentType(MediaType.APPLICATION_JSON_VALUE).content(js)).andReturn();
        assertEquals(400, rs.getResponse().getStatus());
    }

    @Test
    public void createUserWithConfirmPassNotMatch() throws Exception {
        CreateUserRequest rq = new CreateUserRequest();
        rq.setUsername("khanh");
        rq.setPassword("khanhbui");
        rq.setConfirmPassword("khanhvan");

        String js = super.mappingData(rq);
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/create").contentType(MediaType.APPLICATION_JSON_VALUE).content(js)).andReturn();
        assertEquals(400, rs.getResponse().getStatus());
    }
}
