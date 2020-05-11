package me.ham.estimate;

import me.ham.estimate.entity.Dealer;
import me.ham.estimate.entity.Estimate;
import me.ham.estimate.entity.EstimateTry;
import me.ham.estimate.repository.DealerRepository;
import me.ham.estimate.repository.EstimateRepository;
import me.ham.estimate.repository.EstimateTryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@Transactional
@Commit
public class EstimateModelingTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    EstimateRepository estimateRepository;

    @Autowired
    DealerRepository dealerRepository;

    @Autowired
    EstimateTryRepository estimateTryRepository;

    @Test
    public void estimateBasicTest(){
        Estimate estimate = new Estimate();
        estimate.setCarNumber("01가1234");
        estimateRepository.save(estimate);
        estimateRepository.flush();

        Dealer dealer1 = new Dealer();
        dealer1.setName("홍길동");
        dealerRepository.save(dealer1);
        dealerRepository.flush();


        EstimateTry estimateTry1 = new EstimateTry();
        estimateTry1.setDealer(dealer1);
        estimateTry1.setPrice(1000);
        estimateTry1.setEstimate(estimate);

        estimateTryRepository.save(estimateTry1);
        estimateTryRepository.flush();

        List<EstimateTry> estimateTryList = estimateTryRepository.findAll();
        assertEquals(1, estimateTryList.size());
    }

    @Test
    public void estimateTest(){
        Estimate estimate = new Estimate();
        estimate.setCarNumber("01가1234");
        estimateRepository.save(estimate);
        estimateRepository.flush();

        Dealer dealer1 = new Dealer();
        dealer1.setName("홍길동");
        dealerRepository.save(dealer1);

        Dealer dealer2 = new Dealer();
        dealer2.setName("이영자");
        dealerRepository.save(dealer2);

        dealerRepository.flush();

        EstimateTry estimateTry1 = new EstimateTry();
        estimateTry1.setDealer(dealer1);
        estimateTry1.setPrice(1000);
        estimateTry1.setEstimate(estimate);

        EstimateTry estimateTry2 = new EstimateTry();
        estimateTry2.setDealer(dealer2);
        estimateTry2.setPrice(1200);
        estimateTry2.setEstimate(estimate);

        estimateTryRepository.save(estimateTry1);
        estimateTryRepository.save(estimateTry2);
        estimateTryRepository.flush();

        List<EstimateTry> estimateTryList = estimateTryRepository.findAll();
        assertEquals(2, estimateTryList.size());
    }

    @Test
    public void estimateEntityManagerTest(){
        Estimate estimate = new Estimate();
        estimate.setCarNumber("01가1234");
        entityManager.persist(estimate);

        Dealer dealer1 = new Dealer();
        dealer1.setName("신동엽");
        entityManager.persist(dealer1);

        Dealer dealer2 = new Dealer();
        dealer2.setName("유재숙");
        entityManager.persist(dealer2);

        Dealer dealer3 = new Dealer();
        dealer3.setName("김자몽");
        entityManager.persist(dealer3);


        EstimateTry estimateTry1 = new EstimateTry();
        estimateTry1.setDealer(dealer1);
        estimateTry1.setPrice(1000);
        estimateTry1.setEstimate(estimate);

        EstimateTry estimateTry2 = new EstimateTry();
        estimateTry2.setDealer(dealer2);
        estimateTry2.setPrice(1200);
        estimateTry2.setEstimate(estimate);

        entityManager.persist(estimateTry1);
        entityManager.persist(estimateTry2);

        List<EstimateTry> estimateTryList = estimateTryRepository.findAll();
        assertEquals(2, estimateTryList.size());
        assertEquals(3, dealerRepository.findAll().size());
    }

}
