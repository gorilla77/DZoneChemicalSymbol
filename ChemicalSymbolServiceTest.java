import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class ChemicalSymbolServiceTest {
    private static ChemicalSymbolService service;

    @BeforeClass
    public static void setup() {
        service = new ChemicalSymbolService();
    }

    @Test
    public void isValidSymbol() throws Exception {
        assertTrue(service.isValidSymbol("Spenglerium", "Ee"));
        assertTrue(service.isValidSymbol("Zeddemorium", "Zr"));
        assertTrue(service.isValidSymbol("Venkmine", "Kn"));
        assertFalse(service.isValidSymbol("Stantzon", "Zt"));
        assertFalse(service.isValidSymbol("Melintzum", "Nn"));
        assertFalse(service.isValidSymbol("Tullium", "Ty"));
    }

    @Test
    public void getFirstValidSymbol() {
        assertEquals(service.getFirstInOrderSymbol("Gozerium"), "Ei");
        assertEquals(service.getFirstInOrderSymbol("Slimyrine"), "Ie");
    }

    @Test
    public void getDistinctSymbols() {
        String elementName = "Zuulon";
        Set<String> symbols = service.getDistinctSymbols(elementName);
        assertEquals(symbols.size(), 11);
        for (String symbol : symbols) {
            assertTrue(service.isValidSymbol(elementName, symbol));
        }
    }

}