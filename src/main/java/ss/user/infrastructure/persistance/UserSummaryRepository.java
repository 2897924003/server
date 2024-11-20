package ss.user.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import ss.user.domain.UserSummary;

public interface UserSummaryRepository extends JpaRepository<UserSummary, Long> {

}
