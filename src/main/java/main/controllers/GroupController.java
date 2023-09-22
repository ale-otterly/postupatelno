package main.controllers;

import main.model.entities.Group;
import main.model.entities.Student;
import main.model.entities.StudentInGroup;
import main.model.entities.enums.Subject;
import main.model.repositories.GroupRepository;
import main.model.repositories.StudentRepository;
import main.model.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentInGroupController studentInGroupController;

    @PostMapping("/groups")
    public ResponseEntity<Group> add(Subject subject, int year, int teacherId) {
        Group group = new Group();
        group.setSubject(subject);
        group.setYear(year);
        group.setTeacher(teacherRepository.findById(teacherId).get());
        groupRepository.save(group);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public List<Group> getAll() {
        Iterable<Group> groupIterable = groupRepository.findAll();
        ArrayList<Group> groupList = new ArrayList<>();
        for (Group group : groupIterable) {
            groupList.add(group);
        }
        return groupList;
    }

//    @GetMapping("/groups/{id}")
//    public ResponseEntity<Group> getById(@PathVariable int id) {
//        Optional<Group> groupOptional = groupRepository.findById(id);
//        if (groupOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//        return new ResponseEntity<>(groupOptional.get(), HttpStatus.OK);
//    }

    @GetMapping("/groups/{id}")
    public String getById(@PathVariable int id, Model model) {
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isEmpty()) {
            return "not-found-page";
        }
        Group group = groupOptional.get();
        model.addAttribute("groupInfo", group);
        model.addAttribute("activeStudents", getActiveStudents(id, model));
        model.addAttribute("waitingStudents", getWaitingStudents(id, model));
        return "attendance_index";
    }

    public List<Student> getActiveStudents(int groupId, Model model) {
        return studentInGroupController.getByGroupId(groupId).stream().filter(e -> !e.isWaitingList()).map(StudentInGroup::getStudent).toList();
    }

    public List<Student> getWaitingStudents(int groupId, Model model) {
        return studentInGroupController.getByGroupId(groupId).stream().filter(StudentInGroup::isWaitingList).map(StudentInGroup::getStudent).toList();
    }

//    @DeleteMapping("/groups/{id}")
//    public ResponseEntity<?> delete(@PathVariable int id) {
//        ResponseEntity<?> responseEntity = ResponseEntity.status(getById(id).getStatusCode()).body(null);
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            groupRepository.deleteById(id);
//        }
//        return responseEntity;
//    }


    //TODO UPDATE method for groups

    @GetMapping("/groups")
    public String page(Model model) {
        model.addAttribute("groupsData", getAll());
        model.addAttribute("sigController", studentInGroupController);
        return "groups_index";
    }
}
