package com.spring.boot;

import com.spring.boot.controller.HelloController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Description:
 *
 * @JDK Version:1.8.0_40
 * Version: V1.0
 * Author: hs
 * Date: 2018/4/16 15:24
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = MockServletContext.class) 较新版本中已不再使用，使用@SpringBootTest即可
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class HelloControllerTest {
    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello/say").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
