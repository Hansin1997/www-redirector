package cn.dustlight.wwwredirector.filters;

import cn.dustlight.wwwredirector.core.Redirector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Getter
@Setter
@AllArgsConstructor
public class RedirectFilter implements WebFilter {

    private static final String LOCATION_HEADER = "Location";
    private Redirector redirector;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String uri = exchange.getRequest().getURI().toASCIIString();
        String location = redirector.computeRedirect(uri);
        if (StringUtils.hasText(location)) {
            ServerHttpResponse response = exchange.getResponse();
            return Mono.fromRunnable(() -> {
                response.setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
                response.getHeaders().add(LOCATION_HEADER, location);
            });
        }
        return chain.filter(exchange);
    }
}
