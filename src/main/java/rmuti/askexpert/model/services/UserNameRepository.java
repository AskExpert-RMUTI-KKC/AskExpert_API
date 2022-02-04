package rmuti.askexpert.model.services;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.UserName;

public interface UserNameRepository  extends JpaRepository<UserName, Integer> {

}
