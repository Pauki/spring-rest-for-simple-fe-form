package cz.pauki.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Base class for form 'contact us'
 */
public class ContactFormDTO extends RestBaseObject {

    @NotNull
    private String contactRequestCode;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "policyNumber must contains only alphanumeric characters")
    private String policyNumber;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+$", message = "name could contains only letters")
    private String name;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+$", message = "surname could contains only letters")
    private String surname;

    @NotNull
    @Size(max = 5000, message = "request parameter allow only 5000 characters")
    private String request;

    public String getContactRequestCode() {
        return contactRequestCode;
    }

    public void setContactRequestCode(String contactRequestCode) {
        this.contactRequestCode = contactRequestCode;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
