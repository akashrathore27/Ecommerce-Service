package com.ecommerce.springboot.controller;

import com.ecommerce.springboot.payloads.FeedbackDto;
import com.ecommerce.springboot.services.FeedbackService;
import com.ecommerce.springboot.payloads.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/product/{productId}/feedbacks")
    public ResponseEntity<FeedbackDto> createFeedback(@RequestBody FeedbackDto feedbackDto, @PathVariable Long productId)
    {
      FeedbackDto createFeedback=this.feedbackService.createFeedback(feedbackDto,productId);
      return  new ResponseEntity<FeedbackDto>(createFeedback, HttpStatus.CREATED);
    }

    @DeleteMapping("/feedbacks/{feedbackId}")
    public ResponseEntity<ApiResponse> deleteFeedback(Long feedbackId)
    {
        this.feedbackService.deleteFeedback(feedbackId);
        return  new ResponseEntity<ApiResponse>(new ApiResponse("feedback deleted successfully",true), HttpStatus.OK);
    }





}
