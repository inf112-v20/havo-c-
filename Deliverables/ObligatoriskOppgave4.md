# Obligatorisk oppgave 4

## Deloppgave 1: Team og prosjekt

**Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamlead eller kundekontakt?**

Rollene i teamet fungerer like greit som til sist innlevering. Alle er fortsatt fornøyd med egen rolle, og ingen har noe klager på andres roller i gruppen. 
Vi har fortsatt å tildele mer spesifikke oppgaver til hver enkelt.
Gruppen er fortsatt fornøyd med teamlead og kundekontakt, så vi trenger ikke å opptadete rollene nå før fristen.

---
**Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? 
Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?**

Nå som vi nærmer oss et mer ferdig produkt, er det lettere å få til konkrete oppgaver til hvert gruppemøte.
Dette gjør at vi kan fokusere på den tingen hele møtet, og ting blir gjort mer effektivt enn tidligere. 
Nå som dette er et faktum, har ikke teamet noe å klage på mtp prosjektmetodikk. 

---
**Gjør et retrospektiv hvor dere vurderer prosjektet har gått. Hva har dere gjort bra, hva hadde dere gjort
annerledes hvis dere begynte på nytt?**

Prosjektet har gått relativt greit synes vi. Med unntak av litt treig start og litt skjev fordeling av commits, 
synes vi dette har gått bra. Det har også vært læringsprosess og vi har lært mye om hvordan å jobbe i team. 
Hvis vi skulle begynt på nytt hadde vi vel hatt klare roller og arbeidsoppgaver fra dag 1, og komt igang med 
selve prosjektet så fort som mulig. Å være godt forberedt mtp hvordan github og libgdx fungerer har vi lært at
er veldig lurt før vi skal begynne å jobbe med det.

---
**Legg ved skjermdump av project board ved innlevering. Sørg for at det er oppdatert med siste status
  ved innlevering.**

Ligger i deliverables som filen projectBoard_oblig4.png

---
**Hvordan fungerer gruppedynamikken og kommunikasjonen nå i forhold til i starten? Hvordan påvirket
karantene og nedstengning teamet og fremdriften?**

Gruppedynamikken fungerer mye bedre nå som vi kjenner hverandre bedre. I begynnelsen kunne gruppemøtene være
litt stive og lite pratsomme, men etter et par uker når vi visste litt om hva vi var gode på ble det lettere
å spørre om hjelp og samarbeid ble bedre. Kommunikasjon utenom på gruppemøter har fungert bra hele veien, 
siden alle har vært aktiv på slack (og senere på Discord), så ingen har noe klager på det. Karantenen har 
ikke påvirket oss alt for mye, siden vi alle ble enige om å møtes på voice call på Discord til møtene. Dette 
har også ført at vi lettere kan kan ha møter oftere, siden vi alle er stuck inne med pcen uansett. 

## Deloppgave 2: Krav

**Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.** 
Til denne innleveringen er disse MVP-kravene prioritert, mer info kommer under brukerhistorier.
- Et gamplay system som er runde basert
- Vegger og hindringer
- Roboten skal kunne skyte laser
- Game Lobby
- Game over skjerm
- Victory skjerm
- AI
- Velge flere maps

Disse kravene trengte finpuss fra sist innlevering og fikk dermed nødvendige forbedringer:
- Velge kort fra en kortstokk
- Programmere kort
- Nye kort hver runde


---

**For hvert krav dere jobber med, må dere lage 1) ordentlige brukerhistorier, 2) akseptansekriterier og 3)
  arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester**
  

**Brukerhistorie: Motspiller/spillere**

- Som spiller vil jeg spille mot andre roboter slik at spilleren ikke kun beveger seg rundt i et brett uten motstand.

**Akseptanskriterier**

- Det er en eller flere motstandere på brettet
- Mostander kan vinne spillet
- Mostander kan påvirke spiller skade/kollisjon

**Arbeidsoppgaver**

- Lage AI klasse
- Få AI som kan bevege seg
- Lage AI har liv og tar skade
- Lage AI kan respawne 


---
**Brukerhistorie: Laser**

- Som spiller vil jeg at robotene skal skyte laser etter hver runde, for å få et gøyere gameplay

**Akseptanskriterier**

- Når runden er over skal alle roboter skyte laser fra fronten
- Dersom en robot står vendt mot deg i slutten av en runde vil du ta 1 skade

**Arbeidsoppgaver**

- Implementere laser på gameboard
- lage skyt laser funskjon i hver player/robot
- Få doTurn() til å skyte laser i riktig rekkefølge

