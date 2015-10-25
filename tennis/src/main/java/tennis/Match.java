package tennis;

import tennis.exceptions.AufschlaegerIstNochNichtBestimmt;

import java.util.HashMap;

import static tennis.AufschlagRolle.AUFSCHLAEGER;
import static tennis.AufschlagRolle.RUECKSCHLAEGER;

public class Match {

    private static Match instance = null;

    MatchTeilnehmer matchTeilnehmer;

    public static Match getInstance() {
        if(instance == null)
            instance = new Match();
        return instance;
    }

    public Match() {
        matchTeilnehmer = new MatchTeilnehmer();
    }

    public static void setInstance(Match instance) {
        Match.instance = instance;
    }

    public Spieler bestimmeAufschlaeger() throws AufschlaegerIstNochNichtBestimmt {
        return matchTeilnehmer.getSpieler(AUFSCHLAEGER);
    }

    public void passeSpielstandAnPunktgewinnAn(AufschlagRolle rolleDesPunktendenSpielers) {
        Spieler spieler = matchTeilnehmer.getSpieler(rolleDesPunktendenSpielers);
        spieler.gewinnePunkt();
    }

    public void aktualisiereSpielstand(MatchSpielstand neuerSpielstand) throws AufschlaegerIstNochNichtBestimmt {
        matchTeilnehmer.aktualisiereAufschlaeger(getMatchSpielstand(), neuerSpielstand);
        setSpielstand(neuerSpielstand);
    }

    public void fuegeSpielerHinzu(Spieler... spieler) {
        matchTeilnehmer.fuegeHinzu(spieler);
    }

    public void setSpielstand(MatchSpielstand matchSpielstand) {
        matchTeilnehmer.setzteSpielstand(AUFSCHLAEGER, matchSpielstand.getSpielstand(AUFSCHLAEGER));
        matchTeilnehmer.setzteSpielstand(RUECKSCHLAEGER, matchSpielstand.getSpielstand(RUECKSCHLAEGER));
    }

    public void setzteAufschlagRolle(String spielerName, AufschlagRolle aufschlagRolle) {
        matchTeilnehmer.setzeAufschlageRolle(spielerName, aufschlagRolle);
    }

    public MatchSpielstand getMatchSpielstand() {
        HashMap<Spieler, SpielerSpielstand> spielstaende = new HashMap<Spieler, SpielerSpielstand>();

        Spieler aufschlaeger = matchTeilnehmer.getSpieler(AUFSCHLAEGER);
        spielstaende.put(aufschlaeger,aufschlaeger.getSpielstand());
        Spieler rueckschlager = matchTeilnehmer.getSpieler(RUECKSCHLAEGER);
        spielstaende.put(rueckschlager,rueckschlager.getSpielstand());

        return new MatchSpielstand(spielstaende);
    }

    public void setSpieler(Spieler spieler1, Spieler spieler2) {
        matchTeilnehmer.fuegeHinzu(spieler1, spieler2);
    }

    public MatchTeilnehmer getMatchTeilnehmer() {
        return matchTeilnehmer;
    }
}
