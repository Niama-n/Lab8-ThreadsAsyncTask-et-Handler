# LAB 8 - Threads, AsyncTask et Handler

Cette application Android démontre l'utilisation des threads et des tâches asynchrones (`AsyncTask`) pour effectuer des opérations en arrière-plan sans bloquer l'interface utilisateur (UI).

Ce travail pratique apprend à exécuter un traitement long (ex. calcul lourd, chargement d’image) sans bloquer l’interface. Le lab construit une application Android avec des boutons : un bouton lance un traitement long, un autre affiche un Toast immédiatement. Si l’UI reste fluide, la programmation asynchrone est correcte.

## 🎬Vidéo de démonstration

Vous pouvez visualiser le test de l'application ici :
---





## Objectifs

- Comprendre UI Thread et Worker Thread
- Créer un Thread avec Runnable
- Mettre à jour l’interface avec View.post(...) et Handler
- Utiliser une AsyncTask (approche pédagogique) avec progression
- Éviter les erreurs classiques (UI bloquée, mise à jour UI hors thread principal)

## Structure du Projet

- **MainActivity.java** : Contient la logique de gestion des threads, de l'AsyncTask et des interactions UI.
- **activity_main.xml** : Définit la mise en page de l'application.


## Fonctionnalités

L'application se compose d'une interface simple avec :
- Un **TextView** pour afficher l'état actuel de l'application.
- Une **ProgressBar** horizontale pour suivre la progression des tâches.
- Un **ImageView** pour afficher une image chargée dynamiquement.
- Trois boutons d'action :

### 1. Charger image (Thread)
Démarre un nouveau `Thread` pour simuler le chargement d'une image (attente d'une seconde). Une fois le "chargement" terminé, l'image est mise à jour sur l'interface utilisateur via un `Handler` (lié au `Looper.getMainLooper()`).

### 2. Calcul lourd (AsyncTask)
Exécute un calcul intensif simulé à l'aide d'une classe héritant de `AsyncTask`. 
- **onPreExecute** : Affiche la barre de progression.
- **doInBackground** : Effectue une boucle de calcul et met à jour la progression.
- **onProgressUpdate** : Actualise la `ProgressBar` en temps réel.
- **onPostExecute** : Masque la barre de progression et affiche le résultat final.

### 3. Afficher Toast
Affiche un message `Toast` instantanément. Ce bouton permet de vérifier que l'interface utilisateur reste fluide et réactive, même pendant qu'un calcul lourd ou un chargement d'image est en cours en arrière-plan.



## Réalisé PAR
 NAFTAOUI NIAMA
