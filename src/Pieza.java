public class Pieza implements ItipoPieza{

    // atributs d'objectes
    private final char tipo;
    private int fila;
    private char columna;

    // constants de la classe
    public static final char REY = 'R';
    public static final char REINA = 'D';
    public static final char TORRE = 'T';
    public static final char ALFIL = 'A';
    public static final char CAVALLO = 'C';
    public static final char PEON = 'P';



    public Pieza(char tipo, int fila, int columna) {
        // comprovem que no dona excepció el tipus
        this.checkTipo(tipo);
        //comprovem que no dona excepció la posició
        this.setPosicion(fila,columna);
        this.tipo = tipo;
    }

    public char getTipus() {
        return this.tipo;
    }

    public int getFila() {
        return this.fila;
    }

    public int getColumna() {
        return (int) this.columna-65;
    }

    @Override
    public void setPosicion(int fila, int columna) {
        if( fila< 0 || fila > 8 || columna < 0 || columna > 8)
            throw new IllegalArgumentException("posició incorrecte");
        this.fila = fila;
        this.columna = (char) (65+columna);
    }

    @Override
    public boolean fiJoc() {
        return this.tipo == REY;
    }

    private void checkTipo(char tipo) {
        switch (tipo) {
            case REY:
            case REINA:
            case TORRE:
            case ALFIL:
            case CAVALLO:
            case PEON:
                return ;
            default:
                throw new IllegalArgumentException("Desconegut");
        }
    }

}
