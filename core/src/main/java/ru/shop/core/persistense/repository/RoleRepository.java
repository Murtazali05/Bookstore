package ru.shop.core.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.core.persistense.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findOneByName(String name);

}
