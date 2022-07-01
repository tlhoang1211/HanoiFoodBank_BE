package com.example.hfb.repository;

import com.example.hfb.entity.Food;
import com.example.hfb.entity.FoodPro;
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
public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> findByName(String name);

    @Query("select f from Food as f " +
            "where " +
            "(f.name like %:name%) " +
            "AND (f.categoryId = :categoryId OR :categoryId = -1) " +
            "AND (f.createdBy = :createdBy OR :createdBy = -1) " +
            "AND (f.updatedBy = :updatedBy OR :updatedBy = -1) " +
            "AND ((f.expirationDate between :today and :expirationDate) or :expirationDate = -1L) " +
            "AND ((f.createdAt between :startCreated and :endCreated) or (:startCreated = -1L AND  :endCreated = -1L )) " +
            "AND ((f.updatedAt between :startUpdated and :endUpdated) or (:startUpdated = -1L AND  :endUpdated = -1L )) " +
            "AND (f.status = :status OR :status = -1)")
    Page<Food> findAll(
            @Param(value="name") String name,
            @Param(value="categoryId") Integer categoryId,
            @Param(value="createdBy") Integer createdBy,
            @Param(value="updatedBy") Integer updatedBy,
            @Param(value="today") Long today,
            @Param(value="expirationDate") Long expirationDate,
            @Param(value="startCreated") Long startCreated,
            @Param(value="endCreated") Long endCreated,
            @Param(value="startUpdated") Long startUpdated,
            @Param(value="endUpdated") Long endUpdated,
            @Param(value="status") int status,
            Pageable pageable);
//    @Query("select" +
//            " new com.example.hfb.model.dto.StatisticFood (f.createdAt, count(f.id) )" +
//            " from Food as f" +
//            " where f.createdAt between :startDate and :endDate" +
//            " group by DATE_FORMAT(FROM_UNIXTIME(f.createdAt/1000), '%Y-%m-%d')" +
//            " ORDER BY f.createdAt")
//    List<StatisticFood> statisticFood (@Param(value="startDate") Long startDate, @Param(value="endDate") Long endDate);

//    @Query(value =
//            "SELECT DATE_FORMAT(FROM_UNIXTIME(created_at/1000), '%Y-%m-%d') as created_date, count(id)" +
//            " from Food" +
//            " where created_at between :startDate and :endDate" +
//            " group by created_date"
//            , nativeQuery = true)
//    List<Object[]> statisticFood (@Param(value="startDate") Long startDate, @Param(value="endDate") Long endDate);

    @Query(value =
        "SELECT TO_CHAR(TO_TIMESTAMP(f.created_at / 1000), 'YYYY-MM-DD'), count(id)" +
                " FROM food AS f" +
                " WHERE f.created_at BETWEEN :startDate and :endDate" +
                " GROUP BY TO_CHAR(TO_TIMESTAMP(f.created_at / 1000), 'YYYY-MM-DD')"
        , nativeQuery = true)
    List<Object[]> statisticFood (@Param(value="startDate") Long startDate, @Param(value="endDate") Long endDate);

    @Modifying
    @Transactional
    @Query(value = "delete from food; commit;", nativeQuery = true)
    void deleteAll();

    @Modifying
    @Transactional
    @Query(value = "ALTER sequence food_id_seq restart with 1", nativeQuery = true)
    void resetId();

    @Query(value = "with a as (select id,\n" +
            "                  (\n" +
            "                          6371 *\n" +
            "                          acos(cos(radians(:lat)) *\n" +
            "                               cos(radians(position_latitude)) *\n" +
            "                               cos(radians(position_longitude) -\n" +
            "                                   radians(:lng)) +\n" +
            "                               sin(radians(:lat)) *\n" +
            "                               sin(radians(position_latitude)))\n" +
            "                      ) as distance\n" +
            "           from account),\n" +
            "     b as (select *\n" +
            "           from a\n" +
            "           where distance < :distance\n" +
            "           order by distance)\n" +
            "select * from food where created_by in (select id from b) and status = 2\n", nativeQuery = true)
    List<FoodPro> getNearestLocation (@Param(value="lng") double lng,
                                      @Param(value="lat") double lat,
                                      @Param(value="distance") double distance,
                                      Pageable pageable);
}
