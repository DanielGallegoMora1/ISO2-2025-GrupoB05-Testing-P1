package p1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class FechaTest {

    private Fecha fecha;

    @Before
    public void setUp() {
        // Fecha base (se reconfigura en cada caso de prueba)
        fecha = null;
    }

    /*
     * A continuación se realizarán pruebas unitarias para la clase Fecha,
     * con casos definidos a partir del documento teórico (clases de equivalencia,
     * valores límite y decisiones para años bisiestos).
     */

    // TESTS PARA LA CONDICIÓN DE "AÑO BISIESTO / NO BISIESTO" (decisiones)
    // test case 1: Año divisible por 400 -> es bisiesto
    @Test
    public void testCase1_AnioDivisiblePor400_EsBisiesto() throws Exception {
        fecha = new Fecha(1, 1, 2000);
        assertTrue(fecha.comprobarBisiesto());
    }

    // test case 2: Año divisible por 100 pero no por 400 -> no es bisiesto
    @Test
    public void testCase2_AnioDivisiblePor100_NoEsBisiesto() throws Exception {
        fecha = new Fecha(1, 1, 1900);
        assertFalse(fecha.comprobarBisiesto());
    }

    // test case 3: Año divisible por 4 pero no por 100 -> es bisiesto
    @Test
    public void testCase3_AnioDivisiblePor4_EsBisiesto() throws Exception {
        fecha = new Fecha(1, 1, 2024);
        assertTrue(fecha.comprobarBisiesto());
    }

    // test case 4: Año no divisible por 4 -> no es bisiesto
    @Test
    public void testCase4_AnioNoDivisiblePor4_NoEsBisiesto() throws Exception {
        fecha = new Fecha(1, 1, 2023);
        assertFalse(fecha.comprobarBisiesto());
    }

    // TESTS PARA LA CONDICIÓN DE "FECHA VÁLIDA" (clases de equivalencia)
    // test case 5: Fecha válida normal
    @Test
    public void testCase5_FechaValida_Normal() throws Exception {
        fecha = new Fecha(15, 6, 2024);
        assertEquals(15, fecha.getDia());
        assertEquals(6, fecha.getMes());
        assertEquals(2024, fecha.getAnio());
    }

    // TESTS PARA LA CONDICIÓN DE "DÍA INVÁLIDO" (valores límite)
    // test case 6: Día = 0 -> inválido
    @Test
    public void testCase6_DiaCero_Invalido() throws Exception {
        assertThrows(FechaNoValidaException.class, () -> {
            new Fecha(0, 5, 2024);
        });
    }

    // test case 7: Día > 31 -> inválido
    @Test
    public void testCase7_DiaMayor31_Invalido() throws Exception {
        assertThrows(FechaNoValidaException.class, () -> {
            new Fecha(32, 5, 2024);
        });
    }

    // TESTS PARA LA CONDICIÓN DE "MES INVÁLIDO" (valores límite)
    // test case 8: Mes = 0 -> inválido
    @Test
    public void testCase8_MesCero_Invalido() throws Exception {
        assertThrows(FechaNoValidaException.class, () -> {
            new Fecha(10, 0, 2024);
        });
    }

    // test case 9: Mes > 12 -> inválido
    @Test
    public void testCase9_MesMayor12_Invalido() throws Exception {
        assertThrows(FechaNoValidaException.class, () -> {
            new Fecha(10, 13, 2024);
        });
    }

    // TESTS PARA LA CONDICIÓN DE "FEBRERO" (casos especiales)
    // test case 10: 29/02 en año bisiesto -> válido
    @Test
    public void testCase10_Febrero29_EnBisiesto_Valido() throws Exception {
        fecha = new Fecha(29, 2, 2024);
        assertEquals(29, fecha.getDia());
        assertEquals(2, fecha.getMes());
        assertEquals(2024, fecha.getAnio());
    }

    // test case 11: 29/02 en año NO bisiesto -> inválido
    @Test
    public void testCase11_Febrero29_EnNoBisiesto_Invalido() throws Exception {
        assertThrows(FechaNoValidaException.class, () -> {
            new Fecha(29, 2, 2023);
        });
    }

    // TESTS PARA LA CONDICIÓN DE "AÑO INVÁLIDO" (clases de equivalencia)
    // test case 12: Año negativo -> inválido
    @Test
    public void testCase12_AnioNegativo_Invalido() throws Exception {
        assertThrows(FechaNoValidaException.class, () -> {
            new Fecha(1, 1, -2024);
        });
    }

    // test case 13: Año anterior al gregoriano (<1582) -> inválido
    @Test
    public void testCase13_AnioAnteriorGregoriano_Invalido() throws Exception {
        assertThrows(FechaNoValidaException.class, () -> {
            new Fecha(1, 1, 1500);
        });
    }

    // TESTS PARA LA CONDICIÓN DE "CONJETURA DE ERRORES" (caso conflictivo)
    // test case 14: 28/02 en año no bisiesto -> válido
    @Test
    public void testCase14_Febrero28_EnNoBisiesto_Valido() throws Exception {
        fecha = new Fecha(28, 2, 2023);
        assertEquals(28, fecha.getDia());
        assertEquals(2, fecha.getMes());
        assertEquals(2023, fecha.getAnio());
    }
}
