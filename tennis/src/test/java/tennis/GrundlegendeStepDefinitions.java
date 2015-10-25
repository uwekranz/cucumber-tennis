package tennis;

import cucumber.api.java.Before;
import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Und;
import cucumber.api.java.de.Wenn;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static tennis.AufschlagRolle.AUFSCHLAEGER;
import static tennis.AufschlagRolle.RUECKSCHLAEGER;

public class GrundlegendeStepDefinitions {

    private Match match;

    @Before
    public void setUp() {
        Match.setInstance(new Match());
        match = Match.getInstance();
    }

    @Und("^(\\w+) schlägt auf$")
    public void spieler_schlägt_auf(String spielerName) throws Throwable {
        match.setzteAufschlagRolle(spielerName, AUFSCHLAEGER);
    }

    @Wenn("^der Spielstand zu (\\d+) zu (\\d+) gewechselt hat$")
    public void es_zu_steht(int aufschlaegerSpiele, int rueckschlaegerSpiele) throws Throwable {
        MatchSpielstand neuerSpielstand = erzeugeNeuenSpielstand(aufschlaegerSpiele, rueckschlaegerSpiele);
        match.aktualisiereSpielstand(neuerSpielstand);
    }

    private MatchSpielstand erzeugeNeuenSpielstand(int aufschlaegerSpiele, int rueckschlaegerSpiele) {
        MatchSpielstand spielstand = match.getMatchSpielstand();
        spielstand.getSpielstand(AUFSCHLAEGER).setSpiele(aufschlaegerSpiele);
        spielstand.getSpielstand(RUECKSCHLAEGER).setSpiele(rueckschlaegerSpiele);
        return spielstand;
    }

    @Angenommen("^es steht (\\d+) zu (\\d+) im aktuellen Satz$")
    public void es_steht_(int aufschlaegerPunkte, int rueckschlaegerPunkte) throws Throwable {
        match = Match.getInstance();
        SpielerSpielstand aufschlaegerSpielstand = new SpielerSpielstand(aufschlaegerPunkte);
        SpielerSpielstand rueckschlaegerSpielstand = new SpielerSpielstand(rueckschlaegerPunkte);
        Spieler spieler1 = new Spieler("spieler1", AUFSCHLAEGER, aufschlaegerSpielstand);
        Spieler spieler2 = new Spieler("spieler2", RUECKSCHLAEGER, rueckschlaegerSpielstand);
        match.setSpieler(spieler1,spieler2);
    }

    @Wenn("^der (Aufschläger|Rückschläger) einen Punkt gewinnt$")
    public void der_Aufschläger_einen_Punkt_gewinnt(String rolle) throws Throwable {
        String nameDerAufschlagrolle = rolle.replace("ü","ue").replace("ä", "ae").toUpperCase();
        AufschlagRolle aufschlagRolle = AufschlagRolle.valueOf(nameDerAufschlagrolle);
        match.passeSpielstandAnPunktgewinnAn(aufschlagRolle);
    }

    @Dann("^steht es (\\d+) zu (\\d+)$")
    public void steht_es_(int punkteAufschlaeger, int punkteRueckschlager) {
    }

    @Wenn("^der aktuelle Spielstand lautet$")
    public void der_aktuelle_Spielstand_lautet(List<SpielerSpielstand> spielerSpielstaende) throws Throwable {
        MatchTeilnehmer matchTeilnehmer = match.getMatchTeilnehmer();
        matchTeilnehmer.getSpieler(AUFSCHLAEGER).setSpielstand(spielerSpielstaende.get(0));
        matchTeilnehmer.getSpieler(RUECKSCHLAEGER).setSpielstand(spielerSpielstaende.get(1));
    }

    @Wenn("^der Spielstand gewechselt hat, zu$")
    public void der_Spielstand_gewechselt_hat_zu(List<SpielerSpielstand> spielerSpielstaende) throws Throwable {
        MatchTeilnehmer matchTeilnehmer = match.getMatchTeilnehmer();
        HashMap<Spieler, SpielerSpielstand> spielstaende = new HashMap<Spieler, SpielerSpielstand>();
        spielstaende.put(matchTeilnehmer.getSpieler(AUFSCHLAEGER),spielerSpielstaende.get(0));
        spielstaende.put(matchTeilnehmer.getSpieler(RUECKSCHLAEGER),spielerSpielstaende.get(1));
        MatchSpielstand neuerSpielstand = new MatchSpielstand(spielstaende);
        match.aktualisiereSpielstand(neuerSpielstand);
    }

    @Dann("^schlägt Djokivic auf$")
    public void schlägt_Djokivic_auf() throws Throwable {
        Spieler bestimmterAufschlaeger = match.bestimmeAufschlaeger();
        assertThat(bestimmterAufschlaeger.equals(new Spieler("Djokovic", AUFSCHLAEGER)),is(true));
    }

}
