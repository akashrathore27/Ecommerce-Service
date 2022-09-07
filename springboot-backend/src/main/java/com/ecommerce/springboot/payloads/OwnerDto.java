package com.ecommerce.springboot.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class OwnerDto
{
    private long ownerId;

    @NotEmpty
    @Size(min=4 ,message = "Customer Name must be min of 4 characters ")
    private String ownerName;

    @Email(message = "Email address is not valid Please Enter valid Email")
    private String email;

    @NotEmpty
    @Size(min = 8 ,message = "Password Must be min of 8 characters")
    private String password;
}
