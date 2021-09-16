package cz.pauki.dto.response;

import cz.pauki.dto.ContactFormDTO;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Object for form 'contact us' response
 */
public class ContactFormResponse {

    private List<ContactForm> contactFormsData = new ArrayList<>();

    public List<ContactForm> getContactFormsData() {
        return contactFormsData;
    }

    public static class ContactForm extends ContactFormDTO {
        private BigInteger id;
        private LocalDateTime createdAt;

        public BigInteger getId() {
            return id;
        }

        public void setId(BigInteger id) {
            this.id = id;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }
    }

}
