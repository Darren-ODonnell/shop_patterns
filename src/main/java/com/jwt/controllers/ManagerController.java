package com.jwt.controllers;


import com.jwt.models.Manager;
import com.jwt.models.ManagerModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Darren O'Donnell
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping({"/manager","/managers"})
public class ManagerController {
    public final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    // return all managers - done

    @GetMapping(value={"/", "/list"} )
    @PreAuthorize("hasRole('ROLE_PLAYER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Manager> list() {
        return managerService.list();
    }

    // return manager by id

    @GetMapping(value="/findByEmail")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Manager findByEmail(@RequestParam("email") String email){
        return managerService.findByEmail(email);
    }

    // return manager by Email

    @GetMapping(value="/findById")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Manager findById(@RequestParam("id") Long id){
        return managerService.findById(id);
    }

    // return manager by firstname + lastname

    @GetMapping(value="/findByFirstnameLastname")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody Manager findByFirstnameLastname(@ModelAttribute ManagerModel managerModel ) {
        return managerService.findByFirstnameLastname(managerModel);
    }

    // return managers with same lastname

    @GetMapping(value="/findByLastname")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Manager> findByLastname(@ModelAttribute ManagerModel managerModel) {
        return managerService.findByLastname(managerModel);
    }

    // return managers with same firstname

    @GetMapping(value="/findByFirstname")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Manager> findByFirstname(@ModelAttribute ManagerModel managerModel) {
        return managerService.findByFirstname(managerModel);
    }

    // add manager

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public ResponseEntity<MessageResponse> add( @ModelAttribute ManagerModel managerModel){
        return managerService.add( managerModel);
    }

    // update manager

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public ResponseEntity<MessageResponse> update( @ModelAttribute Manager manager){
        return managerService.update(manager.getId(), manager);
    }


    // delete manager

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public @ResponseBody List<Manager>  delete(@ModelAttribute Manager manager){
        return managerService.delete(manager);
    }
}
