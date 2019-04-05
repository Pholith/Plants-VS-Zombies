

===== Plants VS Zombies =====
Vincent Buisset
Julien Faidide


Ce qui a été implémenté:
- Entiereté de la phase 1 à l'exception du debug
- Lecture des sprites et animation
- Système de Projectile
- Caméra déplaçable


Organisation du programme:

 - Tous les objets du jeu sont des GameObject (exception des Square) et possèdent des méthodes utilitaires tels que start() qui permet leur initialisation, update() qui permet leur vie ou encore destroy() pour les détruire.
 
 - Ces objets sont tous stockés dans la liste de GameObject "sceneContent". Un GameObject est 
 automatiquement mis dans cette liste lors de son instanciation. Le GameManager s'occupe d'appeler la fonction start() à l'ajout de l'objet et la fonction update() chaque frame du jeu.

 - La GameManager est un singleton, il possède une unique instance de lui même qui est accessible par n'importe quel GameObject via la méthode statique getInstance().

 - La GameManager possède la visibilité vers les ressources importantes pour le jeu, tel que les constantes, la Camera qui permet l'affichage de chaque objet, le LevelManager qui s'occupe de la génération du niveau. Ainsi que les Ressources qui stoquent le terrain.

 - Nous avons ensuite créé de nombreuses classes qui sont beaucoup plus simple à comprendre dans notre diagramme UML. Les principales classes sont les Plants, les Zombies, les Projectiles et les UI_Elements.


Les choix techniques:

 - La position de chaque GameObject est un vecteur 2 dimensions qui représente ses coordonnées.

 - 

Les éventuels problèmes rencontrés:

 - Il a été assez difficile de rendre générique la partie du code permettant de choisir un type de Zombie aléatoire à faire spawn sans utiliser plein de if ou un switch. Pour cela, j'ai réalisé un tableau de classes qui contient toutes les classes de Zombie, et qui me permet d'en choisir un aléatoirement et de l'instancier.


====== Notre Diagramme UML ====== 

Pour rendre compréhensible l'organisation de notre projet, nous avons réalisé un diagramme UML avec le logiciel StarUML, un export en image de ce diagramme est disponible: PlantsVSZombies.jpg dans le même dossier que le README.txt .