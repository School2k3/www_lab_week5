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
import java.util.Arrays;
import java.util.List;
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
    CommandLineRunner initData(
            AddressRepository addressRepository,
            CompanyRepository companyRepository,
            CandidateRepository candidateRepository,
            SkillRepository skillRepository,
            CandidateSkillRepository candidateSkillRepository,
            JobRepository jobRepository,
            JobSkillRepository jobSkillRepository,
            ExperienceRepository experienceRepository
    ) {
        return args -> {
//            // 1. Tạo và lưu Address
//            List<Address> addresses = Arrays.asList(
//                    new Address("1600", "Amphitheatre Pkwy", "Mountain View", "94043", CountryCode.US),
//                    new Address("1", "Microsoft Way", "Redmond", "98052", CountryCode.US),
//                    new Address("1", "Infinite Loop", "Cupertino", "95014", CountryCode.US),
//                    new Address("456", "Broadway St", "New York", "10001", CountryCode.US),
//                    new Address("789", "Market St", "San Francisco", "94103", CountryCode.US)
//                    // Add 15 more unique addresses
//            );
//            addressRepository.saveAll(addresses);
//
//            // 2. Tạo và lưu Company
//            List<Company> companies = Arrays.asList(
//                    new Company("Google", "contact@google.com", "123456789", addresses.get(0), "https://google.com", "Search Engine"),
//                    new Company("Microsoft", "contact@microsoft.com", "987654321", addresses.get(1), "https://microsoft.com", "Software Development"),
//                    new Company("Apple", "contact@apple.com", "555666777", addresses.get(2), "https://apple.com", "Consumer Electronics")
//                    // Add 17 more unique companies
//            );
//            companyRepository.saveAll(companies);
//
//            // 3. Tạo và lưu Skill
//            List<Skill> skills = Arrays.asList(
//                    new Skill("Java", "Java programming", SkillType.TECHNICAL_SKILL),
//                    new Skill("Spring Boot", "Spring Boot framework", SkillType.TECHNICAL_SKILL),
//                    new Skill("React", "React library for frontend development", SkillType.TECHNICAL_SKILL),
//                    new Skill("Docker", "Containerization tool", SkillType.TECHNICAL_SKILL),
//                    new Skill("Communication", "Effective communication skills", SkillType.SOFT_SKILL),
//                    new Skill("Problem Solving", "Analytical thinking and problem solving", SkillType.SOFT_SKILL)
//                    // Add 14 more unique skills
//            );
//            skillRepository.saveAll(skills);
//
//            // 4. Tạo và lưu Candidate
//            List<Candidate> candidates = Arrays.asList(
//                    new Candidate("John Doe", LocalDate.of(1990, 5, 15), addresses.get(3), "123456789", "john.doe@gmail.com"),
//                    new Candidate("Jane Smith", LocalDate.of(1985, 3, 10), addresses.get(4), "987654321", "jane.smith@gmail.com")
//                    // Add 18 more unique candidates
//            );
//            candidateRepository.saveAll(candidates);
//
//            // 5. Tạo và lưu CandidateSkill
//            List<CandidateSkill> candidateSkills = Arrays.asList(
//                    new CandidateSkill(new CandidateSkillId(candidates.get(0).getId(), skills.get(0).getId()), candidates.get(0), skills.get(0), "Advanced Java knowledge", SkillLevel.PROFESSIONAL),
//                    new CandidateSkill(new CandidateSkillId(candidates.get(1).getId(), skills.get(1).getId()), candidates.get(1), skills.get(1), "Intermediate Spring Boot knowledge", SkillLevel.INTERMEDIATE)
//                    // Add more CandidateSkill objects
//            );
//            candidateSkillRepository.saveAll(candidateSkills);
//
//            // 6. Tạo và lưu Job
//            List<Job> jobs = Arrays.asList(
//                    new Job("Java Developer", "Develop scalable backend systems", companies.get(0)),
//                    new Job("Frontend Developer", "Build responsive user interfaces", companies.get(1))
//                    // Add 18 more unique jobs
//            );
//            jobRepository.saveAll(jobs);
//
//            // 7. Tạo và lưu JobSkill
//            List<JobSkill> jobSkills = Arrays.asList(
//                    new JobSkill(new JobSkillId(jobs.get(0).getId(), skills.get(0).getId()), jobs.get(0), skills.get(0), "Advanced Java required", SkillLevel.PROFESSIONAL),
//                    new JobSkill(new JobSkillId(jobs.get(1).getId(), skills.get(2).getId()), jobs.get(1), skills.get(2), "Intermediate React required", SkillLevel.INTERMEDIATE)
//                    // Add more JobSkill objects
//            );
//            jobSkillRepository.saveAll(jobSkills);
//
//            // 8. Tạo và lưu Experience
//            List<Experience> experiences = Arrays.asList(
//                    new Experience(LocalDate.of(2015, 1, 1), LocalDate.of(2020, 1, 1), "Backend Developer", "Google", "Developed APIs", candidates.get(0)),
//                    new Experience(LocalDate.of(2016, 1, 1), LocalDate.of(2021, 1, 1), "Frontend Developer", "Apple", "Designed UIs", candidates.get(1))
//                    // Add more Experience objects
//            );
//            experienceRepository.saveAll(experiences);
        };
    }

}
