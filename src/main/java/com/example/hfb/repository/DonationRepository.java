package com.example.hfb.repository;

import com.example.hfb.entity.Donation;
import com.example.hfb.model.dto.StatisticDonation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {
    @Query("select d from Donation as d " +
            "where " +
            "(d.name like %:name%) " +
            "AND (d.phone like %:phone%) " +
            "AND (d.amount = :amount OR :amount = -1) " +
            "AND ((d.createdAt between :startCreated and :endCreated) or (:startCreated = -1L AND  :endCreated = -1L )) " +
            "AND (d.status = :status OR :status = -1)")
    Page<Donation> findAll(
            @Param(value="name") String name,
            @Param(value="phone") String phone,
            @Param(value="amount") Double amount,
            @Param(value="startCreated") Long startCreated,
            @Param(value="endCreated") Long endCreated,
            @Param(value="status") int status,
            Pageable pageable);
//    @Query("select new com.example.hfb.model.dto.StatisticDonation (d.createdAt, sum(d.amount) )" +
//            " from Donation as d" +
//            " group by DATE_FORMAT(FROM_UNIXTIME(d.createdAt/1000), '%Y-%m-%d')" +
//            " ORDER BY d.createdAt")
//    List<StatisticDonation> statisticDonation (@Param(value="startDate") Long startDate, @Param(value="endDate") Long endDate);
//    @Query("select new com.example.hfb.model.dto.StatisticDonation (TO_CHAR(TO_TIMESTAMP(d.createdAt / 1000), 'DD/MM/YYYY'), sum(amount))" +
//            " FROM Donation AS d" +
//            " WHERE d.createdAt BETWEEN :startDate and :endDate" +
//            " group by TO_CHAR(TO_TIMESTAMP(d.createdAt / 1000), 'DD/MM/YYYY')" +
//            " ORDER BY d.createdAt")
//    List<StatisticDonation> statisticDonation (@Param(value="startDate") Long startDate, @Param(value="endDate") Long endDate);

        @Query(value =
            "SELECT TO_CHAR(TO_TIMESTAMP(d.created_at / 1000), 'YYYY-MM-DD'), sum(amount)" +
            " FROM donation AS d" +
            " WHERE d.created_at BETWEEN :startDate and :endDate" +
            " GROUP BY TO_CHAR(TO_TIMESTAMP(d.created_at / 1000), 'YYYY-MM-DD')"
            , nativeQuery = true)
        List<Object[]> statisticDonation (@Param(value="startDate") Long startDate, @Param(value="endDate") Long endDate);
}
