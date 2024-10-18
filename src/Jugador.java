import java.util.ArrayList;

class Jugador<E extends ItipoPieza> {
    private class NodePieza {
        public E pieza;
        public NodePieza seguent;

        public NodePieza(E pieza, NodePieza seguent) {
            this.pieza = pieza;
            this.seguent = seguent;
        }
    }

    private NodePieza piezasVivas;

    public Jugador(ArrayList<E> piezasIniciales) {
        if (piezasIniciales.isEmpty()) {
            throw new IllegalArgumentException("El jugador debe tener almenos una pieza");
        }


        this.piezasVivas = new NodePieza(piezasIniciales.getFirst(), null);
        NodePieza aux = this.piezasVivas;


        for (int i = 1; i < piezasIniciales.size(); i++) {
            aux.seguent = new NodePieza(piezasIniciales.get(i), null);
            aux = aux.seguent;
        }
    }

    public ArrayList<E> getPiezasVivas() {
        ArrayList<E> piezas = new ArrayList<>();
        NodePieza aux = this.piezasVivas;

        while (aux != null) {
            piezas.add(aux.pieza);
            aux = aux.seguent;
        }

        return piezas;
    }

    public void moverPieza(int columnaAnterior, int filaAnterior, int nuevaColumna, int nuevaFila) {
        if (this.buscarEnPosicion(nuevaFila, nuevaColumna) != null)
            throw new RuntimeException("Posició ocupada per una peça del mateix jugador");

        E item = this.buscarEnPosicion(filaAnterior, columnaAnterior);
        if (item == null)
            throw new RuntimeException("Peça no trobada fila:" + filaAnterior + " columna:" + columnaAnterior);

        item.setPosicion(nuevaFila, nuevaColumna);
        System.out.println("Peça moguda");
    }

    private E buscarEnPosicion(int nuevaFila, int nuevaColumna) {
        NodePieza aux = this.piezasVivas;
        while (aux != null) {
            if (aux.pieza.getFila() == nuevaFila && aux.pieza.getColumna() == nuevaColumna) {
                return aux.pieza;
            }
            aux = aux.seguent;
        }
        return null;
    }

    public boolean eliminarPiezaEnPosicion(int columna, int fila) throws FiJocException {
        E item = this.buscarEnPosicion(fila, columna);
        if (item == null) {
            return false;
        }

        NodePieza anterior = null;
        NodePieza aux = this.piezasVivas;


        while (aux != null) {
            if (aux.pieza.equals(item)) {

                if (anterior == null) {
                    this.piezasVivas = aux.seguent;
                } else {
                    anterior.seguent = aux.seguent;
                }

                if (aux.pieza.getTipus() == 'R') {
                    throw new FiJocException();
                }
                System.out.println("Peça eliminada");
                return true;
            }
            anterior = aux;
            aux = aux.seguent;
        }
        return false;
    }

}
