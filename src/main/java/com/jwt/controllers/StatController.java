package com.jwt.controllers;

import com.jwt.models.Stat;
import com.jwt.models.StatId;
import com.jwt.models.StatModel;
import com.jwt.payload.response.MessageResponse;
import com.jwt.services.StatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping({"/stat","/stats",""})
public class StatController {

    public final StatService statService;

    @Autowired
    public StatController(StatService statService) {
        this.statService = statService;
    }

    // return all Stats

    @GetMapping(value={"/","/list",""} )
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Stat> list(){
        return statService.list();
    }

    // return Club by id

    @GetMapping(value="/findById")
    @PreAuthorize("hasRole('ROLE_USER')  or hasRole('ROLE_ADMIN')")
    public @ResponseBody Stat findById(@RequestParam("id") StatId id){
        return statService.findById(id);
    }

    // return Club by name

    @GetMapping(value="/findByName")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody  Stat findByName(@ModelAttribute StatModel statModel) {
        return statService.findByName(statModel);
    }

    // add new Club

    @PutMapping(value="/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> add(@RequestBody StatModel statModel){
        return statService.add(statModel);
    }

    // edit/update a Club record - only if record with id exists

    @PostMapping(value="/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> update(@RequestBody Stat stat) {
        return statService.update( stat.getId(), stat );
    }


    // delete by id

    @DeleteMapping(value="/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> delete(@RequestBody Stat stat){
        return statService.delete(stat);
    }
}
