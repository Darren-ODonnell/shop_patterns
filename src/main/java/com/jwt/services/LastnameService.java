package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.Lastname;
import com.jwt.models.LastnameModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.LastnameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LastnameService {
    private static final Logger logger = LoggerFactory.getLogger(LastnameService.class);
    private final LastnameRepository lastnameRepository;

    @Autowired
    public LastnameService(LastnameRepository lastnameRepository) {
        this.lastnameRepository = lastnameRepository;
    }

    public ResponseEntity<MessageResponse> add(LastnameModel lastnameModel){
        if(lastnameRepository.existsByLastname(lastnameModel.getLastname()))
            return ResponseEntity.ok(new MyMessageResponse("Error: Lastname already exists", MessageTypes.WARN));

        lastnameRepository.save(lastnameModel.translateModelToLastname());
        return ResponseEntity.ok(new MyMessageResponse("new Lastname added", MessageTypes.INFO));
    }

    // edit/update lastname

    public ResponseEntity<MessageResponse> update( Long id,  LastnameModel lastnameModel){

        if(!lastnameRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Lastname with Id: ["+id+"] -> does not exist - cannot update record", MessageTypes.WARN));

        lastnameRepository.save(lastnameModel.translateModelToLastname(id));
        return ResponseEntity.ok(new MyMessageResponse("Lastname record updated", MessageTypes.INFO));

    }

    // return all lastnames

    public List<Lastname> list(){
        return lastnameRepository.findAll();
    }

    // return Lastname by id

    public Optional<Lastname> findById(Long id){
        return lastnameRepository.findById(id);
    }

    // return lastname given English lastname

    public List<Lastname> findByEnglishLastname(LastnameModel lastnameModel) {

        Optional<List<Lastname>> lastname = lastnameRepository.findByLastname(lastnameModel.getLastname());
        if(lastname.isEmpty()) logger.warn(String.format("Firstname : %s not found", lastnameModel.getLastname()));
        return lastname.orElse(new ArrayList<>());
    }

    // return lastname given Irish lastname

    public List<Lastname> findByIrishLastname(LastnameModel lastnameModel) {

        Optional<List<Lastname>> lastname = lastnameRepository.findByLastnameIrish(lastnameModel.getLastnameIrish());
        if(lastname.isEmpty()) logger.warn(String.format("Firstname : %s not found", lastnameModel.getLastnameIrish()));
        return lastname.orElse(new ArrayList<>());
    }

    // delete lastname

    public ResponseEntity<MessageResponse> deleteById( Long id){

        // check if records exists first
        // delete record

        if(!lastnameRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: No Record exists with id: "+id, MessageTypes.WARN));

        lastnameRepository.deleteById(id);
        return ResponseEntity.ok(new MyMessageResponse("Lastname deleted with id: " + id, MessageTypes.INFO));
    }


}
