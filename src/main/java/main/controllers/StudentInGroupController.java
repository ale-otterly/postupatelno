package main.controllers;

import main.model.entities.Group;
import main.model.entities.Lesson;
import main.model.entities.StudentInGroup;
import main.model.repositories.GroupRepository;
import main.model.repositories.StudentInGroupRepository;
import main.model.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentInGroupController {
    @Autowired
    private StudentInGroupRepository studentInGroupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @PostMapping("/studentsInGroups")
    public ResponseEntity<StudentInGroup> add(int studentId, int groupId) {
        StudentInGroup studentInGroup = new StudentInGroup(studentId, groupId);
        studentInGroup.setStudent(studentRepository.findById(studentId).get());
        studentInGroup.setGroup(groupRepository.findById(groupId).get());
        studentInGroupRepository.save(studentInGroup);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public List<StudentInGroup> getAll() {
        Iterable<StudentInGroup> studentInGroupIterable = studentInGroupRepository.findAll();
        ArrayList<StudentInGroup> studentInGroupList = new ArrayList<>();
        for (StudentInGroup item : studentInGroupIterable) {
            studentInGroupList.add(item);
        }
        return studentInGroupList;
    }

    public List<StudentInGroup> getByGroupId(int groupId) {
        return studentInGroupRepository.findByGroupId(groupId);
    }

    public List<StudentInGroup> getByStudentId(int studentId) {
        return studentInGroupRepository.findByStudentId(studentId);
    }

    public StudentInGroup getByGroupIdAndStudentId(int groupId, int studentId) {
        return studentInGroupRepository.findByGroupIdAndStudentId(groupId, studentId);
    }


    // TODO delete by key
    // TODO update
}
