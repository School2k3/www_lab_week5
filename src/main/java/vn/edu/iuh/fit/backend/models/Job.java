package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "job")
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id", nullable = false)
    private Long id;

    @Column(name = "job_name", nullable = false)
    private String jobName;

    @Column(name = "job_desc", nullable = false, length = 2000)
    private String jobDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company")
    private Company company;

    public Job(String jobName, String jobDesc, Company company) {
        this.jobName = jobName;
        this.jobDesc = jobDesc;
        this.company = company;
    }

    // Thay vì trực tiếp liên kết với Skill, Job sẽ có liên kết với JobSkill
    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    private Set<JobSkill> jobSkills; // Quan hệ với bảng JobSkill (thông qua bảng trung gian)

}