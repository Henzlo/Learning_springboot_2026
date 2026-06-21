package com.Rest_api.Restful_Api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class EmployeeEntity {

    // for the unique identity we need to squence wise generate the id
    @Id
    // and for the generate the value we ue
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String role;
    private Double salary;
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")

    private Boolean isActive;
}
