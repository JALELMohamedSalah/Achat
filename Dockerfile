# Utilisez une image de base avec Java Runtime Environment (JRE)
FROM openjdk:11

# Installez curl et autres dépendances nécessaires
RUN apt-get update && apt-get install -y curl

# Téléchargez le fichier JAR depuis l'URL
RUN curl -o achat-1.0.jar -L http://192.168.100.156:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar


# Commande d'entrée pour exécuter l'application Java
ENTRYPOINT ["java", "-jar", "achat-1.0.jar"]