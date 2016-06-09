package br.com.caelum.vraptor.interceptor;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AfterCall;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.BeforeCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.annotations.Public;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.controller.LoginController;
import br.com.caelum.vraptor.controller.UsuarioLogado;

@Intercepts
public class AutorizadorInterceptor {

   @Inject private Result result;
   @Inject private UsuarioLogado usuarioLogado;
   @Inject private ControllerMethod controllerMethod;

    @Inject
    public AutorizadorInterceptor(Result result, 
            UsuarioLogado usuarioLogado) {
        this.result = result;
        this.usuarioLogado = usuarioLogado;
        this.controllerMethod = controllerMethod;
    }
    
    @Accepts
    public boolean accepts() {
        return !controllerMethod.containsAnnotation(Public.class);// alguma condição aqui
    }
    
    

    @Deprecated
    AutorizadorInterceptor() {
        this(null, null); // para uso do CDI
    }

    @AroundCall
    public void intercept(SimpleInterceptorStack stack) {

        if (usuarioLogado.getUsuario() == null) {
            result.redirectTo(LoginController.class).formulario();
            return;
        }
        stack.next();
    }
    
}
