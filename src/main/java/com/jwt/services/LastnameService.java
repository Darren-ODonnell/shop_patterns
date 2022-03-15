package com.jwt.services;

import com.jwt.exceptions.NotFoundException;
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
    private LastnameRepository lastnameRepository;


    @Autowired
    public LastnameService(LastnameRepository lastnameRepository) {
        this.lastnameRepository = lastnameRepository;
    }

    public ResponseEntity<MessageResponse> add(LastnameModel lastnameModel){
        if(lastnameRepository.existsByLastname(lastnameModel.getLastname()))
            return ResponseEntity.ok(new MessageResponse("Error: Lastname already exists"));
        else
            lastnameRepository.save(lastnameModel.translateModelToLastname());

        return ResponseEntity.ok(new MessageResponse("new Lastname added"));
    }

    // edit/update lastname

    public ResponseEntity<MessageResponse> update( Long id,  LastnameModel lastnameModel){

        if(lastnameRepository.existsById(id)) {
            Lastname lastname = lastnameModel.translateModelToLastname();
            lastname.setId(id);
            lastnameRepository.save(lastname);

        } else
            return ResponseEntity.ok(new MessageResponse("Error: Lastname with Id: ["+id+"] -> does not exist - cannot update record"));

        return ResponseEntity.ok(new MessageResponse("Lastname record updated"));

    }

    // return all lastnames

    public List<Lastname> list(){

        return lastnameRepository.findAll();
    }

    // return Lastname by id

    public Optional<Lastname> findById(Long id){

        return lastnameRepository.findById(id);
    }

    // return Lastname by lastname


    public List<Lastname> findByLastname(LastnameModel lastnameModel) {
        return lastnameRepository.findByLastname(lastnameModel.getLastname());
    }

    // return irish lastname given the english lastname

    public List<String> findIrishLastname(LastnameModel lastnameModel) {

        List<String> names = new ArrayList<>();
        List<Lastname> lnames = lastnameRepository.findByLastname(lastnameModel.getLastname());

        if (lnames.isEmpty()) throw new NotFoundException(String.format("Irish Firstname : %s not found", lastnameModel.getLastname()));
        lnames.forEach(ln -> names.add(ln.getLastnameIrish()));

        return names;
    }

    // return english lastname given the irish lastname


    public List<String> findEnglishLastname(LastnameModel lastnameModel) {

        List<String> names = new ArrayList<>();
        List<Lastname> lnames;

        if(lastnameRepository.existsByLastnameIrish(lastnameModel.getLastname())) {
            lnames = lastnameRepository.findByLastnameIrish(lastnameModel.getLastname());
            lnames.forEach(ln -> names.add(ln.getLastname()));
        } else
            throw new NotFoundException(String.format("Irish Firstname : %s not found", lastnameModel.getLastname()));

        return names;
    }

    // delete lastname

    public ResponseEntity<MessageResponse> deleteById( Long id){

        // check if records exists first
        // delete record

        if(lastnameRepository.existsById(id))
            lastnameRepository.deleteById(id);
        else
            return ResponseEntity.ok(new MessageResponse("Error: No Record exists with id: "+id));

        return ResponseEntity.ok(new MessageResponse("Lastname deleted with id: " + id));
    }


}
