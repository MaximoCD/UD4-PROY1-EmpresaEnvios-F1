/**
 * Un envío agrupa los paquetes que un cliente manda a un mismo destino.
 * Un envío admite como máximo tres paquetes.
 *
 * Ninguno de los métodos de esta clase escribe en pantalla.
 *
 * @author
 */
public class Envio {

    // DECISIÓN DE DISEÑO: private es la visibilidad más restrictiva y el programa
    // funciona igual, porque solo se usa dentro de Envio.
    private static final int MAX_PAQUETES = 3;

    private String codigo;
    private String codigoPostal;
    private Paquete paquete1;
    private Paquete paquete2;
    private Paquete paquete3;

    /**
     * Crea un envío sin paquetes.
     *
     * @param codigo       código de seguimiento, p.ej. "PX00000001R"
     * @param codigoPostal código postal del destino, p.ej. "36202"
     */
    public Envio(String codigo, String codigoPostal) {
        this.codigo = codigo;
        this.codigoPostal = codigoPostal;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * @return cuántos paquetes tiene el envío (0 a 3)
     */
    public int getNumeroPaquetes() {
        if (paquete1 == null) {
            return 0;
        }
        if (paquete2 == null) {
            return 1;
        }
        if (paquete3 == null) {
            return 2;
        }
        return 3;
    }

    /**
     * @return true si ya no se pueden añadir más paquetes
     */
    public boolean estaCompleto() {
        return getNumeroPaquetes() == MAX_PAQUETES;
    }

    /**
     * Añade un paquete al envío si no está completo.
     *
     * @return true si se ha añadido, false si el envío estaba completo
     */
    public boolean addPaquete(Paquete paquete) {
        if (paquete1 == null) {
            paquete1 = paquete;
            return true;
        }
        if (paquete2 == null) {
            paquete2 = paquete;
            return true;
        }
        if (paquete3 == null) {
            paquete3 = paquete;
            return true;
        }
        return false;
    }

    /**
     * Crea un paquete con las medidas y peso indicados y lo añade al envío.
     *
     * @return true si se ha añadido, false si el envío estaba completo
     */
    public boolean addPaquete(double alto, double ancho, double largo, double peso) {
        return addPaquete(new Paquete(alto, ancho, largo, peso));
    }

    /**
     * @return la suma de los pesos reales de los paquetes (kg)
     */
    public double calcularPesoReal() {
        int numero = getNumeroPaquetes();
        double suma = 0;
        if (numero >= 1) {
            suma += paquete1.getPeso();
        }
        if (numero >= 2) {
            suma += paquete2.getPeso();
        }
        if (numero >= 3) {
            suma += paquete3.getPeso();
        }
        return suma;
    }

    /**
     * Suma los pesos facturables de los paquetes y redondea hacia arriba
     * al kilo entero.
     *
     * @return el peso facturable del envío (kg enteros)
     */
    public double calcularPesoFacturable() {
        int numero = getNumeroPaquetes();
        double suma = 0;
        if (numero >= 1) {
            suma += paquete1.calcularPesoFacturable();
        }
        if (numero >= 2) {
            suma += paquete2.calcularPesoFacturable();
        }
        if (numero >= 3) {
            suma += paquete3.calcularPesoFacturable();
        }
        return Math.ceil(suma);
    }

    /**
     * @return el paquete de mayor peso real; si hay empate, el que se añadió
     * antes; null si el envío no tiene paquetes
     */
    public Paquete getPaqueteMasPesado() {
        int numero = getNumeroPaquetes();
        if (numero == 0) {
            return null;
        }
        Paquete masPesado = paquete1;
        if (numero >= 2 && paquete2.getPeso() > masPesado.getPeso()) {
            masPesado = paquete2;
        }
        if (numero >= 3 && paquete3.getPeso() > masPesado.getPeso()) {
            masPesado = paquete3;
        }
        return masPesado;
    }

    /**
     * @return representación textual del envío (ver enunciado)
     */
    public String toString() {
        int numero = getNumeroPaquetes();
        String texto = "Envío " + codigo + " -> CP " + codigoPostal
                + " (" + numero + " paquete/s)\n";
        if (numero >= 1) {
            texto += "  1. " + paquete1 + "\n";
        }
        if (numero >= 2) {
            texto += "  2. " + paquete2 + "\n";
        }
        if (numero >= 3) {
            texto += "  3. " + paquete3 + "\n";
        }
        texto += "  Peso real: " + calcularPesoReal()
                + " kg | Peso facturable: " + calcularPesoFacturable() + " kg";
        return texto;
    }
}