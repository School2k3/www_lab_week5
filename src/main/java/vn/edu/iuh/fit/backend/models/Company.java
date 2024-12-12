package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "company")
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comp_id", nullable = false)
    private Long id;

    @Column(name = "comp_name", nullable = false)
    private String compName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @Column(name = "web_url")
    private String webUrl;

    @Column(name = "about", length = 2000)
    private String about;

    public Company(String compName, String email, String phone, Address address, String webUrl, String about) {
        this.compName = compName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.webUrl = webUrl;
        this.about = about;
    }
}