package com.ecommerce.springboot.security;
import com.ecommerce.springboot.exception.ResourceNotFoundException;
import com.ecommerce.springboot.model.Owner;
import com.ecommerce.springboot.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailService implements UserDetailsService {
        @Autowired
        private OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username
        Owner owner=this.ownerRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("owner","Owner username",username));


        return owner;
    }
}
