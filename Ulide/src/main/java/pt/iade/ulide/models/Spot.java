package pt.iade.ulide.models;

import javax.persistence.*;

@Entity
@Table(name = "spots")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sp_id", nullable = false)
    private Integer id;

    @Column(name = "sp_name", nullable = false, length = 60)
    private String spName;

    @Column(name = "sp_lat", nullable = false)
    private Double spLat;

    @Column(name = "sp_long", nullable = false)
    private Double spLong;

    @Column(name = "sp_price")
    private Boolean spPrice;

    @Column(name = "sp_bio", length = 400)
    private String spBio;

    public String getSpBio() {
        return spBio;
    }

    public void setSpBio(String spBio) {
        this.spBio = spBio;
    }

    public Boolean getSpPrice() {
        return spPrice;
    }

    public void setSpPrice(Boolean spPrice) {
        this.spPrice = spPrice;
    }

    public Double getSpLong() {
        return spLong;
    }

    public void setSpLong(Double spLong) {
        this.spLong = spLong;
    }

    public Double getSpLat() {
        return spLat;
    }

    public void setSpLat(Double spLat) {
        this.spLat = spLat;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
