package com.example.demo;

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
public class ItemControllerTest {

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
    public void getAllItemsSuccessTest() throws Exception {
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.get("/api/item").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, rs.getResponse().getStatus());
    }

    @Test
    public void getItemByIdSuccessTest() throws Exception {
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.get("/api/item/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, rs.getResponse().getStatus());
    }

    @Test
    public void getItemFailByInvalidIdTest() throws Exception {
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.get("/api/item/11").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(404, rs.getResponse().getStatus());
    }

    @Test
    public void getItemsSuccessByName() throws Exception {
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.get("/api/item/name/Round Widget").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, rs.getResponse().getStatus());
    }

    @Test
    public void getItemsFailByInvalidName() throws Exception {
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.get("/api/item/name/no name").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertNotNull(rs.getResponse().getContentAsString());
    }
}
