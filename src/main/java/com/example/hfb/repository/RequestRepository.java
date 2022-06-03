package com.example.hfb.repository;


import com.example.hfb.entity.Food;
import com.example.hfb.entity.Request;
import com.example.hfb.entity.UserFoodKey;
import com.example.hfb.model.dto.RequestDetail;
import com.example.hfb.model.dto.StatisticFood;
import com.example.hfb.model.dto.StatisticRequest;
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
public interface RequestRepository extends JpaRepository<Request, UserFoodKey> {
    List<Request> findByFood(Food food);
    @Query("select r from Request as r where r.id.userId = :userId AND r.id.foodId = :foodId")
    RequestRepository getRequest(@Param(value="userId")int userId, @Param(value="foodId") int foodId);

    @Query("select r from Request as r " +
            "where " +
            "(r.id.userId = :userId OR :userId = -1) " +
            "AND (r.id.foodId = :foodId OR :foodId = -1) " +
            "AND (r.status = :status OR :status = -1)")
    Page<Object> findAll(
            @Param(value="userId") int userId,
            @Param(value="foodId") int foodId,
            @Param(value="status") int status,
            Pageable pageable);


    @Query("SELECT new com.example.hfb.model.dto.RequestDetail(" +
            "r.id.userId," +
            " r.user.name," +
            " r.user.phone," +
            " r.user.address," +
            " r.supplierId," +
            " r.supplierName," +
            " user.phone," +
            " user.address," +
            " f.id," +
            " f.name, r.createdAt, r.updatedAt, r.createdBy, r.updatedBy, r.message, r.status, r.food.expirationDate) " +
            "FROM Request r INNER JOIN User u ON r.id.userId = u.id INNER JOIN Food f ON r.id.foodId = f.id " +
            "INNER JOIN User user ON r.food.createdBy = user.id " +
            "where " +
            "(r.id.userId = :userId OR :userId = -1) " +
            "AND (r.id.foodId = :foodId OR :foodId = -1) " +
            "AND ((r.createdAt between :startCreated and :endCreated) or (:startCreated = -1L AND  :endCreated = -1L )) " +
            "AND ((r.updatedAt between :startUpdated and :endUpdated) or (:startUpdated = -1L AND  :endUpdated = -1L )) " +
            "AND (r.status = :status OR :status = -1)")
    Page<RequestDetail> findAllInfo(
            @Param(value="userId") int userId,
            @Param(value="foodId") int foodId,
            @Param(value="startCreated") Long startCreated,
            @Param(value="endCreated") Long endCreated,
            @Param(value="startUpdated") Long startUpdated,
            @Param(value="endUpdated") Long endUpdated,
            @Param(value="status") int status,
            Pageable pageable);
//    @Query("select" +
//            " new com.example.hfb.model.dto.StatisticRequest (r.createdAt, count(r.status) )" +
//            " from Request as r" +
//            " where" +
//            " r.createdAt between :startDate and :endDate" +
//            " AND r.status = 3" +
//            " group by DATE_FORMAT(FROM_UNIXTIME(r.createdAt/1000), '%Y-%m-%d')" +
//            " ORDER BY r.createdAt")
//    List<StatisticRequest> statisticRequest (@Param(value="startDate") Long startDate, @Param(value="endDate") Long endDate);
//    @Query(value =
//            "SELECT DATE_FORMAT(FROM_UNIXTIME(created_at/1000), '%Y-%m-%d') as created_date, count(food_id)" +
//            " from Request" +
//            " where created_at between :startDate and :endDate" +
//            " AND status = 3" +
//            " group by DATE_FORMAT(FROM_UNIXTIME(created_at/1000), '%Y-%m-%d')" +
//            " ORDER BY created_at"
//            , nativeQuery = true)
//    List<Object[]> statisticRequest (@Param(value="startDate") Long startDate, @Param(value="endDate") Long endDate);

    @Query(value =
        "SELECT TO_CHAR(TO_TIMESTAMP(r.created_at / 1000), 'YYYY-MM-DD'), count(r.created_at)" +
                " FROM request AS r" +
                " WHERE r.created_at BETWEEN :startDate and :endDate AND status = 3" +
                " GROUP BY TO_CHAR(TO_TIMESTAMP(r.created_at / 1000), 'YYYY-MM-DD')"
        , nativeQuery = true)
    List<Object[]> statisticRequest (@Param(value="startDate") Long startDate, @Param(value="endDate") Long endDate);

    @Modifying
    @Transactional
    @Query(value = "drop table if exists request", nativeQuery = true)
    void dropTable();
}
