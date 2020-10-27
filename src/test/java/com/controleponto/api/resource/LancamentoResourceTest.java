package com.controleponto.api.resource;

import com.controleponto.api.modelmapper.LancamentoModelInput;
import com.controleponto.api.util.MassaDeDados;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.LocalTime;

import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LancamentoResourceTest extends MassaDeDados {

    @BeforeEach
    public void setUpAll() {
        baseURI = URL_BASE;
    }

    @Test
    public void deve_registrar_lancamento() throws Exception {
        LancamentoModelInput input = new LancamentoModelInput();
        input.setHora(LocalTime.of(13, 00, 00));
        input.setCpf("984.471.886-47");

        Response response = given()
                .body(input)
                .when()
                    .post(PATH_LANCAMENTOS)
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                .extract()
                    .response();

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void deve_validar_funcionario_nao_localizado() throws Exception {
        LancamentoModelInput input = new LancamentoModelInput();
        input.setHora(LocalTime.of(9, 00, 00));
        input.setCpf(cpf_nao_localizado);

        Response response = given()
                .body(input)
                .when()
                    .post(PATH_LANCAMENTOS)
                .then()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                    .response();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    @Test
    public void deve_validar_quantidade_registros_dia() throws Exception {
        LancamentoModelInput input = new LancamentoModelInput();
        input.setHora(LocalTime.of(9, 00, 00));
        input.setCpf(cpf);

        Response response = given()
                .body(input)
                .when()
                    .post(PATH_LANCAMENTOS)
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                    .response();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    private RequestSpecification given() {
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        return request;
    }

}
