package com.tesis.teamsoft.external.combination_mod.services.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tesis.teamsoft.configuration.CombinationModApiConfig;
import com.tesis.teamsoft.external.combination_mod.BaseExternalService;
import com.tesis.teamsoft.external.combination_mod.services.interfaces.IExternalApiService;
import com.tesis.teamsoft.presentation.dto.TeamProposalDTO;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class CombinationService
        extends BaseExternalService<TeamProposalDTO, TeamProposalDTO>
        implements IExternalApiService<TeamProposalDTO, TeamProposalDTO> {

    private final ObjectMapper objectMapper;

    @Autowired
    public CombinationService(CombinationModApiConfig apiConfig,
                                  WebClient.Builder webClientBuilder,
                                  ObjectMapper objectMapper) {
        super(apiConfig, webClientBuilder, "combine");
        this.objectMapper = objectMapper;
    }

    @Override
    public TeamProposalDTO fetchData(TeamProposalDTO inputDTO) {
        log.info("INITIALIZING FETCHING DATA CALL");

//        validateInput(inputDTO);

        try {
            return callExternalApi(inputDTO);
        } catch (WebClientResponseException e) {
            log.error("External API Error - Status: {}, Body: {}",
                    e.getStatusCode(), e.getResponseBodyAsString());
            throw new ServiceException("Error Consulting Combination API", e);
        } catch (Exception e) {
            log.error("Unexpected Error Consulting Combination API", e);
            throw new ServiceException("Server Internal Error", e);
        }
    }

    @Override
    protected TeamProposalDTO callExternalApi(TeamProposalDTO input) {
        CombinationModApiConfig.ServiceConfig config = apiConfig.getServiceConfig(serviceName);

//        Map<String, Object> requestBody = Map.of(
//                "message", input
//        );

        return webClient
                .post()
                .uri(config.getEndpoint())
                .bodyValue(input)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    log.warn("Error 4xx in external API");
                    return Mono.error(new ServiceException("Error 4xx in external API"));
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    log.error("Error 5xx in external API");
                    return Mono.error(new ServiceException("SERVER ERROR IN EXTERNAL API"));
                })
                .bodyToMono(TeamProposalDTO.class)
                .timeout(Duration.ofSeconds(apiConfig.getTimeoutSeconds()))
                .retryWhen(Retry.backoff(apiConfig.getMaxRetries(),
                                Duration.ofSeconds(1))
                        .filter(this::isRetryableException))
                .block();
    }

    private boolean isRetryableException(Throwable throwable) {
        return throwable instanceof WebClientResponseException
                && ((WebClientResponseException) throwable)
                .getStatusCode().is5xxServerError()
                || throwable instanceof TimeoutException;
    }

//    private void validateInput(ExampleDTO.ExampleInputDTO input) {
//        if (false) {
//            throw new ValidationException("Some error");
//        }
//    }
}
