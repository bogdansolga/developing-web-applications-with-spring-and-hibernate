package net.safedata.spring.training.d03s03;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import net.safedata.spring.training.d03s03.model.Product;
import net.safedata.spring.training.d03s03.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@SpringBootTest(
        classes = ProductServiceDemo.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("default")
public class ProductControllerTest extends AbstractTransactionalTestNGSpringContextTests {

    private static final String PRODUCT_NAME = "Tablet";

    @LocalServerPort
    private int port;

    @Autowired
    private ProductService productService;

    @BeforeMethod
    public void init() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

    @BeforeClass
    public void initializeProduct() {
        final Product product = new Product();
        product.setName(PRODUCT_NAME);
        productService.saveProduct(product);
    }

    @Test
    public void givenTheContentTypeIsCorrect_WhenGettingAProduct_ThenAllGood() {
        given()
                .accept(ContentType.JSON).
        when()
                .get("/product/{id}", 1).
        then()
                .statusCode(HttpStatus.OK.value())
                .body("name", is(PRODUCT_NAME));
    }

    @Test
    public void givenTheContentTypeIsCorrect_WhenGettingAllProducts_ThenAllGood() {
        given()
                .accept(ContentType.JSON).
        when()
                .get("/product").
        then()
                .statusCode(HttpStatus.OK.value())
                .body("$.size", is(3)) // the response array size is 3
                .body("[0].name", is(PRODUCT_NAME));
    }

    // a sample of using a dataProvider
    @Test(dataProvider = "dataProvider")
    public void givenTheContentTypeIsCorrect_WhenUsingADataProvider_ThenAllGood(final String productId, final int statusCode) {
        given()
                .accept(ContentType.JSON).
        when()
                .get("/product/{id}", productId).
        then()
                .statusCode(statusCode);
    }

    @DataProvider(name = "dataProvider", parallel = true)
    public Object[][] dataProvider() {
        return new Object[][]{
                { "1", HttpStatus.OK.value()}, // productId and status code
                {"13", HttpStatus.BAD_REQUEST.value()}
        };
    }
}
