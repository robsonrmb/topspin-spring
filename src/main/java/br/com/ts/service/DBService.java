package br.com.ts.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.AcessoDao;
import br.com.ts.dao.UsuarioDao;
import br.com.ts.domain.Acesso;
import br.com.ts.domain.Usuario;

@Service 
@Transactional(readOnly = false)
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private AcessoDao acessoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;

	public void instanciandoBancoDeDados() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Acesso ac1 = new Acesso("robson.rmb@gmail.com", pe.encode("123"));
		Acesso ac2 = new Acesso("lilian.965698@gmail.com", pe.encode("123"));
		Acesso ac3 = new Acesso("pedro@gmail.com", pe.encode("123"));
		Acesso ac4 = new Acesso("lucas@gmail.com", pe.encode("123"));
		acessoDao.saveAll(Arrays.asList(ac1, ac2, ac3, ac4));
		
		Usuario usu1 = new Usuario(null, "Robson Brito", "robson.rmb@gmail.com", "Robinho", sdf.parse("24/06/1978"), "AABB", "1", "1", "Brasília", "DF", "A", "M");
		Usuario usu2 = new Usuario(null, "Lilian Faria", "lilian.965698@gmail.com", "Lilinha", sdf.parse("30/03/1978"), "AABB", "1", "1", "Goiânia", "GO", "A", "F");
		Usuario usu3 = new Usuario(null, "Pedro Faria", "pedro@gmail.com", "Pepe", sdf.parse("16/12/2010"), "IATE", "1", "1", "Brasília", "DF", "A", "M");
		Usuario usu4 = new Usuario(null, "Lucas Brito", "lucas@gmail.com", "Luquibas", sdf.parse("18/09/2014"), "SQA", "1", "1", "Brasília", "DF", "A", "M");
		usuarioDao.saveAll(Arrays.asList(usu1, usu2, usu3, usu4));
		
	}

}
