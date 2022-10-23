ToDo
----
Ex: indicate problems found.
Cx: New Code additions (new features)
Rx: Refactoring
Ix: Investigations
Ux: Files currently UnUsed -
Ig: Ignored for now
Dx: Database Changes/UPdates

# Bugs
    E1 Update and Edit do not work with React
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
    E3 Mysql vulnerabiulity
    E4 Using Postman to list stats results in Error
        ... nested exception is org.springframework.http.converter.HttpMessageConversionException: Type definition error: [s
     
# New Code Cx
    C1: Add Teamsheet and Stat Clases plus Controller / Service and Repos
    D1: Make Stat and Temsheet bridging tables and reload database
    D2: Load sample Data
    D3: Add PanelMember Boolean
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
        Need to test with other methods in Competition - thenb replicate to other controllers.

# Completed
    E2 Spring updated from 2.5.9 to 2.5.14 - Vulnerabilities with spring disappeared
    E3 Mysql updated.
    C1 Stat and Teamsheet classes added
    E4 Change LAZY to EAGER in Temsheet and Stat and Fixture classes.
    D1 Bridging tables added
    D2 Data Loaded



