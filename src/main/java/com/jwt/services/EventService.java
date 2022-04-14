package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.Event;
import com.jwt.models.EventModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // return all Events

    public List<Event> list(){
        List<Event> events = eventRepository.findAll();
        if(events.isEmpty()) new MyMessageResponse("Error: No Events listed", MessageTypes.WARN);
        return events;
    }

    // return Event by id

    public Event findById(Long id){
        Optional<Event> event = eventRepository.findById(id);
        if(event.isEmpty())
            new MyMessageResponse(String.format("Event id: %d not found", id), MessageTypes.ERROR);
        return event.orElse(new Event());
    }


    // add new Event

    public ResponseEntity<MessageResponse> add(EventModel eventModel){

        eventRepository.save(eventModel.translateModelToEvent());
        return ResponseEntity.ok(new MyMessageResponse("new Event added", MessageTypes.INFO));
    }






    // delete by id

    public ResponseEntity<MessageResponse> deleteById( Long id){

        if(!eventRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete Event with id: "+id, MessageTypes.WARN));

        eventRepository.deleteById(id);
        return ResponseEntity.ok(new MyMessageResponse("Event deleted with id: " + id, MessageTypes.INFO));
    }

    // edit/update a Event record - only if record with id exists

    public ResponseEntity<MessageResponse> update(Long id, EventModel eventModel){

        // check if exists first
        // then update

        if(!eventRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Id does not exist ["+id+"] -> cannot update record", MessageTypes.WARN));

        eventRepository.save(eventModel.translateModelToEvent(id));
        return ResponseEntity.ok(new MyMessageResponse("Event record updated", MessageTypes.INFO));
    }


}
