package com.ecommerce.springboot.controller;

import com.ecommerce.springboot.payloads.ApiResponse;
import com.ecommerce.springboot.payloads.OwnerDto;
import com.ecommerce.springboot.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.springboot.services.OwnerService;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class OwnerController
{


    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private OwnerService ownerService;


    //    Post Api For Add  Owner
    @PostMapping("/owners")
    public ResponseEntity<OwnerDto> createOwner(@Valid @RequestBody OwnerDto ownerDto)
    {
        OwnerDto createOwnerDto=this.ownerService.createOwner(ownerDto);
        return new ResponseEntity<>(createOwnerDto,HttpStatus.CREATED);

    }

    //    Put Api for Delete Owner
    @PutMapping("/owners/{ownerId}")
    public ResponseEntity<OwnerDto> updateOwner(@Valid @RequestBody OwnerDto ownerDto,@PathVariable Long ownerId){

        OwnerDto updateOwner=this.ownerService.updateOwner(ownerDto,ownerId);
        return  ResponseEntity.ok(updateOwner);

    }

    //Delete  Api for  delete owner
    @DeleteMapping("/owners/{ownerId}")
    public ResponseEntity<?> deleteOwner(@PathVariable("ownerId") Long ownerId){
        this.ownerService.deleteOwner(ownerId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Owner deleted Successfully",true), HttpStatus.OK);

    }

    //Get Api for get All owners
    @GetMapping("/owners")
    public ResponseEntity<List<OwnerDto>> getAllOwners(){
        return ResponseEntity.ok(this.ownerService.getAllOwners());
    }

    //Get api for get owner by id
    @GetMapping("/owners/{ownerId}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable Long ownerId){
        return ResponseEntity.ok(this.ownerService.getOwnerById(ownerId));

    }

}
