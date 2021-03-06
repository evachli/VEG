package gr.codehub.RecruMe.VEG.controllers.api;

import gr.codehub.RecruMe.VEG.dtos.JobOfferDto;
import gr.codehub.RecruMe.VEG.exceptions.JobOfferNotFoundException;
import gr.codehub.RecruMe.VEG.models.JobOffer;
import gr.codehub.RecruMe.VEG.services.JobOfferUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * JobOfferUpdateController used here to provide the data of all job offer update services
 * (title of position, activate/inactivate status, region, education level, company) by
 * displaying the above as json files on the web via HTTP responses.
 */

@RestController
@RequestMapping("/recrumeVEG/")
public class JobOfferUpdateController {
    @Autowired
    private JobOfferUpdateService jobOfferUpdateService;

    /**
     * endpoint http://localhost:8080/recrumeVEG/joboffer/{id}/title
     * updates title of position of job offer
     * @param id
     * @param jobOfferDto
     * @return updated job offer
     * @throws JobOfferNotFoundException
     */

    @PutMapping("joboffer/{id}/title")
    public JobOffer updateTitleOfPosition(@PathVariable int id,
                              @RequestBody JobOfferDto jobOfferDto)
            throws JobOfferNotFoundException {
        return jobOfferUpdateService.updateTitleOfPosition(id, jobOfferDto);
    }

    /**
     * endpoint http://localhost:8080/recrumeVEG/joboffer/{id}/activatestatus
     * updates activatestatus of job offer
     * @param id
     * @param jobOfferDto
     * @return updated job offer
     * @throws JobOfferNotFoundException
     */

    @PutMapping("joboffer/{id}/activatestatus")
    public JobOffer activateStatus(@PathVariable int id,
                              @RequestBody JobOfferDto jobOfferDto)
            throws JobOfferNotFoundException {
        return jobOfferUpdateService.activateStatus(id, jobOfferDto);
    }

    /**
     * endpoint http://localhost:8080/recrumeVEG/joboffer/{id}/inactivatestatus
     * updates inactivatestatus of job offer
     * @param id
     * @param jobOfferDto
     * @return updated job offer
     * @throws JobOfferNotFoundException
     */

    @PutMapping("joboffer/{id}/inactivatestatus")
    public JobOffer inactivateStatus(@PathVariable int id,
                                 @RequestBody JobOfferDto jobOfferDto)
            throws JobOfferNotFoundException {
        return jobOfferUpdateService.inactivateStatus(id, jobOfferDto);
    }

    /**
     * endpoint http://localhost:8080/recrumeVEG/joboffer/{id}/region
     * updates region of job offer
     * @param id
     * @param jobOfferDto
     * @return updated job offer
     * @throws JobOfferNotFoundException
     */

    @PutMapping("joboffer/{id}/region")
    public JobOffer updateRegion(@PathVariable int id,
                                     @RequestBody JobOfferDto jobOfferDto)
            throws JobOfferNotFoundException {
        return jobOfferUpdateService.updateRegion(id, jobOfferDto);
    }

    /**
     * endpoint http://localhost:8080/recrumeVEG/joboffer/{id}/educationLevel
     * updates educationLevel of job offer
     * @param id
     * @param jobOfferDto
     * @return updated job offer
     * @throws JobOfferNotFoundException
     */

    @PutMapping("joboffer/{id}/educationLevel")
    public JobOffer updateEducationLevel(@PathVariable int id,
                                 @RequestBody JobOfferDto jobOfferDto)
            throws JobOfferNotFoundException {
        return jobOfferUpdateService.educationLevel(id, jobOfferDto);
    }

    /**
     * endpoint http://localhost:8080/recrumeVEG/joboffer/{id}/company
     * updates the company offering the job
     * @param id
     * @param jobOfferDto
     * @return updated job offer
     * @throws JobOfferNotFoundException
     */

    @PutMapping("joboffer/{id}/company")
    public JobOffer updateCompany(@PathVariable int id,
                                   @RequestBody JobOfferDto jobOfferDto)
            throws JobOfferNotFoundException {
        return jobOfferUpdateService.company(id, jobOfferDto);
    }

    /**
     * endpoint http://localhost:8080/recrumeVEG/joboffer/{id}/updateskills
     * updates job offer by id with new job skills
     * @param id of the job offer
     * @param jobOfferDto
     * @return a JobOffer updated by skills
     * @throws JobOfferNotFoundException
     */
    @PutMapping("joboffer/{id}/updateskills")
    public JobOffer updateJobOfferSkill(@PathVariable int id,
                                  @RequestBody JobOfferDto jobOfferDto)
            throws JobOfferNotFoundException {
        return jobOfferUpdateService.updateJobOfferSkill(id, jobOfferDto);
    }

}
