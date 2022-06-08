package com.jwt.controllers;

import com.jwt.models.Competition;
import com.jwt.models.Firstname;
import com.jwt.models.FirstnameModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.FirstnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Darren O'Donnell
 */
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping({"/firstname","/firstnames"})
public class FirstnameController {
    public final FirstnameService firstnameService;

    @Autowired
    public FirstnameController(FirstnameService firstnameService) {
        this.firstnameService = firstnameService;
    }

    // return all Firstnames - done

    @GetMapping(value={"/","/list",""} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody
    List<Firstname> list(){
        return firstnameService.list();
    }

    // return Firstname by id

    @GetMapping(value="/findById")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Firstname findById(@RequestParam("id") Long id){

        return firstnameService.findById( id);
    }

    // return Firstname by firstname

    @GetMapping(value="/findByFirstname")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public @ResponseBody Firstname findByFirstname(@ModelAttribute FirstnameModel firstnameModel) {
        return firstnameService.findByFirstname(firstnameModel);
    }

    // return irish firstname given the english firstname

    @GetMapping(value="/findIrish")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Firstname findIrishFirstname(@ModelAttribute FirstnameModel firstnameModel) {
        return firstnameService.findIrishFirstname( firstnameModel);
    }

    // return english lastname given the irish lastname

    @GetMapping(value="/findEnglish")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Firstname> findEnglishFirstname(@ModelAttribute FirstnameModel firstnameModel) {
        return firstnameService.findEnglishFirstname( firstnameModel);
    }

    // add new firstname

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> add( @RequestBody FirstnameModel firstnameModel){
        return firstnameService.add( firstnameModel);
    }

    // edit/update a firstname record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> update(@RequestBody Firstname firstname){
        return firstnameService.update(firstname.getId(), firstname);
    }

    // delete by id

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> delete(@RequestBody Firstname firstname){
        return firstnameService.delete(firstname);
    }
}
