package com.jwt.models;

public class TeamsheetModel {
    private Player player;
    private Fixture fixture;
    private Position position;
    private int jerseyNumber;

    public TeamsheetModel() {}

    public void setPlayer(Player player) {           this.player = player;    }
    public void setFixture(Fixture fixture) {        this.fixture = fixture;    }
    public void setPosition(Position position) {     this.position = position;    }
    public void setJerseyNumber(int jerseyNumber) {  this.jerseyNumber = jerseyNumber; }

    public int getJerseyNumber() {         return jerseyNumber; }
    public Player getPlayer() {            return player;       }
    public Fixture getFixture() {          return fixture;      }
    public Position getPosition() {        return position;     }

    public Teamsheet translateModelToTeamsheet(){
        Teamsheet teamsheet = new Teamsheet();
        teamsheet.setPlayer(this.player);
        teamsheet.setFixture(this.fixture);
        teamsheet.setPosition(this.position);
        teamsheet.setJerseyNumber(this.jerseyNumber);
        return teamsheet;
    }

    public Teamsheet translateModelToTeamsheet(TeamsheetId id){
        Teamsheet teamsheet = translateModelToTeamsheet();
        teamsheet.setId(id);
        return teamsheet;
    }
}