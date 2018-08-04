package ru.dz.traffic_rest_service.services;

import ru.dz.traffic_rest_service.model.Response;
import ru.dz.traffic_rest_service.model.Visit;

import java.sql.Date;

public interface VisitService {

    void createVisit(Visit visit);

    Response countTodayVisits();

    Response countTodayUniqueVisits();

    Response countPeriodVisits(Date from, Date to);

    Response countPeriodUniqueVisits(Date from, Date to);

    Response countPeriodRegularUsers(Date from, Date to);
}
