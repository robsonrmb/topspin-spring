package br.com.ts.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.AmigoDao;
import br.com.ts.dao.AreaAvaliacaoDao;
import br.com.ts.dao.ConviteDao;
import br.com.ts.dao.JogoDao;
import br.com.ts.dao.TipoAvaliacaoDao;
import br.com.ts.dao.TipoEstatisticaDao;
import br.com.ts.dao.TipoRespostaAvaliacaoDao;
import br.com.ts.dao.TipoRespostaEstatisticaDao;
import br.com.ts.dao.UsuarioDao;
import br.com.ts.domain.Amigo;
import br.com.ts.domain.AreaAvaliacao;
import br.com.ts.domain.Convite;
import br.com.ts.domain.Jogo;
import br.com.ts.domain.TipoAvaliacao;
import br.com.ts.domain.TipoEstatistica;
import br.com.ts.domain.TipoRespostaAvaliacao;
import br.com.ts.domain.TipoRespostaEstatistica;
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
	private AmigoDao amigoDao;
	
	@Autowired
	private AreaAvaliacaoDao areaAvaliacaoDao;
	
	@Autowired
	private TipoAvaliacaoDao tipoAvaliacaoDao;
	
	@Autowired
	private TipoRespostaAvaliacaoDao tipoRespostaAvaliacaoDao;
	
	@Autowired
	private TipoEstatisticaDao tipoEstatisticaDao;
	
	@Autowired
	private TipoRespostaEstatisticaDao tipoRespostaEstatisticaDao;
	
	@Autowired
	private JogoDao jogoDao;
	
	@Autowired
	private ConviteDao conviteDao;

	public void instanciandoBancoDeDadosH2() throws ParseException {
		
		instanciandoBancoDeDados();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Usuario usu1 = new Usuario(null, "Robson Brito", "robson.rmb@gmail.com", pe.encode("123"), "Robinho", sdf.parse("24/06/1978"), "AABB", "1", "1", "Brasília", "DF", "A", "M");
		usu1.addPerfil(Perfil.ADMIN);
		
		Usuario usu2 = new Usuario(null, "Lilian Faria", "lilian.965698@gmail.com", pe.encode("123"), "Lilinha", sdf.parse("30/03/1978"), "AABB", "1", "1", "Goiânia", "GO", "A", "F");
		Usuario usu3 = new Usuario(null, "Pedro Faria", "pedro@gmail.com", pe.encode("123"), "Pepe", sdf.parse("16/12/2010"), "IATE", "1", "1", "Brasília", "DF", "A", "M");
		Usuario usu4 = new Usuario(null, "Lucas Brito", "lucas@gmail.com", pe.encode("123"), "Luquibas", sdf.parse("18/09/2014"), "SQA", "1", "1", "Brasília", "DF", "A", "M");
		usuarioDao.saveAll(Arrays.asList(usu1, usu2, usu3, usu4));
		
		Amigo amigo = new Amigo(null, usu1, usu2);
		amigoDao.save(amigo);
		
		Jogo jogo1 = new Jogo(null, new Date(), "0", "V", "0", 0, 0, usu1, null);
		Jogo jogo2 = new Jogo(null, new Date(), "2", "V", "0", 0, 0, usu1, null);
		Jogo jogo3 = new Jogo(null, new Date(), "0", "D", "4", 0, 0, usu1, null);
		Jogo jogo4 = new Jogo(null, new Date(), "0", "V", "0", 0, 0, usu4, null);
		jogoDao.saveAll(Arrays.asList(jogo1, jogo2, jogo3, jogo4));
		
		Convite cvt1 = new Convite(null, usu1, usu2, new Date(), "0", "AABB", "Vamos nessa!!!", "P");
		Convite cvt2 = new Convite(null, usu1, usu3, new Date(), "0", "AABB", "Topa!!!", "P");
		Convite cvt3 = new Convite(null, usu3, usu4, new Date(), "0", "SQA", "Sem chance!!!", "P");
		conviteDao.saveAll(Arrays.asList(cvt1, cvt2, cvt3));
	}

	public void instanciandoBancoDeDadosMySQL() {
		instanciandoBancoDeDados();
	}
	
	private void instanciandoBancoDeDados() {
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
		TipoRespostaAvaliacao tra4 = new TipoRespostaAvaliacao(null, "ÓTIMO");
		tipoRespostaAvaliacaoDao.saveAll(Arrays.asList(tra1, tra2, tra3, tra4));
		
		TipoEstatistica te1 = new TipoEstatistica(null, "SAQUE");
		TipoEstatistica te2 = new TipoEstatistica(null, "FOREHAND");
		TipoEstatistica te3 = new TipoEstatistica(null, "BACKHAND");
		TipoEstatistica te4 = new TipoEstatistica(null, "VOLEIO");
		TipoEstatistica te5 = new TipoEstatistica(null, "SMASH");
		TipoEstatistica te6 = new TipoEstatistica(null, "OFENSIVO");
		TipoEstatistica te7 = new TipoEstatistica(null, "DEFENSIVO");
		TipoEstatistica te8 = new TipoEstatistica(null, "TATICO");
		TipoEstatistica te9 = new TipoEstatistica(null, "COMPETITIVO");
		TipoEstatistica te10 = new TipoEstatistica(null, "PREPARO");
		TipoEstatistica te11 = new TipoEstatistica(null, "VITORIA");
		TipoEstatistica te12 = new TipoEstatistica(null, "DERROTA");
		TipoEstatistica te13 = new TipoEstatistica(null, "TIEBREAKVENCIDO");
		TipoEstatistica te14 = new TipoEstatistica(null, "TIEBREAKPERDIDO");
		tipoEstatisticaDao.saveAll(Arrays.asList(te1, te2, te3, te4, te5, te6, te7, te8, te9, te10, te11, te12, te13, te14));
		
		TipoRespostaEstatistica tre1 = new TipoRespostaEstatistica(null, "RUIM");
		TipoRespostaEstatistica tre2 = new TipoRespostaEstatistica(null, "REGULAR");
		TipoRespostaEstatistica tre3 = new TipoRespostaEstatistica(null, "BOM");
		TipoRespostaEstatistica tre4 = new TipoRespostaEstatistica(null, "ÓTIMO");
		tipoRespostaEstatisticaDao.saveAll(Arrays.asList(tre1, tre2, tre3, tre4));
	}

}
