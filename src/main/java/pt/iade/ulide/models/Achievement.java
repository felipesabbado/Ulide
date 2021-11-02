package pt.iade.ulide.models;

import javax.persistence.*;

@Entity
@Table(name = "achievements")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ac_id", nullable = false)
    private Integer id;

    @Column(name = "ac_name", length = 30)
    private String acName;

    @Column(name = "ac_limit")
    private Integer acLimit;

    public Integer getAcLimit() {
        return acLimit;
    }

    public void setAcLimit(Integer acLimit) {
        this.acLimit = acLimit;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
