package cz.pauki.controller;

import cz.pauki.dto.response.CodetableContactRequestsResponse;
import cz.pauki.service.CodetableService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CodetableController.class)
public class CodetableControllerTest extends AbstractControllerTest {

    @MockBean
    private CodetableService codetableService;

    @Test
    public void getContactRequests() throws Exception {
        var mockResponse = new CodetableContactRequestsResponse();
        mockResponse.getContactRequests().add(new CodetableContactRequestsResponse.ContactRequest("code", "text"));
        Mockito.when(codetableService.getContactRequests()).thenReturn(mockResponse);

        MvcResult mvcResult = mvc.perform(get("/codetable/contactRequests"))
                .andExpect(status().isOk()).andReturn();

        Assert.assertEquals("{\"contactRequests\":[{\"code\":\"code\",\"text\":\"text\"}]}",
                mvcResult.getResponse().getContentAsString());
    }
}