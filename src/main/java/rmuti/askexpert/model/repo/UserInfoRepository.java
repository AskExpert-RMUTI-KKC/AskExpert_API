package rmuti.askexpert.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rmuti.askexpert.model.table.UserInfoData;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfoData,String> {
    boolean existsByUserInfoId(String id);

    List<UserInfoData> findByUserNameContainingOrFirstNameContainingOrLastNameContaining(
            String userName,
            String FN,
            String LN
    );

    List<UserInfoData> findByUserNameContainingOrFirstNameContainingOrLastNameContainingAndExpertGroupId(
            String userName,
            String FN,
            String LN,String groupId
    );


    //FindTop10Like

    List<UserInfoData> findTop10ByOrderByLikeCountDesc();


    Optional<UserInfoData> findByUserName(String userName);

}
