package com.jwt.models;

public class TeamsheetModel {

    private Fixture fixture;
    private Player player;
    private Position position;

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Fixture getFixture() {
        return fixture;
    }
    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }


    public Teamsheet translateModelToTeamsheet(){
        Teamsheet teamsheet = new Teamsheet();

        teamsheet.setPosition(this.position);
        teamsheet.setPlayer(this.player);
        teamsheet.setFixture(this.fixture);

        return teamsheet;
    }

    public Teamsheet translateModelToTeamsheet(Long id){
        Teamsheet teamsheet = translateModelToTeamsheet();

        teamsheet.setId(id);

        return teamsheet;
    }
}