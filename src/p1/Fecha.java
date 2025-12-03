package p1;


public class Fecha {
    private int dia;
    private int mes;
    private int anio;

    public Fecha(int dia, int mes, int anio) throws FechaNoValidaException {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;

        if (!esFechaValida())
            throw new FechaNoValidaException("Error. La fecha introducida no es válida.");
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

    public boolean esFechaValida() {
        boolean esValida = true;

        if (mes < 1 || mes > 12)
            esValida = false;
        else {
            int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (mes == 2)
                try {
                    if (comprobarBisiesto())
                        diasPorMes[1] = 29;
                } catch (Exception e) {
                    esValida = false;
                }
            if (dia < 1 || dia > diasPorMes[mes - 1])
                esValida = false;
        }

        return esValida;
    }
}
