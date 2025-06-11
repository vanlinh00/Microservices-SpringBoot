package com.example.deliveryservice.config;

import com.example.deliveryservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;


/*
API Gateway trong hệ thống microservices hoạt động như một reverse proxy,
 điều phối các yêu cầu từ client đến các dịch vụ backend,
 giúp quản lý routing, xác thực và giám sát.
  Nó cũng cung cấp bảo mật và tối ưu hóa hiệu suất,
  giảm tải cho các microservices bằng cách xử lý xác thực JWT,
  caching và rate limiting. 🚀
 */

/*
Ví dụ về API Gateway trong hệ thống Microservices
Tình huống thực tế:
Giả sử bạn có một ứng dụng giao hàng với các microservices sau:

Auth Service (localhost:8080) → Xác thực người dùng, cấp JWT.

Order Service (localhost:8082) → Quản lý đơn hàng.

Delivery Service (localhost:8089) → Quản lý vận chuyển.

Cách API Gateway hoạt động:
Client gửi yêu cầu lấy đơn hàng:

http
GET http://localhost:9000/orders/123
Authorization: Bearer <JWT_TOKEN>
API Gateway kiểm tra JWT, nếu hợp lệ → Chuyển tiếp yêu cầu đến Order Service (localhost:8082/orders/123).

Order Service xử lý yêu cầu và trả về phản hồi.

API Gateway nhận phản hồi và gửi lại cho Client.

Lợi ích:
✅ Tập trung xác thực JWT → Các microservices không cần tự kiểm tra token.
✅ Điều phối yêu cầu → Client chỉ cần gọi API Gateway thay vì từng service riêng lẻ.
✅ Bảo mật và tối ưu hóa → Có thể thêm caching, rate limiting, logging.

API Gateway giúp hệ thống gọn nhẹ, bảo mật và dễ quản lý hơn trong kiến trúc microservices! 🚀

 */

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Logic xác thực
            ServerHttpRequest request = exchange.getRequest();
            // Ví dụ: Kiểm tra JWT hoặc header nào đó
            if (!request.getHeaders().containsKey("Authorization")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            // Nếu hợp lệ
            return chain.filter(exchange);
        };
    }

    public static class Config {
        // Nếu cần cấu hình gì thêm
    }
}
