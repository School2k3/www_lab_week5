package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.backend.services.JobService;

import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private SkillRepository skillRepository;

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
}

