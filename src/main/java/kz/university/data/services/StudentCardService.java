package kz.university.data.services;

import kz.university.data.dto.StudentCardDTO;
import kz.university.data.exceptions.ResourceNotFoundException;
import kz.university.data.models.Student;
import kz.university.data.models.StudentCard;
import kz.university.data.repo.StudentCardRepository;
import kz.university.data.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCardService {

    private final StudentCardRepository repository;
    private final StudentRepository studentRepository;

    public StudentCardService(
            StudentCardRepository repository,
            StudentRepository studentRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
    }

    public List<StudentCard> getAll() {
        return repository.findAll();
    }

    public StudentCard getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "StudentCard with id " + id + " not found"));
    }

    public StudentCard create(StudentCardDTO cardDTO) {

        Student student = studentRepository.findById(cardDTO.getStudentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found: " + cardDTO.getStudentId()));

        StudentCard studentCard = new StudentCard();
        studentCard.setStudent(student);
        studentCard.setCardNumber(cardDTO.getCardNumber());
        student.setStudentCard(studentCard);
        return repository.save(studentCard);
    }

    public StudentCard update(Long id,
                              StudentCardDTO cardDTO) {
        StudentCard studentCard = repository.findById(id)
                .orElseThrow(() ->
                new ResourceNotFoundException("Student Card not found: " + id));


        Student student = studentRepository.findById(cardDTO.getStudentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found: " + cardDTO.getStudentId()));

        studentCard.setStudent(student);
        studentCard.setCardNumber(cardDTO.getCardNumber());

        return repository.save(studentCard);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Student Card not found: " + id);
        }
        repository.deleteById(id);
    }
}

