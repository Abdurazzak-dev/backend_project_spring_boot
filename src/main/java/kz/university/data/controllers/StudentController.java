package kz.university.data.controllers;

import jakarta.validation.Valid;
import kz.university.data.models.Student;
import kz.university.data.dto.StudentDTO;
import kz.university.data.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Iterable<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PostMapping
    public Student create(@Valid @RequestBody StudentDTO dto) {
        return studentService.create(dto);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id,
                          @Valid @RequestBody StudentDTO dto) {
        return studentService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }

    @GetMapping("/teacher/{teacherId}")
    public Iterable<Student> getByTeacher(@PathVariable Long teacherId) {
        return studentService.findByTeacherId(teacherId);
    }
}