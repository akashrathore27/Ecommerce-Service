package com.ecommerce.springboot.services;

import com.ecommerce.springboot.payloads.OwnerDto;

import java.util.List;

public interface OwnerService {


    //create

    OwnerDto createOwner(OwnerDto ownerDto);

    //update
    OwnerDto updateOwner(OwnerDto ownerDto,Long ownerId);

    //get owner by id
    OwnerDto getOwnerById(Long ownerId);

    //get all owners
    List<OwnerDto> getAllOwners();

    //delete owner

    void  deleteOwner(Long ownerId);

}
