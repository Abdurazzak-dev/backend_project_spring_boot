package org.example.cruds.services;

import org.example.cruds.models.Student;
import org.example.cruds.models.Teacher;
import org.example.cruds.repo.StudentRepository;
import org.example.cruds.repo.TeacherRepository;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    private final StudentRepository repo;
    private final TeacherRepository teacherRepository;

    public StudentService(StudentRepository repo, TeacherRepository teacherRepository) {
        this.repo = repo;
        this.teacherRepository = teacherRepository;
    }


    public Student getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Iterable<Student> getAll() {
        return repo.findAll();
    }

    public Student create(Student student) {
        if (student.getTeacher() != null && student.getTeacher().getId() != null) {
            Long teacherId = student.getTeacher().getId();
            Teacher managedTeacher = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Учитель с ID " + teacherId + " не найден"));
            student.setTeacher(managedTeacher);
        }

        return repo.save(student);
    }

    public Student update(Long id, Student student) {
        Student old = repo.findById(id).orElse(null);
        if (old != null) {
            old.setName(student.getName());
            old.setSurname(student.getSurname());
            old.setTeacher(student.getTeacher());
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
