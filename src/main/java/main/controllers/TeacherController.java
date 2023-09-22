package main.controllers;

import main.model.entities.Teacher;
import main.model.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AdminController adminController;

//    @PostMapping("/teachers")
//    public ResponseEntity<Teacher> add(String name, String phone) {
//        Teacher teacher = new Teacher();
//        teacher.setName(name);
//        teacher.setPhone(phone);
//        teacherRepository.save(teacher);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @GetMapping("/teachers")
    public List<Teacher> getAll() {
        Iterable<Teacher> teacherIterable = teacherRepository.findAll();
        ArrayList<Teacher> teacherList = new ArrayList<>();
        for (Teacher teacher : teacherIterable) {
            teacherList.add(teacher);
        }
        return teacherList;
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable int id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if(teacherOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(teacherOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        ResponseEntity<?> responseEntity = ResponseEntity.status(getById(id).getStatusCode()).body(null);
        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            teacherRepository.deleteById(id);
        }
        return responseEntity;
    }

    //TODO in UPDATE method make sure that all of the fields come from frontend

    @PatchMapping("/teachers/{id}")
    public ResponseEntity<?> patch(@PathVariable int id, @RequestBody Teacher teacher) {
        ResponseEntity<?> responseEntity = getById(id);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Teacher response = (Teacher) responseEntity.getBody();
            teacher.setId(response.getId());

            if(teacher.getName() == null) {
                teacher.setName(response.getName());
            }
            teacherRepository.save(teacher);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/staff")
    public String page(Model model) {
        model.addAttribute("teachersData", getAll());
        model.addAttribute("adminsData", adminController.getAll());
        return "staff_index";
    }
}
