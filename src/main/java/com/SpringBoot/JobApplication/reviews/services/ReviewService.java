package com.SpringBoot.JobApplication.reviews.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.SpringBoot.JobApplication.companies.entity.companyEntity;
import com.SpringBoot.JobApplication.companies.repositories.companyRepository;
import com.SpringBoot.JobApplication.exceptions.ResourceNotFoundException;
import com.SpringBoot.JobApplication.reviews.dto.ReviewDTO;
import com.SpringBoot.JobApplication.reviews.entity.ReviewEntity;
import com.SpringBoot.JobApplication.reviews.repositories.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repo;
    private final ModelMapper mapper;
    private final companyRepository companyrepo;

    public Boolean doesCompanyExist(Long companyId){
       if(companyrepo.findById(companyId).isPresent()) return true;
       return false;
    }


    public List<ReviewDTO> getAllReview(Long companyId){
        if(!doesCompanyExist(companyId)) throw new ResourceNotFoundException("Company With Provided Id Does Not Exist");
       
        List<ReviewEntity> reviewsEntity = repo.findByCompanyId(companyId);
        return reviewsEntity
                .stream()
                .map(reviewEntity -> mapper.map(reviewEntity , ReviewDTO.class))
                .collect(Collectors.toList()); 
    }


    public ReviewDTO PostReviewForCompany(ReviewDTO review, Long companyId) {
        if(!doesCompanyExist(companyId)) throw new ResourceNotFoundException("Company With Provided Id Does Not Exist");
       
       companyEntity company = companyrepo.findById(companyId).get();
        ReviewEntity entity = mapper.map(review , ReviewEntity.class);
        entity.setCompany(company);
        return mapper.map(repo.save(entity),ReviewDTO.class);

    }


    public void deleteReviewOfCompanyByReviewId(Long companyId, Long reviewId) {
       ReviewEntity review = repo.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("Review With Particular Id Does Not Exist"));
       if(!review.getCompany().getId().equals(companyId)) throw new ResourceNotFoundException("Review DoesNot Belong to This Company");
        repo.deleteById(reviewId);
    }

    public ReviewDTO getReviewByreviewId(Long companyId ,Long reviewId) {
      if(doesCompanyExist(companyId)) {

        if(repo.findById(reviewId).isPresent()){
            ReviewEntity entity = repo.findById(reviewId).get();
           return  mapper.map(entity,ReviewDTO.class);
        }else 
            throw new ResourceNotFoundException("Review  With Provided Id Does Not Exist");
       
      }else 
        throw new ResourceNotFoundException("Company With Provided Id Does Not Exist");
       
    }


    public ReviewDTO updateReviewofACopmanyWithReviewId(Long companyId, Long reviewId , ReviewDTO ReviewUpdate) {

        // if(doesCompanyExist(companyId) && repo.findById(reviewId).isPresent()){
        //     ReviewEntity updates = mapper.map(ReviewUpdate, ReviewEntity.class);
        //     updates.setId(reviewId);
        //     return mapper.map(repo.save(updates),ReviewDTO.class);
        // }else  throw new ResourceNotFoundException("Company or Review  With Provided Id Does Not Exist");
       
        ReviewEntity existing = repo.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("Review With Id -> " +reviewId+ " Does Not Exist"));

        if(!existing.getCompany().getId().equals(companyId)) throw new ResourceNotFoundException("Review With Id -> " +reviewId+ " Does Not Belong to Particular Company");

        existing.setTitle(ReviewUpdate.getTitle());
        existing.setDescription(ReviewUpdate.getDescription());
        existing.setRatings(ReviewUpdate.getRatings());

        return mapper.map(repo.save(existing),ReviewDTO.class);
    }


   
    
}
