package br.com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.ConviteDao;
import br.com.ts.domain.Convite;
import br.com.ts.domain.Usuario;
import br.com.ts.dto.ConviteDTO;

@Service 
@Transactional(readOnly = false)
public class ConviteService {

	@Autowired
	private ConviteDao conviteDao;
	
	public void salva(ConviteDTO formConvite) {
		
		Usuario usuario = new Usuario();
		usuario.setId(formConvite.getIdUsuario());
		
		Usuario convidado = new Usuario();
		convidado.setId(formConvite.getIdConvidado());
		
		Convite convite = new Convite();
		convite.setUsuario(usuario);
		convite.setConvidado(convidado);
		convite.setData(formConvite.getData());
		convite.setPeriodo(formConvite.getPeriodo());
		convite.setLocalJogo(formConvite.getLocalJogo());
		convite.setDescricao(formConvite.getDescricao());
		convite.setStatus("P");
      	
		conviteDao.save(convite);
	}

	public void atualiza(Convite convite) {
		conviteDao.save(convite);
	}
	
	public void aceita(Convite convite) {
		Convite conv = conviteDao.findById(convite.getId()).get();
		conv.setStatus("A");
	}

	public void recusa(Convite convite) {
		Convite conv = conviteDao.findById(convite.getId()).get();
		conv.setStatus("R");
	}

	public void exclui(Long id) {
		conviteDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Convite buscaPorId(Long id) {
		return conviteDao.findById(id).get();
	}

	@Transactional(readOnly = true)
	public List<Convite> listaTodos() {
		return conviteDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Convite> listaPorUsuarioEStatus(Convite convite) {
		return null; //TODO conviteDao.listaPorUsuarioEStatus(convite);
	}
	
	@Transactional(readOnly = true)
	public List<Convite> listaPorConvidadoEStatus(Convite convite) {
		return null; //TODO conviteDao.listaPorConvidadoEStatus(convite);
	}

	public List<Convite> listaPorConvidadoENaoPendentes(Convite convite) {
		return null; //TODO conviteDao.listaPorConvidadoENaoPendentes(convite);
	}
	
	public int countPorConvidadoEPendentes(Convite convite) {
		convite.setStatus("P");
		return 0; //TODO conviteDao.countPorConvidadoEStatus(convite);
	}

}
