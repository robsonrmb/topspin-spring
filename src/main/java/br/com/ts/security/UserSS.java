package br.com.ts.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.ts.enums.Perfil;

/*
 * ESPÉCIE DE UM CONTRATO QUE O SPRING PRECISA PARA TRABALHAR COM O USUÁRIO.
 * PODERÁ IMPLEMENTAR UMA LÓGICA CONFORME SUAS REGRAS DE NEGÓCIO.
 * ATRIBUTOS: ID, EMAIL, SENHA E UMA LISTA DE PERFIS.
 */

public class UserSS implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	//Atributos conforme minha regra de negócio.
	private Long id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {
	}
	
	public UserSS(Long id, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream()
								 .map(x -> new SimpleGrantedAuthority(x.getDescricao()))
								 .collect(Collectors.toList());
	}
	
	// MÉTODO ORIGINAL DO FRAMEWORK É O getUsername()
	public String getEmail() {
		return email;
	}
	
	// VERIFICA SE O USUÁRIO POSSUI O PERFIL PASSADO NO PARÂMETRO.
	public boolean isUsuarioComPerfil(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}

	// Atributo a mais da minha regra de negócio.
	public Long getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
