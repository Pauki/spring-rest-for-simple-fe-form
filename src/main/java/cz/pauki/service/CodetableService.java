package cz.pauki.service;

import cz.pauki.dto.response.CodetableContactRequestsResponse;

/**
 * Interface for codetable service
 */
public interface CodetableService {

    /**
     *
     * @return object with list of contact requests
     */
    CodetableContactRequestsResponse getContactRequests();
}
