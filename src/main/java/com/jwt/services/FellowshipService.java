package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.Fellowship;
import com.jwt.models.FellowshipModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.FellowshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FellowshipService {
    FellowshipRepository fellowshipRepository;

    @Autowired
    public FellowshipService(FellowshipRepository fellowRepository) {
        this.fellowshipRepository = fellowRepository;
    }

    // return all fellows - done

    public List<Fellowship> list() {
        return fellowshipRepository.findAll();
    }


    // return fellow by id

    public Fellowship findById(Long id){
        Optional<Fellowship> fellow = fellowshipRepository.findById(id);
        if(fellow.isEmpty())
            new MyMessageResponse("Fellowship not found with id: " + id , MessageTypes.WARN);

        return fellow.orElse(new Fellowship());
    }

    // return fellow by email

    public Fellowship findByEmail(String email){
        Optional<Fellowship> fellow = fellowshipRepository.findByEmail(email);
        if(fellow.isEmpty())
            new MyMessageResponse("Fellowship not found with id: " + email , MessageTypes.WARN);

        return fellow.orElse(new Fellowship());
    }

    // return fellow by firstname + lastname

    public Fellowship findByFirstnameLastname(FellowshipModel fellowModel) {
        Optional<Fellowship> fellow = fellowshipRepository.findByFirstnameAndLastname(fellowModel.getFirstname(), fellowModel.getLastname());
        if(fellow.isEmpty())
            new MyMessageResponse("Fellowship not found with Firstname: "+fellowModel.getFirstname()+", and lastname: "+fellowModel.getLastname(), MessageTypes.WARN);

        return fellow.orElse(new Fellowship());
    }

    // return fellows with same lastname

    public  List<Fellowship> findByLastname(FellowshipModel fellowModel) {
        Optional<List<Fellowship>> fellows = fellowshipRepository.findByLastname(fellowModel.getLastname());
        if(fellows.isEmpty())
            new MyMessageResponse("Fellowship not found with lastname: " + fellowModel.getLastname(), MessageTypes.WARN);

        return fellows.orElse(new ArrayList<>());
    }

    public  List<Fellowship> findByLastname(String lastname) {
        Optional<List<Fellowship>> fellows = fellowshipRepository.findByLastname(lastname);
        if(fellows.isEmpty())
            new MyMessageResponse("Fellowship not found with lastname: " + lastname, MessageTypes.WARN);

        return fellows.orElse(new ArrayList<>());
    }

    // return fellows with same firstname

    public List<Fellowship> findByFirstname(FellowshipModel fellowModel) {
        Optional<List<Fellowship>> fellows = fellowshipRepository.findByFirstname(fellowModel.getFirstname());
        if(fellows.isEmpty())
            new MyMessageResponse("Fellowship not found with Firstname: "+fellowModel.getFirstname(), MessageTypes.WARN);

        return fellows.orElse(new ArrayList<>());
    }

    // add fellow

    public ResponseEntity<MessageResponse> add( FellowshipModel fellowModel){
        if(!fellowshipRepository.existsByFirstnameAndLastname(fellowModel.getFirstname(), fellowModel.getLastname())) {
            fellowshipRepository.save(fellowModel.translateModelToFellowship());
            return ResponseEntity.ok(new MyMessageResponse("New PLayer Added", MessageTypes.INFO));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MyMessageResponse("Error: Fellowship already exists", MessageTypes.WARN));
        }
    }

    // edit/update fellow

    public ResponseEntity<MessageResponse> update(Long id, Fellowship fellow){
        if(fellowshipRepository.existsById(id)) {
            fellowshipRepository.save(fellow);
            return ResponseEntity.ok(new MyMessageResponse("Fellowship record updated", MessageTypes.INFO));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MyMessageResponse("Error: Fellowship with Id: [" + id + "] -> does not exist - cannot update record", MessageTypes.WARN));
        }

    }

    // delete fellow

    public List<Fellowship>  delete(Fellowship fellow){
        Long id = fellow.getId();
        if(fellowshipRepository.existsById(id)) {
            fellowshipRepository.deleteById(id);
             ResponseEntity.ok(new MyMessageResponse("Fellowship deleted with id: " + id, MessageTypes.INFO));
        } else {
             ResponseEntity.status(HttpStatus.CONFLICT).body(new MyMessageResponse("Error: Cannot delete fellow with id: " + id, MessageTypes.WARN));
        }
        return list();

    }


    public Fellowship getByEmail(String email) {
        Optional<Fellowship> fellow = fellowshipRepository.findByEmail(email);
        if(fellow.isEmpty())
            new MyMessageResponse("Fellowship not found with id: " + email , MessageTypes.WARN);

        return fellow.orElse(new Fellowship());
    }
}
