package pt.iade.ulide.models;

import javax.persistence.*;

@Entity
@Table(name = "spot_evaluations")
public class SpotEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "se_id", nullable = false)
    private Integer id;

    @Column(name = "se_rate")
    private Integer seRate;

    @Column(name = "se_comment", length = 400)
    private String seComment;

    @Column(name = "se_us_id", nullable = false)
    private Integer seUsId;

    @Column(name = "se_sp_id", nullable = false)
    private Integer seSpId;

    public Integer getSeSpId() {
        return seSpId;
    }

    public void setSeSpId(Integer seSpId) {
        this.seSpId = seSpId;
    }

    public Integer getSeUsId() {
        return seUsId;
    }

    public void setSeUsId(Integer seUsId) {
        this.seUsId = seUsId;
    }

    public String getSeComment() {
        return seComment;
    }

    public void setSeComment(String seComment) {
        this.seComment = seComment;
    }

    public Integer getSeRate() {
        return seRate;
    }

    public void setSeRate(Integer seRate) {
        this.seRate = seRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
