package ru.dz.traffic_rest_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dz.traffic_rest_service.model.Response;
import ru.dz.traffic_rest_service.model.Visit;
import ru.dz.traffic_rest_service.services.VisitService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Past;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private VisitService service;

    @GetMapping("/visit")
    public List<Response> addVisitAndGetDailyTraffic(@RequestParam(name = "id") Long id,
                                                     @RequestParam(name = "page_id") Long page_id,
                                                     HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(365 * 24 * 60 * 60); // сессия живёт 365 дней
        service.createVisit(new Visit(id, page_id, Date.valueOf(LocalDate.now()), session.getId()));

        List<Response> responses = new ArrayList<>();
        responses.add(service.countTodayVisits());
        responses.add(service.countTodayUniqueVisits());

        return responses;
    }

    @GetMapping("/traffic")
    public List<Response> periodTraffic(@RequestParam(name = "from") @Past Date from,
                                        @RequestParam(name = "to") @Past Date to) {
        List<Response> responses = new ArrayList<>();
        responses.add(service.countPeriodVisits(from, to));
        responses.add(service.countPeriodUniqueVisits(from, to));
        responses.add(service.countPeriodRegularUsers(from, to));

        return responses;
    }
}
