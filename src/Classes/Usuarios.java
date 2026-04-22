package Classes;

import java.time.LocalDateTime;

/**
 * Maria Paula -9284
 */
public class Usuarios {

    // PRIMARY KEY no banco)
    private int idUsuario;
    private String nome;
    private String email;
    
    /**
     * AS IMAGENS SÃO EM FORMATOS DE BYTES
     * O SQL ARMAZENA AS IMGENS EM COLUNAS DO TIPO:
     *      BLOB >> BINARY LARGE OBJECT 
     *          >> CONJUNTO DE BYTES
     * JAVA TAMBÉM USA BYTE PORQUE É O CONTEÚDO CRU DO ARQUIVO
    */
    private byte[] fotoPerfil;

    // Define se o usuário é administrador.
    // No SQL é TINYINT(1) (convertido automaticamente no SQL) >> usado como boolean.
    private boolean isAdmin;

    // Data e hora de criação do registro
    private LocalDateTime dataCriacao;


    // --------------------------------------------------------
    // CONSTRUTOR VAZIO
    // Necessário para criar objetos manualmente
    // --------------------------------------------------------
    public Usuarios() {}


    // --------------------------------------------------------
    // CONSTRUTOR SEM ID
    // Necessário pra quando for cria um novo usuário
    // O SQL vai gerar o id automaticamente.
    // --------------------------------------------------------
    public Usuarios(String nome, String email, byte[] fotoPerfil, boolean isAdmin) {
        this.nome = nome; 
        this.email = email; 
        this.fotoPerfil = fotoPerfil; 
        this.isAdmin = isAdmin; 
        this.dataCriacao = LocalDateTime.now(); 
    }


    // --------------------------------------------------------
    // CONSTRUTOR COMPLETO
    // Usado quando os dados vêm do banco.
    // --------------------------------------------------------
    public Usuarios(int idUsuario, String nome, String email, byte[] fotoPerfil, boolean isAdmin, LocalDateTime dataCriacao) {
        this.idUsuario = idUsuario;     
        this.nome = nome;               
        this.email = email;             
        this.fotoPerfil = fotoPerfil;   
        this.isAdmin = isAdmin;         
        this.dataCriacao = dataCriacao; 
    }


    // --------------------------------------------------------
    // GETTERS E SETTERS
    // Permitem acessar e modificar os atributos de forma segura.
    // --------------------------------------------------------

    // ID do usuário
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Nome completo
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Email único
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Foto de perfil em bytes (LONGBLOB)
    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    // Data de criação da conta
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
