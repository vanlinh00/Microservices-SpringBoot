package com.example.deliveryservice.repository;

import com.example.deliveryservice.entity.Account;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /*
    2. Trường hợp có Locking (Pessimistic Locking)
    🔹 Ưu điểm: ✅ Đảm bảo số dư không bị trừ sai khi có nhiều giao dịch đồng thời.
    🔹 Nhược điểm: ❌ Có thể gây chậm trễ, vì các giao dịch phải chờ nhau.

     Pessimistic Locking cho giao dịch quan trọng (rút tiền, đặt vé).
     */

    /*
    PESSIMISTIC_READ –   ( vẫn cho đọc)
    Khóa bản ghi để đọc, ngăn chặn cập nhật hoặc xóa nhưng vẫn cho phép đọc từ các giao dịch khác.

PESSIMISTIC_WRITE –  ( cấm hết )
Khóa bản ghi để ghi, ngăn chặn cả đọc, cập nhật và xóa từ các giao dịch khác.

PESSIMISTIC_FORCE_INCREMENT –
Giống PESSIMISTIC_WRITE, nhưng tăng giá trị của trường @Version để đảm bảo kiểm soát phiên bản.
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findById(Long id);

    /*
    3. Trường hợp không có Locking (Optimistic Locking hoặc No Locking)
    🔹 Ưu điểm: ✅ Xử lý nhanh hơn, không cần chờ giao dịch khác hoàn thành.
     🔹 Nhược điểm: ❌ Có thể dẫn đến xung đột dữ liệu, cần xử lý lỗi và thử lại.
     Optimistic Locking cho cập nhật thông tin ít quan trọng (giỏ hàng, chỉnh sửa hồ sơ).
     */
    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findByIdNotLocking(Long id);
}
