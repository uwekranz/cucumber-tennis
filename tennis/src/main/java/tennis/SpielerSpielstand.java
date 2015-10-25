package tennis;

public class SpielerSpielstand {
    int saetze = 0;
    int spiele = 0;
    int punkte = 0;

    public SpielerSpielstand() {
    }

    public SpielerSpielstand(int aufschlaegerPunkte) {
        this.punkte = aufschlaegerPunkte;
    }

    public void passeAnPunktgewinnAn() {
        if(punkte == 30)
            punkte = 40;
        else punkte += 15;
    }

    public int getSpiele() {
        return spiele;
    }

    public int getSaetze() {
        return saetze;
    }

    public void setSpiele(int spiele) {
        this.spiele = spiele;
    }

    @Override
    public int hashCode() {
        int result = saetze;
        result = 31 * result + spiele;
        result = 31 * result + punkte;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpielerSpielstand that = (SpielerSpielstand) o;

        if (punkte != that.punkte) return false;
        if (saetze != that.saetze) return false;
        if (spiele != that.spiele) return false;

        return true;
    }

    @Override
    public String toString() {
        return "SpielerSpielstand{" +
                "saetze=" + saetze +
                ", spiele=" + spiele +
                ", punkte=" + punkte +
                '}';
    }
}
