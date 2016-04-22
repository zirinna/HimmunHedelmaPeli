# Aiheen kuvaus

##Aihe: 
Himmun Hedelmäpeli on bejeweled-tyylinen peli, jossa pelaaja yrittää saada kolme samanlaista hedelmää pystysuoraan tai vaakasuoraan riviin pelikentällä. Pelikenttä on täynnä satunnaisia hedelmiä. Pelaaja voi syödä hiirellä klikkaamalla kentältä hedelmän, jolloin syödyn hedelmän yläpuoliset hedelmät tipahtavat alaspäin. Kun pelaaja saa kolme samanlaista hedelmää riviin, ne katoavat kentältä ja pelaaja saa pisteitä. Pelaajalla on rajallinen määrä syöntikertoja.

##Käyttötapauksia:
* Pelaaja syö kentältä hedelmän, hedelmä poistuu
* Pelaaja saa kolme samanlaista hedelmää riviin, pelaaja saa pisteitä
* Pelaajan siirrot loppuvat, peli loppuu

##Luokkakaavio
![luokkakaavio](luokkakaavio.png)

##Sekvenssikaavioita

###Pelaaja klikkaa hedelmää kentällä
![klikki](mouseclickonfruit.png)

###Pelaajan viimeinen siirto
![loppu](lastmove.png)

###Kentän päivittävä looppi
![loop](updateboardloop.png)

