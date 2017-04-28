# HASHIWOKAKERO
---------------

Projet Logique INF402 par Foray Théo, Dumax-Vorzet Mathieu, Roquet-Dolomanova Denys 

## Introduction:
Notre sujet de projet de logique est de résoudre une grille de Hashiwokakero. Pour réaliser ce projet nous avons analysé les règles et les contraintes implicites du jeu pour en déduire des prédicats et des propriétés générales. Tout d’abord il s’agit d’identifier les différents objets présents dans ce jeu et leurs caractéristiques. Ensuite, nous reformulons ceux-ci sous forme de proposition logique, afin d’en déduire une solution. Pour que le projet puisse être testé par des utilisateurs, une interface logicielle a été créée.

## Règles:
Le plateau de jeu est composé d’un grille carrée quadrillée, sur laquelle on trouve à certaines intersections, des cellules.Ces cellules contiennent un nombre compris entre 1 et 8 (inclus) ; ce sont des îles. Les autres cellules sont vides. Le but est de connecter toutes les îles entre elles en dessinant des ponts entre celles-ci. Les ponts doivent suivre certains critères :
ils doivent commencer et finir sur des îles distinctes, selon une ligne droite ; 
ils ne doivent pas croiser d'autres ponts ; 
ils ne sont que verticaux ou horizontaux 
il y a au plus deux ponts entre deux îles, on parle alors de pont double ;
et le nombre de pont partant de chaque île doit correspondre au nombre indiqué sur l'île. 

## Caractérisations:
Les variables utilisées pour les coordonnées sont un couple de naturels compris dans l’intervalle [1;L], pour L la taille de la grille. Le nombre indiqué sur une île est un naturel compris dans [1;8].
* Il(a) alors a est une île
* P(a,b) alors il existe un pont entre a et b (avec symétrie : P(a,b)⟺P(b,a) )
* Il’(a’) alors a’ est l’île double de l’île définie par Il(a) (avec l’équivalence :P(a',b)⟺P(b',a))
* f(a) renvoie n le nombre indiqué sur a
* L(a,b) alors a et b sont sur la même ligne
* C(a,b) alors a et b sont sur la même colonne
* Cr(a,b,c,d) alors a,b,c et d sont en situation de croisement

## Propriétés:
* **Définition L(a,b):**
	> *Il(a)⋀Il(b)⋀X(a)=X(b)*
* **Définition C(a,b):**
	> *Il(a)⋀Il(b)⋀Y(a)=Y(b)*
* **Définition Cr(a,b,c,d):**
 	> *L(a,b)⋀C(c,d)⋀Y(a)<Y(c)<Y(b)⋀ X(c)<X(a)<X(d)*
