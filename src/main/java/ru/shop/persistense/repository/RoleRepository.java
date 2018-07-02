package ru.shop.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.persistense.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
