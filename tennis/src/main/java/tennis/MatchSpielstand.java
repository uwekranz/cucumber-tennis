package tennis;

import tennis.exceptions.AufschlaegerIstNochNichtBestimmt;

import java.util.Map;

import static tennis.AufschlagRolle.AUFSCHLAEGER;
import static tennis.AufschlagRolle.RUECKSCHLAEGER;

public class MatchSpielstand {
    private Map<Spieler,SpielerSpielstand> spielstaende;

    public MatchSpielstand(Map<Spieler, SpielerSpielstand> spielstaende) {
        this.spielstaende = spielstaende;
    }

    public SpielerSpielstand getSpielstand(AufschlagRolle aufschlagRolle) {
        Spieler aufschlaeger = null;

        for (Spieler spieler: spielstaende.keySet()){
            if(spieler.is(aufschlagRolle)){
                aufschlaeger = spieler;
            }
        }

        if (aufschlaeger == null)
            throw new AufschlaegerIstNochNichtBestimmt();
        return spielstaende.get(aufschlaeger);
    }

    public boolean isSummeGespielterSpieleUndSaetzeGerade() {
        return (summeGespielterSaetze() + summeGespielterSpiele()) % 2 == 0;
    }

    private int summeGespielterSpiele() {
        return getSpielstand(AUFSCHLAEGER).getSpiele() + getSpielstand(RUECKSCHLAEGER).getSpiele();
    }

    private int summeGespielterSaetze() {
        return getSpielstand(AUFSCHLAEGER).getSaetze() + getSpielstand(RUECKSCHLAEGER).getSaetze();
    }

    @Override
    public String toString() {
        return "MatchSpielstand{" +
                "spielstaende=" + spielstaende +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchSpielstand that = (MatchSpielstand) o;

        if (spielstaende != null ? !spielstaende.equals(that.spielstaende) : that.spielstaende != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return spielstaende != null ? spielstaende.hashCode() : 0;
    }
}
