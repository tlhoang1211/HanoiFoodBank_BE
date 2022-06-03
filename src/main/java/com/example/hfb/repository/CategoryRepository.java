package com.example.hfb.repository;

import com.example.hfb.entity.Category;
import com.example.hfb.entity.User;
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
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByName(String name);

    @Query("select c from Category as c " +
            "where " +
            "(c.name like %:name%) " +
            "AND (c.status = :status OR :status = -1)")
    Page<Category> findAll(@Param(value="name") String name, @Param(value="status") int status, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "drop table category", nativeQuery = true)
    void deleteAll();

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE category AUTO_INCREMENT = 1;", nativeQuery = true)
    void resetId();
}
