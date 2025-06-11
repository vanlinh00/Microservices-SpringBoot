import com.example.deliveryservice.DeliveryServiceApplication;
import com.example.deliveryservice.entity.Account;
import com.example.deliveryservice.repository.AccountRepository;
import com.example.deliveryservice.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;



@ExtendWith(SpringExtension.class)


@SpringBootTest(classes = DeliveryServiceApplication.class)
public class PaymentServiceTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentService paymentService;

    @Test
    public void testExample() {
        System.out.println("Test dang chạy...");
        assertTrue(true); // Đảm bảo test không bị bỏ qua
    }

    @BeforeEach
    public void setup() {
        Account account = new Account();
        account.setBalance(300.0);
        accountRepository.save(account);
    }

    // 2. Trường hợp có Locking (Pessimistic Locking)  (  so du cuoi cung 180.0 )

    /*
    Pessimistic Locking chặn cập nhật đồng thời, nên khi một luồng đang xử lý,
     luồng kia phải chờ thay vì gây xung đột.

    Không có lỗi Optimistic Locking, vì Hibernate khóa bản ghi ngay từ khi đọc,

    Đảm bảo chỉ một giao dịch có thể thay đổi dữ liệu tại một thời điểm. 🚀
     */
    @Test
    public void testConcurrentPaymentsWithoutLocking() throws InterruptedException {

        Long accountId = 1L;

        Runnable paymentTask = () -> paymentService.processPayment(accountId, 60.0);

        Thread thread1 = new Thread(paymentTask);
        Thread thread2 = new Thread(paymentTask);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        Account account = accountRepository.findById(accountId).get();
        System.out.println("so du cuoi cung " + account.getBalance());

        assertTrue(account.getBalance() >= 0, "Lỗi: Số dư bị âm do race condition!");
    }

    //3. Trường hợp không có Locking (Optimistic Locking hoặc No Locking) (  so du cuoi cung 180.0 )

//    @Test
//    public void testConcurrentPaymentsWithLocking() throws InterruptedException {
//        Long accountId = 1L;
//
//        Runnable paymentTask = () -> paymentService.processPaymentWithNotLocking(accountId, 60.0);
//
//        /*
//        Hai luồng chạy song song cố gắng cập nhật cùng một bản ghi Account#1, gây xung đột dữ liệu.
//
//        Hibernate phát hiện bản ghi đã bị thay đổi bởi giao dịch khác và từ chối cập nhật,
//         dẫn đến lỗi Optimistic Locking Failure.
//
//        Giải pháp: Thêm @Version vào entity, bắt lỗi ObjectOptimisticLockingFailureException,
//        và thử lại giao dịch. 🚀
//         */
//
//        Thread thread1 = new Thread(paymentTask);
//        Thread thread2 = new Thread(paymentTask);
//
//        thread1.start();
//        thread2.start();
//
//        thread1.join();
//        thread2.join();
//
//        Account account = accountRepository.findByIdNotLocking(accountId).get();
//        System.out.println("Số dư cuối cùng: " + account.getBalance());
//
//        assertTrue(account.getBalance() >= 0, "error:  So du bi am!");
//    }

}
