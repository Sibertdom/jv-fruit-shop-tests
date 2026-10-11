package core.basesyntax.service;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.FruitTransaction.Operation;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataConverterImplTest {
    private final DataConverter dataConverter = new DataConverterImpl();

    @Test
    void convertToTransaction_validInput_ok() {
        List<String> input = List.of(
                "type,fruit,quantity",
                "b,banana,100",
                "s,apple,50"
        );

        List<FruitTransaction> actual = dataConverter.convertToTransaction(input);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());

        Assertions.assertEquals(Operation.BALANCE, actual.get(0).getOperation());
        Assertions.assertEquals("banana", actual.get(0).getFruit());
        Assertions.assertEquals(100, actual.get(0).getQuantity());

        Assertions.assertEquals(Operation.SUPPLY, actual.get(1).getOperation());
        Assertions.assertEquals("apple", actual.get(1).getFruit());
        Assertions.assertEquals(50, actual.get(1).getQuantity());
    }
}
