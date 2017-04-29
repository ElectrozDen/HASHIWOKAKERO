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
* Il’(a’) alors a’ est l’île double de l’île définie par Il(a) (avec l’équivalence :P(a',b)⟺P(b',a) et P(a,b')⟺P(a',b'))
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
* **Pas de ponts entre 2 îles collées:**
	> *∀a,b (Il(a)⋀Il(b)⋀a≠b⋀(X(a)=X(b)+-1 ⋁ Y(a) == Y(b) +-1)⇒¬P(a,b)⋀¬P(a',b'))
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


*` P(a,a)⋁P(b,b)⋁P(c,c)⋁P(a,b)⋁P(a,c)⋁P(b,c)⋁P(a',a')⋁P(b',b')⋁P(c',c')⋁
P(a',b')⋁P(a',c')⋁P(b',c')⋁P(a,a')⋁P(b,b')⋁P(c,c')`* 

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


**Probléme de cette propriété:**


Ce que nous avons est une fnd et pour une obtenir une fnc, il faut de très nombreuses distributions, l'ordinateur ne supportera pas cette fnd car nous pouvons avoir de nombreux îles


Nous remplaçons cette propriété par celle-ci:

 ** *∀a (Il(a)⇒¬(∃b1, ... ,bm+1 ((Il(b1)⋁Il'(b1)) ⋀...⋀(Il(bm+1)⋁Il'(bfm+1))⋀P(a,b1)⋀... ⋀P(a,bm+1)⋀a≠b1⋀...⋀b1≠b2⋀...⋀bm≠bm+1)))   avec m = nombre îles-f(a)* **
	
Ce qui revient à dire qu'il existe m îles sans ponts avec a

Pour la FNC finale, nous continuons avec toutes les autres possibilités et nous faisons la conjonction de chaque règle.

## Programme Hashiwokakero

Ce programme permet de sortir à l'écran la forme normale conjonctive à partir des instances que l'utilisateur à entrer ou d'en créer un fichier dimacs.

Nous avons choisi le language java pour ce programme, vous pouvez retrouver le code en cliquant [ici](https://github.com/ElectrozDen/HASHIWOKAKERO/tree/master/hashi/main)


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


Dans map.java les îles seront identifiées par des positions(position = numéro de la case du tableau, par exemple l'île C est à la position 9), les indices du tableau ou de la carte en x(ligne) et y(colonne) commencent à 1

### Predicat.java

C'est un objet qui représente comme son nom l'indique un prédicat, on peut juste savoir si c'est une négation ou pas


Exemple:
```
Predicat predicat = new Predicat();
predicat.setNegatif();
if(predicat.isNegatif()) predicat.setPositif();
```

### Ile.java

C'est l'objet qui définit l'île, il est le fils de l'objet predicat (cela veut dire que Ile.java comporte les mêmes fonctions qu'un predicat)
On peut récupérer sa position, le n (nombre de ponts reliés à l'île) et si elle se situe sur la 1ere grille ou sur la 2eme grille

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

Ceci n'est pas un objet mais un fichier qui contient des fonctions qui permettent de récuperer les définitions que nous avons dit au début
* **Définition L(a,b):**
* **Définition C(a,b):**
* **Définition Cr(a,b,c,d):**

Chacune de ses fonctions renvoie un boolean.

### Tools.java

C'est un fichier qui contient des fonctions "outils" qui simplifie le code de d'autres fichiers. On peut y retrouver la fonction qui compare 2 îles, celle qui compare 2 ponts, celle qui regarde si une clause est déjà valide, etc

### Proprietes.java

C'est un fichier qui contient toutes les fonctions qui représentent les propriétes que nous avons caractériser (les ponts ne se croisent pas, les ponts sont horizontaux ou verticaux, etc), ils renvoient tous un modéle qui caractérise la propriété

### CommandsManager.java

Elle permet de récuperer la commande que l'utilisateur rentre dans la console et d'executer cette commande


**Commandes**:
* map n
	> Créer une map de taille n
* ile x y n
	> Ajoute une Ile(n) aux coordonnées x et y
* fnc [propriete]
	> Affiche à la console la fnc de la propriété : {nosame, nonear,2p, crois, auplus, aumoins, horivert} -> voir dans le code à quoi correspondent ces noms
* fnc all
	> Affiche la fnc finale simplifié(élimination des clauses valides et des clauses contenant une autre clause)
* dimacs
	> Créer le fichier dimacs que nous verrons comment aprés
* exit
	> Stopper le programme
	
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
(P(3',3') V P(3,9) V P(3',9') V P(3,11) V P(3',11'))^
(P(11,3) V P(11',3') V P(11,9) V P(11',9'))^
(P(11,3) V P(11',3') V P(11,9) V P(11',11'))^
(P(11,3) V P(11',3') V P(11',9') V P(11',11'))^
(P(11,3) V P(11,9) V P(11',9') V P(11',11'))^
(P(11',3') V P(11,9) V P(11',9') V P(11',11'))
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
(-P(9,1) V -P(9',1'))
```

Là nous pouvons voir par nous-mêmes que la seule solution est P(1,9)


## Programme Dimacs

C'est un programme en java que nous avons fait pour lire des fichiers dimacs et de transformer les clauses du dimacs en 3-SAT pour utiliser le solveur à la suite (Nous avons pris le solveur SAT4J disponible sur internet). Vous pouvez retrouver le code du programme en cliquant [ici](https://github.com/ElectrozDen/HASHIWOKAKERO/tree/master/dimacs/main)

### PredicatID.java
Même principe que le programme d'avant mais comme on travaille dans le domaine dimacs , on lui demande d'ajouter un id(positif ou négatif) qui définit la variable par exemple x1, x2

```
PredicatID pred = new PredicatID(5);
PredicatID pred2 = new PredicatID(-5);
```
Les deux prédicats comportent l'id 5 mais le deuxiéme prédicat est une négation

### ClauseDimacs.java

Un objet qui représente une clause de PredicatID. C'est le même principe

### ModeleDimacs.java

C'est un objet qui représente un modéle qui contient les clauses ClauseDimacs

### Dimacs.java

Elle représente un fichier dimacs, on la déclare en lui donnant le lien vers un fichier dimacs. Cet objet permet de lire un dimacs , d'afficher son contenu et d'en créer un ModeleDimacs ou d'écrire un fichier dimacs à partir d'un ModeleDimacs.

```
Dimacs dimacs = new Dimacs("mondimaxs.txt");
ModeleDimacs modele = dimacs.getModeleFromDimacs();
//on manipule notre modele comme on veut (transormation en 3Sat par exemple)
modele = Sat3.get3SAT(modele);
modele.print(); // on affiche notre nouveau modéle
dimacs.writeModele(modele);
console.print(dimacs.readFile()); // on affiche le nouveau contenue de notre fichier dimacs
```
### Sat3.java

Ce fichier contient la fonction qui permet de transormer un ModeleDimacs en 3Sat (voir l'exemple au-dessus)

### Solveur.java

Permet de résoudre (Sat4J) un ModeleDimacs ou un Dimacs directement et affiche sa satisfaisabilité ainsi que les solutions

### CommandsManager.java

**Commandes**:
* new dimacs [nom_fichier]
 > Créer un objet dimacs avec [nom_fichier] comme entrée
* new modele
 > Créer un nouveau modéle pour l'utilisateur
* add [variables]
 > Ajoute une clause au modéle avec les variables données en entrée
* print modele
 > Affiche le modéle
* print dimacs
 > Affiche le contenu du fichier dimacs
* to dimacs [nom_fichier]
 > Créer un fichier Dimacs à partir du modéle creer
* to modele
 > Récupere le modele à partir d'un fichier dimacs
* dimacs 3sat
 > Transforme le fichier dimacs en un fichier dimacs sous forme 3SAT
* modele 3sat 
 > Transforme le modele en un modele sous forme 3sat
* solve dimacs
 > Résous le fichier dimacs
* solve modele
 > Résous le modéle
 * exit
 > Stopper le programme
 
 
 **Exemple:**
 
 
 ```
 new modele
add 1 -5 4
add -1 5 3 4
add -3 -4
print modele
(1 V -5 V 4)^
(-1 V 5 V 3 V 4)^
(-3 V -4)
to dimacs test.dim
print dimacs
p cnf 5 3
1 -5 4 0
-1 5 3 4 0
-3 -4 0

dimacs 3sat
print dimacs
p cnf 52 5
1 -5 4 0
-1 5 22 0
3 4 -22 0
-3 -4 52 0
-3 -4 -52 0

to modele
print modele
(1 V -5 V 4)^
(-1 V 5 V 22)^
(3 V 4 V -22)^
(-3 V -4 V 52)^
(-3 V -4 V -52)
new modele
add -11
add 11
modele 3sat
solve modele
La fnc est insatisfaisable
new modele
add 5
modele sat3
Commande inconnue
modele 3sat
print modele
(5 V 289 V 248)^
(5 V 289 V -248)^
(5 V -289 V 248)^
(5 V -289 V -248)
new modele
add 4 5
modele 3sat
to dimacs
manque le nom de votre fichier
to dimacs test3
print dimacs
p cnf 256 2
4 5 256 0
4 5 -256 0

solve dimacs
La fnc est satisfaisable
Voici un domaine de validité :  : 
-4 vaut vrai
5 vaut vrai
-256 vaut vrai
```

## Lien entre les 2 programmes:

Si on revient sur notre premier programme, nous avons la commande suivante :
> dimacs


Comme vous l'avez devinez, le programme hashiwokakero va se servir du programme Dimacs pour transformer son modéle en un ModeleDimacs afin de créer un fichier dimacs


Des exemples et tests entre les 2 programmes pour trouver des solutions de ponts se ferra à la soutenance comme l'indique le polycopié de projet.


Mais profitions un peu pour en avoir un petit aperçu.


Utilisons un petit exemple rapide:


1          |2     |3          |4
-----------|------|-----------|------
**Ile a (1)**       |Vide  |Vide  |Vide
  **Ile b (1)**     | Vide |   Vide    | Vide
   Vide    | Vide |   Vide    | Vide
| Vide | Vide | Vide


Ne nous préoccupons pas de comment nous allons définir les id des ponts pour le prédicatID, cela sera expliqué à la soutenance.


Nous devons avoir une fnc avec aucune solution de ponts, testons donc : 

```
map 4
ile 1 1 1
ile 2 1 1
fnc all
Tous les ponts sont horizontaux ou verticaux entre eux
Aucun pont est à coté d'un autre pont
Aucun croisement entre iles
------------FNC FINALE------------
(-P(1,1))^
(-P(1',1'))^
(-P(5,5))^
(-P(5',5'))^
(-P(1,5))^
(-P(1',5'))^
(-P(5,1) V -P(5',1'))
dimacs



new dimacs hashiwokakero_fnc.dim
print dimacs
p cnf 550 7
-11 0
-110 0
-55 0
-550 0
-15 0
-150 0
-15 -150 0

solve dimacs
La fnc est satisfaisable
Voici un domaine de validité : 
-11 vaut vrai
-15 vaut vrai
-55 vaut vrai
-110 vaut vrai
-150 vaut vrai
-550 vaut vrai
```

Toutes les négations sont vrai donc nous avons bien aucune solution de ponts


