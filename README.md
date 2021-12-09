# API-2021-SMTP - JEANNERET Hugo et LOGAN Victoria

## 1. Description du projet 

La transmission de mail se fait via un serveur SMTP. Dans ce projet, nous voulons simuler l'envoi d'mails en se faisant passer par quelqu'un d'autre (une victime choisie aléatoirement) à un groupe de victimes. Nous allons faire ceci via un mock (serveur SMTP simulé) server nommé MockMock, dont le fichier exécutable se trouve dans notre package "docker" ; ceci nous permettra donc de simuler cet envoi, i.e. de ne pas l'effectuer sur un vrai serveur mail. MockMock fournit également une interface web qui nous permet de visualiser les mails reçus et leurs détails.

NB : Les adresses mails sont considérées comme nom.prenom@[...].

## 2. Instructions pour lancer le serveur MockMock (SMTP) avec un Docker

Pour lancer le serveur MockMock, il faut lancer ces deux commandes (à la suite) : 
sudo docker build -t mockmock .
sudo docker run -p 8282 -p 2525 mockmock

## 3. Instructions pour configurer et exécuter les pranks

Pour permettre l'envoi automatique des mails, différents fichiers de configuration et fichiers texte entrent en jeu.
Les fichiers de configurations peuvent être chargés par des classes java pour ensuite récupérer leur contenu grâce à un principe de "clé - valeur".
C'est la classe ConfigurationManager qui se charge de récupérer les contenus.

### 1. Connexion avec le serveur de mail
Pour établir la connexion nous avons ajouté différentes valeurs dans le fichier config.properties. L'extension ".properties" permet d'accéder très facilement à une valeur pour un clé donnée en paramètre.
+ L'adresse du serveur web pour accéder à l'interface de MockMock et afficher les mails reçu.
(Il s'agit de l'adresse de bouclage et nous avons attribué le port 8282 pour l'écoute).
+ Le port d'écoute du serveur pour le protocole STMP. Nous avons attribué le port 2525 au lieu du port par défaut pour le SMTP (25).

### 2. numberOfGroups
+ Défini la valeur du nombre de groupes générés à partir de la liste de victimes.

### 3. witnessesToCc
+ Défini une adresse mail qui sera mise en copie des mails pour pouvoir vérifier que les pranks ont bien été envoyées.

### 4. messages.utf8
+ Ce fichier n'est qu'un fichier text qui contient tous les mails. Il ont déjà été rédigé à la suite avec comme séparateur '--'.
+ Le sujet à été indiqué au début de chaque message pour être facilement récupéré.
+ C'est la fonction loadMessagesFromFile() qui se charge de parser correctement les données.

### 5. victims.utf8
+ C'est également un simple fichier texte mais qui contient cette fois la liste des adresses mails des victimes qui seront ensuite utilisées dans les groupes définis plus haut.
+ C'est la fonction loadAddressFromFile() qui de traduit cette liste en une liste de String qui sera dès lors manipulable.

## 4. Description de l'implémentation

Voici les fonctions des différentes classes/interfaces implémentées : 

- IConfigurationManager : 
Force l'implémentation de différentes méthodes absolument nécessaires à la récupération des données de configurations pour ce projet ;

- ConfigurationManager :
Récupère les données de configuration pour ce projet, décrites en point 3 de ce rapport ;

- Group :
Implémente un groupe de victimes, i.e. une liste de personne ;

- Mail :
Implémente un mail, avec l'adresse de l'expédit.eur.rice, les adresse des récept.eur.ice.s et des éventuel.le.s cc, ainsi que le message lui-même ;

- Person :
Représente une personne, par son nom, son prénom et son adresse mail ;

- Prank :
Implémente les carcatéristiques du prank : stocke donc la victime d'expédition, la liste de victimes, la liste des cc, ainsi que le message : 

- PrankGenerator :
Implémente la fonction de génération de prank ;

- ISmtpClient :
Forcera l'implémentation de la méthode d'envoi de mail dans SmtpClient ;

- SmtpClient :
Implémente les fonctions utiles au client : il traite donc l'adresse du serveur SMTP son port d'écoute, un socket ainsi qu'un flot d'entrée et un flot de sortie.

- App :
Implémente le client (ce qu'on pourrait faire en ligne de commande du terminal pour envoyer un mail ainsi).

## Références

MockMock server : 
https://github.com/HEIGVD-Course-API/MockMock

Docker : 
https://www.youtube.com/watch?v=crauCDslRMI&list=PLfKkysTy70QbseGZcVbpTXhas2xrXKk61&index=1
https://www.youtube.com/watch?v=nslj8cOEIos&list=PLfKkysTy70QbseGZcVbpTXhas2xrXKk61&index=2
https://www.youtube.com/watch?v=DSswdvOs1FI&list=PLfKkysTy70QbseGZcVbpTXhas2xrXKk61&index=3
https://www.youtube.com/watch?v=7GirsawtE8I&list=PLfKkysTy70QbseGZcVbpTXhas2xrXKk61&index=4

Implémentation client : 
https://www.youtube.com/watch?v=ot-bDyqgTtk&list=PLfKkysTy70QY_C0t9avTuEsLVVObxOtTM
https://www.youtube.com/watch?list=PLfKkysTy70QY_C0t9avTuEsLVVObxOtTM&v=Nj34XicS6JM&feature=youtu.be
https://www.youtube.com/watch?v=LoFKsT9Rj10&list=PLfKkysTy70QY_C0t9avTuEsLVVObxOtTM&index=22
https://www.youtube.com/watch?v=OrSdRCt_6YQ&list=PLfKkysTy70QY_C0t9avTuEsLVVObxOtTM&index=27
