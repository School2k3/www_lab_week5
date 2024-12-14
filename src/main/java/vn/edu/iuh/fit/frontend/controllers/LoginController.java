package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;

@Controller
public class LoginController {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CompanyRepository companyRepository;

    /**
     * Hiển thị trang chủ với 2 tùy chọn
     */
    @GetMapping("/login")
    public String showHomePage() {
        return "index"; // Trang chứa 2 nút chọn loại tài khoản
    }

    /**
     * Hiển thị giao diện đăng nhập ứng viên
     */
    @GetMapping("/login/candidate")
    public String showCandidateLoginPage() {
        return "login/login-candidate"; // Chỉ rõ đường dẫn đến thư mục login
    }

    /**
     * Hiển thị giao diện đăng nhập nhà tuyển dụng
     */
    @GetMapping("/login/company")
    public String showCompanyLoginPage() {
        return "login/login-company"; // Chỉ rõ đường dẫn đến thư mục login
    }

    /**
     * Xử lý đăng nhập ứng viên
     */
    @PostMapping("/login/candidate")
    public String handleCandidateLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        Candidate candidate = candidateRepository.findById(Long.valueOf(username))
                .orElse(null);

        if (candidate != null && candidate.getPhone().equals(password)) {
            // Chuyển hướng đến trang gợi ý công việc
            return "redirect:/candidates/" + candidate.getId() + "/suggested-jobs";
        } else {
            // Hiển thị lại trang đăng nhập với thông báo lỗi
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
            return "login/login-candidate";
        }
    }

    /**
     * Xử lý đăng nhập nhà tuyển dụng
     */
    @PostMapping("/login/company")
    public String handleCompanyLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpSession session
    ) {
        Company company = companyRepository.findById(Long.valueOf(username))
                .orElse(null);

        if (company != null && company.getPhone().equals(password)) {
            // Chuyển hướng đến trang dashboard của nhà tuyển dụng
            session.setAttribute("company", company);
            return "redirect:/companies/" + company.getId() + "/dashboard";
        } else {
            // Hiển thị lại trang đăng nhập với thông báo lỗi
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
            return "login/login-company";
        }
    }
}


