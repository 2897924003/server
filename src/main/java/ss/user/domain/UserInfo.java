package ss.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class UserInfo {
    @Id
    private Long id;  // 这里的 ID 是与 User ID 对应的
    private String email;
}
