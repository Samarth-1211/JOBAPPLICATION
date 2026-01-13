package com.SpringBoot.JobApplication.job.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringBoot.JobApplication.job.entity.jobEntity;

@Repository
public interface jobRepository extends JpaRepository<jobEntity , Long>  {
    
}
