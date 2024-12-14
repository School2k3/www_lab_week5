package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.services.JobService;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private JobService jobService;

    /**
     * Hiển thị Dashboard của nhà tuyển dụng
     */
    @GetMapping("/{id}/dashboard")
    public String showCompanyDashboard(@PathVariable Long id, Model model) {
        // Lấy danh sách công việc của công ty
        List<Job> jobs = jobService.getJobsByCompanyId(id);
        model.addAttribute("jobs", jobs);
        model.addAttribute("companyId", id);
        return "companies/dashboard"; // Tên tệp giao diện
    }

    /**
     * Xóa công việc
     */
    @PostMapping("/{companyId}/jobs/{jobId}/delete")
    public String deleteJob(
            @PathVariable Long companyId,
            @PathVariable Long jobId
    ) {
        jobService.deleteJobById(jobId);
        return "redirect:/companies/" + companyId + "/dashboard";
    }
}
