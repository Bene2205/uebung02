package ueb02;

import java.util.HashMap;
import java.util.Map;

class Duplikate {
	/**
	 * Gibt ein StringSet mit den Wörtern zurück, welche mindestens zwei mal im Text vorkommen.
	 * Alle Satzzeichen im Text sollen ignoriert werden.
	 * @param text Eingabetext, kann Satzzeichen enthalten welche ignoriert werden.
	 * @return StringSet mit den Wörtern, welche mind. zwei mal vorkommen.
	 */
	static StringSet findeDuplikate(String text) {
		// TODO Implementieren Sie die Methode gemäß dem obigen Javadoc Kommentar.
		String textneu = text.replaceAll("[^a-zA-Z 0-9]", "");
		String[] strsplitted = textneu.split(" ");

		StringSet sS = new StringSetImpl();
		for (String s : strsplitted) {
			sS.add(s);
		}

		Map<String, Integer> counterValues = new HashMap<>();

		for (String s : strsplitted) {
			counterValues.put(s, 0);
		}

		for (String s : strsplitted) {
			if (sS.contains(s)) {
				counterValues.put(s, counterValues.get(s) +1);
			}
		}

		StringSet r = new StringSetImpl();
		for (String s : counterValues.keySet()) {
			if (counterValues.get(s) > 1) {
				r.add(s);
			}
		}
		return r;
	}
}
