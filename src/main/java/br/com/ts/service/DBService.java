package br.com.ts.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.UsuarioDao;
import br.com.ts.domain.Usuario;
import br.com.ts.enums.Perfil;

@Service 
@Transactional(readOnly = false)
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioDao usuarioDao;

	public void instanciandoBancoDeDados() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Usuario usu1 = new Usuario(null, "Robson Brito", "robson.rmb@gmail.com", pe.encode("123"), "Robinho", sdf.parse("24/06/1978"), "AABB", "1", "1", "Brasília", "DF", "A", "M");
		usu1.addPerfil(Perfil.ADMIN);
		
		Usuario usu2 = new Usuario(null, "Lilian Faria", "lilian.965698@gmail.com", pe.encode("123"), "Lilinha", sdf.parse("30/03/1978"), "AABB", "1", "1", "Goiânia", "GO", "A", "F");
		Usuario usu3 = new Usuario(null, "Pedro Faria", "pedro@gmail.com", pe.encode("123"), "Pepe", sdf.parse("16/12/2010"), "IATE", "1", "1", "Brasília", "DF", "A", "M");
		Usuario usu4 = new Usuario(null, "Lucas Brito", "lucas@gmail.com", pe.encode("123"), "Luquibas", sdf.parse("18/09/2014"), "SQA", "1", "1", "Brasília", "DF", "A", "M");
		usuarioDao.saveAll(Arrays.asList(usu1, usu2, usu3, usu4));
		
	}

}
