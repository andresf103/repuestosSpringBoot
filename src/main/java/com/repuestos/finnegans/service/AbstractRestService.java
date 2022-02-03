package com.repuestos.finnegans.service;

import com.repuestos.finnegans.EndPoints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public abstract class AbstractRestService {

    protected HttpHeaders header;
    protected RestTemplate restTemplate;

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    private static String currentToken;

    public AbstractRestService(){
        restTemplate=new RestTemplate();
        header=new HttpHeaders();
    }

    public String getToken() {
        String url;
        if (currentToken==null) {
            url = String.format(EndPoints.GET_TOKEN.getUrl(),
                    clientId, clientSecret);
        } else {
            url = String.format(EndPoints.RENEW_TOKEN.getUrl(),
                    clientId, clientSecret, currentToken);
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> restTemplateResult = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if (restTemplateResult.getStatusCode() == HttpStatus.OK) {
            currentToken = restTemplateResult.getBody();
        }
        return currentToken;
    }

    protected ResponseEntity executeRequest(String url, HttpMethod method, Class clase) {
        header.setBearerAuth(getToken());
        HttpEntity entity = new HttpEntity(header);
        ResponseEntity restTemplateResult = restTemplate.exchange(url, method, entity, clase);
        return restTemplateResult;
    }
    protected ResponseEntity executeRequest(String url, HttpMethod method, ParameterizedTypeReference clase) throws URISyntaxException {
        URI uri=new URI(url);
        header.setBearerAuth(getToken());
        RequestEntity request = new RequestEntity(header, method, uri);
        ResponseEntity restTemplateResult = restTemplate.exchange(request, clase);
        return restTemplateResult;
    }

    protected <T> ResponseEntity executeRequest(String url, HttpMethod method, T instance , Class clase){
        header.setBearerAuth(getToken());
        HttpEntity<T> entity = new HttpEntity(instance,header);
        ResponseEntity restTemplateResult = restTemplate.exchange(url, method, entity, clase);
        return restTemplateResult;
    }
}
