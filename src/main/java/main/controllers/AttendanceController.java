package main.controllers;

import main.model.entities.Attendance;
import main.model.entities.Group;
import main.model.entities.keys.StudentLessonKey;
import main.model.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AttendanceController {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @PostMapping("/attendance")
    public ResponseEntity<Attendance> add(int studentId, int lessonId) {
        Attendance attendance = new Attendance(studentId, lessonId);
        attendance.setStudent(studentRepository.findById(studentId).get());
        attendance.setLesson(lessonRepository.findById(lessonId).get());
        attendanceRepository.save(attendance);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/attendance/{studentId}_{lessonId}")
    public ResponseEntity<?> attend(@PathVariable int studentId, @PathVariable int lessonId) {
        Optional<Attendance> attendanceOptional = attendanceRepository.findById(new StudentLessonKey(studentId, lessonId));
        if (attendanceOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Attendance attendance = attendanceOptional.get();
        attendance.attend();
        attendanceRepository.save(attendance);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO get by student id
    //TODO get by lesson id
    // TODO delete by key
    // TODO update
}
