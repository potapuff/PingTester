package tss.sumdu.test.unit;

import org.junit.jupiter.api.Test;
import tss.sumdu.util.CSRFTokenService;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSRFTokenServiceTest {

    @Test
    public void testTokenIsValidable(){
        String sessionId = "test";
        String token = CSRFTokenService.generateToken(sessionId);
        assertTrue(CSRFTokenService.validateToken(token, sessionId));
    }

    @Test
    public void testTokenToUserGeneratesDifferentToken(){
        assertNotEquals(CSRFTokenService.generateToken("test1"), CSRFTokenService.generateToken("test2"));
    }

    @Test
    public void testDifferentTokenForSameUserInDifferentTime(){
        String sessionId = "test";
        String token1 = CSRFTokenService.generateToken(sessionId);

        assertTrue(CSRFTokenService.validateToken(token1, sessionId));

        String token2 = CSRFTokenService.generateToken(sessionId);
        assertTrue(CSRFTokenService.validateToken(token2, sessionId));
    }


}
