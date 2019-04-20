package br.com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.bean.FormConvite;
import br.com.ts.dao.ConviteDao;
import br.com.ts.domain.Convite;
import br.com.ts.domain.Usuario;

@Service 
@Transactional(readOnly = false)
public class ConviteServiceImpl {

	@Autowired
	private ConviteDao conviteDao;
	
	public void salva(FormConvite formConvite) {
		
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
		conviteDao.update(convite);
	}
	
	public void aceita(Convite convite) {
		Convite conv = conviteDao.findById(convite.getId());
		conv.setStatus("A");
	}

	public void recusa(Convite convite) {
		Convite conv = conviteDao.findById(convite.getId());
		conv.setStatus("R");
	}

	public void exclui(Long id) {
		conviteDao.delete(id);
	}

	@Transactional(readOnly = true)
	public Convite buscaPorId(Long id) {
		return conviteDao.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Convite> listaTodos() {
		return conviteDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Convite> listaPorUsuarioEStatus(Convite convite) {
		return conviteDao.listaPorUsuarioEStatus(convite);
	}
	
	@Transactional(readOnly = true)
	public List<Convite> listaPorConvidadoEStatus(Convite convite) {
		return conviteDao.listaPorConvidadoEStatus(convite);
	}

	public List<Convite> listaPorConvidadoENaoPendentes(Convite convite) {
		return conviteDao.listaPorConvidadoENaoPendentes(convite);
	}
	
	public int countPorConvidadoEPendentes(Convite convite) {
		convite.setStatus("P");
		return conviteDao.countPorConvidadoEStatus(convite);
	}

}
