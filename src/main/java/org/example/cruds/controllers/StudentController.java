package org.example.cruds.controllers;

import org.example.cruds.models.Student;
import org.example.cruds.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/teacher/{teacherId}")
    public Iterable<Student> getStudentsByTeacher(@PathVariable Long teacherId) {
        return studentService.findByTeacherId(teacherId);
    }

    @GetMapping()
    public Iterable<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{Id}")
    public Student getById(@PathVariable Long Id) {
        return studentService.getById(Id);
    }

    @PutMapping("/{Id}")
    public Student update(@PathVariable Long Id,@RequestBody Student student) {
        return studentService.update(Id, student);
    }

    @PostMapping()
    public Student create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @DeleteMapping("/{Id}")
    public void delete(@PathVariable Long Id) {
        studentService.delete(Id);
    }



}
