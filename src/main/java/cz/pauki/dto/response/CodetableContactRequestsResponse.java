package cz.pauki.dto.response;

import cz.pauki.dto.RestBaseObject;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Object for codetable (contact requests) response
 */
public class CodetableContactRequestsResponse extends RestBaseObject {

    private List<ContactRequest> contactRequests = new ArrayList<>();

    public List<ContactRequest> getContactRequests() {
        return contactRequests;
    }

    public static class ContactRequest extends RestBaseObject {
        @NotNull
        private String code;
        @NotNull
        private String text;

        public ContactRequest(@NotNull String code, @NotNull String text) {
            this.code = code;
            this.text = text;
        }

        public String getCode() {
            return code;
        }

        public String getText() {
            return text;
        }
    }
}
