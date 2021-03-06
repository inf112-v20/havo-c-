# Obligatorisk oppgave 3

## Deloppgave 1: Team og prosjekt

**Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamlead eller kundekontakt?**

Rollene i teamet fungerer fortsatt greit synes vi. Alle er fornøyd med egen rolle, og ingen har noe klager på andres roller i gruppen. Vi har begynt å få til mer spesifikke arbeidsoppgaver til hver enkelt, for å kunne gjøre det enklere for oss, ellers fungerer det bra siden sist oblig. Gruppen er fortsatt fornøyd med teamlead og kundekontakt. 

---
**Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?**
  
Vi har begynt å gi hverandre mer konkrete oppgaver til hvert gruppemøte, som gjør arbeidsfordelingen bedre, og gjør at vi kommer raskere igang med arbeidet. Teamet har ingen klager på hvordan dette blir gjort, annet enn at vi alltids kan bli enda flinkere til mer konkrete arbeidsoppgaver til hver enkelt.

---
**Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres. Dette skal handle om prosjektstruktur, ikke kode. Dere kan selvsagt diskutere kode, men dette handler ikke om feilretting, men om hvordan man jobber og kommuniserer.**

Til sist oblig gjorde vi en dårlig jobb med project board. Vi prøver også stadig å bli bedre på å tildele konkrete arbeidsoppgaver til alle frem til hvert gruppemøte. Det har ikke vært like mange møter den siste tiden nå på grunn av hele COVID-19 krisen. Når hverdagen blir helt snudd på hodet er det litt vanskelig å opprettholde gode vaner, noe vi har merket. Vi merker også at det er lettere å ha møter fysisk med hverandre enn over blant annet discord. Dette blir nok bedre med tiden, men foreløpig går det litt slapt. Vi har heller ikke hatt alt for hyppige møter, noe vi også skal klare å få til bedre med tiden. 

---
**Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.**

Hyppigere møter er noe vi bør fokusere på nå fremover, så vi får jobbet godt og jevnt med det. Som vanlig må vi bli litt bedre med konkrete arbeidsoppgaver. Selv om vi er blitt flinkere til det er det forbedringspotensiale der. 

---
**Forklar kort hvordan dere har prioritert oppgavene fremover. Legg ved skjermdump av project board ved innlevering**

Etter at vi har fått et kortsystem til å funke vil prioriteringene våre være å få rundesystem og å få flere spillere på brettet. 

---
**Hvordan fungerer gruppedynamikken og kommunikasjonen?**

Gruppedynamikken er super. Vi har ingen uenigheter som skaper noe dårlig stemning i gruppen, og ellers er vi fornøyd med hverandre. Vi har blitt litt bedre på klarere arbeidsfordeling siden sist. Kommunkiasjonen har vært bra. Vi har bra dialog på Slack, og avtaler gruppemøter derfra. På grunn av at Covid-19 hindrer oss fra å møte hverandre på grupperom etc. på Campus har vi opprettet en Discord server der vi har lydsamtaler når vi har møter. Det fungerer bra. 

## Deloppgave 2: Krav

**Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.** 

Holder på å lage en main menu, og prøver å implementere et kortsystem. 

---

**For hvert krav dere jobber med, må dere lage 1) ordentlige brukerhistorier, 2) akseptansekriterier og 3)
  arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester**

**Brukerhistorie:**

- Som spiller vil jeg ha en main menu, hvor jeg kan velge å enten starte spillet eller avslutte spillet, slik at spillet ikke starter før jeg ønsker det. 

**Akseptanskriterier**

- Spilleren må kunne velge enten start eller exit
- Spillet må starte når spilleren velger start
- Spillet må avsluttes når spilleren velger exit

**Arbeidsoppgaver:**
- Lage MainMenuScreen klasse
- Lage knapper som spiller kan trykke på
- Implementere et system som gjør at når spiller trykker på exit, så avsluttes spillet, og når spiller trykker på start, så starter spillet.

---
**Brukerhistorie:**

- Som spiller vil jeg velge kort jeg kan programmere roboten rundt med, slik at roboten blir styrt og oppfører seg slik kortene sier.

**Akseptansekriterier:**

- Spilleren må kunne velge kort.
- Spilleren må kunne velge rekkefølgen på valgte kort.
- Spilleren må kunne legge ned kortene slik at roboten blir programmert.
- Roboten må bevege seg etter kortene som blir lagt ned.

**Arbeidsoppgaver:**

- Lage en kortklasse.
- Lage forskjellige kort, med forskjellige instrukser.
- Implementere et system som gjør at spiller kan velge et kort.
- Utvikle systemet videre slik at spilleren kan velge flere kort blant en samling av kort.
- Gjøre slik at kortene påvirker bevegelsen til roboten.
- Få roboten til å bevege seg riktig etter en sekvens med kort er blitt lagt ut

---

