package br.com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.ConviteDao;
import br.com.ts.domain.Convite;
import br.com.ts.domain.Usuario;
import br.com.ts.dto.ConviteDTO;
import br.com.ts.service.mail.EmailService;

@Service 
@Transactional(readOnly = false)
public class ConviteService {

	@Autowired
	private ConviteDao conviteDao;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EmailService emailService;
	
	public void salva(ConviteDTO conviteDTO) {
		
		Usuario usuario = usuarioService.find(conviteDTO.getIdUsuario());
		Usuario convidado = usuarioService.find(conviteDTO.getIdConvidado());
		
		RegrasNegocioService.umUsuarioNaoPodeConvidarEleMesmo(convidado);
		RegrasNegocioService.umUsuarioNaoPodeConvidarPorOutroUsuario(usuario);
		
		Convite convite = new Convite();
		convite.setUsuario(usuario);
		convite.setConvidado(convidado);
		convite.setData(conviteDTO.getData());
		convite.setPeriodo(conviteDTO.getPeriodo());
		convite.setLocalJogo(conviteDTO.getLocalJogo());
		convite.setDescricao(conviteDTO.getDescricao());
		convite.setStatus("P");
      	
		conviteDao.save(convite);
		
		convite.setUsuario(usuarioService.find(usuario.getId()));
		
		emailService.sendOrderConfirmationEmail(convite);
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
