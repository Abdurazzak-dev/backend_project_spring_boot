package kz.university.data.repo;

import kz.university.data.models.StudentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCardRepository extends JpaRepository<StudentCard, Long> {

}
