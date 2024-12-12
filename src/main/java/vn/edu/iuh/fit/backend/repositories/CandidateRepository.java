package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.models.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
//  Page<Candidate> findAllByStatus(int status, Pageable pageable);
}