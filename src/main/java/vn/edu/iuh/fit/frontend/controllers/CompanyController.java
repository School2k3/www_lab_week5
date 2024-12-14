package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.backend.services.CompanyService;
import vn.edu.iuh.fit.backend.services.JobService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CompanyController {
    @Autowired
    private JobService jobService;

    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Autowired
    private CompanyService companyService;

    // Phương thức hiển thị trang chủ với danh sách các công ty
    @GetMapping("/")
    public String showCompanyList(Model model, Optional<Integer> pageNo, Optional<Integer> pageSize) {
        int currentPage = pageNo.orElse(1);
        int currentPageSize = pageSize.orElse(5);
        Page<Company> companies = companyService.findAll(currentPage - 1, currentPageSize, "id", "asc");
        model.addAttribute("companies", companies);
        int totalPages = companies.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "index";
    }

    @GetMapping("/company/{id}")
    public String showCompanyDetails(@PathVariable Long id, Model model) {
        // Lấy thông tin công ty từ database theo id
        Company company = companyService.findById(id);
        model.addAttribute("company", company);
        return "companies/company-details";  // Trang hiển thị chi tiết công ty
    }

    /**
     * Hiển thị Dashboard của nhà tuyển dụng
     */
    @GetMapping("/companies/{id}/dashboard")
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
    @PostMapping("/companies/{companyId}/jobs/{jobId}/delete")
    public String deleteJob(
            @PathVariable Long companyId,
            @PathVariable Long jobId
    ) {
        jobService.deleteJobById(jobId);
        return "redirect:/companies/" + companyId + "/dashboard";
    }
}
