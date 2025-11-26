
public class Fecha {
    private int dia;
    private int mes;
    private int anio;

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    
    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }


    public int getAnio() {
        return anio;
    }


    public boolean comprobarBisiesto() throws AnioNegativoException {
        boolean esBisiesto = false;
        if (anio < 0)
            throw new AnioNegativoException("Error. El año introducido no puede ser negativo.");
        
        if (anio % 400 == 0)
            esBisiesto = true;
        else if (anio % 100 == 0)
            esBisiesto = false;
        else
            esBisiesto = (anio % 4 == 0);

        return esBisiesto;
    }
}
