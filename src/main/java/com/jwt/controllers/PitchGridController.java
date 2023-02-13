package com.jwt.controllers;

import com.jwt.models.PitchGrid;
import com.jwt.models.PitchGridModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.PitchGridService;
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
@RequestMapping({"/pitchgrid","/pitchgrids"})
public class PitchGridController {
    public final PitchGridService pitchGridService;

    @Autowired
    public PitchGridController(PitchGridService pitchGridService) {
        this.pitchGridService = pitchGridService;
    }

    // return all PitchGrids

    @GetMapping(value={"/","/list"} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<PitchGrid> list(){
        return pitchGridService.list();
    }

    // return PitchGrid by id

    @GetMapping(value="/findById")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody PitchGrid findById(@RequestParam("id")  String id){
        return pitchGridService.findById(id);
    }

    // return PitchGrid by name

    @GetMapping(value="/findByName")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody  PitchGrid findByName(@ModelAttribute PitchGridModel pitchGridModel) {
        return pitchGridService.findByName(pitchGridModel);
    }

    // add new PitchGrid

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> add(@RequestBody PitchGridModel pitchGridModel){
        return pitchGridService.add(pitchGridModel);
    }

    // edit/update a PitchGrid record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> update(@RequestBody PitchGrid pitchGrid) {
        return pitchGridService.update( pitchGrid.getId(), pitchGrid);
    }

    // delete by id

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> delete(@RequestBody PitchGrid pitchGrid){
        return pitchGridService.delete(pitchGrid);
    }
}
