package com.jwt.services;

import com.jwt.enums.MessageTypes;
import com.jwt.exceptions.MyMessageResponse;
import com.jwt.models.*;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repositories.FixtureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class FixtureService {
    @Value("$(club.name)")
    String clubName;

    private final FixtureRepository fixtureRepository;

    private final ClubService clubService;
    private final StatService statService;
    private final CompetitionService competitionService;

    @Autowired
    public FixtureService(FixtureRepository fixtureRepository, ClubService clubService, CompetitionService competitionService, StatService statService) {
        this.fixtureRepository = fixtureRepository;
        this.clubService = clubService;
        this.statService = statService;
        this.competitionService = competitionService;
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


    // list fixtures by date

    public List<Fixture> findByFixtureDate(Date fixtureDate) {
        List<Fixture> fixtures = fixtureRepository.findByFixtureDate(fixtureDate).orElse(new ArrayList<>());

        if(fixtures.isEmpty()) {
            new MyMessageResponse("No Fixtures found for this date: " + fixtureDate, MessageTypes.WARN);
        }
        return fixtures;
    }

    public List<Fixture> findByHomeTeamAndAwayTeamOrHomeTeamAndAwayTeam(Club club, Club opposition) {
        return fixtureRepository.findByHomeTeamAndAwayTeamOrHomeTeamAndAwayTeam(club, opposition, opposition, club).orElse(new ArrayList<>());

    }

    // find fixtures by opposition id

    public List<Fixture> findByOppositionId(Long clubId) {
        Long id = getClubId(clubName);
        Club club = clubService.findById(id);
        Club opposition = clubService.findById(clubId);

        List<Fixture> fixtures = findByHomeTeamAndAwayTeamOrHomeTeamAndAwayTeam(club, opposition);

        if(fixtures.isEmpty()) {
            new MyMessageResponse("No Fixtures found for this clubId: " + clubId, MessageTypes.WARN);
        }
        return fixtures;
    }

    // list fixtures by date

    public Fixture getById(Long fixtureId) {
        Fixture fixture = fixtureRepository.findById(fixtureId).orElse(new Fixture());

        if(fixture.getId()==null) {
            new MyMessageResponse("No Fixtures found for this Id: " + fixtureId, MessageTypes.WARN);
        }
        return fixture;
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

        Club homeTeam = clubService.findById(fixtureModel.getHomeTeamId());
        Club awayTeam = clubService.findById(fixtureModel.getAwayTeamId());
        Competition competition = competitionService.findById(fixtureModel.getCompetitionId());

        if(fixtureRepository.existsByHomeTeamAndAwayTeamAndCompetitionAndSeason(homeTeam, awayTeam, competition, fixtureModel.getSeason()))
            return ResponseEntity.ok(new MyMessageResponse("Error: Fixture already exists", MessageTypes.WARN));

        fixtureRepository.save(fixtureModel.translateModelToFixture(competitionService, clubService));
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
        return clubService.getIdByName(name);
    }

    // retrieve fixture by date where one of the clubs is St Judes

    public Fixture findByFixtureDateAndHomeTeamIdOrFixtureDateAndAwayTeamId(Date fixtureDate, Long clubId, Date fixtureDate1, Long clubId1) {
        List<Fixture> fixtures = fixtureRepository.findByFixtureDateAndHomeTeamIdOrFixtureDateAndAwayTeamId(fixtureDate, clubId, fixtureDate, clubId).orElse(new ArrayList<>());

        if(fixtures.size() > 1)
            new MyMessageResponse("Unique Fixture Does not exist",MessageTypes.WARN);
        else if(fixtures.size() == 0)
            new MyMessageResponse("No Fixture Found for this date" + fixtureDate,MessageTypes.WARN);
        return fixtures.get(0);
    }

    private List<Result> findWinsByOpposition(String team) {
        //Get all fixtures where judes are playing against opposition where home/ opposition is judes AND home/opposition is team
        Long clubId = clubService.getIdByName(team);
        List<Fixture> fixturesVsOpponent = findByOppositionId(clubId);

        List<Result> results = new ArrayList<>();
        for(Fixture fixture : fixturesVsOpponent) {
            Result result = statService.scoreByFixtureDate(fixture.getFixtureDate());
            long homeScore = result.getHomeScorePoints();
            long awayScore = result.getAwayScorePoints();
            if (Objects.equals(clubId, fixture.getHomeTeam().getId()) && homeScore < awayScore)
                results.add(result);
            if(Objects.equals(clubId, fixture.getAwayTeam().getId()) && awayScore > homeScore)
                results.add(result);

        }
        return results;
    }
}
