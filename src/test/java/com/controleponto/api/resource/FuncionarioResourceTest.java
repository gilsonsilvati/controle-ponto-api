package com.controleponto.api.resource;

import com.controleponto.api.modelmapper.FuncionarioModelInput;
import com.controleponto.api.util.MassaDeDados;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FuncionarioResourceTest extends MassaDeDados {

    @BeforeEach
    public void setUpAll() {
        baseURI = URL_BASE;
    }

    @Test
    public void deve_buscar_funcionarios() throws Exception {
        Response response = given()
                .when()
                    .get(PATH_FUNCIONARIOS)
                .then()
                    .statusCode(HttpStatus.OK.value())
                .extract()
                    .response();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void deve_salvar_funcionario() throws Exception {
        FuncionarioModelInput input = new FuncionarioModelInput();
        input.setNome("Biel");
        input.setEmail("biel@teste.com");
        input.setCpf("984.471.886-47");

        Response response = given()
                .body(input)
                .when()
                    .post(PATH_FUNCIONARIOS)
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                .extract()
                    .response();

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void deve_validar_cpf_funcionario() throws Exception {
        FuncionarioModelInput input = new FuncionarioModelInput();
        input.setNome("Biel");
        input.setEmail("biel@teste.com.br");
        input.setCpf(cpf_invalido);

        Response response = given()
                .body(input)
                .when()
                    .post(PATH_FUNCIONARIOS)
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                    .response();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    @Test
    public void deve_adicionar_funcionario_ao_projeto() {
        Response response = given()
                .with()
                    .pathParam("id", ID)
                    .queryParam("nome", nome)
                .when()
                    .put(PATH_FUNCIONARIOS + "/{id}/projeto")
                .then()
                    .statusCode(HttpStatus.OK.value())
                .extract()
                    .response();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    private RequestSpecification given() {
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        return request;
    }

}
