package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.backend.enums.SkillType;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "skill")
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id", nullable = false)
    private Long id;

    @Column(name = "skill_description", length = 300)
    private String skillDescription;

    @Column(name = "skill_name", length = 150)
    private String skillName;

    @Column(name = "type")
    private SkillType type;

    @OneToMany(mappedBy = "skill")
    private List<JobSkill> jobSkills;
}