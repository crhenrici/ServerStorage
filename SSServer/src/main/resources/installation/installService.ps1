param (
    [string]$profile = "h2db", $DB_NAME, $DB_URI, $DB_USER, $DB_PW
)
nssm install ServerStorage "java"
nssm set ServerStorage AppDirectory C:\serverStorage
nssm set ServerStorage AppParameters "-jar lib/SSServer-0.0.1-SNAPSHOT.jar --spring.config.location=config/ --spring.profiles.active=$profile"
nssm set ServerStorage DisplayName ServerStorage
nssm set ServerStorage Description Service of ServerStorage with
nssm set ServerStorage AppEnvironmentExtra DB_NAME=$DB_NAME
nssm set ServerStorage AppEnvironmentExtra DB_URI=$DB_URI
nssm set ServerStorage AppEnvironmentExtra DB_USER=$DB_USER
nssm set ServerStorage AppEnvironmentExtra DB_PW=$DB_PW
