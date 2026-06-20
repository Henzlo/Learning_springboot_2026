package com.Rest_api.Restful_Api.controllers;

import com.Rest_api.Restful_Api.dto.EmployeeDTO;
import com.Rest_api.Restful_Api.entities.EmployeeEntity;
import com.Rest_api.Restful_Api.respositories.EmployeeRepository;
import com.Rest_api.Restful_Api.services.EmployeeService;
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
//    private final EmployeeRepository employeeRepository; ---> due to we dont use this thing we use this by adding service
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //    public EmployeeController(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
    // we remvoe the employeecontroller constuctor and use the employeeservice constructor


    //     path variable ----> used for the normal ids
    @GetMapping("/{employeeID}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeID") Long id){
//         return new EmployeeDTO(employeeID,  "Harh","harsh@gmial.com", 23,LocalDate.of(2024,1,12),true);
        return employeeService.getEmployeeById(id);


    }


    // Requestparam --> you need get all the employee and short or filter those in based of other thing like age

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }

    // postmapping
//    @PostMapping
//    public String createNewEmployee(){
//        return "Hello form Post";
//    }

    // postmapping using the requestbody
    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }
    // putmapping---------
    @PutMapping("/{employeeId}")
    EmployeeDTO updateEmployeeById(){
        return "hwollo form put";
    }


}

