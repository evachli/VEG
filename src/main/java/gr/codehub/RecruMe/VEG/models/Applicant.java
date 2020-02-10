package gr.codehub.RecruMe.VEG.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String address;
    private String region;
    private String educationLevel;

    @Column(columnDefinition = "bit default 1")
    private boolean active;

    @OneToMany(mappedBy = "applicant" )
    @JsonIgnore
    private List<ApplicantSkill> applicantSkills;

    public Applicant(String firstName, String lastName, String address, String region, String educationLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.region = region;
        this.educationLevel = educationLevel;
    }
}
