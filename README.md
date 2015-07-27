# Running Live

Pour lancer ce projet :

```
mvn package
java -jar target/runninglive-1.0-SNAPSHOT.jar

```

Pour tester, se rendre sur http://localhost:8080 apr�s lancement 
(compte : aurelien/4ur3l13n).

Le fichier src/main/resources/import.sql est ex�cut� au lancement de
l'application, vous y trouverez entre autres une liste des utilisateurs/mots de
passe cr��s par d�faut.

Les tests d'int�gration contiennent des exemples d'appels de l'API.


## User stories choisies

|Id|Tâche                                                                                                                 |Valeur business  |
|--|-----------------------------------------------------------------------------------------------------------------------|---------------- |
| 1|En tant que développeur, je voudrais avoir un healthcheck afin de m'assurer que le service fonctionne.                |5                |
| 2|En tant que coureur, je voudrais pouvoir me connecter au web service.                                                  |4                |
| 3|En tant qu'organisateur, je voudrais pouvoir me connecter au web service.                                              |5                |
| 4|En tant qu'organisateur, je voudrais pouvoir lister toutes les compétitions.                                          |4                |
| 5|En tant qu'organisateur, je voudrais qu'une compétition ait un nom et une date.                                       |3                |
| 6|En tant que coureur, je voudrais connaître le lieu d'une compétition.                                                |2                |
| 7|En tant que coureur, je voudrais pouvoir lister toutes les compétitions auxquelles je suis inscrit ou j'ai participé.|3                |
| 8|En tant qu'organisateur, je voudrais pouvoir créer une compétition.                                                  |4                |
| 9|En tant que coureur, je voudrais pouvoir ajouter mon temps à une compétition à laquelle j'ai participé.            |2                |
|10|En tant qu'organisateur, je voudrais pouvoir lister tous les coureurs inscrits à une compétition.                    |2                |
|11|En tant qu'organisateur, je voudrais pouvoir connaître la taille des coureurs.                                        |1                |

## Temps visé : 8h

## Date de livraison : dimanche 26 juillet

## Temps passé : 9h30
