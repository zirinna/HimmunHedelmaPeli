# Aiheen kuvaus

##Aihe: 
Himmun Hedelmäpeli on bejeweled-tyylinen peli, jossa pelaaja yrittää saada vähintään kolme samanlaista hedelmää pystysuoraan tai vaakasuoraan riviin pelikentällä. Pelikenttä on täynnä satunnaisia hedelmiä. Pelaaja voi syödä hiirellä klikkaamalla kentältä hedelmän, jolloin syödyn hedelmän yläpuoliset hedelmät tipahtavat alaspäin. Kun pelaaja saa kolme samanlaista hedelmää riviin, ne katoavat kentältä ja pelaaja saa pisteitä. Pelaajalla on rajallinen määrä syöntikertoja.

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

##Rakennekuvaus
Pelini rakenne on varsin yksinkertainen: ohjelma rakentuu MainAppin lisäksi kahdesta logiikkaluokasta ja neljästä peliobjektiluokasta sekä käyttöliittymäluokasta. Pelilogiikkaluokalla on pelikenttä, joka koostuu ruuduista (Tile), jotka puolestaan voivat olla tyhjiä tai sisältää hedelmän. Hedelmiä on viisi erilaista, ja niiden määrää on tarvittaessa helppo lisätä, koska hedelmätyypit on tallennettu FruitType-enumeraattoriin.

GameLogic-luokka hoitaa suurimman osan pelin varsinaisesta logiikasta. Se mm. asettaa pelikentän pelin alussa täyteen satunnaisia hedelmiä, poistaa hedelmän kun sitä klikataan (hiiren kuuntelija on MainAppissa) ja etsii ja täyttää tyhjät ruudut (joko pudottamalla tyhjän ruudun yläpuolisia hedelmiä alaspäin tai jos yläpuolella ei ole hedelmiä, arpomalla uudet hedelmät). Tyhjiä ruutuja voi syntyä kahdella eri tavalla: pelaaja klikkaa hedelmää, jolloin se katoaa, tai pelaaja saa tarpeeksi monta samanlaista hedelmää riviin, jolloin pelilogiikka poistaa ne ja antaa pelaajalle pisteitä. Varsinainen samanlaisten hedelmien matchien etsiminen on GameRules-luokan metodien tehtävä: luokassa on erikseen metodi vaakasuuntaiselle ja pystysuuntaiselle matchille.

UserInterFace-luokka puolestaan hoitaa UI:n sekä pelin loppuruudun piirtämisen, ja sen drawTiles-metodi käy läpi pelikentän ruudut ja käskee niitä piirtää itsensä.

MainApp pyörittää JavaFX-pelilooppia ja hoitaa myös hiirenkuuntelijan. Pelilooppi pyörii 60 kertaa sekunnissa (tai ainakin mahdollisimman lähellä tätä nopeutta). Pelilooppi käskee UserInterFacea piirtämään tarvittavat asiat näytölle (drawThings) sekä käsittelee hiiren klikkaukset (handleUserInput) ja käskee pelilogiikkaa päivittää pelikentän (updateBoard). Hiirenkuuntelija tallettaa hiiren klikkaukset stackiin. Stacki on oikeastaan väärässä järjestyksessä koska uusin klikkaus on päällimmäisenä, mutta tämä ei ole ongelma, koska stackia puretaan 60 kertaa sekunnissa. On hyvin epätodennäköistä, että pelaaja onnistuu saamaan stackiin enemmän kuin yhden klikkauksen, ja jos näin tapahtuu, klikit ovat niin nopeita että pelaaja ei ehdi huomata, jos kaksi klikkia käsitelläänkin väärin päin.

