package ss.user.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import ss.user.domain.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {


}
