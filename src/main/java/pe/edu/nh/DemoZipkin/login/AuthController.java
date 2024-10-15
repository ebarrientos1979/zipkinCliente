package pe.edu.nh.DemoZipkin.login;

import org.springframework.web.bind.annotation.*;
import org.slf4j.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public Respuesta login(@RequestBody LoginRequest loginRequest){
        logger.info("Ingresando al método login");
        System.out.println(loginRequest.getPassword());
        System.out.println(loginRequest.getUsername());

        if(!isValidUser(loginRequest)){
            logger.error("Login fallido para el usuario: {}", loginRequest.getUsername());
            return new Respuesta("Login fallido");
        }else{
            logger.info("Login exitoso para el usuario: {}", loginRequest.getUsername());
            return new Respuesta("Login exitoso");
        }
    }

    private boolean isValidUser(LoginRequest loginRequest){
        return "admin".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword());
    }

}

class Respuesta {
    private String respuesta;

    public Respuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
