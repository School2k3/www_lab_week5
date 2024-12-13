package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.backend.models.Job;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
  @Query("SELECT DISTINCT j FROM Job j JOIN j.jobSkills js JOIN js.skill s WHERE s.id IN :skillIds")
  List<Job> findJobsBySkills(@Param("skillIds") List<Long> skillIds);
}