import kz.university.data.dto.StudentDTO;
import kz.university.data.exceptions.ResourceNotFoundException;
import kz.university.data.models.Student;
import kz.university.data.models.StudentCard;
import kz.university.data.models.StudentGroup;
import kz.university.data.models.Teacher;
import kz.university.data.repo.StudentCardRepository;
import kz.university.data.repo.StudentGroupRepository;
import kz.university.data.repo.StudentRepository;
import kz.university.data.repo.TeacherRepository;
import kz.university.data.services.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private StudentGroupRepository groupRepository;

    @Mock
    private StudentCardRepository cardRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void createStudent() {

        StudentDTO dto = new StudentDTO();
        dto.setName("Ali");
        dto.setSurname("Valiyev");
        dto.setTeacherId(1L);
        dto.setGroupId(1L);
        dto.setStudentCardId(1L);

        Teacher teacher = new Teacher();
        StudentGroup group = new StudentGroup();
        StudentCard card = new StudentCard();

        Student savedStudent = new Student();
        savedStudent.setName("Ali");
        savedStudent.setSurname("Valiyev");

        when(teacherRepository.findById(1L))
                .thenReturn(Optional.of(teacher));

        when(groupRepository.findById(1L))
                .thenReturn(Optional.of(group));

        when(studentRepository.save(any(Student.class)))
                .thenReturn(savedStudent);

        when(cardRepository.findById(1L))
                .thenReturn(Optional.of(card));

        Student result = studentService.create(dto);

        assertEquals("Ali", result.getName());

        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void findStudentById() {

        Student student = new Student();
        student.setName("Ali");

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        Student result = studentService.getById(1L);

        assertEquals("Ali", result.getName());
    }

    @Test
    void studentNotFound() {

        when(studentRepository.findById(100L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> studentService.getById(100L)
        );
    }
}