**Forklar kort hvilke hovedkrav dere anser som en del av MVP og hvorfor. Hvis det er gjort endringer i rekkefølge utfra hva som er gitt fra kunde, hvorfor er dette gjort?**

Eneste endringer vi har gjort her er at vi har lagt til flerspillerfunksjoner på listen av MVP. Dette skyldes hovedsakelig at vi glemte at det burde være en MVP på de 2 første MVP listene våre. Ellers ser den helt lik ut som sist. Rekkefølgen på hva som er prioritert er vist på neste spørsmål.

-	Spillebrett
-	Roboter som kan plassers på bordet
-	Muligheten til å vinne
-	Kunne ta skade
-	Roboten skal kunne miste alle liv og dø
-	Et GUI som er funksjonelt for spillet (Denne er egentlig noe som hele veien kan forbedres, og kommer til å bli forbedret etterhvert som flere funksjoner blir lagt til.)
-	Velge kort fra en kortstokk
-	Programmere robot med kort
-	Nye kort hver runde
-	Et gameplay system som er runde basert
-	Flag på brettet (Denne er allerede gjort, da den var med i tutorial på hvordan vi får programmet til å kjøre)
-	Register at robot har vært innom flagg (Denne burde også være lenger oppe da den er allerede gjort.)
-	Vegger og hindringer
-	Rullebanen
-	Roboten skal kunne skyte laser
- flerspillerfunksjoner


**Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige
gang.**

**Følgende Oppgaver ble løst de forrige innleveringene**
- Spillebrett
- Roboter som kan plassers på bordet
- Flag på brettet
- Bevegelses system til brikken (Var opprinnelig et krav vi glemte å ta med på den opprinnelige kravlisten vår.)
- Muligheten til vinne
- Register at robot har vært innom flagg
- Kunne ta skade
- Roboten skal kunne miste alle liv og dø

**Følgende oppgaver ble prioritert til denne gang**
- Rullebanen
- Et GUI som er funksjonelt for spillet
- Velge kort fra en kortstokk
- Programmere kort
- Nye kort hver runde

**Av MVP listen ble proritert til denne gang trenger følgende punkter mer arbeid**
- Et GUI som er funksjonelt for spillet
- Velge kort fra en kortstokk
- Programmere kort
- Nye kort hver runde

Grunnen til at vi ikke har gjort oss helt ferdig skyldes hovedsakelig at vi skal de neste møtene og utover begynne med implementeringen av runde system og flerspillerfunksjoner. Brukeren kan nå trykke på kort (velge kort), og brikken skal endre seg der etter. Det som da gjenstår er at rekkefølgen kortene blir valgt, skal bli lagt inn i en sekvens, som blir startet av å trykke på «start round» knappen. Dette skal kobles sammen med rundesystemet til RobboRally, vi har derfor valgt sekvensen til kortene kundesystemet skal implementeres sammen da vi har et fungerende rundesystem. Merk at mye av logikken til kort, og kort sekvensen allerede er skrevet og ligger i klassen Card og CardDeck, foreløpig som ubrukt kode. Med andre ord er vår neste oppgave å lage et system for rundehåndtering.

Vi har også valgt å lage en main menu, dette er ikke ett direkte krav på MVP, men vi tenker det faller inn under ‘Et GUI som er funksjonelt for spillet’. Noe av tanken var også, å først lage en main menu for finne ut hvordan håndtere en GUI løsning, med knapper og mus input.
På ‘Et GUI som er funksjonelt for spillet’ trenger følgende knapper og bli koblet opp slik at de kan ta mus-input. Nå fungerer de mest som en mal, på hvordan vi har planlagt GUI løsningen.

	- Start round
	- Reset cards
	- Power down
	- Hjertene (Trenger å faktisk vise mengden liv igjen og ikke bare tre røde hjerter)
        - Vi mangler en healthbar som kan vise hvor mye skade roboten har tatt


**MVP oppgaver som enda gjenstår**
- Et gamplay system som er runde basert
- Vegger og hindringer
- Roboten skal kunne skyte laser
- flerspillerfunksjoner

**MVP som har blitt lagt til denne gangen**
- flerspillerfunksjoner (Vi glemte å ha flerspillerfunskjoner som MVP da listen ble først skrevet, så den ble lagt til MVP listen da det er et viktig krav for at spillet skal være ferdig.)




---
# Bugs
**Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).**

**Får fortsatt denne warningen:**
WARNING: An illegal reflective access operation has occurred

WARNING: Illegal reflective access by org.lwjgl.LWJGLUtil$3 (file:/Users/heineolsenhartvedt/.m2/repository/org/lwjgl/lwjgl/lwjgl/2.9.3/lwjgl-2.9.3.jar) to method java.lang.ClassLoader.findLibrary(java.lang.String)

WARNING: Please consider reporting this to the maintainers of org.lwjgl.LWJGLUtil$3

WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations

WARNING: All illegal access operations will be denied in a future releas

