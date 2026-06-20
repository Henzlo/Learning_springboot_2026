package com.Rest_api.Restful_Api.services;

import com.Rest_api.Restful_Api.dto.EmployeeDTO;
import com.Rest_api.Restful_Api.entities.EmployeeEntity;
import com.Rest_api.Restful_Api.respositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;

        this.modelMapper = modelMapper;
    }







    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//       Optional<EmployeeEntity> employeeEntity= employeeRepository.findById(id);
//
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDTO.class));
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
       List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
       return employeeEntities
               .stream()
               .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
               .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        // to check if user is admin
        //log something
    EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee,EmployeeEntity.class);
    EmployeeEntity saveEmployeeEntity = employeeRepository.save(toSaveEntity);
    return modelMapper.map(saveEmployeeEntity,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        // if this employeeId is prsent in the data then update and if not present then create
        EmployeeEntity employeeEntity = employeeRepository
                .findById(employeeId)
                .orElse(new EmployeeEntity());

        // if the user has porvided the id  but it does not exist
        modelMapper.map(employeeDTO, employeeEntity);

        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    // to make it clear we need to create the method for existbyid
    public boolean isExistByEmployeeId(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean check = isExistByEmployeeId(employeeId);
        if(!check) return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean check = isExistByEmployeeId(employeeId);
        if(!check) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        //reflection-- we can inversely update the field value direclty
        updates.forEach((field,value)->{
            Field fieldToBeUpdates=ReflectionUtils.findField(EmployeeEntity.class,field);
            fieldToBeUpdates.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdates,employeeEntity,value);

        });
         return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }
}