---
**Brukerhistorie: Game lobby**

- Som spiller vil jeg ha en game lobby screen, så jeg kan ha noen valg for hvordan gamet skal bli

**Akseptanskriterier**

- Når spiller har trykket på start, skal det komme opp en ny meny med valg
- Spiller skal få valg om (og få velge) hvor mange motspillere å spille mot, og hvilke bane å spille på
- Når spiller trykker på start skal spillet begynne med de valgene som er tatt
- Når spiller trykker på back skal det gå tilbake til der hvor det står start og exit

**Arbeidsoppgaver**

- Sette opp en klasse som implementerer screen
- Sette opp de rette grafikkene
- Trenger touch fields, så spiller kan trykke på de ulike knappene
- Linke game lobby til main game

---
**Brukerhistorie: Game over/Victory - Screen**

- Som spiller ønsker jeg en indikasjon på om jeg har vunnet eller tapt.

**Akseptanskriterier**

- Når spilleren vinner, skal det komme opp en victory screen
- Når spilleren taper, skal det komme opp en game over screen

**Arbeidsoppgaver**

- Lage MainVictoryScreen
- Lage MainGameOverScreen

---
### Brukerhistorie som ikke 100% er implementert riktig

**Brukerhistorie: AI som ikke dreper seg selv**
- Som spiller vil jeg kunne spille mot et AI som ikke velger kort som dreper seg selv, slik at jeg som spiller ikke mister alle fiendene mine til deres dårlige kort valg.

**Akseptansekriterier:**
- Roboten faller ikke ned i hull.
- Roboten går ikke av banen.
- Roboten tar med i beregningen av rullebaner da hull og banens grenser sjekkes for.

**Arbeidsoppgaver:**

- Sette opp en forsøkskanin («guineapig»).
- Sjekke om neste kort sender AI i hull.
- Sjekke om neste kort sender AI ut av banen.
- Sjekke om hull og ut av banen inntreffer, etter at rullebanen er aktivert.

 **Hva som mangler til denne innleveringen:**
 - Etter siste doTurn update velger nå AI bare tilfeldige kort. Den har funket tidligere, altså valgt kort som ikke dreper AI, men den siste updaten til doTurn ble ansatt som viktigere. AI velger nå kort som dreper AI
 
---

**Brukerhistorie: Kort verdisystem**
- Som spiller vi jeg at kortene jeg velger skal bli prioritert i en synlig rekkefølge slik at det er lettere å planlegge hvilken rekkefølge kortene skal spilles i.


**Akseptanskriterier**
 - Kortene spilles i riktig rekkefølge.
 - Som spiller vi jeg at kortene jeg velger skal bli prioritert i en synlig rekkefølge slik at det er lettere å planlegge hvilken rekkefølge kortene skal spilles i.
 
 **Arbeidsoppgaver**
 - Sette opp Card klassen slik at den får en owner og en value per kort.
 - Designere riktig owner og verdi i CardDeck klassen.
 - Sette opp et system som sorter kortene i riktig rekkefølge.
 - Sette soteringsystemet inn i doTurn() slik at kortene spilles i riktig rekkefølge
 
 **Hva som mangler til denne innleveringen:**
 - Sette soteringsystemet inn i doTurn() slik at kortene spilles i riktig rekkefølge.
 ---
 **Bruker historie: Periode System (doTurn())**
 - Som spiller vil jeg at kortene og hendelsene på brettet skal skje i den rekkefølgen beskrevet i regelboken til Robo Rally, slik at jeg vet når og hvorfor ting skjer, men også slik at jeg kan planlegge der etter.
 
 **Aksepteneskriterier**
 - Alt skjer i riktig rekkefølge.
 - Vi kan se hvilken rekkefølge det skjer i.
 
 **Arbeidsoppgaver:**
 - Sette opp doTurn() slik at den går 5 ganger for hver runde.
 - La alt skje med noe delay, slik at brukeren kan se hvilke rekkefølger ting skjer i, istedenfor å bare teleporters rundt.
 - Binde sammen kortsortering og banen slik at de riktige kortene og bane elementene går av til riktig tid.
 
 **Hva som mangler til denne innleveringen:**
 - Binde sammen kortsortering og banen slik at de riktige kortene og bane elementene går av til riktig tid.
 ---
 **Brukerhistorie: Et smartere AI**
 - Som spiller ønsker jeg et AI som utfordrer meg slik at det å vinne spillet faktisk blir vanskelig.
 
 **Akseptansekriterier:**
 - AI må kunne finne den beste retningen.
 - AI finner de nærmeste rutene og plukke ut uaktuelle ruter.
 - Koblet sammen med monkeyAI så hvis at den ikke finner kort som er smartere velger den kort som ikke dreper.
 
 **Arbeidsoppgaver:**
 - Sette opp en smarterAI klasse
 - Funksjonene som finne smarteste retning
 - Funksjon som finner alle rutene roboten kan gå til med neste kort.
 - Finne de rutene som er nærmere flagg, enn posisjonen til roboten.
 - Koble alt sammen med MonkeyAI slik den først sjekker etter smartere tiles og retning, hvis ingen smartere retning eller rute er mulig finn et kort som ikke dreper.
 
 **Hva som mangler til denne innleveringen:**
 - Koble alt sammen med MonkeyAI slik den først sjekker etter smartere tiles og retning, hvis ingen smartere retning eller rute er mulig finn et kort som ikke dreper.
 ---
 **Brukerhistorie: hvis roboten tar skade og kort brenner fast**
 - Som bruker vil jeg at skade skal begrense mine kort og hvis for mye skade skal kort brennes fast i registeret slik at skade har en konsekvens for spilleren.
 
 **Akseptansekriterier:**
 - Kort brennes fast.
 - Skade begrenser kort valg.
 - Hvis spiller får hp tilbake øker antall kort valg og de kortene som brennes fast er ikke lenger brent fast.
 
  **Arbeidsoppgaver:**
