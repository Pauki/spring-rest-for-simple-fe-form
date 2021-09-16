package cz.pauki.controller;

import cz.pauki.handler.ControllerExceptionHandler;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@WebMvcTest({ControllerExceptionHandler.class})
abstract class AbstractControllerTest {

    @Autowired
    private WebApplicationContext context;
    MockMvc mvc;

    @Before
    public void init(){
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }
}