package cz.pauki.controller;

import cz.pauki.dto.response.CodetableContactRequestsResponse;
import cz.pauki.source.RestSource;
import cz.pauki.service.CodetableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * Controller class for 'codetable' REST services
 */
@RestController
public class CodetableController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CodetableController.class);
    private CodetableService codetableService;

    public CodetableController(@NotNull CodetableService codetableService) {
        Assert.notNull(codetableService, "codetableService cannot be null");
        this.codetableService = codetableService;
    }

    @GetMapping(value = RestSource.CODETABLE_CONTACT_REQUESTS)
    public CodetableContactRequestsResponse getContactRequests() {
        LOGGER.debug("getContactRequests");

        return codetableService.getContactRequests();
    }
}
