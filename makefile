JC = javac
MARIA_DB_CLIENT_PATH = res/db/mariadb-client.jar
MARIA_DB_CLASS = res/db/org
JCFLAGS = -d build -classpath "$(MARIA_DB_CLIENT_PATH):$(MARIA_DB_CLASS)" -sourcepath "src" -encoding UTF-8 -implicit:none
JVM = java
JVMFLAGS = -classpath ".:res:build:$(MARIA_DB_CLIENT_PATH):$(MARIA_DB_CLASS)"
JARNAME = jeu.jar
ENTRY = fr.iutfbleau.izanicAissiGallego.projet.vue.main.Main
package = build/fr/iutfbleau/izanicAissiGallego/projet
src = src/fr/iutfbleau/izanicAissiGallego/projet




# VUE 
${package}/vue/main/Main.class: ${src}/vue/main/Main.java $(package)/vue/menu/MenuPrincipale.class
	${JC} ${JCFLAGS} ${src}/vue/main/Main.java
	
${package}/vue/menu/MenuPrincipale.class: ${src}/vue/menu/MenuPrincipale.java $(package)/vue/menu/TableauxScores.class $(package)/vue/menu/MenuJeu.class $(package)/vue/fenetre/MaFenetre.class  $(package)/vue/menu/MenuListener.class $(package)/modele/jeu/Serie.class $(package)/modele/jeu/SerialSerie.class $(package)/vue/jeu/Jeux.class
	${JC} ${JCFLAGS} ${src}/vue/menu/MenuPrincipale.java
	

${package}/vue/menu/MenuJeu.class: ${src}/vue/menu/MenuJeu.java $(package)/vue/fenetre/MaFenetre.class $(package)/vue/menu/MenuJeuListener.class $(package)/vue/menu/MenuListener.class
	${JC} ${JCFLAGS} ${src}/vue/menu/MenuJeu.java

${package}/vue/menu/MenuListener.class: ${src}/vue/menu/MenuListener.java $(package)/vue/fenetre/MaFenetre.class 
	${JC} ${JCFLAGS} ${src}/vue/menu/MenuListener.java
	
${package}/vue/menu/TableauScoreListener.class: ${src}/vue/menu/TableauScoreListener.java $(package)/vue/fenetre/MaFenetre.class 
	${JC} ${JCFLAGS} ${src}/vue/menu/TableauScoreListener.java
	
${package}/vue/menu/MyWindowListener.class ${package}/vue/fenetre/MaFenetre.class ${package}/vue/fenetre/MaFenetreJeux.class: ${src}/vue/menu/MyWindowListener.java ${src}/vue/fenetre/MaFenetre.java ${src}/vue/fenetre/MaFenetreJeux.java
	${JC} ${JCFLAGS} ${src}/vue/menu/MyWindowListener.java ${src}/vue/fenetre/MaFenetre.java ${src}/vue/fenetre/MaFenetreJeux.java

${package}/vue/menu/MenuJeuListener.class: ${src}/vue/menu/MenuJeuListener.java $(package)/vue/fenetre/MaFenetre.class 
	${JC} ${JCFLAGS} ${src}/vue/menu/MenuJeuListener.java
	
${package}/vue/menu/TableauxScores.class: ${src}/vue/menu/TableauxScores.java $(package)/vue/fenetre/MaFenetre.class $(package)/modele/db/BaseDonnee.class $(package)/vue/menu/TableauScoreListener.class
	${JC} ${JCFLAGS} ${src}/vue/menu/TableauxScores.java
	
${package}/vue/jeu/Jeux.class: ${src}/vue/jeu/Jeux.java $(package)/vue/fenetre/MaFenetreJeux.class $(package)/vue/jeu/TuileVue.class $(package)/modele/jeu/Paysage.class $(package)/vue/jeu/PaysageVue.class $(package)/controleur/PaysageListener.class 
	${JC} ${JCFLAGS} ${src}/vue/jeu/Jeux.java
	
${package}/vue/jeu/TuileVue.class: ${src}/vue/jeu/TuileVue.java $(package)/modele/jeu/Tuile.class 
	${JC} ${JCFLAGS} ${src}/vue/jeu/TuileVue.java

${package}/vue/jeu/PaysageVue.class: ${src}/vue/jeu/PaysageVue.java $(package)/modele/jeu/Paysage.class 
	${JC} ${JCFLAGS} ${src}/vue/jeu/PaysageVue.java

# MODELE 
${package}/modele/db/BaseDonnee.class: ${src}/modele/db/BaseDonnee.java
	${JC} ${JCFLAGS} ${src}/modele/db/BaseDonnee.java
	
${package}/modele/jeu/SerialSerie.class: ${src}/modele/jeu/SerialSerie.java $(package)/modele/jeu/Serie.class
	${JC} ${JCFLAGS} ${src}/modele/jeu/SerialSerie.java
	
${package}/modele/jeu/Tuile.class: ${src}/modele/jeu/Tuile.java
	${JC} ${JCFLAGS} ${src}/modele/jeu/Tuile.java
	
${package}/modele/jeu/Serie.class: ${src}/modele/jeu/Serie.java $(package)/modele/jeu/Tuile.class
	${JC} ${JCFLAGS} ${src}/modele/jeu/Serie.java

${package}/modele/jeu/Paysage.class: ${src}/modele/jeu/Paysage.java $(package)/vue/jeu/TuileVue.class
	${JC} ${JCFLAGS} ${src}/modele/jeu/Paysage.java

# CONTROLEUR 

${package}/controleur/PaysageListener.class: ${src}/controleur/PaysageListener.java
	${JC} ${JCFLAGS} ${src}/controleur/PaysageListener.java





run: 
	${JVM} ${JVMFLAGS} ${ENTRY}
	
clean:
	rm -rf build


extract-mariadb:
	rm -rf build/org
	unzip -qo $(MARIA_DB_CLIENT_PATH) -d build
	rm -rf build/Version.java.template
	rm -rf build/META-INF

jar: extract-mariadb
	make
	jar cvfm ${JARNAME} res/manifest.txt -C build/ . -C res/ .
	
javadoc:
	javadoc -d docs -sourcepath src -subpackages fr.iutfbleau.izanicAissiGallego.projet -classpath res/db/mariadb-client.jar
