package com.example.hfb.repository;

import com.example.hfb.entity.Role;
import com.example.hfb.entity.User;
import com.example.hfb.entity.UserRole;
import com.example.hfb.entity.UserRoleKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleKey> {
    List<UserRole> findByRole(Role role);
    @Modifying
    @Transactional
    @Query(value = "delete from user_role;", nativeQuery = true)
    void deleteAll();

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE user_role AUTO_INCREMENT = 1;", nativeQuery = true)
    void resetId();
}
