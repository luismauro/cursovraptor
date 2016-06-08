package br.com.caelum.vraptor.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.caelum.vraptor.model.Usuario;

@SessionScoped
@Named
// sempre q a classe tiver @SessionScoped, precissa colocar implements Serializable
public class UsuarioLogado implements Serializable {

	private Usuario usuario;
	
	public Usuario getUsuario(){
		return usuario;
	}
	
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
}
