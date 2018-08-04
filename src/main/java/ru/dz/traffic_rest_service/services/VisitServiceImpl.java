package ru.dz.traffic_rest_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.traffic_rest_service.model.Response;
import ru.dz.traffic_rest_service.model.Visit;
import ru.dz.traffic_rest_service.repositories.VisitRepository;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitRepository repository;

    @Override
    public void createVisit(Visit visit) {
        repository.save(visit);
    }

    @Override
    public Response countTodayVisits() {
        return new Response("Today's visits", repository.countAllByDate(Date.valueOf(LocalDate.now())));
    }

    @Override
    public Response countTodayUniqueVisits() {
        return new Response("Today's unique visits",
                repository.countAllByDateAndDistinctSession(Date.valueOf(LocalDate.now())));
    }

    @Override
    public Response countPeriodVisits(Date from, Date to) {
        return new Response("Count of visits in given period", repository.countAllByDateBetween(from, to));
    }

    @Override
    public Response countPeriodUniqueVisits(Date from, Date to) {
        return new Response("Count of unique visits in given period",
                repository.countAllByDateBetweenAndDistinctSession(from, to));
    }

    @Override
    public Response countPeriodRegularUsers(Date from, Date to) {
        Response response = new Response("Count of regular users", repository.countAllRegularUsers(from, to));
        if (response.getCount() == null) {
            response.setCount(0L);
        }
        return response;
    }
}
