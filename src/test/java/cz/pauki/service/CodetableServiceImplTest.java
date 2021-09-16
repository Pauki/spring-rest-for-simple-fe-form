package cz.pauki.service;

import cz.pauki.Main;
import cz.pauki.dto.response.CodetableContactRequestsResponse;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@SpringBootTest(classes = {Main.class})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class })
public class CodetableServiceImplTest extends AbstractServiceTest {

    @Autowired
    CodetableService codetableService;

    @Test
    @FlywayTest
    public void getContactRequests(){
        CodetableContactRequestsResponse response = codetableService.getContactRequests();
        Assert.assertEquals(3, response.getContactRequests().size());
        Assert.assertEquals("CONTRACT_ADJUSTMENT", response.getContactRequests().get(0).getCode());
        Assert.assertEquals("Contract Adjustment", response.getContactRequests().get(0).getText());
        Assert.assertEquals("DAMAGE_CASE", response.getContactRequests().get(1).getCode());
        Assert.assertEquals("Damage Case", response.getContactRequests().get(1).getText());
        Assert.assertEquals("COMPLAINT", response.getContactRequests().get(2).getCode());
        Assert.assertEquals("Complaint", response.getContactRequests().get(2).getText());
    }
}