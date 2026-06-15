package kz.university.data.services;

import kz.university.data.dto.DepartmentDTO;
import kz.university.data.exceptions.ResourceNotFoundException;
import kz.university.data.models.Department;
import kz.university.data.repo.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> getAll() {
        return repository.findAll();
    }

    public Department getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department with id " + id + " not found"));
    }

    public Department create(DepartmentDTO department) {

        Department newDepartment = new Department();
        newDepartment.setName(department.getName());

        return repository.save(newDepartment);
    }

    public Department update(Long id,
                             DepartmentDTO department) {
        Department newDepartment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department with id " + id + " not found"));

        newDepartment.setName(department.getName());

        return repository.save(newDepartment);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Department not found: " + id);
        }
        repository.deleteById(id);
    }
}