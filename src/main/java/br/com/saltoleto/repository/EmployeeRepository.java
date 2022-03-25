package br.com.saltoleto.repository;

import br.com.saltoleto.model.Employee;
import br.com.saltoleto.model.QEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface EmployeeRepository extends JpaRepository<Employee, Long>,
        QuerydslPredicateExecutor<Employee>, SaltoletoQuerydslBinderCustomizer<QEmployee> {
}
