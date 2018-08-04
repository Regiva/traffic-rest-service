package ru.dz.traffic_rest_service.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "visit")
public class Visit {

    public Visit(Long userId, Long pageId, Date date, String session) {
        this.userId = userId;
        this.pageId = pageId;
        this.date = date;
        this.session = session;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "page_id")
    private Long pageId;

    @Column(name = "date")
    private Date date;

    @Column(name = "session")
    private String session;
}
