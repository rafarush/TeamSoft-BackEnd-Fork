package com.tesis.teamsoft.external.combination_mod;

import com.tesis.teamsoft.configuration.CombinationModApiConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public abstract class BaseExternalService<I, O> {

    protected final CombinationModApiConfig apiConfig;
    protected final WebClient webClient;
    protected final String serviceName;

    public BaseExternalService(CombinationModApiConfig apiConfig,
                               WebClient.Builder webClientBuilder,
                               String serviceName) {
        this.apiConfig = apiConfig;
        this.serviceName = serviceName;
        this.webClient = buildWebClient(webClientBuilder);
    }

    private WebClient buildWebClient(WebClient.Builder builder) {
        CombinationModApiConfig.ServiceConfig config = apiConfig.getServiceConfig(serviceName);

        return builder
                .baseUrl(apiConfig.getBaseUrl())
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("User-Agent", "TeamSoft-Backend/1.0")
                .defaultHeader("X-API-Key",
                        StringUtils.hasText(config.getApiKey())
                                ? config.getApiKey()
                                : apiConfig.getDefaultKey())
                .filter(logRequest())
                .filter(logResponse())
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.debug("External API Request: {} {}",
                    clientRequest.method(),
                    clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) ->
                            values.forEach(value ->
                                    log.trace("Header: {}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.debug("External API Response Status: {}",
                    clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }

    protected abstract O callExternalApi(I input);
}
