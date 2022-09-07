package com.ecommerce.springboot.repository;

import com.ecommerce.springboot.model.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<FeedBack,Long>
{

}
