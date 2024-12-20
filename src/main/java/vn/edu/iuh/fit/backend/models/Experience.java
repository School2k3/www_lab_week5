package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "experience")
@NoArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exp_id", nullable = false)
    private Long id;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "role", length = 100)
    private String role;

    @Column(name = "company", length = 120)
    private String company;

    @Column(name = "work_desc", length = 400)
    private String workDesc;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "can_id", nullable = false)
    private Candidate candidate;

    public Experience(LocalDate fromDate, LocalDate toDate, String role, String company, String workDesc, Candidate candidate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.role = role;
        this.company = company;
        this.workDesc = workDesc;
        this.candidate = candidate;
    }
}