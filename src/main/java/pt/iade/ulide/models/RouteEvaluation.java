package pt.iade.ulide.models;

import javax.persistence.*;

@Entity
@Table(name = "route_evaluations")
public class RouteEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "re_id", nullable = false)
    private Integer id;

    @Column(name = "re_rate")
    private Integer reRate;

    @Column(name = "re_comment", length = 400)
    private String reComment;

    @Column(name = "re_us_id", nullable = false)
    private Integer reUsId;

    @Column(name = "re_rt_id", nullable = false)
    private Integer reRtId;

    public Integer getReRtId() {
        return reRtId;
    }

    public void setReRtId(Integer reRtId) {
        this.reRtId = reRtId;
    }

    public Integer getReUsId() {
        return reUsId;
    }

    public void setReUsId(Integer reUsId) {
        this.reUsId = reUsId;
    }

    public String getReComment() {
        return reComment;
    }

    public void setReComment(String reComment) {
        this.reComment = reComment;
    }

    public Integer getReRate() {
        return reRate;
    }

    public void setReRate(Integer reRate) {
        this.reRate = reRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
