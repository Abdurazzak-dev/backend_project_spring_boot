package org.example.cruds.services;

import org.example.cruds.models.Teacher;
import org.example.cruds.repo.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher getById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public Iterable<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public Teacher create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher update(Long id, Teacher teacher) {
        Teacher t = teacherRepository.findById(id).orElse(null);
        if (t != null) {
            t.setName(teacher.getName());
            t.setSurname(teacher.getSurname());
            return teacherRepository.save(teacher);
        }
        return null;
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }


}
