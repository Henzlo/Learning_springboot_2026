package com.Rest_api.Restful_Api.respositories;

import com.Rest_api.Restful_Api.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// for entity we need to create a repository so that we are extends that jparepository and pass the employeeentity class

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

}
