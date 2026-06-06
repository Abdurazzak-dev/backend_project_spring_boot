package org.example.cruds.controllers;

import org.example.cruds.models.Student;
import org.example.cruds.models.Teacher;
import org.example.cruds.repo.TeacherRepository;
import org.example.cruds.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @GetMapping()
    public Iterable<Teacher> getAll() {
        return service.getAll();
    }

    @GetMapping("/{Id}")
    public Teacher getById(@PathVariable Long Id) {
        return service.getById(Id);
    }

    @PutMapping("/{Id}")
    public Teacher update(@PathVariable Long Id,@RequestBody Teacher teacher) {
        return service.update(Id, teacher);
    }

    @PostMapping()
    public Teacher create(@RequestBody Teacher teacher) {
        return service.create(teacher);
    }

    @DeleteMapping("/{Id}")
    public void delete(@PathVariable Long Id) {
        service.delete(Id);
    }

}
