package ss.user.infrastructure.persistance;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ss.user.domain.User;

/**
 * 仓储服务
 * 仓储（Repository）的主要职责是将领域对象与数据库交互的细节进行封装，
 * 为应用层提供一个访问领域对象的接口。
 * 根据 DDD 的原则，
 * 仓储应该专注于数据的存储、查询、删除等操作，而不应包含业务规则或逻辑。
 * 业务逻辑应在领域模型（实体、值对象）或领域服务中体现。
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return User
     */
    User findByUsername(String username);

    User findByEmail(String recipient);



}