- System som begrenser kort valg for spilleren.
- System som begrenser kort valg for AI.
- Kort brennes fast for spiller.
- Kort brennes fast for AI.
- Om robot får hp tilbake blir fast brente kort resett.
- Om robot får hp tilbake blir antall valg økt.
**Hva som mangler til denne innleveringen:**
Vi fikk ikke til å tilfredsstille akseptansekriterier:
- Hvis spiller får hp tilbake øker antall kort valg og de kortene som brennes fast er ikke lenger brent fast.
Beskrivelse av hvorfor dette skjer kan finnes i bugg forklaringen og manuelle tester, men endel av koden her er kommentert ut slik at det ikke ødelegger spillet.


 
---
**Forklar kort hvilke hovedkrav dere anser som en del av MVP og hvorfor. Hvis det er gjort endringer i 
rekkefølge utfra hva som er gitt fra kunde, hvorfor er dette gjort?**

Eneste endringer vi har gjort her er at vi har presisert AI multiplayer i stedet for flerspillerfunksjoner, 
siden vi nå er sikre på at det er det vi skal gå for (og ikke online mulitplayer) Ellers ser den helt lik ut som sist. 
Rekkefølgen på hva som er prioritert er vist på neste spørsmål.

-	Spillebrett
-	Roboter som kan plassers på bordet
-	Muligheten til å vinne
-	Kunne ta skade
-	Roboten skal kunne miste alle liv og dø
-	Et GUI som er funksjonelt for spillet 
-	Velge kort fra en kortstokk
-	Programmere robot med kort
-	Nye kort hver runde
-	Et gameplay system som er runde basert
-	Flag på brettet 
-	Register at robot har vært innom flagg (Denne burde også være lenger oppe da den er allerede gjort.)
-	Vegger og hindringer
-	Rullebanen
-	Roboten skal kunne skyte laser
-   AI mulitplayer 

Vi har prøvd etter beste evne å få til flere av kravene fra kunde:
- Når spiller tar skade reduseres antall kort den kan velge blant.
- Når spiller tar skader brennes kort fast.
- Flere vanskelighetsgrader på AI
- Victory screen
- Game over screen
- Game lobby

Av nevnte krav er:
- Når spiller tar skade reduseres antall kort den kan velge blant.
- Når spiller tar skader brennes kort fast.
- Flere vanskelighetsgrader på AI
I en tilstand der det enda ikke fungerer helt riktig. Vi har beskrevet mer detaljert hva som mangler på disse kravene i brukerhistorie seksjonen over.

Vi har også kodet mye av koden for flere AI, men vi kom ikke langt nok til å implementere hvordan de spawner inn, så maks antall enemies er foreløpig 1.



# Bugs
**Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).**

**Får fortsatt denne warningen:**
WARNING: An illegal reflective access operation has occurred

WARNING: Illegal reflective access by org.lwjgl.LWJGLUtil$3 (file:/Users/heineolsenhartvedt/.m2/repository/org/lwjgl/lwjgl/lwjgl/2.9.3/lwjgl-2.9.3.jar) to method java.lang.ClassLoader.findLibrary(java.lang.String)

