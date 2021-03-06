package gr.codehub.RecruMe.VEG.controllers.api;

import gr.codehub.RecruMe.VEG.exceptions.MatchNotFoundException;
import gr.codehub.RecruMe.VEG.models.Match;
import gr.codehub.RecruMe.VEG.services.FinalizeMatchService;
import gr.codehub.RecruMe.VEG.services.ListMatchService;
import gr.codehub.RecruMe.VEG.services.ReportingApplicantSkillService;
import gr.codehub.RecruMe.VEG.services.ReportingJobSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ReportingController used here to provide data of reporting services:
 * - Top 5 most requested skills by job offers
 * - Top 5 most offered jobs by applicants
 * - List of proposed manual and automatic matches
 * - List of the finalized matches between job offers and applicants (manual or automatic)
 * by displaying all the above via the corresponding HTTP responses as json files on the web.
 */

@RestController
@RequestMapping("/recrumeVEG/")
public class ReportingController {
    @Autowired
    private ReportingJobSkillService reportingJobSkillService;

    /**
     * Display the top 5 most requested skills by job offers
     * @return a list of the top 5 most requested skills
     */

    @Autowired
    private FinalizeMatchService finalizeMatchService;

    @GetMapping("top5MostRequestedSkills")
    public List<String> getTop5MostRequestedSkills() {
        return reportingJobSkillService.getTop5MostRequestedSkills();
    }

    @Autowired
    private ReportingApplicantSkillService reportingApplicantSkillService;

    /**
     * Display the top 5 most offered skills by applicants
     * @return a list of the top 5 most offered skills
     */

    @GetMapping("top5MostOfferedSkills")
    public List<String> getTop5MostOfferedSkills() {
        return reportingApplicantSkillService.getTop5MostOfferedSkills();
    }

    @Autowired
    private ListMatchService listMatchService;

    /**
     * Display a list of the proposed matches (manual and automatic)
     * @return a list of the proposed matches
     */

    @GetMapping("listOfProposedMatches")
    public List<String> getListOfProposedMatches() {
        return listMatchService.getListOfProposedMatched();
    }

    /**
     * Display a list of the finalized matches between job offers and applicants (manual and automatic)
     * @return a list of finalized matches
     */

    @GetMapping("listOfFinalizedMatches")
    public List<Match> getListOfFinalizedMatches() throws MatchNotFoundException {
        return finalizeMatchService.getFinalizedMatches();
    }
}
