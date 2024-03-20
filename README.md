Ce projet est une application développée en utilisant Spring Boot et Maven, avec une base de données SQL.
Prérequis

    JDK (Java Development Kit) installé sur votre système.
    Maven installé sur votre système.
    Un serveur de base de données compatible avec Spring Boot (par exemple, MySQL, PostgreSQL, H2, etc.).

Installation et Configuration

    Cloner le Projet

    bash

git clone <URL_du_projet>

Importer le Projet dans votre IDE
Importez le projet dans votre IDE préféré (Eclipse, IntelliJ IDEA, etc.) en tant que projet Maven existant.

Configurer la Base de Données

    Créez une base de données dans votre serveur de base de données.
    Assurez-vous que les informations de connexion à la base de données sont correctement configurées dans le fichier application.properties sous src/main/resources.

Exécution du Projet

    Utilisez votre IDE pour exécuter l'application Spring Boot, ou bien exécutez la commande suivante à la racine du projet :

    arduino

        mvn spring-boot:run

Utilisation

    Une fois l'application déployée et en cours d'exécution, vous pouvez accéder aux fonctionnalités à travers les points d'extrémité REST exposés par l'application.
    Consultez la documentation de l'API pour connaître les différents points d'accès et leurs fonctionnalités associées.

Fichier SQL

    Le fichier SQL fourni contient les scripts nécessaires pour initialiser et peupler la base de données. Assurez-vous d'exécuter ces scripts dans votre base de données avant de lancer l'application.
