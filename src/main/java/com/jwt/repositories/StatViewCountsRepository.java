package com.jwt.repositories;

import com.jwt.models.StatViewCounts;
import com.jwt.models.StatsViewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatViewCountsRepository extends JpaRepository<StatViewCounts, StatsViewId> {

//    @Query( value = "SELECT *, count(*) FROM teamstats.stats_view " +
//            "where stat_name = :statName " +
//            "GROUP BY stat_name, season " +
//            "ORDER BY season ",
//            nativeQuery = true)
    List<StatViewCounts> findDistinctBySeason(String statName);
}
