package com.SpringBoot.JobApplication.companies.dto;

import java.util.List;

import com.SpringBoot.JobApplication.job.entity.jobEntity;
import com.SpringBoot.JobApplication.reviews.entity.ReviewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class companydto {
    
    private Long id;
    private String name;
    private String description;
    private List<jobEntity> jobs;
    private List<ReviewEntity> reviews;


}
