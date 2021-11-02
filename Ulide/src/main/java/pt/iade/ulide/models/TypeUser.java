package pt.iade.ulide.models;

import javax.persistence.*;

@Entity
@Table(name = "type_users")
public class TypeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tu_id", nullable = false)
    private Integer id;

    @Column(name = "tu_name", length = 30)
    private String tuName;

    public String getTuName() {
        return tuName;
    }

    public void setTuName(String tuName) {
        this.tuName = tuName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
