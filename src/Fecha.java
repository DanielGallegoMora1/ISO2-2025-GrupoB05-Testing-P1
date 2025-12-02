package src;

import java.nio.file.NotLinkException;

public class Fecha {
    private int anio;

    public Fecha(int anio) {

        this.anio = anio;
    }

    public int getAnio() {
        return anio;
    }

    public boolean comprobarBisiesto() throws AnioNegativoException, NoExisteBisiestoException {
        boolean esBisiesto = false;
        if (anio < 0)
            throw new AnioNegativoException("Error. El año introducido no puede ser negativo.");
        
        if (anio < 1582)
            throw new NoExisteBisiestoException("Error. El año introducido no es válido para el calendario gregoriano.");

        if (anio % 400 == 0)
            esBisiesto = true;
        else if (anio % 100 == 0)
            esBisiesto = false;
        else
            esBisiesto = (anio % 4 == 0);

        return esBisiesto;
    }
}
