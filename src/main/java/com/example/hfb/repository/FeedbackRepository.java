package com.example.hfb.repository;

import com.example.hfb.entity.Feedback;
import com.example.hfb.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    @Query("select f from Feedback as f " +
            "where " +
            "(f.type = :type OR :type = -1) " +
            "AND ((f.rate between :startRate and :endRate) OR (:startRate = -1 AND :endRate = -1))" +
            "AND (f.status = :status OR :status = -1) " +
            "AND (f.createdBy = :createdBy OR :createdBy = -1) " +
            "AND (f.userId = :userId OR :userId = -1)" +
            "AND (f.foodId = :foodId OR :foodId = -1)")
    Page<Feedback> findAll(
            @Param(value="type") Integer type,
            @Param(value="status") Integer status,
            @Param(value="createdBy") Integer createdBy,
            @Param(value="userId") Integer userId,
            @Param(value="foodId") Integer foodId,
            @Param(value="startRate") Integer startRate,
            @Param(value="endRate") Integer endRate,
            Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "delete from feedback; commit;", nativeQuery = true)
    void deleteAll();

    @Modifying
    @Transactional
    @Query(value = "ALTER sequence feedback_id_seq restart with 1", nativeQuery = true)
    void resetId();
}

