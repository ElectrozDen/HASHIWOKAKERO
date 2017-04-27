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
* P(a,b) alors il existe un pont entre a et b (avec symétrie : P(a,b)P(b,a) )
* Il’(a’) alors a’ est l’île double de l’île définie par Il(a) (avec l’équivalence :P(a',b)P(b',a))
* f(a) renvoie n le nombre indiqué sur a
* L(a,b) alors a et b sont sur la même ligne
* C(a,b) alors a et b sont sur la même colonne
* Cr(a,b,c,d) alors a,b,c et d sont en situation de croisement

## Propriétés:
∀ ∃ ¬ ⋀ ⋁ ⇒ ≠
* Définition L(a,b):
	Il(a)⋀Il(b)⋀X(a)=X(b) 
* Définition C(a,b):
	Il(a)⋀Il(b)⋀Y(a)=Y(b)
* Définition Cr(a,b,c,d): 
 L(a,b)⋀C(c,d)⋀Y(a)<Y(c)<Y(b)⋀ X(c)<X(a)<X(d)
* Les ponts sont horizontaux ou verticaux:
 ∀a,b (Il(a)⋀Il(b)⋀a≠b ⋀P(a,b)⇒((L(a,b)⋁C(a,b))⋀(¬L(a,b)⋁¬C(a,b)))
* Il n’existe pas de pont reliant une île à elle-même ou à sa copie:
	∀a (Il(a)⇒P(a,a)⋀P(a',a')⋀P(a,a'))
* Les ponts ne se croisent pas:
	∀a,b,c,d ( Cr(a,b,c,d)⇒(¬P(a,b)⋁¬P(c,d)))
* Il y a deux ponts ou moins entre 2 îles :
 ∀a,b,a',b' ( Il(a)⋀Il(b)⋀Il'(a')⋀Il'(b')⋀a≠b⇒(P(a',b')⇒P(a,b))) 
* Le nombre de ponts partant de chaque île correspond au plus au nombre indiqué sur l’île:
 ∀a (Il(a)⇒¬(∃b1, ... ,bf(a)+1 ((Il(b1)⋁Il'(b1)) ⋀...⋀(Il(bf(a)+1)⋁Il'(bf(a)+1))⋀P(a,b1)⋀
 ... ⋀P(a,bf(a)+1)⋀a≠b1⋀...⋀b1≠b2⋀...⋀bf(a)≠bf(a)+1)))
* Le nombre de ponts partant de chaque île correspond au moins au nombre indiqué sur l’île:
 ∀a (Il(a)⇒(∃b1, ... ,bf(a) ((Il(b1)⋁Il'(b1)) ⋀...⋀(Il(bf(a))⋁Il'(bf(a)))⋀P(a,b1)⋀
 ... ⋀P(a,bf(a))⋀a≠b1⋀...⋀b1≠b2⋀...⋀bf(a)≠bf(a)-1)))
