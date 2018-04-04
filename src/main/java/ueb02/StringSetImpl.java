package ueb02;

import java.util.NoSuchElementException;

public class StringSetImpl implements StringSet {

    class Element {
        String value;
        Element right, left;
        Element(String s, Element right, Element left) {
            value = s;
            this.right = right;
            this.left = left;
        }

        int size() {
            int i = 1; //min 1 Element, weil root nicht null

            if (left != null) {
                i += left.size();
            }
            if (right != null) {
                i += right.size();
            }

            return i;
        }
    }

    Element root;

    @Override
    public boolean add(String s) {

        Element e = new Element(s, null, null);

        //wenn root leer setzte Element gleich Wurzelelement
        if (root == null) {
            root = e;
            return true;
        }

        Element it = root; //beginne an der Wurzel
        while (it != null) {
            if (it.value.equals(e.value)) {
                //String/Element schon vorhanden
                return false;
            } else if (e.value.length() < it.value.length()) { //links?
                if (it.left == null){
                    it.left = e;
                    return true;
                } else {
                    it = it.left;
                }
            } else { //rechts?
                if (it.right == null) {
                    it.right = e;
                    return true;
                } else {
                    it = it.right;
                }
            }
        }

        return false;
    }

    @Override
    public boolean contains(String s) {
        if(root == null) {
            return false;
        }

        Element it = root;
        while (it != null) { //root gleich s value
            if (it.value.equals(s)) {
                return true;
            } else if (s.length() < it.value.length()) {
                it = it.left;
            } else {
                it = it.right;
            }
        }

        // nicht enthalten
        return false;
    }

    @Override
    public String remove(String s) {
        if (root == null) {
            throw new NoSuchElementException();
        }
        // Wurzel löschen
        /*if (root.value.equals(s)) {
            return removeRoot();
        }*/

        Element it = root;
        while (it != null) {
            if (s.length() < it.value.length()) { //gehe nach links
                if (it.left != null && it.left.value.equals(s)) { //lösche aktuelles Element
                    return removeElement(it, it.left);
                }
                it = it.left;
            } else { //gehe nach rechts
                if (it.right != null && it.right.value.equals(s)) { //lösche aktuelles Element
                    return removeElement(it, it.right);
                }
                it = it.right;
            }
        }
        throw new NoSuchElementException();
    }

    private String removeElement(Element parent, Element element) {
        if (parent.left == element) {
            parent.left = null;
        } else {
            parent.right = null;
        }

        addElement(element.left);
        addElement(element.right);

        return element.value;
    }

    private void addElement(Element newElement) {
        if (newElement == null) {
            return;
        }
        Element it = root; //beginne bei parent Element
        while (it != null) {
            if (newElement.value.length() < it.value.length()) { //links?
                if (it.left == null){
                    it.left = newElement;
                    return;
                } else {
                    it = it.left;
                }
            } else { //rechts?
                if (it.right == null) {
                    it.right = newElement;
                    return;
                } else {
                    it = it.right;
                }
            }
        }
    }

    @Override
    public int size() {
        if (root == null){
            return 0;
        } else {
            return root.size();
        }
    }
}
