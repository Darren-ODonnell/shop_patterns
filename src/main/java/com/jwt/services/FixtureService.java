package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.*;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.ClubRepository;
import com.jwt.repositories.CompetitionRepository;
import com.jwt.repositories.FixtureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class FixtureService {

    private final FixtureRepository fixtureRepository;
    private final ClubRepository clubRepository;
    private final CompetitionRepository competitionRepository;

    @Autowired
    public FixtureService(FixtureRepository fixtureRepository, ClubRepository clubRepository, CompetitionRepository competitionRepository) {
        this.fixtureRepository = fixtureRepository;
        this.clubRepository = clubRepository;
        this.competitionRepository = competitionRepository;
    }

    // return all fixtures

    public List<Fixture> findAll() {  return fixtureRepository.findAll();    }

    // return all fixtures

    public List<Fixture> findUpcoming() {
        // the upcoming fixtures will pick up all fixtures after todays date
        // but they should include todays fixture also
        // to ensure we get todays fixture the date after is todays date - 1 day
        long day = 1000 * 60 * 60 * 24;
        Date date = new Date(System.currentTimeMillis() - day);
        return fixtureRepository.findByFixtureDateAfterOrderByFixtureDate(date).orElse(new ArrayList<>());
    }

    // Return all fixture for a given club

    public List<Fixture> getClubFixtures(ClubModel clubModel)  {
        List<Fixture> fixtures = getClubHomeFixtures(clubModel);
        fixtures.addAll(getClubAwayFixtures(clubModel.getName()));
        return fixtures;
    }

    // return all the home fixtures form a club

    public List<Fixture> getClubHomeFixtures(ClubModel clubModel)  {
        Long id = getClubId(clubModel.getName());

        if(id == null) {
            new MyMessageResponse("No Home fixtures found for Club: " + clubModel.getName(), MessageTypes.WARN);
            return new ArrayList<>();
        }
        Optional<List<Fixture>> fixtures = fixtureRepository.findByHomeTeamId(id);
        return fixtures.orElse(new ArrayList<>());
    }

    // return all the away fixtures for a club

    public List<Fixture> getClubAwayFixtures(String name)  {
        Long id = getClubId(name);

        if(id == null) {
            new MyMessageResponse("No Away fixtures found for Club: " + name, MessageTypes.WARN);
            return new ArrayList<>();
        }

        Optional<List<Fixture>> fixtures = fixtureRepository.findByAwayTeamId(id);
        return fixtures.orElse(new ArrayList<>());
    }

    // find next fixture for club

    public Fixture getNextClubFixture(ClubModel clubModel) {
        Long clubId = getClubId(clubModel.getName());
        Date today = new Date(Calendar.getInstance().getTime().getTime());

        if(clubId == null) {
            new MyMessageResponse("No Next fixture found with club id: " + clubId + " and date: " + today, MessageTypes.WARN);
            return new Fixture();
        }

        // using todays date and home and away team IDs collect all the fixtures for 'name' greater than todays date - sorted by fixture date
        // return the first fixture in this list - this will be the next fixture.

        Optional<Fixture> fixture = fixtureRepository.findFirstByAwayTeamIdOrHomeTeamIdAndFixtureDateGreaterThanOrderByFixtureDate(clubId, clubId, today);
        if(fixture.isEmpty())
            new MyMessageResponse("No Fixture Found with clubid: "+ clubId + ", and date: "+today,MessageTypes.WARN);

        return fixture.orElse(new Fixture());
    }

    // return fixture by id

    public Fixture findById(Long id) {
        Optional<Fixture> fixture = fixtureRepository.findById(id);
        if(fixture.isEmpty())
            new MyMessageResponse("Fixture Does not exist",MessageTypes.WARN);

        return fixture.orElse(new Fixture());
    }

    // Return fixture by Competition, HomeTeam, AwayTeam, FixtureDate and Season

    public Fixture findByCompetitionHomeTeamAwayTeamFixtureDateSeason(FixtureModel fixtureModel) {

        Optional<Fixture> fixture = fixtureRepository.findByCompetitionIdAndHomeTeamIdAndAwayTeamIdAndFixtureDateAndSeason(
                fixtureModel.getCompetitionId(),
                fixtureModel.getHomeTeamId(),
                fixtureModel.getAwayTeamId(),
                fixtureModel.getFixtureDate(),
                fixtureModel.getSeason()
        );
        if(fixture.isEmpty())
            new MyMessageResponse("Error: Fixture does not exist", MessageTypes.WARN);

        return fixture.orElse(new Fixture());
    }

    // Add Fixture

    public ResponseEntity<MessageResponse> add(FixtureModel fixtureModel){

        Club homeTeam = clubRepository.getById(fixtureModel.getHomeTeamId());
        Club awayTeam = clubRepository.getById(fixtureModel.getAwayTeamId());
        Competition competition = competitionRepository.getById(fixtureModel.getCompetitionId());

        if(fixtureRepository.existsByHomeTeamAndAwayTeamAndCompetitionAndSeason(homeTeam, awayTeam, competition, fixtureModel.getSeason()))
            return ResponseEntity.ok(new MyMessageResponse("Error: Fixture already exists", MessageTypes.WARN));

        fixtureRepository.save(fixtureModel.translateModelToFixture(competitionRepository, clubRepository));
        return ResponseEntity.ok(new MyMessageResponse("new Fixture added", MessageTypes.INFO));
    }

    // edit/update Fixture

    public ResponseEntity<MessageResponse> update(Long id, Fixture fixture){
        if(!fixtureRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Fixture with Id: ["+id+"] -> does not exist - cannot update record", MessageTypes.WARN));

        fixtureRepository.save(fixture);
        return ResponseEntity.ok(new MyMessageResponse("Fixture record updated", MessageTypes.INFO));
    }

    // delete fixture

    public ResponseEntity<MessageResponse> delete(Fixture fixture) {
        Long id = fixture.getId();

        if(!fixtureRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete fixture with id: "+id, MessageTypes.WARN));

        fixtureRepository.deleteById(id);
        return ResponseEntity.ok(new MyMessageResponse("Fixture deleted with id: " + id, MessageTypes.INFO));
    }

    // get clubid from name

    private Long getClubId(String name) {
        Long id = null;

        Optional<Club> club = clubRepository.findByName(name);
        if(club.isEmpty())
            new MyMessageResponse("Club name does not exists: " + name, MessageTypes.WARN);
        else
            id = club.get().getId();
        return id;
    }
}
