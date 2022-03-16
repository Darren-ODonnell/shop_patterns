package com.jwt.controllers;

import com.jwt.models.Lastname;
import com.jwt.models.LastnameModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.LastnameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Darren O'Donnell
 */
@Controller
@RequestMapping("/lastname")
public class LastnameController {
    private static final Logger logger = LoggerFactory.getLogger(LastnameController.class);
    public final LastnameService lastnameService;

    @Autowired
    public LastnameController(LastnameService lastnameService) {
        this.lastnameService = lastnameService;
    }

    @PostMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody ResponseEntity<MessageResponse> add(@ModelAttribute LastnameModel lastnameModel){
        return lastnameService.add(lastnameModel);
    }

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<MessageResponse> update(@RequestParam Long id, @ModelAttribute LastnameModel lastnameModel){
        return lastnameService.update(id, lastnameModel);
    }

    // return all lastnames

    @GetMapping(value={"/","/list"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Lastname> list(){
        return lastnameService.list();
    }

    // return Lastname by id

    @GetMapping(value="/findById/")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Optional<Lastname> findById(@RequestParam("id") Long id){
        return lastnameService.findById(id);
    }

    // return irish lastname given the english lastname

    @GetMapping(value={"/findIrish/","/findByLastname"})
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Lastname> findIrishLastname(@ModelAttribute LastnameModel lastnameModel) {
        return lastnameService.findByIrishLastname(lastnameModel);
    }

    // return english lastname given the irish lastname

    @GetMapping(value="/findEnglish/")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Lastname> findEnglishLastname(@ModelAttribute LastnameModel lastnameModel) {
        return lastnameService.findByEnglishLastname(lastnameModel);
    }

    // delete lastname

    @DeleteMapping(value="/deleteById/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> deleteById(@RequestParam("id") Long id){
        return lastnameService.deleteById(id);

    }
}
