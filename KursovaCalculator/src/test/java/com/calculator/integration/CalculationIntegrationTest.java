package com.calculator.integration;
import com.calculator.dto.CalculationRequestDTO;
import com.calculator.dto.CalculationResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class CalculationIntegrationTest extends AbstractIntegrationTest {

    @Test
    void shouldEvaluateSimpleExpression() {
        CalculationRequestDTO request = new CalculationRequestDTO();
        request.setExpression("2 + 2");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getValidUserToken());

        HttpEntity<CalculationRequestDTO> entity = new HttpEntity<>(request, headers);

        ResponseEntity<CalculationResponseDTO> response = restTemplate.exchange(
                getUrl("/api/calculations/evaluate"),
                HttpMethod.POST,
                entity,
                CalculationResponseDTO.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getResult()).isEqualTo("4.0");
    }

    private String getValidUserToken() {
        return "";
    }
}