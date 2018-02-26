# Similarity
Algorithms to calculate similarity of user pairs based on common likes.

Algorithmen, um die Ähnlichkeit von Paaren von Nutzern basierend auf gemeinsamen Interessen zu berechnen.

# Fragestellung

Bei Similarity geht es um das folgende algorithmische Problem:

**Eingabe:**
- Es gibt Nutzer.
- Es gibt Ziele / Interessen.
- Jeder Nutzer unterstützt ein Ziel oder eben nicht (0/1).

**Ausgabe:**
- Welche Paare von Nutzern haben besonders ähnliche Ziele?

# Benutzung

Ein Beispiel für die Benutzung findest du unter [``SimilarityDemo``](src/net/poczone/similarity/SimilarityDemo.java).

Typischerweise machst du es so:
- Du implementierst deine eigene [``SimilaritySource``](src/net/poczone/similarity/data/SimilaritySource.java). Die ``SimilaritySource`` ist ein Adapter zwischen der ``Similarity``-API und deinem Datenmodell. Du bestimmst darin, wie der Algorithmus an die Ziele und die Unterstützer für ein Ziel gelangt. Die Klasse ist generisch.
- Über [``Similarity``](src/net/poczone/similarity/Similarity.java)``.calculateStats`` lässt du zu deiner ``SimilaritySource`` eine Statistik berechnen, die [``SimilarityStats``](src/net/poczone/similarity/data/SimilarityStats.java). Dafür werden die Ziele selbst gezählt. Außerdem werden die Einträge pro Nutzer gezählt. Und vor allem werden Vorkommnisse von Paaren von Nutzern gezählt, die sich ein gemeinsames Ziel teilen.
- Zuletzt forderst du über ``SimilarityStats.getRankedPairs`` die nach Ähnlichkeit bewertete und absteigend sortierte Liste von Nutzer-Paaren an. Dazu kannst du verschiedene Metriken heranziehen, die nun beschrieben werden.

# Metriken zur Bewertung

Eine Ähnlichkeitsmetrik bewertet die Ähnlichkeit zweier Nutzer. Es sind verschiedene Metriken als Implementierungen von [``SimilarityMetric``](src/net/poczone/similarity/metrics/SimilarityMetric.java) verfügbar.

Da wir zwei Nutzer vergleichen und jeder Nutzer eine Sache unterstützt oder nicht (0/1), ergeben sich insgesamt nur vier mögliche Kombinationen (00/01/10/11): Keiner von beiden unterstützt ein Ziel (00), A unterstützt das Ziel und B nicht (10), B unterstützt das Ziel und A nicht (01), beide unterstützen das Ziel (11). Die Ähnlichkeitsmetrik kann also auf vier Zahlenwerten leicht definiert werden:

N = Anzahl der Interessen insgesamt (00 & 01 & 10 & 11)

A = Anzahl der Interessen von Nutzer A (10 & 11)

B = Anzahl der Interessen von Nutzer B (01 & 11)

X = Anzahl der Interessen, die A und B gemeinsam haben (11)

Nachfolgend werden die Metriken definiert. Alle liegen im Wertebereich [0,1], wobei 0 komplett verschieden und 1 komplett identisch bedeutet.

## GeometricMeanFractionSimilarityMetric
``GeometricMeanFractionSimilarityMetric = Wurzel( X/A * X/B )``

Die Werte ``X/A`` und ``X/B`` sind der Anteil der Interessen beider Nutzer, die der andere teilt. Beide Werte werden für diese Metrik geometrisch gemittelt, also multipliziert und Wurzel gezogen. Die Metrik bewertet demnach Anteile aus beiden Richtungen. Diese Metrik wird als Standard-Metrik verwendet, wenn keine explizite Metrik angegeben wird.

## SingleFractionSimilarityMetric
``SingleFractionSimilarityMetric = 2*X / (A+B)``

Diese Metrik fasst die Interessensbekundungen beider Nutzer zusammen (``A+B``) und fragt nach dem Anteil dieser Bekundungen, die der andere auch unterstützt. Gemeinsame Interessen werden dabei doppelt gezählt, weil sie auch in A und B doppelt auftauchen.

## PearsonCorrelationSimilarityMetric

``PearsonCorrelationSimilarityMetric = 0,5 + 0,5 * (X*N - A*B) / Wurzel( A * (N-A) * B * (N-B) )``

Diese Metrik basiert auf dem [Korrelationskoeffizienten](https://de.wikipedia.org/wiki/Korrelationskoeffizient). Es handelt sich um einen Spezialfall der Formel für den empirischen Korrelationskoeffizienten nach Pearson für die Situation, dass nur die oben genannten vier Kombinationen vorkommen.

Der Korrelationskoeffizient nimmt eigentlich Werte zwischen -1 und +1 an und wird hier auf Werte zwischen 0 und 1 verschoben. 0,5 bedeutet, dass die Nutzer in keinem Zusammenhang stehen. Werte hin zu 1 zeigen eine Tendenz, dass die Nutzer gleiche Dinge unterstützen und nicht unterstützen. Werte hin zu 0 zeigen eine Tendenz, dass die Nutzer genau entgegengesetzt bewerten.

## InCommonSimilarityMetric
``InCommonSimilarityMetric = X / N``

Diese Metrik ist in vielerlei Hinsicht eine Ausnahme.

Sie bewertet nur die gemeinsamen Interessen X, normiert diese allerdings mit der Anzahl der Interessen N. Bei dieser Metrik gilt "viel hilft viel": Wenn jemand mehr Ziele unterstützt, wird er dadurch den anderen nur ähnlicher. Andere Metriken geben Ähnlichkeitsabzug für Ziele, die der eine unterstützt, aber der andere nicht; das ist hier nicht der Fall.

Achtung außerdem: Bei dieser Metrik bedeutet ein Wert von 1 "Beide mögen alles". Hier ist es nicht so, dass Nutzer mit identischen Zielen den vollen Ähnlichkeitswert erreichen.

## Welche Metrik wähle ich?

Die Wahl der Metrik hängt von der Datenlage ab. Ich empfehle, alle Metriken auszuprobieren und zu schauen, welche für die Anwendung am plausibelsten erscheint. Für meine Anwendung habe ich die Metriken in der oben genannten Reihenfolge bewertet. Ich denke, dass GeometricMeanFractionSimilarityMetric daher eine gute Standard-Metrik ist.