WARNING: Please consider reporting this to the maintainers of org.lwjgl.LWJGLUtil$3

WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations

WARNING: All illegal access operations will be denied in a future releas

---
- Får ikke loopen til bakgrunnsmusikken i spillet til å slå seg av (med mindre du avslutter programmet), som 
fører til at det spilles på nytt over den forrige loopen om du trykker next game etter å ha vunnet/tapt. 

---
- Både mapet "Hell" og "Cluster Cross" har tiles som ikke helt fungerer, og blir buggy

---
 Når du velger 5 kort, tar skade ned til 1 hp, og trykker start round, vil spillet crashe

--
- Når du velger 5 kort, tar skade ned til 0 hp, og respawner, vil spillet crashe

---

## Referat

**Onsdag 01.04.20** ca. 2 timer, alle møtte opp
- Knappene “Reset Cards”, “Start Round” og “Power Down” fikk hvert sitt touch field.
- Vi la en plan på hvordan vi må jobbe frem over for å nå alle målene.
- Koble kortene systemene opp til GUI var en ting som måtte gjøres ASAP.
- Få et periodesystem til å virke måtte også gjøres ASAP.
- Vi lagte en DoTurn funksjon som var veldig simpel for å få en ide på hvordan det ville fungere.

**Fredag 03.04.20** ca. 2 timer, alle møtte opp
- Prøvde å legge til funksjonalitet til knappene, hadde en del problemer her.
- Endret slik at hjertene nå forsvinner etter hvor mange liv brukeren har igjen.
- Mer logistikk på hvordan periodesystem, kortsystem, flerspillerfinskjoner skal fungere.
- Generelt var energi nivået ganske labert dette møte da de fleste har fått en døgnrytme som tilsier at kl 10:00 er enda veldig tidlig om morgenen.

**Mandag 06.04.20**: ca. 1.5 timer, Andreas, Otso, Heine
- Fikk fikset problemene vi hadde med knappene.
- Nå viste kortene hvilken rekkefølge bruekrene har valg kortene i.
- La til flere kort grafisk.
- Endret på koden for de nye kortene.
- Ordnet et merge problem, commitsene ble noe uryddige her da vi hadde fått merge feil.

**Onsdag 08.04.20**: ca. 2 timer, alle møtte opp
- Det ble laget en healthbar, (9 bilder)
- Det ble ryddet i asset mappen 
- Vi gikk en i gang med å gjøre mye refactorering in mainGameScreen
- Vi kom ganske langt i å koble GUI opp til kortsystemet.

**Fredag 10.04.20**: ca. 2 timer, alle møtte opp
- Implementert healthbar inn med GUI
- Koblet ferdig GUI opp til kortsystemet
- Implementerte lasere som er på mapet
- Startet å skrive ObligatoriskOppgave4.md
- Laget klassen GUI.java for å håndtere all GUI der

**Mandag 13.04.20**: ca. 2 timer. Alle møtte opp
- Laget og implementerte diverse sound effects
- Forbedret måten player tar damage
- Startet implementering av doTurn()
- checkHoles() er nå knuttet til player Move()

**Fredag 17.04.20**: ca. 1 time. Alle møtte opp
- Laget en ny branch for å prøve å implementere online multiplayer 
- Implementerte et wallcheck system og knyttet det til Move(), så det skulle funke bra
- Planla og tenkte på hvordan vi skulle få til online multiplayer

**Tirsdag 21.04.20**: ca. 1,5 timer. Alle møtte opp
- Designet noen buttons
- Tenkte mye på hvordan vi skulle fortsette med online multplayer. Var vanskeligere enn først antatt
- Diskuterte også hva annet vi trengte å få gjort fremover 

**Fredag 24.04.20**: ca. 2 timer. Alle møtte opp
- Begynte å få second thoughts angående online multiplayer. Kanskje AI ville vært bedre
- Player må nå gå igjennom alle flaggene i rekkefølge for å vinne
- Diskuterte hvordan vi skulle gå frem med AI mulitplayer

**Mandag 27.04.20**: ca. 2 timer. Alle møtte opp
- Prototype til powerdown er implementert
- Startet implementeringen av AI multiplayer
- Skrev mer av ObligatoriskOppgave4.md

**Onsdag 29.04.20**: ca. 2 timer. Alle møtte opp
- Fikset bugs på GUI i doTurn()
- Endringer på AI
- Laget victory og lobby screen (ikke ferdig implementert)

**Fredag 01.05.20**: ca. 2 timer. Alle møtte opp
- Implementerte AI inn i doTurn()
- La til mer lydeffekter. Har nå bakgrunnsmusikk 
- Fikset problem som oppsto når man pushet/ble pushet inn i en vegg

