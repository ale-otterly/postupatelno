package main.model.entities.keys;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class StudentLessonKey implements Serializable {
    @Column(name = "student_id")
    private final int studentId;

    @Column(name = "lesson_id")
    private final int lessonId;

    public StudentLessonKey() {
        this.studentId = 0;
        this.lessonId = 0;
    }

    public StudentLessonKey(int studentId, int lessonId) {
        this.studentId = studentId;
        this.lessonId = lessonId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getLessonId() {
        return lessonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentLessonKey that = (StudentLessonKey) o;
        return studentId == that.studentId && lessonId == that.lessonId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, lessonId);
    }
}
