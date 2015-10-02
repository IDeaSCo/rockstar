import com.ideas.game.AuthFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;

// Place your Spring DSL code here
beans = {


    negotiateSecurityFilter(waffle.servlet.NegotiateSecurityFilter){
    }

    waffleNegotiateSecurityFilter(FilterRegistrationBean) {
        filter = ref('negotiateSecurityFilter')
        order = 1000
        urlPatterns = ["/*"]
    }

    authFilter(FilterRegistrationBean) {
        filter = new AuthFilter()
        order = 2000
        urlPatterns = ["/*"]
    }
}
