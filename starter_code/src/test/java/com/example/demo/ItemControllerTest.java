package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ItemControllerTest extends SupportTest {

    @Override
    @Before
    public void configTest() {
        super.configTest();
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
