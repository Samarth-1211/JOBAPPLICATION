package com.SpringBoot.JobApplication.reviews.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.JobApplication.reviews.dto.ReviewDTO;
import com.SpringBoot.JobApplication.reviews.services.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path ="companies/{companyId}/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;


    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(service.getAllReview(companyId),HttpStatus.OK);
    }

    @GetMapping(path = "/{reviewId}")
    public ResponseEntity<ReviewDTO> getReviewByreviewId(@PathVariable Long companyId , @PathVariable Long reviewId){
        return new ResponseEntity<>(service.getReviewByreviewId(companyId ,reviewId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> PostReviewForCompany(@RequestBody ReviewDTO review , @PathVariable Long companyId ){
        ReviewDTO reviewcreated = service.PostReviewForCompany(review ,companyId);
        return new ResponseEntity<>(reviewcreated,HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{reviewId}")
    public ResponseEntity<String> deleteReviewOfCompanyByReviewId(@PathVariable Long companyId , @PathVariable Long reviewId){
        service.deleteReviewOfCompanyByReviewId(companyId, reviewId);
        return new ResponseEntity<>("Review with Id " + reviewId + " DeletedSuccessfully", HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewDTO> updateReviewofACopmanyWithReviewId(@PathVariable Long companyId , @PathVariable Long reviewId , @RequestBody ReviewDTO ReviewUpdate){
        ReviewDTO updatedReview = service.updateReviewofACopmanyWithReviewId(companyId , reviewId , ReviewUpdate);
        return new ResponseEntity<>(updatedReview,HttpStatus.OK);
    }
 

    
}
