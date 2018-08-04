package ru.dz.traffic_rest_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.dz.traffic_rest_service.model.Visit;

import java.sql.Date;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    Long countAllByDate(Date date);

    @Query(value = "select count(distinct session) from visit v where (v.date = ?1);", nativeQuery = true)
    Long countAllByDateAndDistinctSession(Date date);

    Long countAllByDateBetween(Date from, Date to);

    @Query(value = "select count(distinct session) from visit v where (v.date between ?1 and ?2);", nativeQuery = true)
    Long countAllByDateBetweenAndDistinctSession(Date from, Date to);

    @Query(value = "select count(distinct user_id) from visit v where (v.date between ?1 and ?2) " +
            "group by user_id having count(page_id) >= 10;", nativeQuery = true)
    Long countAllRegularUsers(Date from, Date to);
}
