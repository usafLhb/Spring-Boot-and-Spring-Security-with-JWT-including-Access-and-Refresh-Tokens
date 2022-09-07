package io.getarrays.userservice.repository;

import io.getarrays.userservice.domaine.Role;
import io.getarrays.userservice.domaine.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface roleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
