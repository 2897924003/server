package ss.user.service;

import ss.user.domain.UserSummary;

public interface UserSummaryQueryService {
    UserSummary findUserSummaryById(Long id);
}
