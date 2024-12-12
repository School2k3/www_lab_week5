package vn.edu.iuh.fit.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.ids.JobSkillId;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobSkillRepository jobSkillRepository;

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
                    "Yêu cầu kỹ năng này cho công việc",
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
}
