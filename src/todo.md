ToDo
# Upgrade Springframework from 2.5.9 to at least 2.6.4
2.5.9 works without any errors at startup
But vulnerabilities are listed.

# Fix any load errors at startup at console

with 2.6.1 and 2.7.1
┌─────┐
|  webSecurityConfig
↑     ↓
|  org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$EnableWebMvcConfiguration
└─────┘

Relying upon circular references is discouraged and they are prohibited by default. 
Update your application to remove the dependency cycle between beans. 
As a last resort, it may be possible to break the cycle automatically by setting spring.main.allow-circular-references to true.




# Bugs
    E1 Update and Edit do not work with React

# In Progress    
    E1: Update and Edit do not work with React
        The default attribute used for all endpoints was @ModelAttribute - this is suitable for form-data and not JSON data/objects
        Changes to @RequestBody - this now works with add()
        Need to test with other methods in Competition - thenb replicate to other controllers.

# Completed
    
