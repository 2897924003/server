package ss.user.domain;

import jakarta.persistence.*;
import lombok.Data;
import ss.user.domain.service.UserBusinessImpl;


/**
 * Spring Data JPA
 */
@Data
@Entity
@Table(name = "user") // 指定表名
public  class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 指定自增策略
    private Long id; // 主键字段

    @Column(name = "username", nullable = false, unique = true)
    private String username; // 指定列名和约束

    @Column(name = "password", nullable = false)
    private String password; // 指定列名和约束

    @Column(name="email")
    private String email;

    @Column(name = "authorities")
    private int authorities; // 指定列名

    @Column(name = "account_non_expired")
    private boolean accountNonExpired; // 指定列名


    @Column(name = "account_non_locked")
    private boolean accountNonLocked; // 指定列名


    @Column(name = "enabled")
    private boolean enabled; // 指定列名

    public User() {
    }

    public User(Long id, String username, String password, int authorities,
                boolean accountNonExpired, boolean accountNonLocked, boolean enabled) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.enabled = enabled;
    }




}
