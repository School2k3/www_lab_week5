package vn.edu.iuh.fit.backend.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.ids.JobSkillId;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    public Job findById(Long id) {
        return jobRepository.findById(id).orElseThrow();
    }

    /**
     * Tạo công việc và liên kết các kỹ năng với mức độ
     */
    public Job createJobWithSkills(Job job, List<Long> skillIds, SkillLevel skillLevel) {
        // Lưu công việc
        Job savedJob = jobRepository.save(job);

        // Lấy danh sách kỹ năng từ skillIds
        List<Skill> skills = skillRepository.findAllById(skillIds);

        // Liên kết các kỹ năng với công việc
        skills.forEach(skill -> {
            JobSkill jobSkill = new JobSkill(
                    new JobSkillId(savedJob.getId(), skill.getId()),
                    savedJob,
                    skill,
                    "This skill is required for the job",
                    skillLevel
            );
            jobSkillRepository.save(jobSkill);
        });

        return savedJob;
    }

    /**
     * Lấy danh sách tất cả các công việc
     */
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    /**
     * Gợi ý công việc dựa trên kỹ năng của ứng viên.
     */
    public List<Job> suggestJobsForCandidate(Long candidateId) {
        // Lấy danh sách kỹ năng của ứng viên
        List<Long> skillIds = candidateSkillRepository.findSkillIdsByCandidateId(candidateId);

        // Tìm các công việc phù hợp với danh sách kỹ năng
        return jobRepository.findJobsBySkills(skillIds);
    }

    /**
     * Lấy danh sách công việc của một công ty
     */
    public List<Job> getJobsByCompanyId(Long companyId) {
        return jobRepository.findByCompanyId(companyId);
    }

    /**
     * Xóa công việc theo ID
     */
    // Đảm bảo phương thức này được gọi trong một giao dịch
    @Transactional
    public void deleteJobById(Long jobId) {
        // Xóa tất cả các bản ghi job_skill liên quan đến công việc này
        jobSkillRepository.deleteByJobId(jobId);

        // Sau đó, xóa công việc
        jobRepository.deleteById(jobId);
    }

    @Transactional
    public Job updateJobWithSkills(Job job, List<Long> skillIds, Map<Long, String> skillLevels) {
        Long jobId = job.getId();
        if (jobId == null) {
            throw new IllegalArgumentException("Job ID must not be null");
        }

        Job existingJob = jobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Job not found!"));

        // Update job details
        existingJob.setJobName(job.getJobName());
        existingJob.setJobDesc(job.getJobDesc());

        // Delete existing JobSkills
        jobSkillRepository.deleteByJobId(existingJob.getId());

        // Add new JobSkills
        for (Long skillId : skillIds) {
            Skill skill = skillRepository.findById(skillId)
                    .orElseThrow(() -> new IllegalArgumentException("Skill not found!"));

            // Get skillLevel from the map and convert to SkillLevel enum
            String levelString = skillLevels.get(skillId);
            SkillLevel skillLevel = SkillLevel.valueOf(levelString); // Convert string to enum

            JobSkill jobSkill = new JobSkill(
                    new JobSkillId(existingJob.getId(), skill.getId()),
                    existingJob,
                    skill,
                    "This skill is required for the job",
                    skillLevel
            );
            jobSkillRepository.save(jobSkill);
        }

        // Save job
        return jobRepository.save(existingJob);
    }


}
