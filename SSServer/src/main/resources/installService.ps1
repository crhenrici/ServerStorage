nssm install ServerStorage "java"
nssm set ServerStorage AppDirectory C:\test
nssm set ServerStorage AppParameters "-jar lib/SSServer-0.0.1-SNAPSHOT.jar --spring.config.location=config/ --spring.profiles.active=dev,testdata,mockdata,log"
nssm set ServerStorage DisplayName ServerStorage
nssm set ServerStorage Description Service of ServerStorage
