package main.model.repositories;

import main.model.entities.Attendance;
import main.model.entities.StudentInGroup;
import main.model.entities.keys.StudentLessonKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, StudentLessonKey> {
    List<Attendance> findByLessonId(int lessonId);

    List<Attendance> findByStudentId(int studentId);

    Attendance findByLessonIdAndStudentId(int lessonId, int studentId);
}
