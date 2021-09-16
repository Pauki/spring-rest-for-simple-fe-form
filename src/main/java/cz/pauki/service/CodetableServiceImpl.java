package cz.pauki.service;

import cz.pauki.controller.CodetableController;
import cz.pauki.db.repository.CodetableContactRequestsRepository;
import cz.pauki.dto.response.CodetableContactRequestsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;

/**
 * Implementation of {@link CodetableService}
 */
@Service
public class CodetableServiceImpl implements CodetableService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CodetableController.class);
    private final CodetableContactRequestsRepository repository;

    public CodetableServiceImpl(@NotNull CodetableContactRequestsRepository repository) {
        Assert.notNull(repository, "repository cannot be null");
        this.repository = repository;
    }

    @Override
    public CodetableContactRequestsResponse getContactRequests() {
        LOGGER.debug("getContactRequests");
        final CodetableContactRequestsResponse result = new CodetableContactRequestsResponse();
        var entities = repository.findAllByOrderByPriorityAsc();

        entities.forEach(entity -> result.getContactRequests()
                .add(new CodetableContactRequestsResponse.ContactRequest(entity.getCode(), entity.getText())));

        return result;
    }
}
