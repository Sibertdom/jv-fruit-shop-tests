package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.FruitTransaction.Operation;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DataConverterImplTest {
    private static DataConverter dataConverter;

    @BeforeAll
    static void beforeAll() {
        dataConverter = new DataConverterImpl();
    }

    @Test
    void convertToTransaction_validInput_ok() {
        List<String> input = List.of(
                "type,fruit,quantity",
                "b,banana,100",
                "s,apple,50",
                "p,orange,30",
                "r,pear,20"
        );

        List<FruitTransaction> actual = dataConverter.convertToTransaction(input);

        assertNotNull(actual);
        assertEquals(4, actual.size());

        assertEquals(Operation.BALANCE, actual.get(0).getOperation());
        assertEquals("banana", actual.get(0).getFruit());
        assertEquals(100, actual.get(0).getQuantity());

        assertEquals(Operation.SUPPLY, actual.get(1).getOperation());
        assertEquals("apple", actual.get(1).getFruit());
        assertEquals(50, actual.get(1).getQuantity());

        assertEquals(Operation.PURCHASE, actual.get(2).getOperation());
        assertEquals("orange", actual.get(2).getFruit());
        assertEquals(30, actual.get(2).getQuantity());

        assertEquals(Operation.RETURN, actual.get(3).getOperation());
        assertEquals("pear", actual.get(3).getFruit());
        assertEquals(20, actual.get(3).getQuantity());
    }

    @Test
    void convertToTransaction_nullInput_notOk() {
        assertThrows(RuntimeException.class, () -> dataConverter.convertToTransaction(null));
    }
}
