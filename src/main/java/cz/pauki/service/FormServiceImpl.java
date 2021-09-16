package cz.pauki.service;

import cz.pauki.controller.CodetableController;
import cz.pauki.db.entity.FormContactUsEntity;
import cz.pauki.db.repository.FormContactUsRepository;
import cz.pauki.dto.request.ContactFormRequest;
import cz.pauki.dto.response.ContactFormResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Collectors;

/**
 * Implementation of {@link FormService}
 */
@Service
public class FormServiceImpl implements FormService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CodetableController.class);
    private final FormContactUsRepository repository;

    public FormServiceImpl(@NotNull FormContactUsRepository repository) {
        Assert.notNull(repository, "repository cannot be null");
        this.repository = repository;
    }

    @Override
    public void saveContactForm(@NotNull ContactFormRequest request) {
        Assert.notNull(request, "request cannot be null");
        LOGGER.debug("saveContactForm {}", request);

        var entity = new FormContactUsEntity();
        entity.setKindOfRequest(request.getContactRequestCode());
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        entity.setPolicyNumber(request.getPolicyNumber());
        entity.setRequest(request.getRequest());
        entity.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));

        repository.save(entity);
    }

    @Override
    public ContactFormResponse getContactFormsData(){
        LOGGER.debug("getContactFormsData");
        final ContactFormResponse result = new ContactFormResponse();

        var entities = repository.findAllByOrderByCreatedAtDesc();
        result.getContactFormsData().addAll(
                entities.stream().map(entity -> {
            var contactFormData = new ContactFormResponse.ContactForm();
            contactFormData.setId(entity.getId());
            contactFormData.setCreatedAt(entity.getCreatedAt());
            contactFormData.setContactRequestCode(entity.getKindOfRequest());
            contactFormData.setName(entity.getName());
            contactFormData.setSurname(entity.getSurname());
            contactFormData.setRequest(entity.getRequest());
            contactFormData.setPolicyNumber(entity.getPolicyNumber());

            return contactFormData;
        }).collect(Collectors.toList()));

        return result;
    }
}
