package com.SpringBoot.JobApplication.companies.entity;

import java.util.List;

import com.SpringBoot.JobApplication.job.entity.jobEntity;
import com.SpringBoot.JobApplication.reviews.entity.ReviewEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "company")
public class companyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany
    @JsonIgnore
    private List<jobEntity> jobs;

    @OneToMany(mappedBy = "company")
    private List<ReviewEntity> reviews;


    
}
