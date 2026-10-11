package core.basesyntax.service;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.FruitTransaction.Operation;
import core.basesyntax.strategy.BalanceOperation;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import core.basesyntax.strategy.PurchaseOperation;
import core.basesyntax.strategy.SupplyOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShopServiceImplTest {
    private final OperationStrategy operationStrategy;
    private final ShopService shopService;

    {
        Map<Operation, OperationHandler> operationHandlers = new HashMap<>();
        operationHandlers.put(Operation.BALANCE, new BalanceOperation());
        operationHandlers.put(Operation.SUPPLY, new SupplyOperation());
        operationHandlers.put(Operation.PURCHASE, new PurchaseOperation());
        operationStrategy = new OperationStrategyImpl(operationHandlers);
        shopService = new ShopServiceImpl(operationStrategy);
    }

    @AfterEach
    void tearDown() {
        Storage.getStorage().clear();
    }

    @Test
    void process_validTransactions_ok() {
        List<FruitTransaction> transactions = List.of(
                new FruitTransaction(Operation.BALANCE, "banana", 100),
                new FruitTransaction(Operation.SUPPLY, "banana", 50),
                new FruitTransaction(Operation.PURCHASE, "banana", 30)
        );

        shopService.process(transactions);

        // 100 (balance) + 50 (supply) - 30 (purchase) = 120
        Integer actualQuantity = Storage.getStorage().get("banana");
        Assertions.assertEquals(120, actualQuantity);
    }
}
