package ar.com.proyectocac24100.web.filters;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/*" })
public class CorsFilter implements Filter {
    private List<String> origins = List.of("http://localhost:5500", "http://127.0.0.1:5500", "http://127.0.0.1:5501");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Puedes hacer alguna configuración inicial aquí si es necesario
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Esto viene desde el front
        String origin = httpRequest.getHeader("origin");

        System.out.println(origin);
        // ¿Ese front está permitido?
        if (origin != null && origins.contains(origin)) {
            httpResponse.addHeader("Access-Control-Allow-Origin", origin);
            httpResponse.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
            httpResponse.addHeader("Access-Control-Allow-Credentials", "true");

            // Manejar solicitudes OPTIONS
            if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
                httpResponse.setStatus(HttpServletResponse.SC_OK);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Puedes hacer alguna limpieza aquí si es necesario
    }
}
