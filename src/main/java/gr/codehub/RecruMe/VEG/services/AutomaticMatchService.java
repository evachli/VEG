package gr.codehub.RecruMe.VEG.services;

import gr.codehub.RecruMe.VEG.exceptions.ApplicantNotFoundException;
import gr.codehub.RecruMe.VEG.exceptions.JobOfferNotFoundException;
import gr.codehub.RecruMe.VEG.exceptions.MatchedAlreadyException;
import gr.codehub.RecruMe.VEG.models.*;
import gr.codehub.RecruMe.VEG.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutomaticMatchService {
    private Matches matchesRepo;
    private Applicants applicantRepo;
    private JobOffers jobOfferRepo;
    private ApplicantSkills applicantSkills;
    private JobSkills jobSkills;

    @Autowired
    public AutomaticMatchService(Matches matchRepo, Applicants applicantRepo, JobOffers jobOfferRepo, ApplicantSkills applicantSkills, JobSkills jobSkills) {
        this.matchesRepo = matchRepo;
        this.applicantRepo = applicantRepo;
        this.jobOfferRepo = jobOfferRepo;
        this.applicantSkills = applicantSkills;
        this.jobSkills = jobSkills;
    }

    public List<Match> automaticMatch(int jobOfferId) throws MatchedAlreadyException,
            JobOfferNotFoundException, ApplicantNotFoundException {
        List<Match> foundMatches = new ArrayList<>();
        List<JobSkill> jobSkillsFound = jobSkills.findByJobOfferId(jobOfferId);
        List<Skill> jobOfferSkills = new ArrayList<>();
        for (JobSkill js : jobSkillsFound) {
            jobOfferSkills.add(js.getSkill());
        }
        List<Applicant> applicants = applicantRepo.findAll();
        List<ApplicantSkill> applicantSkillsList = applicantSkills.findAll();
        //because there is no cascade
        for (Applicant app : applicants) {
            for (ApplicantSkill applicantSkill : applicantSkillsList) {
                if (applicantSkill.getApplicant().getId() == app.getId()) {
                    app.getApplicantSkills().add(applicantSkill);
                }
            }
        }
        for (Applicant applicant : applicants) {
            List<Skill> applicantSkills = new ArrayList<>();
            for (ApplicantSkill as : applicant.getApplicantSkills()) {
                applicantSkills.add(as.getSkill());
            }
            if (applicantSkills.containsAll(jobOfferSkills)) {
                Match match = new Match();
                applicant.setActive(false);
                applicantRepo.save(applicant);
                match.setApplicant(applicant);
                JobOffer jobOffer = new JobOffer();
                Optional<JobOffer> foundJobOfferOptional = jobOfferRepo.findById(jobOfferId);
                JobOffer foundJobOffer = foundJobOfferOptional.get();
                foundJobOffer.setActive(false);
                jobOfferRepo.save(foundJobOffer);
                jobOffer.setId(jobOfferId);
                match.setJobOffer(jobOffer);
                match = matchesRepo.save(match);
                foundMatches.add(match);
            }
        }
        return foundMatches;
    }

    public Match automaticMatchForOne(int jobOfferId) throws MatchedAlreadyException,
            JobOfferNotFoundException, ApplicantNotFoundException {
        Match match = new Match();
        List<JobSkill> jobSkillsFound = jobSkills.findByJobOfferId(jobOfferId);
        List<Skill> jobOfferSkills = new ArrayList<>();
        for (JobSkill js : jobSkillsFound) {
            jobOfferSkills.add(js.getSkill());
        }
        List<Applicant> applicants = applicantRepo.findAll();
        List<ApplicantSkill> applicantSkillsList = applicantSkills.findAll();
        for (Applicant app : applicants) {
            List<Skill> applicantSkills = new ArrayList<>();
            for (ApplicantSkill as : app.getApplicantSkills()) {
                applicantSkills.add(as.getSkill());
            }
            if (applicantSkills.containsAll(jobOfferSkills)) {
                match = new Match();
                app.setActive(false);
                applicantRepo.save(app);
                match.setApplicant(app);
                JobOffer jobOffer = new JobOffer();
                Optional<JobOffer> foundJobOfferOptional = jobOfferRepo.findById(jobOfferId);
                JobOffer foundJobOffer = foundJobOfferOptional.get();
                foundJobOffer.setActive(false);
                jobOfferRepo.save(foundJobOffer);
                jobOffer.setId(jobOfferId);
                match.setJobOffer(jobOffer);
                match = matchesRepo.save(match);
            }
            if (match != null)
                return match;
        }
        return match;
    }
}
