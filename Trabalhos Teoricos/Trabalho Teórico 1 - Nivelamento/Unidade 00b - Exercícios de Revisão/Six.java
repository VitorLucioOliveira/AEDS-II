public class Six {
    public static void main(String args[]) {
    }

    boolean isConsoante(String s, int i) {
        boolean resp = true;
        if (i <= s.length()) {
            if (!isConsoante(s.charAt(i))) {
                resp = false;
            }
        } 
        
        else { resp = isConsoante(s, i + 1);}
        
        return resp;
    }

    boolean isConsoante(char c) {
        return (isLetra(c) == true && isVogal(c) == false);
    }

    boolean isVogal(char c) {
        c = Character.toUpperCase(c);
        return (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U');
    }

    boolean isLetra(char c) {
        c = Character.toUpperCase(c);
        return (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'Z');
    }

}
