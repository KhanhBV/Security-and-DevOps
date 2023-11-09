package com.example.demo;

import com.example.demo.model.requests.ModifyCartRequest;
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
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CartControllerTest {

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
    public void addToCartSuccessTest() throws Exception {
        ModifyCartRequest rq = new ModifyCartRequest();
        rq.setUsername("khanh");
        rq.setItemId(1);
        rq.setQuantity(2);

        String js = mappingData(rq);
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/addToCart").contentType(MediaType.APPLICATION_JSON_VALUE).content(js)).andReturn();
        assertEquals(200, rs.getResponse().getStatus());
    }

    @Test
    public void addToCartFailedTest() throws Exception {
        ModifyCartRequest rq = new ModifyCartRequest();
        rq.setUsername("khanhbvse");
        rq.setItemId(1);
        rq.setQuantity(3);

        String js = mappingData(rq);
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/addToCart").contentType(MediaType.APPLICATION_JSON_VALUE).content(js)).andReturn();
        assertEquals(404, rs.getResponse().getStatus());
    }

    @Test
    public void removeFromCartSuccessTest() throws Exception {
        ModifyCartRequest rq = new ModifyCartRequest();
        rq.setUsername("khanh");
        rq.setItemId(1);
        rq.setQuantity(5);

        String js = mappingData(rq);
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/removeFromCart").contentType(MediaType.APPLICATION_JSON_VALUE).content(js)).andReturn();
        assertEquals(200, rs.getResponse().getStatus());
    }

    @Test
    public void removeFromCartFailedTest() throws Exception {
        ModifyCartRequest rq = new ModifyCartRequest();
        rq.setUsername("khanhbvse");
        rq.setItemId(1);
        rq.setQuantity(3);

        String js = mappingData(rq);
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/removeFromCart").contentType(MediaType.APPLICATION_JSON_VALUE).content(js)).andReturn();
        assertEquals(404, rs.getResponse().getStatus());
    }
}