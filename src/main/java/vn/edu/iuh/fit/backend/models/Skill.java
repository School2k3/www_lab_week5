package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.backend.enums.SkillType;

@Getter
@Setter
@Entity
@Table(name = "skill")
@NoArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id", nullable = false)
    private Long id;

    @Column(name = "skill_name", length = 150)
    private String skillName;

    @Column(name = "skill_description", length = 300)
    private String skillDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private SkillType type;

    public Skill(String skillName, String skillDescription, SkillType type) {
        this.skillName = skillName;
        this.skillDescription = skillDescription;
        this.type = type;
    }
}