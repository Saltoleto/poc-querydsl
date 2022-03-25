package br.com.saltoleto.controller;

import br.com.saltoleto.model.Employee;
import br.com.saltoleto.repository.EmployeeRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController extends BaseController<Employee> {

    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        super(employeeRepository);
    }

    @GetMapping(value = "find")
    public Page getAll(@QuerydslPredicate(root = Employee.class) Predicate predicate, Pageable pageable) {
        return super.getCollectionResource(predicate, pageable);
    }


}
