# Obligatorisk oppgave 3

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

Ligger i deliverables som filen uml_oblig4.png

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

Holder på med multiplayer. Etter en stund med å prøve å forstå oss på hvordan online spill ville fungert, 
droppet vi den idéen og bestemte oss for å lage en AI. Har også prioritert at vegger og hindringer stopper roboten, og 
å få til lasersystem. 

---

**For hvert krav dere jobber med, må dere lage 1) ordentlige brukerhistorier, 2) akseptansekriterier og 3)
  arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester**

**Brukerhistorie:**

- Som spiller vil jeg spille mot andre roboter

**Akseptanskriterier**

- Spilleren kan velge én av tre vanskelighetsgrader av AI å spille mot
- "Easy" vil gjøre trekk random
- "Medium" vil gjøre x
- "Hard" vil gjøre x

**Arbeidsoppgaver**

- Lage AI klasse
- Lage noe greier på den andre klassen lmao

---
**Brukerhistorie**

- Som spiller vil jeg at robotene skal skyte laser etter hver runde, for å få en optimal RoboRally opplevelse

**Akseptanskriterier**

- Når runden er over skal alle roboter skyte laser fra fronten
- Dersom en robot står vendt mot deg i slutten av en runde vil du ta 1 skade

**Arbeidsoppgaver**

- iejrgbfdsk

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
-   AI mulitplayer 

# Bugs
**Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).**

**Får fortsatt denne warningen:**
WARNING: An illegal reflective access operation has occurred

WARNING: Illegal reflective access by org.lwjgl.LWJGLUtil$3 (file:/Users/heineolsenhartvedt/.m2/repository/org/lwjgl/lwjgl/lwjgl/2.9.3/lwjgl-2.9.3.jar) to method java.lang.ClassLoader.findLibrary(java.lang.String)

WARNING: Please consider reporting this to the maintainers of org.lwjgl.LWJGLUtil$3

WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations

WARNING: All illegal access operations will be denied in a future releas

