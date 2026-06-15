package kz.university.data.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "teacher")
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Teacher() {
    }
    public Teacher(String name, String surname, Department department) {
        this.name = name;
        this.surname = surname;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
