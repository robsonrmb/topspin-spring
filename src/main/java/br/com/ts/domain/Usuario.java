package br.com.ts.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ts.enums.Perfil;

//@SuppressWarnings("serial")
@Entity
@NamedQueries({
	@NamedQuery(name =  "lista.porEstado", 
				query = "SELECT u FROM Usuario u where u.estado = :estado and u.status = 'A'" ),
	@NamedQuery(name =  "busca.porEmail", 
				query = "SELECT u FROM Usuario u where u.email = :email and u.status = 'A'" ),
	@NamedQuery(name =  "lista.porNome", 
				query = "SELECT u FROM Usuario u where u.nome like :nome and u.status = 'A'" )
})
@Table(name="USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@Column(name = "nome", length = 60)
	private String nome;
	
	@Column(name = "email", nullable = false, unique = true, length = 60)
	private String email;
	
	@JsonIgnore
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Column(name = "apelido", length = 60)
	private String apelido;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dataNascimento")
	private Date dataNascimento;
	
	@Column(name = "ondeJoga", length = 60)
	private String ondeJoga;
	
	@Column(name = "tipo", length = 1)
	private String tipo;
	
	@Column(name = "nivel", length = 1)
	private String nivel;
	
	@Column(name = "cidade", length = 60)
	private String cidade;
	
	@Column(name = "estado", length = 60)
	private String estado;
	
	@Column(name = "status", nullable = false, length = 1)
	private String status;
	
	@Column(name = "sx", length = 1)
	private String sexo;
	
	@JsonIgnore
	@OneToMany(mappedBy="avaliador")
	private List<Avaliacao> avaliacoes;
	
	@JsonIgnore
	@OneToMany(mappedBy="usuario")
	private List<Estatistica> estatisticas;
	
	@JsonIgnore
	@OneToMany(mappedBy="usuario")
	private List<Jogo> jogos;
	
	@JsonIgnore
	@OneToMany(mappedBy="usuario")
	private List<Convite> convites;
	
	@JsonIgnore
	@OneToMany(mappedBy="usuario")
	private List<Contabilizacao> contabilizacoes;
	
	@JsonIgnore
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@Transient
	private boolean amigo;
	
	@Transient
	private String dataNascimentoFormatada;
	
	
	public Usuario() {
		addPerfil(Perfil.USUARIO);
	}
	
	public Usuario(String nome, String email, String senha, String estado, String sexo, String status) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.estado = estado;
		this.sexo = sexo;
		this.status = status;
		addPerfil(Perfil.USUARIO);
	}
	
	public Usuario(Long id, String nome, String email, String senha, String apelido, Date dataNascimento, String ondeJoga,
			String tipo, String nivel, String cidade, String estado, String status, String sexo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.apelido = apelido;
		this.dataNascimento = dataNascimento;
		this.ondeJoga = ondeJoga;
		this.tipo = tipo;
		this.nivel = nivel;
		this.cidade = cidade;
		this.estado = estado;
		this.status = status;
		this.sexo = sexo;
		addPerfil(Perfil.USUARIO);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getOndeJoga() {
		return ondeJoga;
	}

	public void setOndeJoga(String ondeJoga) {
		this.ondeJoga = ondeJoga;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public List<Estatistica> getEstatisticas() {
		return estatisticas;
	}

	public void setEstatisticas(List<Estatistica> estatisticas) {
		this.estatisticas = estatisticas;
	}

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public List<Convite> getConvites() {
		return convites;
	}

	public void setConvites(List<Convite> convites) {
		this.convites = convites;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isAmigo() {
		return amigo;
	}

	public void setAmigo(boolean amigo) {
		this.amigo = amigo;
	}

	public String getDataNascimentoFormatada() {
		String dt = "";
		String dt_banco = null;
		if (getDataNascimento() != null) {
			dt_banco = getDataNascimento().toString();
		}
		if (dt_banco != null && !"".equals(dt_banco.toString())) {
			dt = dt_banco.substring(8) + '/' +
				 dt_banco.substring(5,7) + '/' +
				 dt_banco.substring(0,4);
		}
		return dt;
	}

	public void setDataNascimentoFormatada(String dataNascimentoFormatada) {
		this.dataNascimentoFormatada = dataNascimentoFormatada;
	}

	public List<Contabilizacao> getContabilizacoes() {
		return contabilizacoes;
	}

	public void setContabilizacoes(List<Contabilizacao> contabilizacoes) {
		this.contabilizacoes = contabilizacoes;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Set<Perfil> getPerfis() {
		//retorna os perfis do cliente usando lambda.
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCodigo());
	}
	
}
