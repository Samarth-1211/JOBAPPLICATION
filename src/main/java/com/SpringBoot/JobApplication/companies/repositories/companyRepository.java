package com.SpringBoot.JobApplication.companies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringBoot.JobApplication.companies.entity.companyEntity;

@Repository
public interface companyRepository extends JpaRepository<companyEntity , Long>{
    
}
