package com.controleponto.api.resource;

import com.controleponto.api.domain.exception.NegocioException;
import com.controleponto.api.modelmapper.ProjetoModelInput;
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
public class ProjetoResourceTest extends MassaDeDados {

    @BeforeEach
    public void setUpAll() {
        baseURI = URL_BASE;
    }

    @Test
    public void deve_buscar_projetos() {
        Response response = given()
                .when()
                    .get(PATH_PROJETOS)
                .then()
                    .statusCode(HttpStatus.OK.value())
                .extract()
                    .response();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void deve_salvar_projeto() throws NegocioException {
        ProjetoModelInput input = new ProjetoModelInput();
        input.setNome("projeto-postman API");

        Response response = given()
                .body(input)
                .when()
                    .post(PATH_PROJETOS)
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                .extract()
                    .response();

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void deve_ativar_inativar_projeto() {
        Response response = given()
                .with()
                    .pathParam("id", ID)
                .body(false)
                .when()
                    .put(PATH_PROJETOS + "/{id}/ativo")
                .then()
                    .statusCode(HttpStatus.NO_CONTENT.value())
                .extract()
                    .response();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode());
    }

    private RequestSpecification given() {
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        return request;
    }

}
