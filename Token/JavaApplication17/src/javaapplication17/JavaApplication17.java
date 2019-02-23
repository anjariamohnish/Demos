/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication17;

/**
 *
 * @author mohnish
 */
public class JavaApplication17 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String oldAuthToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdXRoVG9rZW4iLCJpc3MiOiJkb21haW4iLCJleHAiOjE1NTAyNDIwODcsImp0aSI6IjEwMDEifQ.FMwqi3TmSHIa__hecTfv5QsTazNzA7NIazOJaYFGrw4";
        String oldRefreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJpc3MiOiJkb21haW4iLCJqdGkiOiIxMDAxIn0.C7ZHEAiG5WiXKwRCikcEFVkMBUJYFSWSkVONiudLSlM";

        User user = new User(1001, "John Doe", "john@domain.com", "0987654321");
        Token token = TokenService.generateToken(Long.toString(user.getUserId()));
        System.out.println(token.getAuthToken());
        System.out.println(token.getRefreshToken());
        boolean isValid = TokenService.verifyToken(token.getAuthToken());
        if (isValid) {
            System.out.println("Valid");
        } else {
            Token newToken = TokenService.refreshToken(oldRefreshToken);
        }
    }

}
