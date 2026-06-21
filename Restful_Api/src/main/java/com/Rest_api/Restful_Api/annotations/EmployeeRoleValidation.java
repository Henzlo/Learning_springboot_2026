package com.Rest_api.Restful_Api.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
//we nee to pass the value to the constraint of employeerolevalidator logic class to here or connect it
@Constraint(validatedBy = {EmployeeRoleValidator.class})

public @interface EmployeeRoleValidation {
    String message() default "Role of the employee can be USER or ADMIN";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