* **Les ponts sont horizontaux ou verticaux:**
 	> *∀a,b (Il(a)⋀Il(b)⋀a≠b ⋀P(a,b)⇒((L(a,b)⋁C(a,b))⋀(¬L(a,b)⋁¬C(a,b)))*
* **Il n’existe pas de pont reliant une île à elle-même ou à sa copie:**
	> *∀a (Il(a)⇒P(a,a)⋀P(a',a')⋀P(a,a'))*
* **Les ponts ne se croisent pas:**
	> *∀a,b,c,d ( Cr(a,b,c,d)⇒(¬P(a,b)⋁¬P(c,d)))*
* **Il y a deux ponts ou moins entre 2 îles :**
 	> *∀a,b,a',b' ( Il(a)⋀Il(b)⋀Il'(a')⋀Il'(b')⋀a≠b⇒(P(a',b')⇒P(a,b))) *
* **Le nombre de ponts partant de chaque île correspond au plus au nombre indiqué sur l’île:**
 	> *∀a (Il(a)⇒¬(∃b1, ... ,bf(a)+1 ((Il(b1)⋁Il'(b1)) ⋀...⋀(Il(bf(a)+1)⋁Il'(bf(a)+1))⋀P(a,b1)⋀
 ... ⋀P(a,bf(a)+1)⋀a≠b1⋀...⋀b1≠b2⋀...⋀bf(a)≠bf(a)+1)))*
* **Le nombre de ponts partant de chaque île correspond au moins au nombre indiqué sur l’île:**
 	> *∀a (Il(a)⇒(∃b1, ... ,bf(a) ((Il(b1)⋁Il'(b1)) ⋀...⋀(Il(bf(a))⋁Il'(bf(a)))⋀P(a,b1)⋀
 ... ⋀P(a,bf(a))⋀a≠b1⋀...⋀b1≠b2⋀...⋀bf(a)≠bf(a)-1)))*
 
 

## Formalisation en Forme Normale Conjonctive (FNC) :
1          |2     |3          |4
-----------|------|-----------|------
Vide       |Vide  |**Ile a (2)**  |Vide
   Vide    | Vide |   Vide    | Vide
   Vide    | Vide |   Vide    | Vide
**Ile c (1)**  | Vide | **Ile b (3**) | Vide

On note les îles a, b, c auxquelles on associe les îles doubles a’, b’, c’.
Les ponts possibles sont donc : 


*` P(a,a)⋁P(b,b)⋁P(c,c)⋁P(a,b)⋁P(a,c)⋁P(b,c)⋁P(a',a')⋁P(b',b')⋁P(c',c')
P(a',b') P(a',c') P(b',c') P(a,a')P(b,b')P(c,c')`* 

**Les ponts sont horizontaux ou verticaux:**

>![alt text](https://raw.githubusercontent.com/ElectrozDen/HASHIWOKAKERO/master/ressources/images/test.PNG)
 


**Il n’existe pas de pont reliant une île à elle-même ou à sa copie:**


>![alt text](https://raw.githubusercontent.com/ElectrozDen/HASHIWOKAKERO/master/ressources/images/reli.PNG)


**Les ponts ne se croisent pas:**


>![alt text](https://raw.githubusercontent.com/ElectrozDen/HASHIWOKAKERO/master/ressources/images/crois.PNG)


Etant donné la configuration de notre exemple, cette règle ne s’appliquera pas.


**Il y a deux ponts ou moins entre 2 îles :**


>![alt text](https://raw.githubusercontent.com/ElectrozDen/HASHIWOKAKERO/master/ressources/images/2P.PNG)


**Le nombre de ponts partant de chaque île correspond au plus au nombre indiqué sur l’île:**


>![alt text](https://raw.githubusercontent.com/ElectrozDen/HASHIWOKAKERO/master/ressources/images/auplus.PNG)


Pour la FNC finale, nous continuons avec toutes les autres possibilités et nous faisons la conjonction de chaque règle.



**Le nombre de ponts partant de chaque île correspond au moins au nombre indiqué sur l’île:**


>![alt text](https://raw.githubusercontent.com/ElectrozDen/HASHIWOKAKERO/master/ressources/images/aumoins.PNG)


Pour la FNC finale, nous continuons avec toutes les autres possibilités et nous faisons la conjonction de chaque règle.

## Programme Hashiwokakero

Ce programme permet de sortir à l'écran la forme normale cojonctive à partir des instances que l'utilisateur à entrer ou d'en créer un fichier dimacs.

Nous avons choisi le language java pour ce programme, vous pouvez retrouver le code avec le lien suivant :
Comme le code est déjà commenté nous allons juste dire ce que représente chaque fichier java

### Map.java

C'est un objet java qui permet de représenter la carte du jeu Hashiwokakero.
Reprenons notre exemple d'avant :


1          |2     |3          |4
-----------|------|-----------|------
Vide       |Vide  |**Ile a (2)**  |Vide
   Vide    | Vide |   Vide    | Vide
   Vide    | Vide |   Vide    | Vide
**Ile c (1)**  | Vide | **Ile b (3**) | Vide


Dans map.java les îles seront identifiés par des positions(position = numéro de la case du tableau, par exemple l'île C est à la position 9), les indices du tableau ou de la carte en x(ligne) et y(colonne) commencent à 1

### Predicat.java

C'est un objet qui représente comme son nom l'indique un prédicat, on peut juste savoir si c'est une négation ou pas


Exemple:
```
Predicat predicat = new Predicat();
predicat.setNegatif();
if(predicat.isNegatif()) predicat.setPositif();
```

### Ile.java

C'est l'objet qui définit l'île, il est le fils de l'objet predicat (cela veut dire que ile comporte les mêmes fonctions qu'un predicat)
On peut récupérer sa position, le n (nombre de ponts reliés à l'île) et si elle se situe sur la 1ere grille oue la 2eme

### Pont.java

Il définit un pont (fils de prédicat aussi), il comporte 2 îles avec lui.

### Clause.java

Comme son nom l'indique, ce fichier ou objet représente une clause de ponts.


Par exemple:
```
...
Clause clause = new Clause();
clause.add(pont1);
clause.add(pont2);
clause.add(pont3);
```
Cela se traduit par {pont1 V pont2 V pont3}

### Modele.java

On retrouve ici l'objet modéle qui contient une liste de clauses.
```
...
Modele modele = new Modele();
modele.addClause(clause1);
modele.addClause(clause1);
modele.addClause(clause1);
```
--> {pont1 V pont2 V pont3, pont1 V pont2 V pont3, pont1 V pont2 V pont3}

### Definition.java

Ceci n'est pas un objet mais un fichier qui contient des fonctions qui permettent de récuperer les définitions que nous avous dit au début
* **Définition L(a,b):**
* **Définition C(a,b):**
* **Définition Cr(a,b,c,d):**

Chacune de ses fonctions renvoie un boolean.

### Tools.java

C'est un fichier qui contient des fonctions "outils" qui simplifie le code de d'autres fichiers. On peut y retrouver la fonction qui compare 2 îles, celle qui compare 2 ponts, celle qui regarde si une clause est déjà valide, etc

### Proprietes.java

C'est un fichier qui contient toutes les fonctions qui représentent les propriétes que nous avons caractériser (les ponts ne se croisent pas, les ponts sont horizontaux ou verticaux, etc), ils renvoient tous un modéle qui définit la propriété

### CommandsManager.java

Elle permet de récuper la commande que l'utilisateur rentre dans la console et d'excuter cette commande


**Commandes**:
* map n
	> Créer une map de taille n
* ile x y n
	> Ajoute une Ile(n) aux coordonnées x et y
* fnc [propriete]
	> Affiche à la console la fnc de la propriété : {nosame, nonear,2p, crois, auplus, aumoins, horivert}
* fnc all
	> Affiche la fnc finale simplifié(élimination des clauses valides et des clauses contenant une autre clause)
* dimacs
	> Créer le fichier dimacs que nous verrons comment aprés
	
	
Exemple:


Testons avec notre exemple du début:


1          |2     |3          |4
-----------|------|-----------|------
Vide       |Vide  |**Ile a (2)**  |Vide
   Vide    | Vide |   Vide    | Vide
   Vide    | Vide |   Vide    | Vide
**Ile c (1)**  | Vide | **Ile b (3**) | Vide

Rentrons à la console et voyons ce qu'elle affiche :
```
map 4
ile 1 3 2
ile 3 1 1
ile 3 3 3
fnc all
Aucun pont est à coté d'un autre pont
Aucun croisement entre iles
------------FNC FINALE------------

(-P(3,3))^
(-P(3',3'))^
(-P(9,9))^
(-P(9',9'))^
(-P(11,11))^
(-P(11',11'))^
(-P(3,9))^
(-P(3',9') V P(3,9))^
(-P(3',11') V P(3,11))^
(-P(9',11') V P(9,11))^
(-P(3',9') V -P(3,11) V -P(3',11'))^
(-P(9',3') V -P(9,11))^
(-P(9',3') V -P(9',11'))^
(-P(9,11) V -P(9',11'))^
(P(3',3') V P(3,9) V P(3',9') V P(3,11))^
(P(3',3') V P(3,9) V P(3',9') V P(3',11'))^
(P(3',3') V P(3,9) V P(3,11) V P(3',11'))^
(P(3',3') V P(3',9') V P(3,11) V P(3',11'))^
(P(3,9) V P(3',9') V P(3,11) V P(3',11'))^
(P(9,3) V P(9',3') V P(9',9') V P(9,11) V P(9',11'))^
(P(11,3) V P(11',3') V P(11,9))^
(P(11,3) V P(11',3') V P(11',9'))^
(P(11,3) V P(11',3') V P(11',11'))^
(P(11,3) V P(11,9) V P(11',9'))^
(P(11,3) V P(11,9) V P(11',11'))^
(P(11,3) V P(11',9') V P(11',11'))^
(P(11',3') V P(11,9) V P(11',9'))^
(P(11',3') V P(11,9) V P(11',11'))^
(P(11',3') V P(11',9') V P(11',11'))^
(P(11,9) V P(11',9') V P(11',11'))
```

Un peu difficile de voir par nous mêmes si c'est une bonne fnc, faisons le avec un plus petit :

1          |2     |3          |4
-----------|------|-----------|------
**Ile a (1)**       |Vide  |Vide  |Vide
   Vide    | Vide |   Vide    | Vide
  **Ile b (1)**   | Vide |   Vide    | Vide
Vide  | Vide | Vide | Vide

```
map 4
ile 1 1 1
ile 3 1 1
fnc all
Tous les ponts sont horizontaux ou verticaux entre eux
Aucun pont est à coté d'un autre pont
Aucun croisement entre iles
------------FNC FINALE------------

(-P(1,1))^
(-P(1',1'))^
(-P(9,9))^
(-P(9',9'))^
(-P(1',9') V P(1,9))^
(-P(9,1) V -P(9',1'))^
(P(1',1') V P(1,9) V P(1',9'))^
(P(9,1) V P(9',1') V P(9',9'))
```

Là nous pouvons voir par nous-mêmes que le seul model est P(1,9)
