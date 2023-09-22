package main.model.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;

    private String phone;

    private String color;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    @JoinColumn(columnDefinition = "teacher_id", referencedColumnName = "id")
    private Set<Group> groups;

    public Teacher() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Group> getGroups() {
        return groups;
    }
}
