package main.model.repositories;

import main.model.entities.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    @Query(value = "SELECT * FROM payments p JOIN `students` ON `p.student_id` = `students.id` WHERE `students.name` LIKE '%:name%'",
            nativeQuery = true)
    Optional<Payment> findByStudentName(@Param("name") String name);
}
