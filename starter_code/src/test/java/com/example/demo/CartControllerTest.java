package com.example.demo;

import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class CartControllerTest extends SupportTest {
    @Override
    @Before
    public void configTest() {
        super.configTest();
    }

    @Test
    public void addToCartSuccessTest() throws Exception {
        ModifyCartRequest rq = new ModifyCartRequest();
        rq.setUsername("khanh");
        rq.setItemId(1);
        rq.setQuantity(2);

        String js = super.mappingData(rq);
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/addToCart").contentType(MediaType.APPLICATION_JSON_VALUE).content(js)).andReturn();
        assertEquals(200, rs.getResponse().getStatus());
    }

    @Test
    public void addToCartFailedTest() throws Exception {
        ModifyCartRequest rq = new ModifyCartRequest();
        rq.setUsername("khanhbvse");
        rq.setItemId(1);
        rq.setQuantity(3);

        String js = super.mappingData(rq);
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/addToCart").contentType(MediaType.APPLICATION_JSON_VALUE).content(js)).andReturn();
        assertEquals(404, rs.getResponse().getStatus());
    }

    @Test
    public void removeFromCartSuccessTest() throws Exception {
        ModifyCartRequest rq = new ModifyCartRequest();
        rq.setUsername("khanh");
        rq.setItemId(1);
        rq.setQuantity(5);

        String js = super.mappingData(rq);
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/removeFromCart").contentType(MediaType.APPLICATION_JSON_VALUE).content(js)).andReturn();
        assertEquals(200, rs.getResponse().getStatus());
    }

    @Test
    public void removeFromCartFailedTest() throws Exception {
        ModifyCartRequest rq = new ModifyCartRequest();
        rq.setUsername("khanhbvse");
        rq.setItemId(1);
        rq.setQuantity(3);

        String js = super.mappingData(rq);
        MvcResult rs = mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/removeFromCart").contentType(MediaType.APPLICATION_JSON_VALUE).content(js)).andReturn();
        assertEquals(404, rs.getResponse().getStatus());
    }
}