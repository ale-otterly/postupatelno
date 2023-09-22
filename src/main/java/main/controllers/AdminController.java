package main.controllers;

import main.model.entities.Admin;
import main.model.entities.Student;
import main.model.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/admins/new")
    public String add(Admin admin) {
        adminRepository.save(admin);
        return "index";
    }

    @GetMapping("/admins")
    public List<Admin> getAll() {
        Iterable<Admin> adminIterable = adminRepository.findAll();
        ArrayList<Admin> adminList = new ArrayList<>();
        for (Admin admin : adminIterable) {
            adminList.add(admin);
        }
        return adminList;
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity<Admin> getById(@PathVariable int id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(adminOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/admins/name")
    public ResponseEntity<Admin> getByName(String name) {
        Optional<Admin> adminOptional = adminRepository.findByNameContainingIgnoreCase(name);
        if (adminOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(adminOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        ResponseEntity<?> responseEntity = ResponseEntity.status(getById(id).getStatusCode()).body(null);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            adminRepository.deleteById(id);
        }
        return responseEntity;
    }

    //TODO in UPDATE method make sure that all of the fields come from frontend

    @PatchMapping("/admins/{id}")
    public ResponseEntity<?> patch(@PathVariable int id, @RequestBody Admin admin) {
        ResponseEntity<?> responseEntity = getById(id);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Admin response = (Admin) responseEntity.getBody();
            admin.setId(response.getId());

            if(admin.getName() == null) {
                admin.setName(response.getName());
            }
            adminRepository.save(admin);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
