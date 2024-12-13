package vn.edu.iuh.fit.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.services.CandidateService;
import vn.edu.iuh.fit.backend.services.JobService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/candidates")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @Autowired
    private JobService jobService;

    @GetMapping("/list")
    public String showCandidatesList(Model model, Optional<Integer> pageNo, Optional<Integer> pageSize) {
        int currentPage = pageNo.orElse(1);
        int currentPageSize = pageSize.orElse(5);
        Page<Candidate> candidates = candidateService.findAll(currentPage - 1, currentPageSize, "id", "asc");
        model.addAttribute("candidates", candidates);
        int totalPages = candidates.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidates/candidates-list";
    }

    /**
     * Gợi ý công việc cho ứng viên
     */
    @GetMapping("/{id}/suggested-jobs")
    public String getSuggestedJobs(@PathVariable Long id, Model model) {
        // Lấy danh sách công việc phù hợp
        List<Job> suggestedJobs = jobService.suggestJobsForCandidate(id);

        // Đưa danh sách công việc vào model
        model.addAttribute("jobs", suggestedJobs);
        return "candidates/suggested-jobs"; // Giao diện hiển thị công việc gợi ý
    }
}
