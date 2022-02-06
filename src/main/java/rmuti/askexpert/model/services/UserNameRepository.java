package rmuti.askexpert.model.services;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.UserName;

import java.util.Optional;

public interface UserNameRepository extends JpaRepository<UserName, Integer> {
    //List<UserName> findAll();
    Optional<UserName> findByEmail(String email);
    boolean existsByEmail(String email);
}
