package kz.university.data.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentGroupId;
    private String studentGroupName;

    @OneToMany
    @JoinColumn(name = "studentGroup")
    private List<Student> students;

    public StudentGroup() {}

    public StudentGroup(Long studentGroupId, String studentGroupName) {
        this.studentGroupId = studentGroupId;
        this.studentGroupName = studentGroupName;
    }

    public String getName() {
        return studentGroupName;
    }

    public void setName(String studentGroupName) {
        this.studentGroupName = studentGroupName;
    }
}
