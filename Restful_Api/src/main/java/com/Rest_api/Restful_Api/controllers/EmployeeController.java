package com.Rest_api.Restful_Api.controllers;

import com.Rest_api.Restful_Api.dto.EmployeeDTO;
//import com.Rest_api.Restful_Api.entities.EmployeeEntity;
//import com.Rest_api.Restful_Api.respositories.EmployeeRepository;
import com.Rest_api.Restful_Api.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;


// create a parent url path
@RequestMapping("/employees")


@RestController
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



    @GetMapping("/{employeeID}")
    public ResponseEntity<EmployeeDTO> getEmployeeById (@PathVariable(name = "employeeID") Long id){
        // optinal help us to represent a value that may be prsent or not
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                // we need to change here also due to not found employee exceptional handling
                .orElseThrow(()->new NoSuchElementException("Employee not found"));

    }


    // Requestparam --> you need get all the employee and short or filter those in based of other thing like age

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }


    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO saveEmployee=  employeeService.createNewEmployee(inputEmployee);
        return  new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
    }
    // putmapping---------
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO , @PathVariable Long employeeId){

        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDTO));
    }

    //Delete mapping
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotdeleted = employeeService.deleteEmployeeById(employeeId);
        if(gotdeleted) return ResponseEntity.ok(true);
        else return ResponseEntity.notFound().build();

    }
    // update the partial data
    @PatchMapping(path  = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeId(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
        EmployeeDTO employeeDTO=employeeService.updatePartialEmployeeById(employeeId,updates);
        if(employeeDTO==null) return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }


}

