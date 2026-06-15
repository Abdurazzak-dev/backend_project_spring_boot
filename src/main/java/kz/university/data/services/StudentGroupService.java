package kz.university.data.services;

import kz.university.data.dto.GroupDTO;
import kz.university.data.exceptions.ResourceNotFoundException;
import kz.university.data.models.StudentGroup;
import kz.university.data.repo.StudentGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGroupService {

    private final StudentGroupRepository repository;

    public StudentGroupService(
            StudentGroupRepository repository) {
        this.repository = repository;
    }

    public List<StudentGroup> getAll() {
        return repository.findAll();
    }

    public StudentGroup getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Group with id " + id + " not found"));
    }

    public StudentGroup create(GroupDTO group) {

        StudentGroup studentGroup = new StudentGroup();
        studentGroup.setName(group.getName());

        return repository.save(studentGroup);
    }

    public StudentGroup update(Long id,
                               GroupDTO group) {
        StudentGroup studentGroup= repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Group not found: " + id));

        studentGroup.setName(group.getName());
        return repository.save(studentGroup);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Group not found: " + id);
        }
        repository.deleteById(id);
    }
}

