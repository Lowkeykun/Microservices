package com.jerome.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

// Customer Dto to separate the business entity to front layer

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold customer and account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer", example = "Jerome Felicilda"
    )
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 5, max = 30, message = "The length of Name should be between 5-30")
    private String name;

    @Schema(
            description = "Email of the customer", example = "jerome@gmail.com"
    )
    @NotEmpty(message = "Email should not be empty!")
    @Email(message = "Invalid email address")
    private String email;

    @Schema(
            description = "Mobile number of the customer", example = "092345612341"
    )
    @NotEmpty(message = "Mobile Number should not be empty!")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Please enter 10 digits of number for your mobile number")
    private String mobileNumber;

    @Schema(
            description = "Account detail for the customer"
    )
    // Added the AccountDto so i can merge the DTOs base on the requirement
    private AccountDto accountDto;
}
