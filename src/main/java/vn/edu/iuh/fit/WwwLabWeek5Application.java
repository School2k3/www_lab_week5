package vn.edu.iuh.fit;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.enums.SkillType;
import vn.edu.iuh.fit.backend.ids.CandidateSkillId;
import vn.edu.iuh.fit.backend.ids.JobSkillId;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.*;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class WwwLabWeek5Application {

    public static void main(String[] args) {
        SpringApplication.run(WwwLabWeek5Application.class, args);
    }

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Bean
    CommandLineRunner initData(CandidateSkillRepository candidateSkillRepository){
        return args -> {
//            Random rnd =new Random();
//            for (int i = 1; i < 100; i++) {
//                Address add = new Address(rnd.nextInt(1,100)+"","Quang Trung","HCM",
//                        rnd.nextInt(70000,80000)+"", CountryCode.VN );
//                addressRepository.save(add);
//
//                Candidate can=new Candidate("Name #"+i,
//                        LocalDate.of(1998,rnd.nextInt(1,13),rnd.nextInt(1,29)),
//                        add,
//                        rnd.nextLong(1111111111L,9999999999L)+"",
//                        "email_" + i + "@gmail.com");
//                candidateRepository.save(can);
//                System.out.println("Added: " +can);
//            }
            // Tạo kỹ năng
//            Skill javaSkill = skillRepository.save(new Skill("Java", "Java programming", SkillType.TECHNICAL_SKILL));
//            Skill springSkill = skillRepository.save(new Skill("Spring Boot", "Spring Boot framework", SkillType.TECHNICAL_SKILL));
//            Skill communicationSkill = skillRepository.save(new Skill("Communication", "Effective communication", SkillType.SOFT_SKILL));
//
//            // Tạo dữ liệu mẫu cho Address
//            Address googleAddress = new Address("1600", "Amphitheatre Parkway", "Mountain View", "94043", CountryCode.US);
//            Address microsoftAddress = new Address("1", "Microsoft Way", "Redmond", "98052", CountryCode.US);
//
//            // Lưu Address trước khi sử dụng trong Company và Candidate
//            Address savedGoogleAddress = addressRepository.save(googleAddress);
//            Address savedMicrosoftAddress = addressRepository.save(microsoftAddress);
//
//            // Tạo dữ liệu mẫu cho Company với Address đã lưu
//            Company google = companyRepository.save(new Company("Google Inc", "contact@google.com", "123456789", savedGoogleAddress, "https://google.com", "Search Engine"));
//            Company microsoft = companyRepository.save(new Company("Microsoft Corp", "contact@microsoft.com", "987654321", savedMicrosoftAddress, "https://microsoft.com", "Software Development"));
//
//            // Tạo ứng viên, sử dụng Address đã lưu
//            Candidate johnDoe = candidateRepository.save(new Candidate("John Doe", LocalDate.of(1990, 5, 15), savedGoogleAddress, "123456789", "john.doe@gmail.com"));
//
//            // Tạo công việc
//            Job javaDeveloperJob = jobRepository.save(new Job("Java Developer", "Develop scalable backend systems", google));
//
//            // Tạo JobSkill (Gắn kỹ năng vào công việc)
//            jobSkillRepository.save(new JobSkill(new JobSkillId(javaDeveloperJob.getId(), javaSkill.getId()), javaDeveloperJob, javaSkill, "Advanced Java knowledge required", SkillLevel.PROFESSIONAL));
//            jobSkillRepository.save(new JobSkill(new JobSkillId(javaDeveloperJob.getId(), springSkill.getId()), javaDeveloperJob, springSkill, "Spring Boot expertise needed", SkillLevel.ADVANCED));
//
//            // Tạo CandidateSkill (Gắn kỹ năng vào ứng viên)
//            candidateSkillRepository.save(new CandidateSkill(new CandidateSkillId(johnDoe.getId(), javaSkill.getId()), johnDoe, javaSkill, "Strong Java experience", SkillLevel.PROFESSIONAL));
//            candidateSkillRepository.save(new CandidateSkill(new CandidateSkillId(johnDoe.getId(), communicationSkill.getId()), johnDoe, communicationSkill, "Effective communicator", SkillLevel.INTERMEDIATE));
        };
    }
}
