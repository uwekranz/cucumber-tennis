#language: de

Funktionalität: Spielstand zählen

  Szenario: Der Aufschläger macht den ersten Punkt
    Angenommen es steht 0 zu 0 im aktuellen Satz
    Wenn der Aufschläger einen Punkt gewinnt
    Dann steht es 15 zu 0

  Szenariogrundriss: Ein Punktgewinn wird dem richtigen Spieler zugeordnet
    Angenommen es steht <Ausgangsstand Aufschläger> zu <Ausgangsstand Rückschlager> im aktuellen Satz
    Wenn der <Rolle> einen Punkt gewinnt
    Dann steht es <Endstand Aufschläger> zu <Endstand Rückschläger>
  Beispiele:
    |Rolle       |Ausgangsstand Aufschläger|Ausgangsstand Rückschlager|Endstand Aufschläger|Endstand Rückschläger|
    |Aufschläger |15                       |0                         |30                  |0                    |
    |Rückschläger|15                       |0                         |15                  |15                   |
    |Aufschläger |15                       |15                        |30                  |15                   |
    |Rückschläger|15                       |15                        |15                  |30                   |
    |Aufschläger |30                       |15                        |40                  |15                   |
    |Rückschläger|15                       |30                        |15                  |40                   |

