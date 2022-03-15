package com.jwt.services;

import com.jwt.exceptions.NotFoundException;
import com.jwt.models.Firstname;
import com.jwt.models.FirstnameModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.FirstnameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FirstnameService {
    private static final Logger logger = LoggerFactory.getLogger(FirstnameService.class);
    private final FirstnameRepository firstnameRepository;


    @Autowired
    public FirstnameService(FirstnameRepository firstnameRepository) {
        this.firstnameRepository = firstnameRepository;

    }

    public  List<Firstname> list(){
        return firstnameRepository.findAll();
    }

    // delete by id

    public ResponseEntity<MessageResponse> deleteById(@RequestParam("id") Long id){

        logger.info("delete Firstname by id = "+id);
        if(firstnameRepository.existsById(id))
            firstnameRepository.deleteById(id);
        else
            return ResponseEntity.ok(new MessageResponse("Error: Cannot delete firstname with id: "+id));

        return ResponseEntity.ok(new MessageResponse("Firstname deleted with id: " + id));
    }

    // return Firstname by id

    public Optional<Firstname> findById( @RequestParam("id") Long id){
        logger.info("find Firstname by id = "+id);
//
//        Optional<Firstname> firstname = firstnameRepository.findById(id);
//
//        if (firstname=null)
//            throw new NotFoundException(String.format("Club Id : %d not found", id));
//
        return firstnameRepository.findById(id);
    }


    // return Firstname by firstname

    public  Firstname findByFirstname( @ModelAttribute FirstnameModel firstnameModel) {
        logger.info("find Firstname by firstname = "+firstnameModel.getFirstname());

        Firstname fn = firstnameRepository.findByFirstname(firstnameModel.getFirstname());

        if(fn==null)
            throw  new NotFoundException(String.format("Firstname : %s not found", firstnameModel.getFirstname()));

        return fn;

    }

    // return irish firstname given the english firstname

    public  String findIrishFirstname(@ModelAttribute FirstnameModel firstnameModel) {
        Firstname fname = firstnameRepository.findByFirstname(firstnameModel.getFirstnameIrish());
        if(fname == null)
            throw new NotFoundException(String.format("English Firstname : %s not found", firstnameModel.getFirstnameIrish()));
        return fname.getFirstnameIrish();
    }

    // return english lastname(s) given the irish lastname

    public List<String> findEnglishFirstname( @ModelAttribute FirstnameModel firstnameModel) {

        List<String> names = new ArrayList<>();
        List<Firstname> fnames;

        if(firstnameRepository.existsByFirstnameIrish(firstnameModel.getFirstname())) {
            fnames = firstnameRepository.findByFirstnameIrish(firstnameModel.getFirstname());
            fnames.forEach(ln -> names.add(ln.getFirstname()));
        } else
            throw new NotFoundException(String.format("Irish Firstname : %s not found", firstnameModel.getFirstname()));

        return names;
    }




    // add new firstname

    public  ResponseEntity<MessageResponse> add(@ModelAttribute FirstnameModel firstnameModel){

        if(firstnameRepository.existsByFirstname(firstnameModel.getFirstname()))
            return ResponseEntity.ok(new MessageResponse("Error: Firstname already exists"));
        else
            firstnameRepository.save(firstnameModel.translateModelToFirstname());

        return ResponseEntity.ok(new MessageResponse("new Firstname added"));
    }

    // edit/update a firstname record - only if record with id exists

    public ResponseEntity<MessageResponse> update(Long id, FirstnameModel firstnameModel){
        // check if exists first
        // then update

        if(firstnameRepository.existsById(id)) {
            Firstname firstname = firstnameModel.translateModelToFirstname();
            firstname.setId(id);
            firstnameRepository.save(firstname);

        } else
            return ResponseEntity.ok(new MessageResponse("Error: Firstname with Id: ["+id+"] -> does not exist - cannot update record"));

        return ResponseEntity.ok(new MessageResponse("Firstname record updated"));
    }

}
