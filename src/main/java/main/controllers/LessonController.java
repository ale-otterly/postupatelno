package main.controllers;

import main.model.entities.Lesson;
import main.model.repositories.GroupRepository;
import main.model.repositories.LessonRepository;
import main.model.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class LessonController {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private RoomRepository roomRepository;

    @PostMapping("/lessons")
    public ResponseEntity<Lesson> add(int groupId, int roomId, String dateTime) {
        Lesson lesson = new Lesson();
        lesson.setGroup(groupRepository.findById(groupId).get());
        lesson.setRoom(roomRepository.findById(roomId).get());
        lesson.setDateTime(dateTime);
        lessonRepository.save(lesson);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/lessons")
    public List<Lesson> getAll() {
        Iterable<Lesson> lessonIterable = lessonRepository.findAll();
        ArrayList<Lesson> lessonList = new ArrayList<>();
        for (Lesson lesson : lessonIterable) {
            lessonList.add(lesson);
        }
        return lessonList;
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<Lesson> getById(@PathVariable int id) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(id);
        if (lessonOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(lessonOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/lessons/{start}")
    public Map<Integer, List<Lesson>> getByWeek(@PathVariable String start) {
        LocalDateTime from = LocalDate.parse(start, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay();
//        if (lessonOptional.isEmpty()) {
//            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            return null;
//        }
        Map<Integer, List<Lesson>> map = new TreeMap<>();
        return lessonRepository.findByDateTimeBetween(from, from.plusWeeks(1)).stream().collect(Collectors.groupingBy((Lesson les) -> les.getDateTime().getDayOfWeek().getValue()));
        //return new ResponseEntity<>(lessonOptional.get(), HttpStatus.OK);

    }

    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        ResponseEntity<?> responseEntity = ResponseEntity.status(getById(id).getStatusCode()).body(null);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            lessonRepository.deleteById(id);
        }
        return responseEntity;
    }

    //TODO patch method for lesson

    @GetMapping("/timetable")
    public String page(Model model) {
        model.addAttribute("lessonsMap", getByWeek("13-03-2023"));
        model.addAttribute("allLessons", getAll());
        return "timetable_index";
    }
}
