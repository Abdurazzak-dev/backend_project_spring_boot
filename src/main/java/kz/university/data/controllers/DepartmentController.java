package kz.university.data.controllers;

import kz.university.data.dto.DepartmentDTO;
import kz.university.data.models.Department;
import kz.university.data.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public Iterable<Department> getAll() {
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id)
    {
        return departmentService.getById(id);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody DepartmentDTO department){
        return departmentService.update(id, department);
    }

    @PostMapping()
    public Department save(@RequestBody DepartmentDTO department)
    {
        return departmentService.create(department);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.delete(id);
    }
}
