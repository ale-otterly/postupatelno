package main.controllers;

import main.model.entities.Student;
import main.model.repositories.AdminRepository;
import main.model.repositories.StudentInGroupRepository;
import main.model.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private StudentInGroupRepository studentInGroupRepository;

    @GetMapping("/students/new")
    public String showForm(Model model) {
        model.addAttribute("studentInfo", new Student());
        model.addAttribute("adminsInfo", adminRepository.findAll());
        return "students_new";
    }

    @PostMapping("/students/new")
    public String add(@ModelAttribute("studentInfo") Student student) {
        studentRepository.save(student);
        return "post";
    }

    public List<Student> getAll() {
        Iterable<Student> studentIterable = studentRepository.findAll();
        ArrayList<Student> studentList = new ArrayList<>();
        for (Student student : studentIterable) {
            studentList.add(student);
        }
        return studentList;
    }

//    @GetMapping("/students/{id}")
//    public ResponseEntity<Student> getById(@PathVariable int id) {
//        Optional<Student> studentOptional = studentRepository.findById(id);
//        if (studentOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//        return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
//    }

    @GetMapping("/students/{id}")
    public String getById(@PathVariable int id, Model model) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            return "not-found-page";
        }
        model.addAttribute("studentData", studentOptional.get());
        model.addAttribute("adminsInfo", adminRepository.findAll());
        model.addAttribute("studentsInGroupsInfo", studentInGroupRepository.findByStudentId(id));
        return "student-card";
    }

    @GetMapping("/students/name")
    public ResponseEntity<Student> getByName(String name) {
        Optional<Student> studentOptional = studentRepository.findByNameContainingIgnoreCase(name);
        if (studentOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
    }

//    @DeleteMapping("/students/{id}")
//    public ResponseEntity<?> delete(@PathVariable int id) {
//        ResponseEntity<?> responseEntity = ResponseEntity.status(getById(id).getStatusCode()).body(null);
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            studentRepository.deleteById(id);
//        }
//        return responseEntity;
//    }

    //TODO in UPDATE method make sure that all of the fields come from frontend

//    @PatchMapping("/students/{id}")
//    public ResponseEntity<?> patch(@PathVariable int id, @RequestBody Student student) {
//        ResponseEntity<?> responseEntity = getById(id);
//
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            Student response = (Student) responseEntity.getBody();
//            student.setId(response.getId());
//
//            if(student.getName() == null) {
//                student.setName(response.getName());
//            }
//            studentRepository.save(student);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    }

    @GetMapping("/students")
    public String page(Model model) {
        model.addAttribute("studentsData", getAll());
        model.addAttribute("adminsInfo", adminRepository.findAll());
        return "students_index";
    }
}
