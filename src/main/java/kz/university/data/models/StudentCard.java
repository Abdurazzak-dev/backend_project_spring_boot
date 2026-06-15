package kz.university.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class StudentCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    @JsonIgnore
    private Student student;


    public StudentCard() {
    }
    public StudentCard(String cardNumber,Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
