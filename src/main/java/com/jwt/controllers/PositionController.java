package com.jwt.controllers;

import com.jwt.models.Position;
import com.jwt.models.PositionModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.PositionService;
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
@RequestMapping({"/position","/positions"})
public class PositionController {
    public final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    // return all Positions

    @GetMapping(value={"/","/list"} )
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody List<Position> list(){
        return positionService.list();
    }

    // return Position by id

    @GetMapping(value="/findById")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody Position findById(@RequestParam("id")  Long id){
        return positionService.findById(id);
    }

    // return Position by name

    @GetMapping(value="/findByName")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody  Position findByName(@ModelAttribute PositionModel positionModel) {
        return positionService.findByName(positionModel);
    }

    // return Position by abbrev

    @GetMapping(value="/findByAbbrev")
    @PreAuthorize("hasRole('ROLE_PLAYER')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public @ResponseBody  Position findByAbbrev(@ModelAttribute PositionModel positionModel) {
        return positionService.findByAbbrev(positionModel);
    }
    // add new Position

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public ResponseEntity<MessageResponse> add(@RequestBody PositionModel positionModel){
        return positionService.add(positionModel);
    }

    // edit/update a Position record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public ResponseEntity<MessageResponse> update(@RequestBody PositionModel positionModel) {
        return positionService.update( positionModel);
    }

    // delete by id

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')") 
    public @ResponseBody List<Position> delete(@RequestBody Position position){
        return positionService.delete(position);
    }
}
