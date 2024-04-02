Ce repository contient un projet Spring Boot avec une base de données SQL nommée demo. Le projet est configuré pour être lancé sans IDE en utilisant Maven. De plus, des tests sont inclus qui utilisent une base de données de test distincte nommée demo_test.
Prérequis

Avant de cloner et de lancer le projet, assurez-vous d'avoir installé les outils suivants :

    Java JDK (version 11 ou supérieure)
    Apache Maven
    Un système de gestion de base de données SQL compatible avec Spring Boot (par exemple, MySQL, PostgreSQL, etc.)

Clonage du Projet

Utilisez la commande suivante pour cloner le projet depuis GitHub :

bash

git clone https://github.com/votre-utilisateur/nom-du-projet.git

Configuration de la Base de Données

Avant de lancer l'application, assurez-vous de configurer correctement la base de données.

Assurez-vous également de créer une base de données distincte pour les tests nommée demo_test. Les tests utiliseront cette base de données pour éviter tout conflit avec la base de données principale.
Compilation et Exécution

Utilisez Maven pour compiler et exécuter le projet. Naviguez vers le répertoire racine du projet et exécutez les commandes suivantes :

Pour compiler le projet :

mvn clean install

Pour lancer l'application :

arduino

mvn spring-boot:run

L'application sera alors accessible à l'adresse http://localhost:8080.
Exécution des Tests

Les tests peuvent être exécutés en utilisant la commande Maven suivante :

bash

mvn test

Assurez-vous que la base de données de test demo_test est correctement configurée avant d'exécuter les tests.
