package pt.iade.ulide.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_achievements")
public class UserAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ua_id", nullable = false)
    private Integer id;

    @Column(name = "ua_date")
    private LocalDate uaDate;

    @Column(name = "ua_us_id", nullable = false)
    private Integer uaUsId;

    @Column(name = "ua_ac_id", nullable = false)
    private Integer uaAcId;

    public Integer getUaAcId() {
        return uaAcId;
    }

    public void setUaAcId(Integer uaAcId) {
        this.uaAcId = uaAcId;
    }

    public Integer getUaUsId() {
        return uaUsId;
    }

    public void setUaUsId(Integer uaUsId) {
        this.uaUsId = uaUsId;
    }

    public LocalDate getUaDate() {
        return uaDate;
    }

    public void setUaDate(LocalDate uaDate) {
        this.uaDate = uaDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
