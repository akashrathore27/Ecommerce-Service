package com.ecommerce.springboot.services;

import com.ecommerce.springboot.payloads.FeedbackDto;

public interface FeedbackService
{
    FeedbackDto createFeedback(FeedbackDto feedbackDto, Long productId);
        void deleteFeedback(Long feedbackId);
}
