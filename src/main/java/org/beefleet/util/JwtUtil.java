//package org.beefleet.util;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Base64;
//import java.util.Map;
//
//@Component
//public class JwtUtil {
//    @Autowired
//    private JwtDecoder jwtDecoder;
//
//
//    public Map<String, Object> parseToken(String jwt) throws JsonProcessingException {
//        // Split JWT into header, payload, and signature
//        String[] parts = jwt.split("\\.");
//        if (parts.length != 3) {
//            throw new IllegalArgumentException("Invalid JWT token");
//        }
//
//        // Decode the payload (second part)
//        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
//
//        // Convert payload to JSON object
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Object> claims = mapper.readValue(payload, Map.class);
//        return claims;
//    }
//
//    public String extractUsernameFromJwtString(String jwt) {
//        try {
//            Map<String, Object> claims = parseToken(jwt);
//            // Extract and return the username
//            return (String) claims.get("preferred_username");
//        } catch (Exception e) {
//            System.err.println("Error decoding JWT: " + e.getMessage());
//            return null;
//        }
//    }
//
//    public String extractUserIdFromJwtString(String jwt) {
//        try {
//            Map<String, Object> claims = parseToken(jwt);
//            // Extract and return the username
//            return (String) claims.get("sub");
//        } catch (Exception e) {
//            System.err.println("Error decoding JWT: " + e.getMessage());
//            return null;
//        }
//    }
//}

package org.beefleet.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Map;

@Component
public class JwtUtil {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String extractUsernameFromJwtString(String jwt) {
        try {
            if (jwt != null && jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
            }

            // Parse JWT manually
            String[] parts = jwt.split("\\.");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid JWT format");
            }

            String payload = new String(Base64.getUrlDecoder().decode(parts[1]));

            Map<String, Object> claims = objectMapper.readValue(payload, Map.class);

            return (String) claims.get("preferred_username");
        } catch (Exception e) {
            throw new RuntimeException("Error extracting username from JWT", e);
        }
    }
}