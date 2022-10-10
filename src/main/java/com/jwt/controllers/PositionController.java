package com.jwt.controllers;

import com.jwt.models.Position;
import com.jwt.models.PositionModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.PositionService;
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
@RequestMapping({"/position","/positions",""})
public class PositionController {
    public final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    // return all Positions

    @GetMapping(value={"/","/list",""} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Position> list(){
        return positionService.list();
    }

    // return Position by id

    @GetMapping(value="/findById")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Position findById(@RequestParam("id")  Long id){
        return positionService.findById(id);
    }

    // return Position by name

    @GetMapping(value="/findByName")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody  Position findByName(@ModelAttribute PositionModel positionModel) {
        return positionService.findByName(positionModel);
    }

    // return Position by abbrev

    @GetMapping(value="/findByAbbrev")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody  Position findByAbbrev(@ModelAttribute PositionModel positionModel) {
        return positionService.findByAbbrev(positionModel);
    }
    // add new Position

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> add(@RequestBody PositionModel positionModel){
        return positionService.add(positionModel);
    }

    // edit/update a Position record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> update(@RequestBody Position position) {
        return positionService.update( position.getId(), position);
    }

    // delete by id

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> delete(@RequestBody Position position){
        return positionService.delete(position);
    }
}
