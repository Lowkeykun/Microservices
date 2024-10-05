package com.jerome.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Accounts",
        description = "Schema to hold Accounts information"
)
// Account Dto to separate the business entity to front layer
@Data
public class AccountDto {

    @Schema(
            description = "Account number of customer", example = "2374519374"
    )
    @NotEmpty(message = "Account number should not be empty!")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Please enter 10 digits for your Account number")
    private Long accountNumber;

    @Schema(
            description = "Account type of the customer", example = "Savings"
    )
    @NotEmpty(message = "Account Type should not be empty!")
    private String accountType;

    @Schema(
            description = "Branch address of the customer", example = "124 New York"
    )
    @NotEmpty(message = "Branch Address should not be empty!")
    private String branchAddress;
}
