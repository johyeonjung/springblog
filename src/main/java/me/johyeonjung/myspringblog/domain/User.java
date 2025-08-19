package me.johyeonjung.myspringblog.domain;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name="users",
        indexes = {
                @Index(name="idx_user_username", columnList="username", unique=true)

        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //로그인 아이디(중복불가)
    @Column(nullable = false, length = 50, unique = true)
    private String username;

    //표시이름
    @Column(nullable = false, length = 100)
    private String displayName;

    //해시된 비밀번호(예시)
    @Column(nullable = false)
    private String password;

    public void changeDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
