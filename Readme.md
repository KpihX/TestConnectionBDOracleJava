# Test de Connexion à une Base de Données Oracle

Ce projet vise à inspirer ceux qui souhaitent réaliser des opérations de connexion et de manipulation de données avec une base de données Oracle, via un terminal sur windows où en utilisant Java. Les fonctionnalités principales du projet incluent des insertions de données, l'utilisation de triggers pour la duplication et l'exécution de procédures stockées avec un délai spécifique.

## Description

Ce projet met en évidence la manière dont vous pouvez établir une connexion à une base de données Oracle à partir de votre application Java. Il utilise les fonctionnalités avancées d'Oracle telles que les triggers et les procédures stockées pour fournir des exemples concrets de manipulation de données.

## Fonctionnalités

- Connexion à une base de données Oracle en utilisant les paramètres de connexion appropriés.
- Insertion de données dans une table "Books" avec un schéma prédéfini.
- Utilisation de triggers pour détecter les duplications lors de l'insertion de données dans une table "Books2".
- Exécution d'une procédure stockée avec un délai spécifique pour mettre en attente l'opération de commit.

## Prérequis

Avant de pouvoir exécuter ce projet, assurez-vous d'avoir les éléments suivants :

- JDK 8 (ou version supérieure) installé sur votre machine.
- Une base de données Oracle configurée et accessible depuis votre machine locale ou en réseau.

## Configuration

Avant de pouvoir exécuter le projet, vous devez effectuer les étapes de configuration suivantes :

1. Mettez à jour le fichier `config.properties` avec les informations de connexion à votre base de données Oracle.
2. Assurez-vous d'avoir les pilotes JDBC Oracle appropriés dans votre classpath.

## Utilisation

1. Suivez les recommandations de NotesOracle.txt, en vue de créer la BD Oracle avec ses tables
2. Compilez & Exécutez le fichier Test.java sans oublier de mentionner 'ojdbc11.jar' dans le classPath (option '-cp ...' )
3. Suivez les instructions à l'écran pour saisir les enregistrements à insérer dans la base de données.
4. Les données seront insérées dans la table "Books" ainsi que dans les tables "Books2" et "Books3" avec les fonctionnalités de duplication et de délai spécifique.

## Contributions

Les contributions à ce projet sont les bienvenues ! Si vous souhaitez apporter des améliorations, des corrections de bugs ou de nouvelles fonctionnalités, n'hésitez pas à soumettre une pull request. où à m'ecrire à l'email : kapoivha@gmail.com

## By KπX!
