package com.Rest_api.Restful_Api.dto;

import com.Rest_api.Restful_Api.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {
    private Long id;
//    notNUll->say that that field is notNUll , NotEmpty->say that in that field there is something including space "   ", NotBlank-> the field does not have blank in it,size =limit of size in the field
    @NotBlank(message = "Name of the field cannot be blank" )
    @Size(min = 4,max = 10,message = "Number of character should be in the range : [4,10]")
    private String name;

    @NotBlank(message = "Email filed cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "age filed cannot be NUll")
    @Max(value = 80 ,message = "Age cannot be greater than 80 ")
    @Min(value = 18,message = "Age must be greater than 18")
    private Integer age;

    @NotBlank(message = "Role filed cannot be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$",message = "Role of the employee can be USER or ADMIN")
    @EmployeeRoleValidation
    private String role; // ADMIN | USER

    @NotNull(message = "salary of employeee should be not null")
    @Positive(message = "Salary of employee shuld be positve")
    @Digits(integer = 6,fraction = 2,message = "the salary cannot be more than 6 figure with 2 fraction")
    @DecimalMax(value= "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @PastOrPresent(message = "dateofjoining field must not be of future")
    private LocalDate dateOfJoining;


    // jasonProperty is used for handle Jackson annotation used to map a JSON field name to a Java field when the names are different.
    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;

}





