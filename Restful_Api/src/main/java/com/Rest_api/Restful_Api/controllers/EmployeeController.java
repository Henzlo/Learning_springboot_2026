package com.Rest_api.Restful_Api.controllers;

import com.Rest_api.Restful_Api.dto.EmployeeDTO;
import com.Rest_api.Restful_Api.entities.EmployeeEntity;
import com.Rest_api.Restful_Api.respositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;



// create a parent url path
@RequestMapping("/employees")


@RestController
public class EmployeeController {

//    @GetMapping(path = "/getSecertMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret message: harshit@#9014";
//    }

    // now we direct pass the parameter for the employeerepository
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //     path variable ----> used for the normal ids
    @GetMapping("/{employeeID}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeID") Long id){
//         return new EmployeeDTO(employeeID,  "Harh","harsh@gmial.com", 23,LocalDate.of(2024,1,12),true);
        return employeeRepository.findById(id).orElse(null);
    }


    // Requestparam --> you need get all the employee and short or filter those in based of other thing like age

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
        return employeeRepository.findAll();
    }

    // postmapping
//    @PostMapping
//    public String createNewEmployee(){
//        return "Hello form Post";
//    }

    // postmapping using the requestbody
    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
    }
    // putmapping---------
    @PutMapping
    String updateEmployeeById(){
        return "Hello from put";
    }

}

