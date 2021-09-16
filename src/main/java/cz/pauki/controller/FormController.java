package cz.pauki.controller;

import cz.pauki.dto.request.ContactFormRequest;
import cz.pauki.dto.response.ContactFormResponse;
import cz.pauki.service.FormService;
import cz.pauki.source.RestSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Controller class for 'form' REST services
 */
@RestController
public class FormController {

    private final static Logger LOGGER = LoggerFactory.getLogger(FormController.class);
    private FormService formService;

    public FormController(@NotNull FormService formService) {
        Assert.notNull(formService, "formService cannot be null");
        this.formService = formService;
    }

    @PostMapping(value = RestSource.FORM_CONTACT_US, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveContactForm(@RequestBody @Valid ContactFormRequest request) {
        LOGGER.debug("saveContactForm {}", request);

        formService.saveContactForm(request);
    }

    @GetMapping(value = RestSource.FORM_CONTACT_US)
    public ContactFormResponse getContactFormsData() {
        LOGGER.debug("getContactFormsData");

        return formService.getContactFormsData();
    }
}
