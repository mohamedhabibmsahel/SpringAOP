package com.esprit.examen.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.esprit.examen.TpAchatProjectApplication;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;
import com.esprit.examen.services.StockServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;




@SpringBootTest(classes = TpAchatProjectApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class StockServiceMockitoTest {

	@Mock
	StockRepository stockRepositoryMock;
	@InjectMocks
	StockServiceImpl stockService;


	Stock s = Stock.builder().libelleStock("stock1")
			.qte(33)
			.build();
	List<Stock> listStocks = new ArrayList<Stock>(){
		{
			add(Stock.builder().libelleStock("stock12").build());
			add(Stock.builder().libelleStock("stock13").build());
			add(Stock.builder().libelleStock("stock14").build());
			add(Stock.builder().libelleStock("stock15").build());

		}
	};

	@Test
	public void testretrieveStock(){
		Mockito.when(stockRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(s)); //find all
		Stock s1 = stockService.retrieveStock(2L);
		Assertions.assertNotNull(s1);
		System.out.println("working retrieve MOCKITO !");


	}
	@Test
	public void testaddStock() {
		Mockito.when(stockRepositoryMock.save(s)).thenReturn(s);
		Stock s1 = stockService.addStock(s);
		Assertions.assertNotNull(s1);
		System.out.println("working add MOCKITO !");


	}

	@Test
	public void testretrieveAllStocks() {
		Mockito.when(stockRepositoryMock.findAll()).thenReturn(listStocks);
		List<Stock> listS = stockService.retrieveAllStocks();
		Assertions.assertNotNull(listS);
		System.out.println("working all retrieve MOCKITO !");

	}

	@Test
	public void tesupdateStock() {
		s.setLibelleStock("stockMock");;
		Mockito.when(stockRepositoryMock.save(s)).thenReturn(s);
		Stock s1 = stockService.updateStock(s);
		Assertions.assertEquals(s1.getLibelleStock(),s1.getLibelleStock());
		System.out.println("working update MOCKITO !");


	}

	@Test
	public void testdeleteOperateur() {
		Stock s2 = Stock.builder().libelleStock("stock12").build();
		stockService.deleteStock(s2.getIdStock());
		Mockito.verify(stockRepositoryMock).deleteById(s2.getIdStock());
		System.out.println("working delete MOCKITO !");


	}
}