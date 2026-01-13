package com.SpringBoot.JobApplication.job.entity;
import lombok.*;


import com.SpringBoot.JobApplication.companies.entity.companyEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class jobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Double minSalary;
    private Double maxSalary;
    private String location;

    @ManyToOne
    private companyEntity company ;
    
}
