package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.PitchGrid;
import com.jwt.models.PitchGridModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.PitchGridRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PitchGridService {

    PitchGridRepository pitchGridRepository;

    @Autowired
    public PitchGridService(PitchGridRepository pitchGridRepository) {
        this.pitchGridRepository = pitchGridRepository;
    }

    // return all PitchGrids

    public List<PitchGrid> list(){
        List<PitchGrid> pitchGrids = pitchGridRepository.findAll();
        if(pitchGrids.isEmpty()) new MyMessageResponse("Error: No PitchGrids listed", MessageTypes.WARN);
        return pitchGrids;
    }

    // return PitchGrid by id

    public PitchGrid findById(String id){
        Optional<PitchGrid> pitchGrid = pitchGridRepository.findById(id);
        if(pitchGrid.isEmpty())
            new MyMessageResponse(String.format("PitchGrid id: %s not found", id), MessageTypes.ERROR);
        return pitchGrid.orElse(new PitchGrid());
    }

    // return PitchGrid by name

    public PitchGrid findByName( PitchGridModel pitchGridModel) {
        Optional<PitchGrid> pitchGrid = pitchGridRepository.findByName(pitchGridModel.getName());
        if(pitchGrid.isEmpty()) new MyMessageResponse(String.format("PitchGrid name: %s not found", pitchGridModel.getName()), MessageTypes.INFO);
        return pitchGrid.orElse(new PitchGrid());
    }




    // add new PitchGrid

    public ResponseEntity<MessageResponse> add(PitchGridModel pitchGridModel){
        if(!pitchGridRepository.existsById(pitchGridModel.getName())) {
            pitchGridRepository.save(pitchGridModel.translateModelToPitchGrid());
            return ResponseEntity.ok(new MyMessageResponse("new PitchGrid added", MessageTypes.INFO));
        } else {
            return ResponseEntity.ok(new MyMessageResponse("Error: PitchGrid already exists", MessageTypes.WARN));
        }
    }

    // delete by name

    public ResponseEntity<MessageResponse> delete( PitchGrid pitchGrid){
        String id = pitchGrid.getId();
        if(pitchGridRepository.existsById(id)) {
            pitchGridRepository.deleteById(id);
            return ResponseEntity.ok(new MyMessageResponse("PitchGrid deleted with id: " + id, MessageTypes.INFO));
        } else {
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete PitchGrid with abbrev: " + id, MessageTypes.WARN));
        }
    }

    // edit/update a PitchGrid record - only if record with id exists

    public ResponseEntity<MessageResponse> update(String id, PitchGrid pitchGrid){

        // check if exists first
        // then update

        if(pitchGridRepository.existsById(id)) {
            pitchGridRepository.save(pitchGrid);
            return ResponseEntity.ok(new MyMessageResponse("PitchGrid record updated", MessageTypes.INFO));
        } else {
            return ResponseEntity.ok(new MyMessageResponse("Error: Id does not exist [" + id + "] -> cannot update record", MessageTypes.WARN));
        }
    }
}
