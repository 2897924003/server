package ss.user.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_summary") // 指定表名
public class UserSummary{
    @Id
    private Long id;
    @Column(name = "nickname", nullable = true, unique = true)
    private String nickname;
    @Column(name = "avatar", nullable = true, unique = true)
    private String avatar;


}
