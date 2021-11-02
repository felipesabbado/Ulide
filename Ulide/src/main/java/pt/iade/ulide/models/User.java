package pt.iade.ulide.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id", nullable = false)
    private Integer id;

    @Column(name = "us_name", nullable = false, length = 60)
    private String usName;

    @Column(name = "us_bdate", nullable = false)
    private LocalDate usBdate;

    @Column(name = "us_gender", nullable = false, length = 1)
    private String usGender;

    @Column(name = "us_email", length = 30)
    private String usEmail;

    @Column(name = "us_country", length = 30)
    private String usCountry;

    @Column(name = "us_bio", length = 400)
    private String usBio;

    @Column(name = "us_dist")
    private Integer usDist;

    public Integer getUsDist() {
        return usDist;
    }

    public void setUsDist(Integer usDist) {
        this.usDist = usDist;
    }

    public String getUsBio() {
        return usBio;
    }

    public void setUsBio(String usBio) {
        this.usBio = usBio;
    }

    public String getUsCountry() {
        return usCountry;
    }

    public void setUsCountry(String usCountry) {
        this.usCountry = usCountry;
    }

    public String getUsEmail() {
        return usEmail;
    }

    public void setUsEmail(String usEmail) {
        this.usEmail = usEmail;
    }

    public String getUsGender() {
        return usGender;
    }

    public void setUsGender(String usGender) {
        this.usGender = usGender;
    }

    public LocalDate getUsBdate() {
        return usBdate;
    }

    public void setUsBdate(LocalDate usBdate) {
        this.usBdate = usBdate;
    }

    public String getUsName() {
        return usName;
    }

    public void setUsName(String usName) {
        this.usName = usName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
