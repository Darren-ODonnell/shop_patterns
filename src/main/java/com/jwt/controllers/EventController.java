package com.jwt.controllers;

import com.jwt.models.Event;
import com.jwt.models.EventModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.EventService;
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
@RequestMapping({"/events","/event",""})
public class EventController {
    public final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // return all Events

    @GetMapping(value={"/","/list",""} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Event> list(){

        return eventService.list();
    }

    // return Event by id

    @GetMapping(value="/findById")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Event findById(@RequestParam("id")  Long id){
        return eventService.findById(id);
    }



    // add new Event

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> add(@ModelAttribute EventModel eventModel){
        return eventService.add(eventModel);
    }

    // edit/update a Event record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> update(@RequestParam("id") Long id,  @ModelAttribute EventModel eventModel) {
        return eventService.update( id, eventModel);
    }

    // delete by id

    @DeleteMapping(value="/deleteById")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> deleteById(@RequestParam("id") Long id){
        return eventService.deleteById(id);
    }
}
