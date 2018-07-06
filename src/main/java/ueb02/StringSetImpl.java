package ueb02;

import java.util.NoSuchElementException;

public class StringSetImpl implements StringSet {

    Element root = null;

    @Override
    public boolean add(String s) {
        if (root == null) {
            root = new Element(s, null, null);
            return true;
        }
        if (contains(s)) {
            return true;
        }
        Element it = root;
        while (it != null) {
            if (s.compareTo(it.s) < 0) {
                if (it.left == null) {
                    it.left = new Element(s, null, null);
                    return true;
                }
                it = it.left;
            } else {
                if (it.right == null) {
                    it.right = new Element(s, null, null);
                    return true;
                }
                it = it.right;
            }
        }
        return true;
    }

    public void addTree(Element element) {
        Element it = root;

        while (it != null) {
            if (it.s.compareTo(element.s) < 0) {
                if (it.left == null) {
                    it.left = element;
                    return;
                }
                it = it.left;
            } else {
                if (it.right == null) {
                    it.right = element;
                    return;
                }
                it = it.right;
            }
        }
    }

    @Override
    public boolean contains(String s) {
        if (size() == 0) {
            return false;
        } else if (size() == 1) {
            if (root.s.equals(s)) {
                return true;
            }
            return false;
        }

        Element it = root;
        while (it != null) {
            if (it.s.equals(s)) {
                return true;
            }
            if (s.compareTo(it.s) < 0) {
                it = it.left;
            } else {
                it = it.right;
            }
        }
        return false;
    }

    @Override
    public String remove(String s) {
        if (contains(s) == false) {
            throw new NoSuchElementException();
        }
        if (root.s.equals(s)) {
            return removeRoot(s);
        }
        return removeElement(s);
    }

    private String removeElement(String s) {
        Element it = root;
        String r = "";
        while (it != null) {
            if (s.compareTo(it.s) < 0) {
                if (it.left.s.equals(s)) {
                    r = it.left.s;
                    if (it.left.left == null && it.left.right == null) {
                        // rechts und links nicht gefüllt - lösche element
                        it.left = null;
                        break;
                    }
                    if (it.left.left == null || it.left.right == null) {
                        // nur rechts oder links gefüllt
                        if (it.left.left != null) {
                            // hebe linkes element vom zu löschenden eins nach oben
                            it.left = it.left.left;
                        } else {
                            // hebe rechtes element vom zu löschenden eins nach oben
                            it.left = it.left.right;
                        }
                        break;
                    }
                    // beide teilbäume sind gefüllt
                    // merke rechtes element
                    Element right = it.left.right;
                    // schiebe linken teilbaum nach oben
                    it.left = it.left.left;
                    addTree(right);
                }
                it = it.left;
            } else {
                if (it.right.s.equals(s)) {
                    r = it.right.s;
                    if (it.right.left == null && it.right.right == null) {
                        // rechts und links nicht gefüllt - lösche element
                        it.right = null;
                        break;
                    }
                    if (it.right.left == null || it.right.right == null) {
                        // nur rechts oder links gefüllt
                        if (it.right.left != null) {
                            // hebe linkes element vom zu löschenden eins nach oben
                            it.right = it.right.left;
                        } else {
                            // hebe rechtes element vom zu löschenden eins nach oben
                            it.right = it.right.right;
                        }
                        break;
                    }
                    // beide teilbäume sind gefüllt
                    // merke rechtes element
                    Element right = it.right.right;
                    // schiebe linken teilbaum nach oben
                    it.right = it.right.left;
                    addTree(right);
                }
                it = it.right;
            }
        }
        return r;
    }

    public String removeRoot(String s) {
        if (root.right == null && root.left == null) {
            String r = root.s;
            root = null;
            return r;
        }
        // nur rechts oder nur links leer
        if (root.right == null || root.left == null) {
            String r = root.s;
            if (root.right == null) {
                root = root.left;
            } else {
                root = root.right;
            }
            return r;
        }

        String r = root.s;
        Element right = root.right;
        root = root.left;
        Element it = root;
        while (it != null) {
            if (right.s.compareTo(it.s) < 0) {
                if (it.left == null) {
                    it.left = right;
                    break;
                }
                it = it.left;
            } else {
                if (it.right == null) {
                    it.right = right;
                    break;
                }
                it = it.right;
            }
        }
        return r;
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return root.size();
    }

    @Override
    public String toString() {
        if (root == null) {
            return "";
        }
        return root.toString();
    }

    class Element {
        String s;
        Element left;
        Element right;

        public Element(String s, Element left, Element right) {
            this.s = s;
            this.left = left;
            this.right = right;
        }

        public int size() {
            int i = 1;

            if (left != null) {
                i += left.size();
            }
            if (right != null) {
                i += right.size();
            }
            return i;
        }

        public String toString() {
            String out = s;

            if (left != null) {
                out += " " + left.toString();
            }
            if (right != null) {
                out += " " + right.toString();
            }

            return out;
        }
    }
}