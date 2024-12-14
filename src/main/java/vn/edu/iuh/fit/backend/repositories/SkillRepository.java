package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.backend.models.Skill;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
  @Query("SELECT s FROM Skill s WHERE s.id NOT IN " +
          "(SELECT cs.skill.id FROM CandidateSkill cs WHERE cs.can.id = :candidateId)")
  List<Skill> findMissingSkillsForCandidate(@Param("candidateId") Long candidateId);
}