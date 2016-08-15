import java.util.HashSet;
import java.util.Set;

/**
 * Chemical symbol service for planet Splurth.
 */
public class ChemicalSymbolService {

    public Boolean isValidSymbol(String elementName, String symbol) {
        // Validate & prepare input
        validateElementName(elementName);
        elementName = elementName.trim().toLowerCase();
        symbol = symbol.trim().toLowerCase();

        // Valid symbol?
        return elementName.matches("\\w*" + symbol.charAt(0) + "\\w*" + symbol.charAt(1) + "\\w*");
    }

    public String getFirstValidSymbol(String elementName) {
        // Validate & prepare input
        validateElementName(elementName);
        elementName = elementName.trim().toLowerCase();

        // Find first char
        char firstChar = elementName.charAt(0);
        int firstCharPos = 0;
        for (int i = 1; i < elementName.length()-1; i++) {
            char c = elementName.charAt(i);
            if (c < firstChar) {
                firstChar = c;
                firstCharPos = i;
            }
        }

        // Find second char
        elementName = elementName.substring(firstCharPos+1);
        char secondChar = elementName.charAt(0);
        for (int i = 1; i < elementName.length(); i++) {
            char c = elementName.charAt(i);
            if (c < secondChar) {
                secondChar = c;
            }
        }

        return new String(new char[]{Character.toUpperCase(firstChar), secondChar});
    }

    public Set<String> getDistinctSymbols(String elementName) {
        // Validate & prepare input
        validateElementName(elementName);
        elementName = elementName.trim().toLowerCase();

        // Find symbols
        Set<String> symbols = new HashSet<>();
        for (int i = 0; i < elementName.length()-1; i++) {
            // Get first char
            char c1 = elementName.charAt(i);
            for (int j = i+1; j < elementName.length(); j++) {
                // Get second char
                char c2 = elementName.charAt(j);
                symbols.add(new String(new char[]{Character.toUpperCase(c1), c2}));
            }
        }

        return symbols;
    }


    private void validateElementName(String elementName) {
        if (elementName == null || elementName.trim().length() == 0)
            throw new RuntimeException("Element name is required");
        if (elementName.trim().length() < 2)
            throw new RuntimeException("Element name must be at least 2 characters");
    }

}
