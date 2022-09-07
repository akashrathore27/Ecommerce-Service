package com.ecommerce.springboot.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDto
{
    private long feedbackId;
    private String feedback;
    private double reviews;
}