**Mandag 04.05.20**: ca. 4 timer. Alle møtte opp
- Implementerte en game lobby. Spiller kan nå velge antall motspillere (spillet fungerer foreløpig bare med 1),
og kan også velge hvilke bane (har 2 valgmuligheter her)
- Forbedringer til AI
- Repair tiles er nå implementert
- Spiller kan nå vinne ved å bruke kortene (tidligere kunne spiller bare vinne ved piltaster)
- Begynte forberedelse til presentasjon + demo

**Tirsdag 05.05.20**: ca 2 timer. Alle møtte opp
- Fikset scaling problem på den nye banen.
- Plasserte spawn til AI et annet sted
- Koblet sammen kort og AI til checkforspecialtiles
- Flagg er nå spawn points
- Skaffet oss bedre oversikt over hva som mangler til innlevering fredag

**Onsdag 06.05.20**: ca 5 timer. Alle møtte opp
- Startet implementering av lasersystem
- Fortsatte å prøve å få til en smartere AI
- Skrevet brukerhistoreier etc. 

**Torsdag 07.05.20**: ca 9 timer. Andreas, Otso og Heine møtte opp.
- Ferdig implementerte lasersystem
- Skrev noe tester
- Implementerte vicyory og game over screen
- Fikset vanlig AI, smartere AI fortsatt in the works
- Kan nå se bedre hvilke kort som er valgt, skal hjelpe når kort blir brent fast
- Startet implementering av kort som brennes fast når spiller tar damage + får mindre kort i henhold til damage

**Fredag 08.05.20**: 6 timer. Alle møtte opp
- doturn fungerer odentlig 
- MYE bugfixing
- Gjort koden penere
- Skrevet ferdig obligatoriskoppgave4.md

### Retrospektiv til denne innlevering
I likhet med det som ble gjort til forrige innlevering, så har vi problemer som oppstår når vi ikke får kodet sammen.

Arbeidet vårt foregår fortsatt over discord, og problemene/utfordringene med det er de samme som sist. Vi har vært bedre 
til å bruke screen share for å få til en form for parprogrammering når noen står fast på noe. Dette har hjulpet oss, 
og har løst noen problemer. Vi har også klart å unngå store merge konflikter, og er generelt blitt flinkere til å løse disse
problemene. Så alt i alt er fortsatt problemene/utfordringene med discord der fortsatt, men vi er blitt flinkere til å 
løse disse nå til denne innleveringen. 

Etter at vi fikk ned systemet på 3 møter i uken, en stund etter nedstengningen, har vi klart å holde det oppe veldig godt. 
Dette fungerer veldig bra, spesielt siden vi som regel er inne uansett, og det ikke er så mye annet som skjer for tiden. 
Nå frem mot denne innleveringen har vi hatt litt hyppigere møter og kanskje litt lengre, for å prøve å få gjort så mye som
mulig ferdig før fristen. 

Nå som prosjektet er ferdig, ser vi at det er en del vi burde gjort annerledes. Det meste av dette er hvordan vi arbeidet 
i starten av prosjektet. De første ukene hadde vi svært få møter utenom obligatorisk møte, noe som gjorde at vi hang litt 
etter der vi burde vært. Dette har hjemsøkt oss nå frem mot denne siste innleveringen, i og med at vi fortsatt henger litt
bak. Vi har fått gjort ferdig våre egne MVP, men når vi (litt for sent) begynte på kunde sine MVP merket vi at det var lite
tid igjen, og en del av det er svært buggy/ikke brukbart. Dette er noe som hadde vært unngått dersom vi hadde jobbet mer 
effektivt tidligere, slik at vi ikke hadde hengt bak skjema. 

### Kommunikasjon (Hvordan vi deler og overfører kunnskap)

All kommunikasjon skjer fortsatt over Discord. Dette fungerer fortsatt greit for kommunikasjonsdelen, men det er så klart 
fortsatt ikke like optimalt som å sitte sammen og kode. Selv om vi er blitt flinkere å løse problemer over Discord (med 
for eksempel screen share), er det fortsatt ikke helt optimalt. Alle er fortsatt tilgjengelige på voice chat, så når vi 
lurer på noe er det bare for oss å spørre i vei. Gruppedynamikken blir bare bedre, og vi kan lett snakke om og spørre om 
koden dersom noe er litt uklart. Større avgjørelser som blir tatt med tanke på koden blir diskutert og gjennomgått med alle
før de blir tatt. 
