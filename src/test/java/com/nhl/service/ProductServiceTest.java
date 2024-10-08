package com.nhl.service;

import com.nhl.model.Bill;
import com.nhl.model.Coupon;
import com.nhl.model.Product;
import com.nhl.repository.CouponRepository;
import com.nhl.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class ProductServiceTest {

    // Met '@Mock' zeggen we tegen Mockito dat die een nep-versie moet maken van de CouponRepository klasse.
    @Mock
    private CouponRepository couponRepository;

    // Hetzelfde, maar dan voor de ProductRepository klasse.
    @Mock
    private ProductRepository productRepository;

    // Met '@InjectMocks' zeggen we tegen Mockito dat die de constructor van ProductService moet aanroepen,
    // met de hierboven gemaakte mocks als argumenten.
    @InjectMocks
    private ProductService productService;

    // Voor élke test wordt deze setup methode uitgevoerd dankzij '@BeforeEach'.
    @BeforeEach
    public void setup() {
        // Met de volgende regel laten we Mockito de instantie van deze klasse inspecteren.
        // Die leest dan de @Mock en @InjectMocks annotaties en gaat de mocks aanmaken.
        openMocks(this);

        // Wanneer de methode 'getByIds' op de mock CouponRepository wordt aangeroepen, waarbij het niet uitmaakt
        // wat voor lijst er wordt meegegeven, geef dan een van tevoren vastgestelde lijst terug.
        when(couponRepository.getByIds(anyList()))
                .thenReturn(List.of(new Coupon(15), new Coupon(50)));

        // 'anyList()' is een matcher. Je kan ook specifiekere matchers gebruiken, maar nu zeg je dat deze lijst
        // met producten terug moet geven, ongeacht de lijst die mee wordt gegeven als argument aan findByIds.
        when(productRepository.findByIds(anyList()))
                .thenReturn(List.of(new Product(50), new Product(60), new Product(70)));
    }

    // Door '@Test' te gebruiken vertellen we JUnit dat deze methode uitgevoerd moet worden als test.
    @Test
    public void test() {
        // We roepen de getBill methode aan met twee lege lijsten. Deze lijsten worden doorgegeven aan onze gemockte methodes.
        // Aangezien die methodes alles accepteren, maakt het niet uit wat we hier meegeven.
        Bill bill = productService.getBill(List.of(), List.of());

        // Dit is wat logging, maar meestal doe je dit niet.
        System.out.println(bill);

        // En kijk of de berekening klopt met jouw verwachtingen!
        assertEquals(243.00000000000003, bill.getTotalPrice());

    }

}
