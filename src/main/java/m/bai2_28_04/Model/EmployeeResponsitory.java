package m.bai2_28_04.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeResponsitory extends JpaRepository<Employee,Integer> {
Employee findByEmail(String email);
Employee findById(int id);
}
