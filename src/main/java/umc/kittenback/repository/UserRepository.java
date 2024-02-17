package umc.kittenback.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.kittenback.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 이메일을 통해 해당 이메일 주소를 가진 User를 찾는다.
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);

    boolean existsByNickname(String nickname);
}
