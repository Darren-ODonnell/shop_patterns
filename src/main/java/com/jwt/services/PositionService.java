package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.Position;
import com.jwt.models.PositionModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    // return all Positions

    public List<Position> list(){
        List<Position> positions = positionRepository.findAll();
        if(positions.isEmpty()) new MyMessageResponse("Error: No Positions listed", MessageTypes.WARN);
        return positions;
    }

    // return Position by id

    public Position findById( Long id){
        Optional<Position> position = positionRepository.findById(id);
        if(position.isEmpty())
            new MyMessageResponse(String.format("Position id: %d not found", id), MessageTypes.ERROR);
        return position.orElse(new Position());
    }

    // return Position by name

    public Position findByName( PositionModel positionModel) {
        Optional<Position> position = positionRepository.findByName(positionModel.getName());
        if(position.isEmpty()) new MyMessageResponse(String.format("Position name: %s not found", positionModel.getName()), MessageTypes.INFO);
        return position.orElse(new Position());
    }

    // return Position by abbrev

    public Position findByAbbrev(PositionModel positionModel) {
        Optional<Position> position = positionRepository.findByAbbrev(positionModel.getAbbrev());
        if(position.isEmpty()) new MyMessageResponse(String.format("Position abbrev: %s not found", positionModel.getAbbrev()), MessageTypes.INFO);
        return position.orElse(new Position());
    }

    // add new Position

    public ResponseEntity<MessageResponse> add(PositionModel positionModel){

        if(!positionRepository.existsByName(positionModel.getName())) {
            positionRepository.save(positionModel.translateModelToPosition());
            return ResponseEntity.ok(new MyMessageResponse("new Position added", MessageTypes.INFO));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MyMessageResponse("Error: Position already exists", MessageTypes.WARN));
        }
    }

    // delete by id

    public ResponseEntity<MessageResponse> delete( Position position){
        Long id = position.getId();
        if(positionRepository.existsById(id)) {
            positionRepository.deleteById(id);
            return ResponseEntity.ok(new MyMessageResponse("Position deleted with id: " + id, MessageTypes.INFO));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MyMessageResponse("Error: Cannot delete Position with id: " + id, MessageTypes.WARN));
        }

    }

    // edit/update a Position record - only if record with id exists

    public ResponseEntity<MessageResponse> update(Long id, Position position){

        // check if exists first
        // then update

        if(positionRepository.existsById(id)) {
            positionRepository.save(position);
            return ResponseEntity.ok(new MyMessageResponse("Position record updated", MessageTypes.INFO));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MyMessageResponse("Error: Id does not exist [" + id + "] -> cannot update record", MessageTypes.WARN));
        }

    }


}
