package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.EventName;
import com.jwt.models.EventNameModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.EventNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventNameService {

    EventNameRepository eventNameRepository;

    @Autowired
    public EventNameService(EventNameRepository eventNameRepository) {
        this.eventNameRepository = eventNameRepository;
    }

    // return all EventNames

    public List<EventName> list(){
        List<EventName> eventNames = eventNameRepository.findAll();
        if(eventNames.isEmpty()) new MyMessageResponse("Error: No EventNames listed", MessageTypes.WARN);
        return eventNames;
    }

    // return EventName by id

    public EventName findById( Long id){
        Optional<EventName> eventName = eventNameRepository.findById(id);
        if(eventName.isEmpty())
            new MyMessageResponse(String.format("EventName id: %d not found", id), MessageTypes.ERROR);
        return eventName.orElse(new EventName());
    }

    // return EventName by name

    public EventName findByName( EventNameModel eventNameModel) {
        Optional<EventName> eventName = eventNameRepository.findByName(eventNameModel.getName());
        if(eventName.isEmpty()) new MyMessageResponse(String.format("EventName name: %s not found", eventNameModel.getName()), MessageTypes.INFO);
        return eventName.orElse(new EventName());
    }

    // return EventName by  abbrev

    public EventName findByAbbrev( EventNameModel eventNameModel) {
        Optional<EventName> eventName = eventNameRepository.findByAbbrev(eventNameModel.getAbbrev());
        if(eventName.isEmpty()) new MyMessageResponse(String.format("EventName Abbrev: %s not found", eventNameModel.getAbbrev()), MessageTypes.INFO);
        return eventName.orElse(new EventName());
    }
    // add new EventName

    public ResponseEntity<MessageResponse> add(EventNameModel eventNameModel){

        if(eventNameRepository.existsByName(eventNameModel.getName()))
            return ResponseEntity.ok(new MyMessageResponse("Error: EventName already exists", MessageTypes.WARN));

        eventNameRepository.save(eventNameModel.translateModelToEventName());
        return ResponseEntity.ok(new MyMessageResponse("new EventName added", MessageTypes.INFO));
    }

    // delete by id

    public ResponseEntity<MessageResponse> deleteById( Long id){

        if(!eventNameRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete EventName with id: "+id, MessageTypes.WARN));

        eventNameRepository.deleteById(id);
        return ResponseEntity.ok(new MyMessageResponse("EventName deleted with id: " + id, MessageTypes.INFO));
    }

    // edit/update a EventName record - only if record with id exists

    public ResponseEntity<MessageResponse> update(Long id, EventNameModel eventNameModel){

        // check if exists first
        // then update

        if(!eventNameRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Id does not exist ["+id+"] -> cannot update record", MessageTypes.WARN));

        eventNameRepository.save(eventNameModel.translateModelToEventName(id));
        return ResponseEntity.ok(new MyMessageResponse("EventName record updated", MessageTypes.INFO));
    }


}
