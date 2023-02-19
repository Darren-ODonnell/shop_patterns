package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.Lastname;
import com.jwt.models.LastnameModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.LastnameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LastnameService {
    private final LastnameRepository lastnameRepository;

    @Autowired
    public LastnameService(LastnameRepository lastnameRepository) {
        this.lastnameRepository = lastnameRepository;
    }

    // return all lastnames

    public List<Lastname> list(){
        return lastnameRepository.findAll();
    }

    // return Lastname by id

    public Lastname findById(Long id){
        Optional<Lastname> lastname = lastnameRepository.findById(id);
        if(lastname.isEmpty())
            new MyMessageResponse("Error: No lastname exists with id: "+id, MessageTypes.WARN);
        return lastname.orElse(new Lastname());
    }

    // return lastname given English lastname

    public List<Lastname> findByEnglishLastname(LastnameModel lastnameModel) {

        Optional<List<Lastname>> lastname = lastnameRepository.findByLastname(lastnameModel.getLastname());
        if(lastname.isEmpty())
            new MyMessageResponse(String.format("Firstname : %s not found", lastnameModel.getLastname()), MessageTypes.WARN);
        return lastname.orElse(new ArrayList<>());
    }

    // return lastname given Irish lastname

    public List<Lastname> findByIrishLastname(LastnameModel lastnameModel) {

        Optional<List<Lastname>> lastname = lastnameRepository.findByLastnameIrish(lastnameModel.getLastnameIrish());
        if(lastname.isEmpty())
            new MyMessageResponse(String.format("Firstname : %s not found", lastnameModel.getLastnameIrish()), MessageTypes.WARN);
        return lastname.orElse(new ArrayList<>());
    }

    // delete lastname

    public ResponseEntity<MessageResponse> delete( Lastname lastname){
        Long id = lastname.getId();
        if(lastnameRepository.existsById(id)) {
            lastnameRepository.deleteById(id);
            return ResponseEntity.ok(new MyMessageResponse("Lastname deleted with id: " + id, MessageTypes.INFO));
        } else {
            return ResponseEntity.ok(new MyMessageResponse("Error: No Record exists with id: " + id, MessageTypes.WARN));
        }
    }

    // add record

    public ResponseEntity<MessageResponse> add(LastnameModel lastnameModel){
        if(!lastnameRepository.existsByLastname(lastnameModel.getLastname())) {
            lastnameRepository.save(lastnameModel.translateModelToLastname());
            return ResponseEntity.ok(new MyMessageResponse("new Lastname added", MessageTypes.INFO));
        } else {
            return ResponseEntity.ok(new MyMessageResponse("Error: Lastname already exists", MessageTypes.WARN));
        }
    }

    // edit/update lastname

    public ResponseEntity<MessageResponse> update( Long id,  Lastname lastname){
        if(lastnameRepository.existsById(id)) {
            lastnameRepository.save(lastname);
            return ResponseEntity.ok(new MyMessageResponse("Lastname record updated", MessageTypes.INFO));
        } else {
            return ResponseEntity.ok(new MyMessageResponse("Error: Lastname with Id: [" + id + "] -> does not exist - cannot update record", MessageTypes.WARN));
        }

    }
}
