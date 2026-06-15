package kz.university.data.controllers;

import kz.university.data.dto.TeacherDTO;
import kz.university.data.models.Teacher;
import kz.university.data.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;

    @Autowired
    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping()
    public Iterable<Teacher> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Teacher getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Teacher create(@RequestBody TeacherDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public Teacher update(
            @PathVariable Long id,
            @RequestBody TeacherDTO dto) {

        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
