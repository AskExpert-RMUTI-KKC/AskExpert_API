package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.UserName;

import java.util.Optional;

public interface UserNameRepository extends JpaRepository<UserName, String> {
    //List<UserName> findAll();
    Optional<UserName> findByEmail(String email);
    Optional<UserName> findByUserId(String userId);
    boolean existsByEmail(String email);
}
