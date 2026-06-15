package kz.university.data.services;

import kz.university.data.dto.TeacherDTO;
import kz.university.data.exceptions.ResourceNotFoundException;
import kz.university.data.models.Department;
import kz.university.data.models.Teacher;
import kz.university.data.repo.DepartmentRepository;
import kz.university.data.repo.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private final TeacherRepository repository;
    private final DepartmentRepository departmentRepository;

    public TeacherService(
            TeacherRepository repository,
            DepartmentRepository departmentRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
    }
    public Teacher getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Teacher with id " + id + " not found"));    }

    public Iterable<Teacher> getAll() {
        return repository.findAll();
    }

    public Teacher create(TeacherDTO dto) {

        Department department = departmentRepository
                .findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department with id "
                                        + dto.getDepartmentId()
                                        + " not found"));

        Teacher teacher = new Teacher();
        teacher.setName(dto.getName());
        teacher.setSurname(dto.getSurname());
        teacher.setDepartment(department);

        return repository.save(teacher);
    }
    public Teacher update(Long id, TeacherDTO dto) {

        Teacher teacher = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Teacher with id " + id + " not found"));

        Department department = departmentRepository
                .findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department with id "
                                        + dto.getDepartmentId()
                                        + " not found"));

        teacher.setName(dto.getName());
        teacher.setSurname(dto.getSurname());
        teacher.setDepartment(department);

        return repository.save(teacher);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Teacher not found: " + id);
        }
        repository.deleteById(id);
    }


}
