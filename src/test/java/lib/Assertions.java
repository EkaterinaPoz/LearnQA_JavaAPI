package lib;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Assertions {
    public static void assertJsonByName(Response Response, String name, int expectedValue) {
        Response.then().assertThat().body("$", hasKey(name));

        int value = Response.jsonPath().getInt(name);
        assertEquals(expectedValue, value, "JSON value is not eqaul to expected value");
    }

    public static void assertJsonByName(Response Response, String name, String expectedValue) {
        Response.then().assertThat().body("$", hasKey(name));

        String value = Response.jsonPath().getString(name);
        assertEquals(expectedValue, value, "JSON value is not eqaul to expected value");
    }

    public static void assertResponseTextEquals(Response Responce, String expectedAnswer) {
        assertEquals(
                expectedAnswer,
                Responce.asString(),
                "Response test is not as expected"
        );
    }

    public static void assertResponseCodeEquals(Response Responce, int expectedStatusCoede) {
        assertEquals(
                expectedStatusCoede,
                Responce.statusCode(),
                "Response status code is not as expected"
        );
    }

    public static void assertJsonHasField(Response Response, String expectedFieldName) {
        Response.then().assertThat().body("$", hasKey(expectedFieldName));
    }

    public static void assertJsonHasFields(Response Response, String[] expectedFieldNames) {
        for (String expectedFieldName : expectedFieldNames) {
            Assertions.assertJsonHasField(Response, expectedFieldName);
        }
    }

    public static void assertJsonHasNotField(Response Response, String unexpextedFieldName) {
        Response.then().assertThat().body("$", not(hasKey(unexpextedFieldName)));
    }


}
