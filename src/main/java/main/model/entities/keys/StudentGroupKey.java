package main.model.entities.keys;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class StudentGroupKey implements Serializable {
    @Column(name = "student_id")
    private final int studentId;

    @Column(name = "group_id")
    private final int groupId;

    public StudentGroupKey() {
        studentId = 0;
        groupId = 0;
    }

    public StudentGroupKey(int studentId, int groupId) {
        this.studentId = studentId;
        this.groupId = groupId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getGroupId() {
        return groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroupKey that = (StudentGroupKey) o;
        return studentId == that.studentId && groupId == that.groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, groupId);
    }
}
