#Sovellustason testaus

##testitapaus: Raivokas pelikentän kliksuttelu hiirellä
**pvm:** 8.5.2016
**mitä pitäisi tapahtua:** Peli ei saa kaatua ja hedelmien pitää poistua oikein.
**mitä tehtiin:** Avasin uuden pelin ja kliksuttelin pelikenttää sekä hiiren vasemmalla että oikealla niin nopeasti kuin pystyin.
**mitä tapahtui:** Peli ei kaatunut, mutta kun siirrot ovat loppu ja peli loppuu, game over -ilmoitus lätkähtää ruudulle ennen kuin kaikki hedelmät ovat pudonneet alas.
**tuomio:** Näyttää vähän tyhmältä, mutta ei ole varsinaisesti bugi

##testitapaus: Hiirellä klikkaaminen varsinaisen pelikentän ulkopuolella
**pvm:** 8.5.2016
**mitä pitäisi tapahtua:** Ei mitään, peli ei saa kaatua.
**mitä tehtiin:** Avasin uuden pelin ja kliksuttelin pelikentän ulkopuolista aluetta peli-ikkunassa hiiren vasemmalla ja oikealla.
**mitä tapahtui:** Ei tapahtunut mitään, peli ei kaatunut.
**tuomio:** Toimii oikein.

##testitapaus: Hiiren painikkeen painaminen alas kentällä ja sitten hiiren kursorin vetäminen ulos peli-ikkunasta
**pvm:** 8.5.2016
**mitä pitäisi tapahtua:** Ei mitään, peli ei saa kaatua eikä hedelmiä kuulu poistua laudalta.
**mitä tehtiin:** Avasin uuden pelin. Painoin hiiren vasemman painikkeen kentällä olevan hedelmän päällä alas ja raahasin sitten kursorin ulos peli-ikkunasta. Toistin hiiren oikealla.
**mitä tapahtui:** Ei mitään, peli ei kaatunut eikä hedelmä poistunut.
**tuomio:** Toimii oikein.

##testitapaus: Peli-ikkunan koon muuttaminen
**pvm:** 8.5.2016
**mitä pitäisi tapahtua:** Peli ei kaadu.
**mitä tehtiin:** Avasin uuden pelin. Suurensin ja pienensin peli-ikkunaa ottamalla kiinni ikkunan kulmasta hiirellä ja vetämällä.
**mitä tapahtui:** Peli ei kaadu.
**tuomio:** Toimii oikein.