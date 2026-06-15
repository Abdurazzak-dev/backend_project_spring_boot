package kz.university.data.controllers;


import kz.university.data.dto.StudentCardDTO;
import kz.university.data.models.StudentCard;
import kz.university.data.services.StudentCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentcards")
public class StudentCardController {

    private final StudentCardService studentCardService;

    @Autowired
    public StudentCardController(StudentCardService studentCardService) {
        this.studentCardService = studentCardService;
    }

    @GetMapping()
    public List<StudentCard> getAll() {
        return studentCardService.getAll();
    }

    @GetMapping("/{id}")
    public StudentCard getById(@PathVariable Long id)
    {
        return studentCardService.getById(id);
    }

    @PutMapping("/{id}")
    public StudentCard update(@PathVariable Long id, @RequestBody StudentCardDTO studentCard){
        return studentCardService.update(id, studentCard);
    }

    @PostMapping()
    public StudentCard save(@RequestBody StudentCardDTO studentCard) {
        return studentCardService.create(studentCard);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentCardService.delete(id);
    }
}
