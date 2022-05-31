package com.jwt.controllers;

import com.jwt.models.Competition;
import com.jwt.models.EventName;
import com.jwt.models.EventNameModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.EventNameService;
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
@RequestMapping({"/eventname","/eventnames"})
public class EventNameController {
    public final EventNameService eventNameService;

    @Autowired
    public EventNameController(EventNameService eventNameService) {
        this.eventNameService = eventNameService;
    }

    // return all EventNames

    @GetMapping(value={"/","/list",""} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<EventName> list(){

        return eventNameService.list();
    }

    // return EventName by id

    @GetMapping(value="/findById")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody EventName findById(@RequestParam("id")  Long id){
        return eventNameService.findById(id);
    }

    // return EventName by name

    @GetMapping(value="/findByName")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody  EventName findByName(@ModelAttribute EventNameModel eventNameModel) {
        return eventNameService.findByName(eventNameModel);
    }
    // return EventName by abbrev

    @GetMapping(value="/findByAbbrev")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody  EventName findByAbbrev(@ModelAttribute EventNameModel eventNameModel) {
        return eventNameService.findByAbbrev(eventNameModel);
    }

    // add new EventName

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse>  add(@ModelAttribute EventNameModel eventNameModel){
        return eventNameService.add(eventNameModel);
    }

    // edit/update a EventName record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> update(@RequestBody EventName eventName) {
        return eventNameService.update(eventName.getId(), eventName);
    }



    // delete by id

    @DeleteMapping(value="/deleteById")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> deleteById(@RequestBody EventName eventName){
        return eventNameService.deleteById(eventName.getId());
    }
}
