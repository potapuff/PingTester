package tss.sumdu.test.unit;

import org.testng.annotations.Test;
import tss.sumdu.util.CSRFTokenService;

import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.assertTrue;

public class CSRFTokenServiceTest {

    @Test
    public void testTokenCanBeValidated() {
        String sessionId = "test";
        String token = CSRFTokenService.generateToken(sessionId);
        assertTrue(CSRFTokenService.validateToken(token, sessionId));
    }

    @Test
    public void testTokenToUserGeneratesDifferentToken() {
        assertNotSame(CSRFTokenService.generateToken("test1"), CSRFTokenService.generateToken("test2"));
    }

    @Test
    public void testDifferentTokenForSameUserInDifferentTime() {
        String sessionId = "test";
        String token1 = CSRFTokenService.generateToken(sessionId);

        assertTrue(CSRFTokenService.validateToken(token1, sessionId));

        String token2 = CSRFTokenService.generateToken(sessionId);
        assertTrue(CSRFTokenService.validateToken(token2, sessionId));
    }


}
