# API-2021-SMTP - JEANNERET Hugo et LOGAN Victoria

## Description du projet 

## Instructions pour lancer le serveur MockMock (SMTP) avec un Docker

## Instructions pour exécuter les pranks
## Fichiers de configuration
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
## Description de l'implémentation

## Références









## Deliverables

You will deliver the results of your lab in a GitHub repository. You do not have to fork a specific repo, you can create one from scratch.

Your repository should contain both the source code of your Java project and your report. Your report should be a single `README.md` file, located at the root of your repository. The images should be placed in a `figures` directory.

Your report MUST include the following sections:

* **A brief description of your project**: if people exploring GitHub find your repo, without a prior knowledge of the API course, they should be able to understand what your repo is all about and whether they should look at it more closely.

* **Instructions for setting up a mock SMTP server (with Docker - which you will learn all about in the next 2 weeks)**. The user who wants to experiment with your tool but does not really want to send pranks immediately should be able to use a mock SMTP server. For people who are not familiar with this concept, explain it to them in simple terms. Explain which mock server you have used and how you have set it up.

* **Clear and simple instructions for configuring your tool and running a prank campaign**. If you do a good job, an external user should be able to clone your repo, edit a couple of files and send a batch of e-mails in less than 10 minutes.

* **A description of your implementation**: document the key aspects of your code. It is probably a good idea to start with a class diagram. Decide which classes you want to show (focus on the important ones) and describe their responsibilities in text. It is also certainly a good idea to include examples of dialogues between your client and an SMTP server (maybe you also want to include some screenshots here).

## References

* [Here is our fork of MockMock server](https://github.com/HEIGVD-Course-API/MockMock), in which we resolved an issues with a dependency (see this [pull request](https://github.com/tweakers/MockMock/pull/8) if you want to have more information).
* The [mailtrap](<https://mailtrap.io/>) online service for testing SMTP
* The [SMTP RFC](<https://tools.ietf.org/html/rfc5321#appendix-D>), and in particular the [example scenario](<https://tools.ietf.org/html/rfc5321#appendix-D>)
* Testing SMTP with TLS: `openssl s_client -connect smtp.mailtrap.io:2525 -starttls smtp -crlf`
