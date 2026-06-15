package kz.university.data.controllers;

import kz.university.data.dto.GroupDTO;
import kz.university.data.models.StudentGroup;
import kz.university.data.services.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class StudentGroupController {

    private final StudentGroupService studentGroupService;

    @Autowired
    public StudentGroupController(StudentGroupService studentGroupService) {
        this.studentGroupService = studentGroupService;
    }

    @GetMapping()
    public List<StudentGroup> getAll() {
        return studentGroupService.getAll();
    }

    @GetMapping("/{id}")
    public StudentGroup getById(@PathVariable Long id) {
        return studentGroupService.getById(id);
    }

    @PutMapping("/{id}")
    public StudentGroup update(@PathVariable Long id,@RequestBody GroupDTO studentGroup) {
        return studentGroupService.update(id, studentGroup);
    }

    @PostMapping()
    public StudentGroup create(@RequestBody GroupDTO studentGroup) {
        return studentGroupService.create(studentGroup);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentGroupService.delete(id);
    }


}
