package m.bai2_28_04.Controller;

import m.bai2_28_04.Model.Employee;
import m.bai2_28_04.Model.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public List<Employee> getAll() {
        return employeeService.findAllEmployee();
    }

    @PostMapping("/")
    public String saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
       return employeeService.deleteEmployee(id);
    }
    @GetMapping("/{employeeId}")
    public Object findById(@PathVariable int employeeId){
        return employeeService.findEmployeeById(employeeId);
    }
}
