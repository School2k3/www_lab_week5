package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.backend.ids.CandidateSkillId;
import vn.edu.iuh.fit.backend.models.CandidateSkill;

import java.util.List;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> {
  @Query("SELECT cs.skill.id FROM CandidateSkill cs WHERE cs.can.id = :candidateId")
  List<Long> findSkillIdsByCandidateId(@Param("candidateId") Long candidateId);
}