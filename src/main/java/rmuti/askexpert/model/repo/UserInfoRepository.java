package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.UserInfoData;

public interface UserInfoRepository extends JpaRepository<UserInfoData,String> {
}
