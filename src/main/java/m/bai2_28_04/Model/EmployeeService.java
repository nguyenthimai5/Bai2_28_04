package m.bai2_28_04.Model;

import m.bai2_28_04.Notification.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeResponsitory employeeResponsitory;

    public List<Employee> findAllEmployee() {
        return employeeResponsitory.findAll();
    }
    public Object findEmployeeById(int id){
        Employee employee = employeeResponsitory.findById(id);
        if (employee!=null) {
            return employee;
        }
        return Message.NULLS;
    }

    public String deleteEmployee(int id) {
        Employee employee = employeeResponsitory.findById(id);
        if (employee!=null) {
            employeeResponsitory.delete(employee);
            return Message.SUCCESS;
        }
        return Message.NULLS;
    }

    public String saveEmployee(Employee employee) {
        Employee checkEmail = employeeResponsitory.findByEmail(employee.getEmail());
        boolean checkRoles = checkRoles(employee.getRoles());
        if (checkEmail==null) {
            employee.setEmail(employee.getEmail());
        } else {
            return Message.DUPLICATEEMAIL;
        }
        if (checkRoles) {
            employee.setRoles(employee.getRoles());
        } else {
            return Message.ROLESNOTEXITS;
        }
        Employee e = employeeResponsitory.save(employee);
        if (e != null) {
            return Message.SUCCESS;
        }
        return Message.FAILURE;


    }

    public String updateEmployee(int id, Employee e) {
        Employee employee=employeeResponsitory.findById(id);
        if (employee!=null) {
            Employee checkEmail = employeeResponsitory.findByEmail(e.getEmail());
            boolean checkRoles = checkRoles(e.getRoles());
            employee.setName(e.getName());
            if (checkEmail==null || checkEmail.getId()==employee.getId()) {
                employee.setEmail(e.getEmail());
            } else {
                    return Message.DUPLICATEEMAIL;
            }
            employee.setDepartment(e.getDepartment());
            if (checkRoles) {
                employee.setRoles(e.getRoles());
            } else {
                return Message.ROLESNOTEXITS;
            }
            employeeResponsitory.save(employee);
            return Message.SUCCESS;
        }
        return Message.NULLS;

    }

    public boolean checkRoles(List<String> listRoles) {
        for (String r : listRoles) {
            if (r.equals("ROLE_ADMIN") || r.equals("ROLE_USER")) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }


}
