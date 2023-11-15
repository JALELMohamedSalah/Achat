package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class teststock {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllStocks() {
        // Mocking data
        List<Stock> mockStocks = new ArrayList<>();
        mockStocks.add(new Stock(/* create a stock object */));

        // Mocking repository behavior
        when(stockRepository.findAll()).thenReturn(mockStocks);

        // Calling the service method
        List<Stock> result = stockService.retrieveAllStocks();

        // Verifying the result
        assertEquals(mockStocks, result);

        // Verifying that the repository method was called
        verify(stockRepository, times(1)).findAll();
    }

    // Other test methods...



    @Test
    void testAddStock() {
        // Mocking data
        Stock mockStock = new Stock(/* create a stock object */);

        // Mocking repository behavior
        when(stockRepository.save(any(Stock.class))).thenReturn(mockStock);

        // Calling the service method
        Stock result = stockService.addStock(mockStock);

        // Verifying the result
        assertEquals(mockStock, result);

        // Verifying that the repository method was called
        verify(stockRepository, times(1)).save(any(Stock.class));
    }

    // Similar tests can be written for other methods (deleteStock, updateStock, retrieveStock, retrieveStatusStock)

    @Test
     void testDeleteStock() {
        // Mocking data
        Long stockId = 1L;

        // Calling the service method
        stockService.deleteStock(stockId);

        // Verifying that the repository method was called
        verify(stockRepository, times(1)).deleteById(stockId);
    }

    // Other test methods for updateStock, retrieveStock, retrieveStatusStock

    @Test
    void testRetrieveStatusStock() {
        // Mocking data
        List<Stock> mockStocks = new ArrayList<>();
        mockStocks.add(new Stock(/* create a stock object */));

        // Mocking repository behavior
        when(stockRepository.retrieveStatusStock()).thenReturn(mockStocks);

        // Calling the service method
        String result = stockService.retrieveStatusStock();

        // Verifying that the repository method was called
        verify(stockRepository, times(1)).retrieveStatusStock();

        // You can add more assertions based on your specific requirements
    }
}
