package main.model.entities;


import main.model.entities.keys.StudentLessonKey;

import javax.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {
    @EmbeddedId
    private final StudentLessonKey studentLessonKey;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Student student;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "lesson_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Lesson lesson;

    private boolean attended;

    @Column(name = "is_one_time")
    private boolean isOneTime;

    @Transient
    private final int ONE_TIME_LESSON = 2000;

    @Transient
    private final int SERIES_LESSON = 1500;

    public Attendance() {
        this.studentLessonKey = null;
    }

    public Attendance(int studentId, int lessonId) {
        this.studentLessonKey = new StudentLessonKey(studentId, lessonId);
    }

    public StudentLessonKey getStudentLessonKey() {
        return studentLessonKey;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public boolean hasAttended() {
        return attended;
    }

    public void attend() {
        this.attended = true;
        this.student.setBalance(this.student.getBalance() - (this.isOneTime ? ONE_TIME_LESSON : SERIES_LESSON));
    }

    public boolean getOneTime() {
        return isOneTime;
    }

    public void setOneTime(boolean oneTime) {
        isOneTime = oneTime;
    }
}
