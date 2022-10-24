ToDo
----
Ex: indicate problems found.
Cx: New Code additions (new features)
Rx: Refactoring
Ix: Investigations
Ux: Files currently UnUsed -
Ig: Ignored for now
Dx: Database Changes/Updates

# Bugs
    E1 Update and Edit do not work with React

# New Code Cx


# Database Changes   

    D2: Load sample Data
    D3: Add PanelMember Boolean to Player table
    D4: Update data with new Field data
    D5: Change stats.half to int

# Refactoring Rx
# Investigations Ix
# Files Unused Ux
# Ignored for now Ix

# In Progress    
    E1: Update and Edit do not work with React
        The default attribute used for all endpoints was @ModelAttribute - this is suitable for form-data and not JSON data/objects
        Changes to @RequestBody - this now works with add()
        Need to test with other methods in Competition - then replicate to other controllers.
    
# Completed
    E2 Vulnerabilities appear in Spring modules
        
       Upgrading to 2.6 or 2.7 results in 
        
       ┌─────┐
       |  webSecurityConfig
       ↑     ↓
       |  org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$EnableWebMvcConfiguration
       └─────┘
        
       Relying upon circular references is discouraged and they are prohibited by default.
       Update your application to remove the dependency cycle between beans.
       As a last resort, it may be possible to break the cycle automatically by setting s to true.

       Spring updated from 2.5.9 to 2.5.14 - Vulnerabilities with spring disappeared

    E3 Mysql vulnerability
       Mysql updated.
    C1 Stat and Teamsheet classes added
    E4 Change LAZY to EAGER in Temsheet and Stat and Fixture classes.
    D1 Make Stat and Temsheet bridging tables and reload database
       Bridging tables added
    D2 Data Loaded    
    E5 Created findByStatNameFixtureDate but only 3/5 fields displayed on postman 
       Getters omitted from class - these are needed by spring to generate the ResponseBody to return to client
    E6 Select distinct does not work after uuid() id created
       uuid id variable removed, time_occurred and fixturedate used as the primary key
       statviewid used to getters added to StatsView for time_occurreda and fixtureDate
    D6 Removed UUID() as id and import data again using jpa taking time_occurred and fixture_date as the primary keys
       complete
    C2: Add code to create findByStatnameSeason
        complete.