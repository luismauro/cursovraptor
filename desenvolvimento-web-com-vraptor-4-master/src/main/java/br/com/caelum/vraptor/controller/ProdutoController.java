package br.com.caelum.vraptor.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.ProdutoDao;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.simplemail.Mailer;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;

@Controller
public class ProdutoController {
    
	
	private final Result result;
	private final ProdutoDao dao;
	private final Validator validator;
	private final Mailer mailer;
	
	// injeta no projeto result, o dao, e o validator 
	// para usar inject depende do META-INF/beans.xml
	@Inject
	public ProdutoController(Result result, ProdutoDao dao, Validator validator, Mailer mailer){
		this.result = result;
		this.dao = dao;
		this.validator = validator;
		this.mailer = mailer;
	}
	
	public ProdutoController(){
		this(null, null, null, null);
	}
	
	
	// Path("/")
	@Get("/")
	public void inicio(){
		
	}
	
	
	
	// Path("/produto/lista")
		@Get
		public void lista(){
			result.include("produtoList", dao.lista());
		}
		
		@Get
		public void listaXML(){
			List<Produto> lista = dao.lista();
			// pode ser xml ou outro formato json, ajax
			result.use(Results.xml()).from(lista).serialize();
		}
		
	
	/* Path("/produto/lista")
	@Get
	public List<Produto> lista(){
		EntityManager em = JPAUtil.criaEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		return dao.lista();
	}*/
		
		
	
	// Path("/produto/formulario")
	@Get
	public void formulario(){
		
	}
	
	// Path("/produto/adiciona")
	@Post
	public void adiciona(@Valid Produto produto){ // @Valid valida a classe produto, quantidade de caracteres etc
		
		
		//validacao usando validator
		validator.onErrorUsePageOf(this).formulario();
		
		
		dao.adiciona(produto);
		result.include("mensagem","Produto adicionado com sucesso !!");
		
		// redirect muda o url do navegador e manda uma nova requisicao servidor
		result.redirectTo(ProdutoController.class).lista();
		
		/*Forward: executa todo o trabalho dentro do servidor, n limpa formulario e
		 * parametros que estao no navegador
		 * 
		 * result.forwardTo(ProdutoController.class).lista();
		 * 
		 */
	}
	
	// metodo envia email vraptor
	@Get
	public void enviaPedidoDeNovosItens(Produto produto) throws EmailException {
	    Email email = new SimpleEmail();
	    email.setSubject("[vraptor-produtos] Precisamos de mais estoque");
	    email.addTo("luispenholato@gmail.com");
	    email.setMsg("Precisamos de mais itens do produto" + produto.getNome());
	    mailer.send(email);
	    result.redirectTo(this).lista();
	}
}
