package org.example.cruds.services;

import org.example.cruds.models.Student;
import org.example.cruds.repo.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }


    public Student getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Iterable<Student> getAll() {
        return repo.findAll();
    }

    public Student create(Student student) {
        return repo.save(student);
    }

    public Student update(Long id, Student student) {
        Student old = repo.findById(id).orElse(null);
        if (old != null) {
            old.setName(student.getName());
            old.setSurname(student.getSurname());
            return repo.save(old);
        }
        return null;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Iterable<Student> findByTeacherId(Long teacherId) {
        return repo.findByTeacherId(teacherId);
    }

}
