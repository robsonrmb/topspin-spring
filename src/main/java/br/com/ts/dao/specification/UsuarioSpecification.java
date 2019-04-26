package br.com.ts.dao.specification;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.ts.domain.Usuario;


@SuppressWarnings("serial")
public class UsuarioSpecification implements Specification<Usuario> {

	private String email;
	private String senha;
	
	public UsuarioSpecification(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	@Override
	public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder cb) {	
		final Collection<Predicate> predicates = new ArrayList<Predicate>();
		if (this.email != null) {
			//Predicate p = cb.like(cb.upper(root.get(Acesso_.email)), "%" + this.email.toUpperCase() +"%");
			//predicates.add(p);			
		}
		if (this.senha != null) {
			//Predicate p = cb.isTrue(root.get(Acesso_.senha).in(this.senha));
			//predicates.add(p);
		}

        Predicate[] array = predicates.toArray(new Predicate[predicates.size()]);        
        
		return cb.and(array);
	}

}
