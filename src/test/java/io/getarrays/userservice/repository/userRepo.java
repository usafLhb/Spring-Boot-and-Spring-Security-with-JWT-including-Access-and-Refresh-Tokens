package io.getarrays.userservice.repository;

import io.getarrays.userservice.domaine.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
