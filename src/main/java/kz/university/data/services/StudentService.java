package kz.university.data.services;

import kz.university.data.exceptions.ResourceNotFoundException;
import kz.university.data.models.Student;
import kz.university.data.models.StudentGroup;
import kz.university.data.models.Teacher;
import kz.university.data.dto.StudentDTO;
import kz.university.data.repo.StudentGroupRepository;
import kz.university.data.repo.StudentRepository;
import kz.university.data.repo.TeacherRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final TeacherRepository teacherRepository;
    private final StudentGroupRepository groupRepository;
    private static final Logger logger =
            LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository,
                          TeacherRepository teacherRepository,
                          StudentGroupRepository groupRepository) {
        this.repository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.groupRepository = groupRepository;
    }

    public Student getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Student not found, id={}", id);
                    return new ResourceNotFoundException(
                            "Student with id " + id + " not found");
                });
    }

    public Iterable<Student> getAll() {
        return repository.findAll();
    }

    public Student create(StudentDTO dto) {

        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Teacher not found: " + dto.getTeacherId()));

        StudentGroup group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Group not found: " + dto.getGroupId()));

        Student student = new Student();
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setTeacher(teacher);
        student.setGroup(group);

        Student saved = repository.save(student);

        logger.info("Student created, id={}", saved.getId());

        return saved;
    }

    public Student update(Long id, StudentDTO dto) {

        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found: " + id));

        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Teacher not found: " + dto.getTeacherId()));

        StudentGroup group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Group not found: " + dto.getGroupId()));

        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setTeacher(teacher);
        student.setGroup(group);

        Student updated = repository.save(student);

        logger.info("Student updated, id={}", id);

        return updated;
    }

    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found: " + id);
        }

        repository.deleteById(id);

        logger.info("Student deleted, id={}", id);
    }

    public Iterable<Student> findByTeacherId(Long teacherId) {
        return repository.findByTeacherId(teacherId);
    }
}