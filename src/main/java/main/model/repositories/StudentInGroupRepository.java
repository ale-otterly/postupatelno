package main.model.repositories;

import main.controllers.StudentInGroupController;
import main.model.entities.StudentInGroup;
import main.model.entities.keys.StudentGroupKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentInGroupRepository extends CrudRepository<StudentInGroup, StudentGroupKey> {
    List<StudentInGroup> findByGroupId(int groupId);

    List<StudentInGroup> findByStudentId(int studentId);

    StudentInGroup findByGroupIdAndStudentId(int groupId, int studentId);
}
