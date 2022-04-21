package com.jwt.enums;

import java.util.HashMap;
import java.util.Map;

public enum Urls {

    CLUBS ("CLUBS"),
    COMPETITIONS   ( "COMPETITIONS"),
    PLAYERS        ( "PLAYERS"),
    LASTNAMES      ( "LASTNAMES"),
    FIRSTNAMES     ( "FIRSTNAMES"),
    FIXTURES       ( "FIXTURES"),
    STATS          ( "STATS"),
    TEAMSHEETS     ( "TEAMSHEETS"),

    // auth

    API_AUTH_FORGOT_PASSWORD ( "/api/auth/forgot_password"),
    API_AUTH_CHANGE_PASSWORD ( "/api/auth/change_password"),
    API_AUTH ( "/api/auth/"),
    API_AUTH_DELETEBYID ( "/api/auth/deleteById/"),
    API_AUTH_FINDBYID ( "/api/auth/findById/"),
    API_AUTH_LIST ( "/api/auth/list"),
    API_AUTH_LOGIN ( "/api/auth/login"),
    API_AUTH_REGISTER ( "/api/auth/register/"),
    API_AUTH_UPDATE ( "/api/auth/update"),

    // clubs

    CLUB ( "/club/"),
    CLUB_ADD ( "/club/add"),
    CLUB_DELETEBYID ( "/club/deleteById/"),
    CLUB_FINDBYID ( "/club/findById/"),
    CLUB_FINDBYNAME ( "/club/findByName/"),
    CLUB_LIST ( "/club/list"),
    CLUB_UPDATE ( "/club/update"),

    // players

    PLAYER ( "/player/"),
    PLAYER_ADD ( "/player/add"),
    PLAYER_DELETEBYID ( "/player/deleteById/"),
    PLAYER_FINDBYFIRSTNAME ( "/player/findByFirstname/"),
    PLAYER_FINDBYFIRSTNAMELASTNAME ( "/player/findByFirstnameLastname/"),
    PLAYER_FINDBYID ( "/player/findById/"),
    PLAYER_FINDBYLASTNAME ( "/player/findByLastname/"),
    PLAYER_LIST ( "/player/list"),
    PLAYER_UPDATE ( "/player/update"),

    // fixtures
    FIXTURE ( "/fixture/"),
    FIXTURE_ADD ( "/fixture/add"),
    FIXTURE_DELETEBYID ( "/fixture/deleteById/"),
    FIXTURE_FINDBYAWAYBYCLUB ( "/fixture/findByAwayByClub/"),
    FIXTURE_FINDBYCLUB ( "/fixture/findByClub/"),
    FIXTURE_FINDBYCOMPETITIONHOMETEAMAWAYTEAMFIXTUREDATESEASON ( "/fixture/findByCompetitionHomeTeamAwayTeamFixtureDateSeason"),
    FIXTURE_FINDBYHOMEBYCLUB ( "/fixture/findByHomeByClub/"),
    FIXTURE_FINDBYID ( "/fixture/findById/"),
    FIXTURE_FINDNEXTBYCLUB ( "/fixture/findNextByClub/"),
    FIXTURE_LIST ( "/fixture/list"),
    FIXTURE_UPDATE ( "/fixture/update"),

    // competitions
    COMPETITION ( "/competition/"),
    COMPETITION_ADD ( "/competition/add"),
    COMPETITION_DELETEBYID ( "/competition/deleteById/"),
    COMPETITION_FINDBYID ( "/competition/findById/"),
    COMPETITION_FINDBYNAME ( "/competition/findByName/"),
    COMPETITION_LIST ( "/competition/list"),
    COMPETITION_UPDATE ( "/competition/update"),

    // firstnames
    FIRSTNAME ( "/firstname/"),
    FIRSTNAME_ADD ( "/firstname/add"),
    FIRSTNAME_DELETEBYID ( "/firstname/deleteById/"),
    FIRSTNAME_FINDBYFIRSTNAME ( "/firstname/findByFirstname/"),
    FIRSTNAME_FINDBYID ( "/firstname/findById/"),
    FIRSTNAME_FINDENGLISH ( "/firstname/findEnglish/"),
    FIRSTNAME_FINDIRISH ( "/firstname/findIrish/"),
    FIRSTNAME_LIST ( "/firstname/list"),
    FIRSTNAME_UPDATE ( "/firstname/update"),

    // lastnames
    LASTNAME ( "/lastname/"),
    LASTNAME_ADD ( "/lastname/add"),
    LASTNAME_DELETEBYID ( "/lastname/deleteById/"),
    LASTNAME_FINDBYID ( "/lastname/findById/"),
    LASTNAME_FINDBYLASTNAME ( "/lastname/findByLastname"),
    LASTNAME_FINDENGLISH ( "/lastname/findEnglish/"),
    LASTNAME_FINDIRISH ( "/lastname/findIrish/"),
    LASTNAME_LIST ( "/lastname/list"),
    LASTNAME_UPDATE ( "/lastname/update");

    private final String abbreviation;
    private static final Map<String, Urls> lookup = new HashMap<>();

    static {
        for (Urls url : Urls.values()) {
            lookup.put(url.getAbbreviation(), url);
        }
    }

    Urls(String abbreviation) {  this.abbreviation = abbreviation; }

    public String getAbbreviation() {  return abbreviation; }
    public static Urls get(String abbreviation) { return lookup.get(abbreviation); }

    public static final String ENDPOINT = "http://localhost:8080";
}
