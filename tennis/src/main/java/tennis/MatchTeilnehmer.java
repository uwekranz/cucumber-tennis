package tennis;

import tennis.exceptions.AufschlaegerIstNochNichtBestimmt;
import tennis.exceptions.KeinSpielerMitDiesemNamenGefunden;

import java.util.ArrayList;
import java.util.List;

import static tennis.AufschlagRolle.AUFSCHLAEGER;
import static tennis.AufschlagRolle.RUECKSCHLAEGER;

public class MatchTeilnehmer {

    List<Spieler> matchTeilnehmer = new ArrayList<Spieler>();

    public Spieler getSpieler(AufschlagRolle aufschlagRolle) throws AufschlaegerIstNochNichtBestimmt {
        Spieler spieler = null;

        for(Spieler potenzielGesuchterSpieler: matchTeilnehmer){
            if (potenzielGesuchterSpieler.is(aufschlagRolle)) {
                spieler = potenzielGesuchterSpieler;
                break;
            }
        }

        if (spieler == null)
            throw new AufschlaegerIstNochNichtBestimmt();
        else
            return spieler;
    }

    private Spieler getSpieler(String spielerName) {
        Spieler spielerToReturn = null;

        for(Spieler spieler: matchTeilnehmer){
            if (spielerName.equals(spieler.getName()));
            spielerToReturn = spieler;
        }

        if (spielerToReturn == null)
            throw new KeinSpielerMitDiesemNamenGefunden();
        return spielerToReturn;
    }

    public void fuegeHinzu(Spieler... spieler) {
        for (Spieler neuerSpieler: spieler ) {
            matchTeilnehmer.add(neuerSpieler);
        }
    }

    public void aktualisiereAufschlaeger(MatchSpielstand alterSpielstand, MatchSpielstand neuerSpielstand) throws AufschlaegerIstNochNichtBestimmt {
        if(isAufschlaegerWechselNoetig(alterSpielstand,neuerSpielstand)) {
            wechsleAufschlaeger();
        }
    }

    private boolean isAufschlaegerWechselNoetig(MatchSpielstand alterSpielstand, MatchSpielstand neuerSpielstand) {
        boolean isSummeGespielterSpieleUndSaetzeImAltenUndNeuenSpielstandJeweilsGerade = isSummeGespielterSpieleUndSaetzeImAltenUndNeuenSpielstandJeweilsGerade(alterSpielstand, neuerSpielstand);
        boolean isSummeGespielterSpieleUndSaetzeImAltenUndNeuenSpielstandJeweilsUngerade = isSummeGespielterSpieleUndSaetzeImAltenUndNeuenSpielstandJeweilsUngerade(alterSpielstand, neuerSpielstand);

        return !isSummeGespielterSpieleUndSaetzeImAltenUndNeuenSpielstandJeweilsGerade &
                !isSummeGespielterSpieleUndSaetzeImAltenUndNeuenSpielstandJeweilsUngerade;
    }

    private boolean isSummeGespielterSpieleUndSaetzeImAltenUndNeuenSpielstandJeweilsUngerade(MatchSpielstand alterSpielstand, MatchSpielstand neuerSpielstand) {
        return !alterSpielstand.isSummeGespielterSpieleUndSaetzeGerade() & !neuerSpielstand.isSummeGespielterSpieleUndSaetzeGerade();
    }

    private boolean isSummeGespielterSpieleUndSaetzeImAltenUndNeuenSpielstandJeweilsGerade(MatchSpielstand alterSpielstand, MatchSpielstand neuerSpielstand) {
        return alterSpielstand.isSummeGespielterSpieleUndSaetzeGerade() & neuerSpielstand.isSummeGespielterSpieleUndSaetzeGerade();
    }


    private void wechsleAufschlaeger() throws AufschlaegerIstNochNichtBestimmt {
        Spieler alterAufschlaeger = getSpieler(AUFSCHLAEGER);
        Spieler alterRueckschlaeger = getSpieler(RUECKSCHLAEGER);

        alterAufschlaeger.wechsleAufschlagRolle();
        alterRueckschlaeger.wechsleAufschlagRolle();
    }

    public void setzeAufschlageRolle(String spielerName,AufschlagRolle aufschlagRolle) {
        getSpieler(spielerName);
    }

    public void setzteSpielstand(AufschlagRolle aufschlagRolle, SpielerSpielstand spielstand) {
        getSpieler(AUFSCHLAEGER).setSpielstand(spielstand);
    }
}
