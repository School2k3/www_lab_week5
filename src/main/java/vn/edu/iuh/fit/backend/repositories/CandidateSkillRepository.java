package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.ids.CandidateSkillId;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.CandidateSkill;

import java.util.List;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> {
  @Query("SELECT cs.skill.id FROM CandidateSkill cs WHERE cs.can.id = :candidateId")
  List<Long> findSkillIdsByCandidateId(@Param("candidateId") Long candidateId);

  @Query("SELECT DISTINCT cs.can FROM CandidateSkill cs WHERE cs.skill.id IN :skillIds")
  List<Candidate> findCandidatesBySkills(@Param("skillIds") List<Long> skillIds);

  @Query("SELECT DISTINCT cs.can FROM CandidateSkill cs WHERE cs.skill.id IN :skillIds AND cs.skillLevel >= :requiredLevel")
  List<Candidate> findCandidatesBySkillsAndLevel(@Param("skillIds") List<Long> skillIds, @Param("requiredLevel") SkillLevel requiredLevel);
}