package com.ecommerce.springboot.services.impl;

import com.ecommerce.springboot.exception.ResourceNotFoundException;
import com.ecommerce.springboot.payloads.FeedbackDto;
import com.ecommerce.springboot.repository.CustomerReporsitory;
import com.ecommerce.springboot.repository.FeedbackRepository;
import com.ecommerce.springboot.repository.ProductRepository;
import com.ecommerce.springboot.services.FeedbackService;
import com.ecommerce.springboot.model.FeedBack;
import com.ecommerce.springboot.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private CustomerReporsitory customerReporsitory;


    @Autowired

    private ModelMapper modelMapper;

    @Override
    public FeedbackDto createFeedback(FeedbackDto feedbackDto, Long productId) {

        Product product=this.productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("product","product id",productId));

        FeedBack feedBack=this.modelMapper.map(feedbackDto, FeedBack.class);

        feedBack.setProduct(product);

        FeedBack savedFeedback=this.feedbackRepository.save(feedBack);
        return this.modelMapper.map(savedFeedback, FeedbackDto.class);
    }

    @Override
    public void deleteFeedback(Long feedbackId) {

        FeedBack feedBack=this.feedbackRepository.findById(feedbackId).orElseThrow(()->new ResourceNotFoundException("feedback","feedback Id",feedbackId));

        this.feedbackRepository.delete(feedBack);

    }
}
