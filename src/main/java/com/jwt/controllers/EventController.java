package com.jwt.controllers;

import com.jwt.models.Competition;
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
    public ResponseEntity<MessageResponse> add(@RequestBody EventModel eventModel){
        return eventService.add(eventModel);
    }

    // edit/update a Event record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> update(@RequestBody Event event) {
        return eventService.update( event.getId(), event);
    }


    // delete by id

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> delete(@RequestBody Event event){
        return eventService.delete(event);
    }

}
