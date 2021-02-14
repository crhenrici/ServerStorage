param (
	[string]$profile = "dev"
)
java -jar lib/SSServer-0.0.1-SNAPSHOT.jar --spring.config.location=config/ --spring.profiles.active=$profile

