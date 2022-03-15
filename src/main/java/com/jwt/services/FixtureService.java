package com.jwt.services;

import com.jwt.models.*;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.ClubRepository;
import com.jwt.repositories.CompetitionRepository;
import com.jwt.repositories.FixtureRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class FixtureService {
    private static final Logger logger = LoggerFactory.getLogger(FixtureService.class);
    private final FixtureRepository fixtureRepository;
    private final ClubRepository clubRepository;
    private final CompetitionRepository competitionRepository;

    @Autowired
    public FixtureService(FixtureRepository fixtureRepository, ClubRepository clubRepository, CompetitionRepository competitionRepository) {
        this.fixtureRepository = fixtureRepository;
        this.clubRepository = clubRepository;
        this.competitionRepository = competitionRepository;
    }

    public List<Fixture> getClubFixtures(String name) throws NotFoundException {
        List<Fixture> fixtures = getClubHomeFixtures(name);
        fixtures.addAll(getClubAwayFixtures(name));
        return fixtures;
    }

    public List<Fixture> getClubHomeFixtures(String name) throws NotFoundException {
        Long id = clubRepository.findByName(name).getId();
        List<Fixture> fixtures;
        if(fixtureRepository.existsByHomeTeamId(id))
            fixtures = fixtureRepository.findByHomeTeamId(id);
        else
            throw new NotFoundException("No Home fixtures found for Club: " + name);

        return fixtures;
    }
    public List<Fixture> getClubAwayFixtures(String name) throws NotFoundException {
        Long id = clubRepository.findByName(name).getId();
        List<Fixture> fixtures;
        if(fixtureRepository.existsByAwayTeamId(id))
            fixtures = fixtureRepository.findByAwayTeamId(id);
        else
            throw new NotFoundException("No Away fixtures found for Club: "+name);

        return fixtures;
    }


    public Fixture getNextClubFixture(String name) throws NotFoundException {
        Long id  = clubRepository.findByName(name).getId();
        Date today = new Date(Calendar.getInstance().getTime().getTime());
        Fixture fixture;
        // using todays date and home and away team IDs collect all the fixtures for 'name' greater than todays date - sorted by fixture date
        // return the first fixture in this list - this will be the next fixture.
        if(fixtureRepository.existsFirstByAwayTeamIdOrHomeTeamIdAndFixtureDateGreaterThanOrderByFixtureDate(id, id, today))
            fixture = fixtureRepository.findFirstByAwayTeamIdOrHomeTeamIdAndFixtureDateGreaterThanOrderByFixtureDate(id, id, today);
        else
            throw new NotFoundException("No Next fixture found with id1: "+id+" and date: " +today);

        return fixture;
    }

    public List<Fixture> findAll() {
        return fixtureRepository.findAll();
    }

    public ResponseEntity<MessageResponse> deleteById(Long id) {
        logger.info("delete Fixture by id = "+id);
        if(fixtureRepository.existsById(id))
            fixtureRepository.deleteById(id);
        else
            return ResponseEntity.ok(new MessageResponse("Error: Cannot delete fixture with id: "+id));

        return ResponseEntity.ok(new MessageResponse("Fixture deleted with id: " + id));
    }

    // Add Fixture

    public ResponseEntity<MessageResponse> add(FixtureModel fixtureModel){

        Club homeTeam = clubRepository.getById(fixtureModel.getHomeTeam());
        Club awayTeam = clubRepository.getById(fixtureModel.getAwayTeam());
        Competition competition = competitionRepository.getById(fixtureModel.getCompetitionId());

        if(fixtureRepository.existsByHomeTeamAndAwayTeamAndCompetitionAndSeason(homeTeam, awayTeam, competition, fixtureModel.getSeason()))
            return ResponseEntity.ok(new MessageResponse("Error: Fixture already exists"));
        else {
            Fixture fixture = fixtureModel.translateModelToFixture(competitionRepository, clubRepository);
            fixtureRepository.save(fixture);
        }

        return ResponseEntity.ok(new MessageResponse("new Fixture added"));
    }

    public Fixture findByCompetitionHomeTeamAwayTeamDateSeason(FixtureModel fixtureModel) {
        Competition competition = competitionRepository.getById(fixtureModel.getCompetitionId());
        Club homeTeam = clubRepository.getById(fixtureModel.getHomeTeam());
        Club awayTeam = clubRepository.getById(fixtureModel.getAwayTeam());

        if(fixtureRepository.existsByCompetitionAndHomeTeamAndAwayTeam(competition, homeTeam, awayTeam)) {

            return fixtureRepository.findByCompetitionAndHomeTeamAndAwayTeam(competition, homeTeam, awayTeam);
        }

        return new Fixture();
    }

    public Fixture findById(Long id) {
        if(fixtureRepository.findById(id).isPresent())
            return fixtureRepository.findById(id).get();
        return new Fixture();
    }

    public Fixture findByCompetitionHomeTeamAwayTeamFixtureDateSeason(FixtureModel fixtureModel) {

        return fixtureRepository.findByCompetitionIdAndHomeTeamIdAndAwayTeamIdAndFixtureDateAndSeason(
                fixtureModel.getCompetitionId(),
                fixtureModel.getHomeTeam(),
                fixtureModel.getAwayTeam(),
                fixtureModel.getFixtureDate(),
                fixtureModel.getSeason()
        );
    }

    // edit/update Fixture

    public ResponseEntity<MessageResponse> update(Long id, FixtureModel fixtureModel){
        if(fixtureRepository.existsById(id)) {
            Fixture fixture = fixtureModel.translateModelToFixture(competitionRepository, clubRepository);
            fixture.setId(id);
            fixtureRepository.save(fixture);

        } else
            return ResponseEntity.ok(new MessageResponse("Error: Fixture with Id: ["+id+"] -> does not exist - cannot update record"));

        return ResponseEntity.ok(new MessageResponse("Fixture record updated"));

    }


}