1. Brikken kan bevege seg av brettet (OutofBounds).
2. Power down knapp fungerer ikke (ennå).
3. Start runde knappen på MainMenuScreen fungerer ikke.
4. Liv (hjerter) er ikke fulstendig implementer og vil ikke endre seg ved skade.
5. Kortene fungerer ikke 100%. Vi har ikke laget et runde stystem ennå.
6. Ved bruk av kort, fungerer ingen special tiles (100%). Da vi bruker move 2 og 3 frem, skipper den special tiles på alle moves utenom det siste. Dette vil bli fikset da vi får et rundesystem. Special tiles fungerer normalt med bruk av QWE.

Alt dette vill bli fikset så fort som mulig.
## Refereat

**Torsdag 05.03.20** Ca. 1.5 timer. Heine, Vebjørn og Otso deltok.

-   Heine lagde interfaces til de ulike klassene.
-   Vi brukte tiden på å tenke på kortsystemet og hvordan implementere conveyor belts.
-   Andreas hadde problemer med Slack, og fikk ikke meldingene om avtalt møte, så han kom ikke. Derfor ble møtet gjort litt halvveis.


**Tirsdag 10.03.20** Ca. 2 timer. Alle deltok

-   Heine, Andreas og Vebjørn tok fatt på et meny-system. Dette var tidkrevende, og etter en del parprogrammering fikk vi ned kode som dessverre ikke funket etter dagens gruppetime. Prøver videre til neste gang. 
-   Otso satte igang med å implementere conveyor belts.
-   Vi tenkte også på hvordan vi skulle få implementert kortsystem. 

**Fredag 20.03.20** Ca. 2 timer. Otso, Andreas og Heine deltok

-   Otso og Andreas jobbet videre med å få til meny-system. Det nærmer seg et fullstendig system nå.
-   Heine skrev tekstoppgaver.
-   Vebjørn var med på briefing og gjennomgang av dagens agenda, før han stakk pga. han hadde obligatorisk innlevering i et annet fag som skulle inn om et par timer. 

**Mandag 23.03.20** Ca. 2 timer. Otso, Andreas og Heine deltok

- Andreas merget sin branch, der han hadde implementert en Main Menu, med master branchen. 
- Det ble skrevet mer til tekstoppgaven
- Jobbet med hvordan knappesystemet fungerte
- Det ble laget .png bilder av kortene
- Vi diskuterte hva vi måtte få gjort frem til innleveringsfristen

**Onsdag 25.03.20** Ca. 2 timer. Alle deltok

- Fikset main menu slik at startknappen faktisk startet spillet, og at exit avlsuttet
- Laget flere bilder til spillet
- Startet implementering av kortsystemet 
- Slettet unødvendig kode
- Forbedret GUI

**Fredag 27.03.20** Ca. 3 timer. Alle deltok

- Holdt på en god stund med et merge problem vi til slutt fikk fikset
- La til alle kortene i spillet
- Kan nå trykke på kortene
- Skrev ferdig ObligatoriskOppgave3.md
- Oppdaterte manual tests
- Oppdatert UML-kartet
- Lagt planer for hvordan vi skal fortsette prosjektet vårt

### Retrospektiv til denne innlevering
Covid-19 nedstengelsen av skolen gjorde at vi fikk noen uforutsette utfordringer. De punktene som kan trekkes frem fra denne innleveringen og som blir viktig til neste innleveringen er som følger:

Til denne innleveringen har mye av arbeidet blitt gjort over discord. Hvordan vi deler og diskuterer koden har er dermed blitt en noe større utfordring. Siden vi ikke i like enkelt kan dele/vise kode med andre har mye av løsningen vært å være tydelig med hva vi holder på med, men også pushe commits oftere. Dette har løst noe av problemet, men det øker sannsynligheten for merge konflikter. Vi har ikke hatt noen kjempe problemer med merge konflikter foreløpig, men dette er noe vi må ha fokus på fremover. 

Hvordan møter blir planlagt og hyppigheten til møtene var i første uken etter nedstengelsen noe uklar. Det gjorde at vi ikke fikk like mange møter som vi kanskje burde hatt. Dette ble vi bedre på etter hvert, og vi har nå et system for 3 møter i uken. Disse møtene skjer over discord og varer 1.5 timer til 2 timer.

### Kommunikasjon (Hvordan vi deler og overfører kunnskap)

Nå skjer omtrent all kommunikasjon over discord. Dette fungerer greit for kommunikasjons biten, men det å snakke om kode og dele koden er en større utfordring, sammenlignet med å sitte i samme rom diskutere koden fra en datamaskin. Dette løser vi med hyppigere push, dette løser noe av problemet. Når vi sitter sammen på discord, er alle tilgjengelige i voice chat, så det og spør om problemer og hjelpe hverandre med å forstå koden er noe vi bruker hele veien. Gruppedynamikken bra så vi har alle god forståelse for hvordan prosjektet blir planlagt, da vi snakke sammen om alle avgjørelser som blir tatt.
