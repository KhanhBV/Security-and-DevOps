package com.example.demo;

import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderControllerTest extends SupportTest {
    @Override
    @Before
    public void configTest() {
        super.configTest();
    }

    @Test
    public void submitOrder() throws Exception {
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/order/submit/khanh").contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, rs.getResponse().getStatus());
    }

    @Test
    public void submitOrderFailWithUsernameTest() throws Exception {
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/order/submit/khanhbvse").contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(404, rs.getResponse().getStatus());
    }

    @Test
    public void getOrdersSuccessForUserTest() throws Exception {
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.get("/api/order/history/khanh").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, rs.getResponse().getStatus());
    }

    @Test
    public void getOrdersFailForUser() throws Exception {
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.get("/api/order/history/BuiVanKhanh").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertNotNull(rs.getResponse().getContentAsString());
    }
}
