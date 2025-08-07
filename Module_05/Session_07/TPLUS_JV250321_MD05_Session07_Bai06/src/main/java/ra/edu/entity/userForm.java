package ra.edu.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class userForm {
    @NotBlank(message = "Phone can't not null")
    @Pattern(regexp = "^0\\d{9,10}$",message = "SDT not valid")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public userForm(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public userForm() {
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
