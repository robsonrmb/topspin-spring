package br.com.ts.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.AmigoDao;
import br.com.ts.dao.AreaAvaliacaoDao;
import br.com.ts.dao.TipoAvaliacaoDao;
import br.com.ts.dao.TipoRespostaAvaliacaoDao;
import br.com.ts.dao.UsuarioDao;
import br.com.ts.domain.Amigo;
import br.com.ts.domain.AreaAvaliacao;
import br.com.ts.domain.TipoAvaliacao;
import br.com.ts.domain.TipoRespostaAvaliacao;
import br.com.ts.domain.Usuario;
import br.com.ts.enums.Perfil;

@Service 
@Transactional(readOnly = false)
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private AreaAvaliacaoDao areaAvaliacaoDao;
	
	@Autowired
	private TipoAvaliacaoDao tipoAvaliacaoDao;
	
	@Autowired
	private TipoRespostaAvaliacaoDao tipoRespostaAvaliacaoDao;
	
	@Autowired
	private AmigoDao amigoDao;

	public void instanciandoBancoDeDados() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Usuario usu1 = new Usuario(null, "Robson Brito", "robson.rmb@gmail.com", pe.encode("123"), "Robinho", sdf.parse("24/06/1978"), "AABB", "1", "1", "Brasília", "DF", "A", "M");
		usu1.addPerfil(Perfil.ADMIN);
		
		Usuario usu2 = new Usuario(null, "Lilian Faria", "lilian.965698@gmail.com", pe.encode("123"), "Lilinha", sdf.parse("30/03/1978"), "AABB", "1", "1", "Goiânia", "GO", "A", "F");
		Usuario usu3 = new Usuario(null, "Pedro Faria", "pedro@gmail.com", pe.encode("123"), "Pepe", sdf.parse("16/12/2010"), "IATE", "1", "1", "Brasília", "DF", "A", "M");
		Usuario usu4 = new Usuario(null, "Lucas Brito", "lucas@gmail.com", pe.encode("123"), "Luquibas", sdf.parse("18/09/2014"), "SQA", "1", "1", "Brasília", "DF", "A", "M");
		usuarioDao.saveAll(Arrays.asList(usu1, usu2, usu3, usu4));
		
		Amigo amigo = new Amigo(null, usu1, usu2);
		amigoDao.save(amigo);
		
		AreaAvaliacao area1 = new AreaAvaliacao(null, "Avaliações Técnicas");
		AreaAvaliacao area2 = new AreaAvaliacao(null, "Avaliações Táticas");
		areaAvaliacaoDao.saveAll(Arrays.asList(area1, area2));
		
		TipoAvaliacao ta1 = new TipoAvaliacao(null, "SAQUE", area1);
		TipoAvaliacao ta2 = new TipoAvaliacao(null, "FOREHAND", area1);
		TipoAvaliacao ta3 = new TipoAvaliacao(null, "BACKHAND", area1);
		TipoAvaliacao ta4 = new TipoAvaliacao(null, "VOLEIO", area1);
		TipoAvaliacao ta5 = new TipoAvaliacao(null, "SMASH", area1);
		TipoAvaliacao ta6 = new TipoAvaliacao(null, "OFENSIVO", area2);
		TipoAvaliacao ta7 = new TipoAvaliacao(null, "DEFENSIVO", area2);
		TipoAvaliacao ta8 = new TipoAvaliacao(null, "TATICO", area2);
		TipoAvaliacao ta9 = new TipoAvaliacao(null, "COMPETITIVO", area2);
		TipoAvaliacao ta10 = new TipoAvaliacao(null, "PREPARO", area2);
		tipoAvaliacaoDao.saveAll(Arrays.asList(ta1, ta2, ta3, ta4, ta5, ta6, ta7, ta8, ta9, ta10));
		
		TipoRespostaAvaliacao tra1 = new TipoRespostaAvaliacao(null, "RUIM");
		TipoRespostaAvaliacao tra2 = new TipoRespostaAvaliacao(null, "REGULAR");
		TipoRespostaAvaliacao tra3 = new TipoRespostaAvaliacao(null, "BOM");
		TipoRespostaAvaliacao tra4 = new TipoRespostaAvaliacao(null, "OTIMO");
		tipoRespostaAvaliacaoDao.saveAll(Arrays.asList(tra1, tra2, tra3, tra4));
	}

}
