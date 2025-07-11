package com.crudproj.mysecondcrud002.repository;

import com.crudproj.mysecondcrud002.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Employee_repo extends JpaRepository<Employee, Long> {

}
