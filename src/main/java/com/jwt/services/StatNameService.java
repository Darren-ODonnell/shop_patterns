package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.StatName;
import com.jwt.models.StatNameModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.StatNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatNameService {

    StatNameRepository statNameRepository;

    @Autowired
    public StatNameService(StatNameRepository statNameRepository) {
        this.statNameRepository = statNameRepository;
    }

    // return all StatNames

    public List<StatName> list(){
        List<StatName> statNames = statNameRepository.findAll();
        if(statNames.isEmpty()) new MyMessageResponse("Error: No StatNames listed", MessageTypes.WARN);
        return statNames;
    }

    // return StatName by id

    public StatName findById(String id){
        Optional<StatName> statName = statNameRepository.findById(id);
        if(statName.isEmpty())
            new MyMessageResponse(String.format("StatName id: %s not found", id), MessageTypes.ERROR);
        return statName.orElse(new StatName());
    }

    // return StatName by name

    public StatName findByName( StatNameModel statNameModel) {
        Optional<StatName> statName = statNameRepository.findByName(statNameModel.getName());
        if(statName.isEmpty()) new MyMessageResponse(String.format("StatName name: %s not found", statNameModel.getName()), MessageTypes.INFO);
        return statName.orElse(new StatName());
    }




    // add new StatName

    public ResponseEntity<MessageResponse> add(StatNameModel statNameModel){

        if(statNameRepository.existsById(statNameModel.getAbbrev()))
            return ResponseEntity.ok(new MyMessageResponse("Error: StatName already exists", MessageTypes.WARN));

        statNameRepository.save(statNameModel.translateModelToStatName(statNameModel.getAbbrev()));
        return ResponseEntity.ok(new MyMessageResponse("new StatName added", MessageTypes.INFO));
    }

    // delete by name

    public ResponseEntity<MessageResponse> delete( StatName statName){
        String id = statName.getId();
        if(!statNameRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete StatName with abbrev: "+id, MessageTypes.WARN));

        statNameRepository.deleteById(id);
        return ResponseEntity.ok(new MyMessageResponse("StatName deleted with id: " + id, MessageTypes.INFO));
    }

    // edit/update a StatName record - only if record with id exists

    public ResponseEntity<MessageResponse> update(String id, StatName statName){

        // check if exists first
        // then update

        if(!statNameRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Id does not exist ["+id+"] -> cannot update record", MessageTypes.WARN));

        statNameRepository.save(statName);
        return ResponseEntity.ok(new MyMessageResponse("StatName record updated", MessageTypes.INFO));
    }


}
