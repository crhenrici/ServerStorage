param (
    [string]$profile = "h2db"
)
nssm install ServerStorage "java"
nssm set ServerStorage AppDirectory C:\serverStorage
nssm set ServerStorage AppParameters "-jar lib/SSServer-0.0.1-SNAPSHOT.jar --spring.config.location=config/ --spring.profiles.active=$profile"
nssm set ServerStorage DisplayName ServerStorage
nssm set ServerStorage ObjectName testuser test
nssm set ServerStorage Description Service of ServerStorage with Scripts
