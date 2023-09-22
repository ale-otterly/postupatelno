package main.model.repositories;

import main.model.entities.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Integer> {
    List<Lesson> findByDateTimeBetween(LocalDateTime start, LocalDateTime finish);
}
