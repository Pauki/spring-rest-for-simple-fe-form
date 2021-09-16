package cz.pauki.controller;

import cz.pauki.dto.request.ContactFormRequest;
import cz.pauki.dto.response.CodetableContactRequestsResponse;
import cz.pauki.service.FormService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = FormController.class)
public class FormControllerTest extends AbstractControllerTest {

    @MockBean
    private FormService formService;

    @Test
    public void saveContactFormBadRequest() throws Exception {
        MvcResult mvcResult = mvc.perform(post("/form/contactRequests")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "    \"contactRequestCode\": \"DAMAGE_CASE\",\n" +
                        "    \"policyNumber\": \"123DAGI--++\",\n" +
                        "    \"name\": \"Jarda4577\",\n" +
                        "    \"surname\": \"Fousek7874\",\n" +
                        "    \"request\": \"Mám problém!!!!\"\n" +
                        "  }"))
                .andExpect(status().isBadRequest()).andReturn();

        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("name-name could contains only letters"));
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("surname-surname could contains only letters"));
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("policyNumber-policyNumber must contains only alphanumeric characters"));
    }

    @Test
    public void saveContactForm() throws Exception {
        var captor = ArgumentCaptor.forClass(ContactFormRequest.class);
        var mockResponse = new CodetableContactRequestsResponse();
        mockResponse.getContactRequests().add(new CodetableContactRequestsResponse.ContactRequest("code", "text"));
        Mockito.doNothing().when(formService).saveContactForm(captor.capture());

        MvcResult mvcResult = mvc.perform(post("/form/contactRequests")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "    \"contactRequestCode\": \"DAMAGE_CASE\",\n" +
                        "    \"policyNumber\": \"123DAGI\",\n" +
                        "    \"name\": \"Jarda\",\n" +
                        "    \"surname\": \"Fousek\",\n" +
                        "    \"request\": \"Mám problém!!!!\"\n" +
                        "  }"))
                .andExpect(status().isCreated()).andReturn();

        Assert.assertEquals("DAMAGE_CASE", captor.getValue().getContactRequestCode());
        Assert.assertEquals("123DAGI", captor.getValue().getPolicyNumber());
        Assert.assertEquals("Jarda", captor.getValue().getName());
        Assert.assertEquals("Fousek", captor.getValue().getSurname());
        Assert.assertEquals("Mám problém!!!!", captor.getValue().getRequest());
    }
}