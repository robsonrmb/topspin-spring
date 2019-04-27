package br.com.ts.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@SuppressWarnings("serial")
@Entity
@Table(name="CONVITE")
public class Convite implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario; //quem convidou
	
	@ManyToOne
	@JoinColumn(name="id_usuario_convidado")
	private Usuario convidado;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Date data;
	
	@Column(name = "periodo", nullable = false, length = 1)
	private String periodo;
	
	@Column(name = "localJogo", nullable = false)
	private String localJogo;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@Column(name = "status", nullable = false, length = 1)
	private String status;
	
	public Convite() {}

	public Convite(Long id, Usuario usuario, Usuario convidado, Date data, String periodo, String localJogo,
			String descricao, String status) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.convidado = convidado;
		this.data = data;
		this.periodo = periodo;
		this.localJogo = localJogo;
		this.descricao = descricao;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getConvidado() {
		return convidado;
	}

	public void setConvidado(Usuario convidado) {
		this.convidado = convidado;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getLocalJogo() {
		return localJogo;
	}

	public void setLocalJogo(String localJogo) {
		this.localJogo = localJogo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("\nVocê foi convidado para uma partida de tênis.");
		builder.append("\nPessoa que convidou: " + getUsuario().getNome() + " ("+ getUsuario().getApelido() +")");
		builder.append("\nData do Jogo: " + sdf.format(getData()));
		builder.append("\nPeríodo: " + getPeriodo());
		builder.append("\nLocal: " + getLocalJogo());
		builder.append("\n" + getDescricao());
		return builder.toString();
		
		/* FORMATAÇÃO DE VALOR
		 * PARA ATRIBUTOS DO TIPO DOUBLE.
		 * 
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		nf.format(getPreco()) //Sendo que o atributo preço é do tipo Double.
		*/
	}
	
}
