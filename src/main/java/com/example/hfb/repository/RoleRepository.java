package com.example.hfb.repository;

import com.example.hfb.entity.Category;
import com.example.hfb.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);

    @Query("select r from Role as r " +
            "where " +
            "(r.name like %:name%) " +
            "AND (r.status = :status OR :status = -1)")
    Page<Role> findAll(@Param(value="name") String name, @Param(value="status") int status, Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "delete from role; commit; ", nativeQuery = true)
    void deleteAll();

    @Modifying
    @Transactional
    @Query(value = "ALTER sequence role_id_seq restart with 1", nativeQuery = true)
    void resetId();
}
