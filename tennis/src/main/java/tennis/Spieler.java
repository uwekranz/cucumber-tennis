package tennis;

import tennis.exceptions.AufschlaegerIstNochNichtBestimmt;

import static tennis.AufschlagRolle.*;

public class Spieler {

    String name;
    private AufschlagRolle aufschlagRolle;
    private SpielerSpielstand spielstand;

    public Spieler(String name, AufschlagRolle aufschlagRolle) {
        this.name = name;
        this.aufschlagRolle = aufschlagRolle;
    }

    public Spieler(String name, AufschlagRolle aufschlagRolle, SpielerSpielstand aufschlaegerSpielstand) {
        this.name = name;
        this.aufschlagRolle = aufschlagRolle;
        this.spielstand = aufschlaegerSpielstand;
    }

    public void wechsleAufschlagRolle() throws AufschlaegerIstNochNichtBestimmt {
        if(aufschlagRolle == null)
            throw new AufschlaegerIstNochNichtBestimmt();
        if(AUFSCHLAEGER.equals(aufschlagRolle))
            aufschlagRolle = RUECKSCHLAEGER;
        else
            aufschlagRolle = AUFSCHLAEGER;
    }

    public boolean is(AufschlagRolle aufschlagRolle) {
        return aufschlagRolle.equals(this.aufschlagRolle);
    }

    public void gewinnePunkt() {
        spielstand.passeAnPunktgewinnAn();
    }

    public String getName() {
        return name;
    }

    public SpielerSpielstand getSpielstand() {
        return spielstand;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Spieler spieler = (Spieler) o;

        if (name != null ? !name.equals(spieler.name) : spieler.name != null) return false;

        return true;
    }

    public void setSpielstand(SpielerSpielstand spielstand) {
        this.spielstand = spielstand;
    }
}
