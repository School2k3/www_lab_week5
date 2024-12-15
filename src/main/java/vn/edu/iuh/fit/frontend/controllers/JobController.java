package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.ids.JobSkillId;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.backend.services.JobService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobSkillRepository jobSkillRepository;

    /**
     * Hiển thị form thêm công việc
     */
    @GetMapping("/add")
    public String showJobForm(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("skillLevels", SkillLevel.values()); // Enum SkillLevel
        return "jobs/add-job";
    }

    /**
     * Xử lý thêm công việc
     */
    @PostMapping("/add")
    public String addJob(
            @ModelAttribute Job job,
            @RequestParam List<Long> skillIds,
            @RequestParam SkillLevel skillLevel,
            HttpSession session
    ) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) {
            return "redirect:/login?error";
        }

        job.setCompany(company);  // Gán công ty cho công việc
        jobService.createJobWithSkills(job, skillIds, skillLevel);

        // Sau khi thêm công việc thành công, quay lại dashboard
        return "redirect:/companies/" + company.getId() + "/dashboard";
    }

    /**
     * Hiển thị danh sách công việc
     */
    @GetMapping("/list")
    public String listJobs(Model model) {
        model.addAttribute("jobs", jobService.getAllJobs());
        return "jobs/job-list";
    }

    /**
     * Hiển thị form chỉnh sửa công việc
     */
    @GetMapping("/{id}/edit")
    public String showEditJobForm(@PathVariable Long id, Model model) {
        Job job = jobService.findById(id);
        model.addAttribute("job", job);

        List<Skill> skills = skillRepository.findAll();
        model.addAttribute("skills", skills);

        // Lấy danh sách các kỹ năng đã chọn
        List<Long> selectedSkillIds = job.getJobSkills()
                .stream()
                .map(js -> js.getSkill().getId())
                .toList();
        model.addAttribute("selectedSkillIds", selectedSkillIds);

        // Lấy danh sách mức độ kỹ năng đã chọn
        Map<Long, SkillLevel> selectedSkillLevels = job.getJobSkills()
                .stream()
                .collect(Collectors.toMap(js -> js.getSkill().getId(), JobSkill::getSkillLevel));
        model.addAttribute("selectedSkillLevels", selectedSkillLevels);

        model.addAttribute("skillLevels", SkillLevel.values());
        return "jobs/edit-job";
    }

    @PostMapping("/edit")
    public String updateJob(
            @ModelAttribute Job job,
            @RequestParam List<Long> skillIds,
            @RequestParam Map<Long, String> skillLevels) {
        if (job.getId() == null) {
            throw new IllegalArgumentException("Job ID must not be null");
        }
        // Kiểm tra và chuyển đổi giá trị skillLevel thành giá trị hợp lệ của enum
        for (Map.Entry<Long, String> entry : skillLevels.entrySet()) {
            String levelString = entry.getValue();
            try {
                SkillLevel skillLevel = SkillLevel.valueOf(levelString); // Chuyển đổi thành giá trị hợp lệ của enum
                // Tiến hành cập nhật công việc với skillLevel
            } catch (IllegalArgumentException e) {
                // Xử lý lỗi nếu giá trị skillLevel không hợp lệ
                throw new IllegalArgumentException("Invalid skill level: " + levelString);
            }
        }
        // Cập nhật công việc
        jobService.updateJobWithSkills(job, skillIds, skillLevels);
        return "redirect:/companies/" + job.getCompany().getId() + "/dashboard";
    }
}

