package com.li.springcloud;

import com.li.springcloud.dao.PaymentDao;
import com.li.springcloud.entities.Payment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaymentMain1001Test {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PaymentDao paymentDao;

    @Test
    public void testPaymentDao() {
        /*Payment payment = new Payment("123456");
        int row = paymentDao.addPayment(payment);
        System.out.println(row);
        System.out.println(payment);*/
        Payment payment = paymentDao.getPaymentById((long) 1);
        System.out.println(payment);
    }

    @Test
    public void testDataSource() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }
}
