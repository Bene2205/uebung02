package ueb02;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Duplikate {
	/**
	 * Gibt ein StringSet mit den Wörtern zurück, welche mindestens zwei mal im Text vorkommen.
	 * Alle Satzzeichen im Text sollen ignoriert werden.
	 * @param text Eingabetext, kann Satzzeichen enthalten welche ignoriert werden.
	 * @return StringSet mit den Wörtern, welche mind. zwei mal vorkommen.
	 */
	static StringSet findeDuplikate(String text) {
		StringSet ssimpl = new StringSetImpl();
		text = text.replaceAll("!", ""); //entferne !
        String[] text1 = text.split(" "); //setze Wörter in Array

        int count = 0; //zaehler für gleiche Wörter
        for (String s:
             text1) {
            for (String s1:
                 text1) {
                if (s.equals(s1)) {
                    count++;
                }
            }
            if (count >=2) {
                ssimpl.add(s);
            }
            count = 0;
        }

		return ssimpl;
	}
}
