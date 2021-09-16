package cz.pauki.service;

import cz.pauki.dto.request.ContactFormRequest;
import cz.pauki.dto.response.CodetableContactRequestsResponse;
import cz.pauki.dto.response.ContactFormResponse;

/**
 * Interface for form service
 */
public interface FormService {

    /**
     * Save data from contact us form to db
     * @param request object with form data
     */
    void saveContactForm(ContactFormRequest request);

    /**
     * Return data from all filled contact us forms
     * @return object with response list
     */
    ContactFormResponse getContactFormsData();
}
