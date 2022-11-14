package com.jwt.models;

public class TeamsheetModel {

    private Fixture fixture;
    private Player player;
    private Position position;

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public Player getPlayer() {
        return player;
    }
    public Fixture getFixture() {
        return fixture;
    }
    public Position getPosition() {
        return position;
    }

    public Teamsheet translateModelToTeamsheet(){
        Teamsheet teamsheet = new Teamsheet();
        teamsheet.setPosition(this.position);
        teamsheet.setPlayer(this.player);
        teamsheet.setFixture(this.fixture);
        return teamsheet;
    }

    public Teamsheet translateModelToTeamsheet(TeamsheetId id){
        Teamsheet teamsheet = translateModelToTeamsheet();
        teamsheet.setId(id);
        return teamsheet;
    }
}