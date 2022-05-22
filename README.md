### Projet Annuaire de Restaurant

## Installation

```
git clone git@github.com:Eld0rado/restaurall.git
cd restaurall
mvn clean install
```

Depuis IntelliJ, ouvrez le projet et configurer ajouter votre configuration Tomcat
![tomcat_conf](https://user-images.githubusercontent.com/91850497/169719573-9053d6e8-3fc1-429f-ad27-da8127352898.png)

Dans le package dao > modifier l'URL dans la DaoFactory

Executer les requêtes pour la création des tables du fichier creertable.sql

Vous pouvez ensuite recompiler & run le projet

## URL

Path "/"
Sur la première page executer cliquer sur dans la forme

Path "/Home"
Vous arriverez sur le page Home référençant les restaurants

Si vous n'avez pas utilisé les insert du fichier sql, vous disposez d'un formulaire pour créer des restaurants

Vous disposez de boutons pour consulter un restaurant ou le modifier

Path "/Restaurant?id={id}"
Sur la page de consultation d'un restaurant, vous disposez d'un formulaire pour lui ajouter des plats

Path "/EditRestaurant?id={id}"
Formulaire de modification d'un Restaurant

Path "/EditPlat?id={id}"
Formulaire de modification d'un plat

Path "/DeletePlat?id={id}"
Suppression d'un plat

Manque les fonctionnalités associées aux méthodes

- restaurantDao.getByNom(nom)
- restaurantDao.getRestosByType(type)
  méthodes fonctionnelles (pas encore réussi à les mettre en place)

Diagramme de classes à venir 
