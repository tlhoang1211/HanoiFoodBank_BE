package com.example.hfb.repository;

import com.example.hfb.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query("select s from account as s where (s.username like %:keyword% OR s.username is null)")
    Page<User> search(@Param(value="keyword") String keyword, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "delete from account;", nativeQuery = true)
    void deleteAll();

    @Modifying
    @Transactional
    @Query(value = "ALTER sequence account_id_seq restart with 1", nativeQuery = true)
    void resetId();
}
