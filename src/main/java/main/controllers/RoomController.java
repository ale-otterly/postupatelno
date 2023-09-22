package main.controllers;

import main.model.entities.Room;
import main.model.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @PostMapping("/rooms")
    public ResponseEntity<Room> add(String name) {
        Room room = new Room();
        room.setName(name);
        roomRepository.save(room);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/rooms")
    public List<Room> getAll() {
        Iterable<Room> roomIterable = roomRepository.findAll();
        ArrayList<Room> roomList = new ArrayList<>();
        for (Room room : roomIterable) {
            roomList.add(room);
        }
        return roomList;
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getById(@PathVariable int id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(roomOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        ResponseEntity<?> responseEntity = ResponseEntity.status(getById(id).getStatusCode()).body(null);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            roomRepository.deleteById(id);
        }
        return responseEntity;
    }

    @PatchMapping("/rooms/{id}")
    public ResponseEntity<?> update(@PathVariable int id, String name) {
        Room room = getById(id).getBody();
        ResponseEntity<?> responseEntity = ResponseEntity.status(getById(id).getStatusCode()).body(null);
        if (responseEntity.getStatusCode().is2xxSuccessful() && room != null) {
            room.setName(name);
            roomRepository.save(room);
        }
        return responseEntity;
    }
}
