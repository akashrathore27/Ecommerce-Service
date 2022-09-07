package com.ecommerce.springboot.services.impl;

import com.ecommerce.springboot.exception.ResourceNotFoundException;
import com.ecommerce.springboot.model.Owner;
import com.ecommerce.springboot.payloads.OwnerDto;
import com.ecommerce.springboot.repository.OwnerRepository;
import com.ecommerce.springboot.services.OwnerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService
{

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OwnerDto createOwner(OwnerDto ownerDto) {

        Owner owner=this.dtoToOwner(ownerDto);
        Owner savedOwner=this.ownerRepository.save(owner);
        return this.ownerToDto(savedOwner);
    }


    @Override
    public OwnerDto updateOwner(OwnerDto ownerDto, Long ownerId)
    {
        Owner owner=this.ownerRepository.findById(ownerId).orElseThrow(()-> new ResourceNotFoundException("Owner ","Owner Id",ownerId));

        owner.setOwnerName(ownerDto.getOwnerName());
        owner.setEmail(ownerDto.getEmail());
        owner.setPassword(ownerDto.getPassword());


        Owner updatedOwner=this.ownerRepository.save(owner);
        OwnerDto ownerDto1=this.ownerToDto(updatedOwner);

        return ownerDto1;

    }

    @Override
    public OwnerDto getOwnerById(Long ownerId) {
        Owner owner=this.ownerRepository.findById(ownerId).orElseThrow(()-> new ResourceNotFoundException("Owner ","Owner Id",ownerId));

        return this.ownerToDto(owner);

    }

    @Override
    public List<OwnerDto> getAllOwners() {
        List<Owner>owners=this.ownerRepository.findAll();
        List<OwnerDto>ownerDtos= owners.stream().map(owner -> this.ownerToDto(owner)).collect(Collectors.toList());

        return ownerDtos;

    }

    @Override
    public void deleteOwner(Long ownerId) {

        Owner owner=this.ownerRepository.findById(ownerId).orElseThrow(()-> new ResourceNotFoundException("Owner ","owner Id",ownerId));

        this.ownerRepository.delete(owner);
    }

    private Owner dtoToOwner(OwnerDto ownerDto)
    {
        Owner owner=this.modelMapper.map(ownerDto,Owner.class);


        return owner;
    }
    public OwnerDto ownerToDto(Owner owner)
    {
        OwnerDto ownerDto=this.modelMapper.map(owner,OwnerDto.class);


        return  ownerDto;
    }
}
