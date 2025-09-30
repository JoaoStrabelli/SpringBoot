package utfpr.farmdexp.estufa.openapi;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration

// Configurações gerais da API
@OpenAPIDefinition(
        info =@Info(
                title = "Estufa API",
                version = "${api.version}",
                contact = @Contact(
                        name = "Farm de XP", email = "contato@farmdexp.com.br", url = "https://moodle.utfpr.edu.br/course/view.php?id=33032"
                ),
                license = @License(
                        name = "MIT", url = "https://mit-license.org/"
                ),
                //termsOfService = "${tos.uri}",
                description = "${api.description}"
        ),
        servers = {
                @Server(
                        url = "${api.server.dev}",
                        description = "Development"
                ),
                @Server(
                        url = "${api.server.prod}",
                        description = "Production"
                ),
        }
)

// Configurações de autenticação
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenAPIConfiguration {}
