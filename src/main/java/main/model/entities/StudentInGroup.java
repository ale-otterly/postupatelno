package main.model.entities;

import main.model.entities.keys.StudentGroupKey;

import javax.persistence.*;

@Entity
@Table(name = "students_in_groups")
public class StudentInGroup {
    @EmbeddedId
    private final StudentGroupKey studentGroupKey;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Student student;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "group_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Group group;

    @Column(name = "waiting_list")
    private boolean waitingList;

    public StudentInGroup() {
        studentGroupKey = null;
    }

    public StudentInGroup(int studentId, int groupId) {
        this.studentGroupKey = new StudentGroupKey(studentId, groupId);
    }

    public StudentGroupKey getStudentGroupKey() {
        return studentGroupKey;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isWaitingList() {
        return waitingList;
    }

    public void setWaitingList(boolean waitingList) {
        this.waitingList = waitingList;
    }
}
