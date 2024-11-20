package ss.user.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ss.user.atcl.UserConverter;
import ss.user.domain.User;
import ss.user.domain.ro.UserRO;
import ss.user.infrastructure.persistance.UserRepository;
import ss.user.service.UserQueryService;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;


    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 根据用户名查找用户
     */
    public UserRO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }

        return UserConverter.userToUserRO(user);
    }

    /**
     * 根据分页信息查询用户
     * @param pageable 分页信息
     */
    public Page<UserRO> findAllUsers(Pageable pageable) {
        Page<User> resultWithPageInfo = userRepository.findAll(pageable);
        if (resultWithPageInfo.isEmpty()) {
            return null;
        }
        return resultWithPageInfo.map(UserConverter::userToUserRO);
    }



}
