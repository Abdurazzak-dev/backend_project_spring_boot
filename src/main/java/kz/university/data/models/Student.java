package kz.university.data.models;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToOne(mappedBy = "student")
    private StudentCard studentCard;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private StudentGroup studentGroup;

    public  Student() {
    }

    public Student(String name, String surname, Teacher teacher, StudentCard studentCard, StudentGroup studentGroup) {
        this.name = name;
        this.surname = surname;
        this.teacher = teacher;
        this.studentGroup =  studentGroup;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public StudentGroup getGroup() {
        return studentGroup;
    }

    public void setGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public  StudentCard getStudentCard() {
        return studentCard;
    }

    public void setStudentCard(StudentCard studentCard) {
        this.studentCard = studentCard;
    }

}
