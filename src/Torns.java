import java.io.*;
import java.util.NoSuchElementException;

public class Torns<E> {
    private class NodeTorn {
        public E moviment;
        public NodeTorn seguent;

        public NodeTorn(E moviment, NodeTorn seguent) {
            this.moviment = moviment;
            this.seguent = seguent;
        }
    }

    private NodeTorn llistatTorns;

    public Torns() {
        llistatTorns = new NodeTorn(null, null);
    }

    public Torns(String nomFitxer) throws IOException {
        llistatTorns = new NodeTorn((E) nomFitxer, null);
        carregarDesDeFitxer(nomFitxer);
    }

    public void afegirTorn(E torn) {
        NodeTorn aux = this.llistatTorns;
        while (aux.seguent != null) {
            aux = aux.seguent;
        }
        aux.seguent = new NodeTorn(torn, null);
    }

    public E agafarPrimerTorn() throws NoSuchElementException {
        if (this.llistatTorns.seguent == null) {
            throw new NoSuchElementException("La llista de torns és buida.");
        }

        NodeTorn primerTorn = this.llistatTorns.seguent;
        this.llistatTorns.seguent = primerTorn.seguent;

        return primerTorn.moviment;
    }

    public void guardarAFitxer(String nomFitxer) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(nomFitxer));
        NodeTorn aux = this.llistatTorns.seguent;
        while (aux != null) {
            writer.write((String) aux.moviment);
            writer.newLine();
            aux = aux.seguent;
        }
        writer.close();
    }

    private void carregarDesDeFitxer(String nomFitxer) throws IOException {
        String linia;
        BufferedReader reader = new BufferedReader(new FileReader(nomFitxer));
        while ((linia = reader.readLine()) != null) {
            afegirTorn((E) linia);
        }
        reader.close();
    }
}
