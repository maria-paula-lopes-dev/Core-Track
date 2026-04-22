// --------------------------------------------------------
// CONECTA O JAVA COM O BANCO
// --------------------------------------------------------
package DAOs;

import Classes.Usuarios;
import Conecxao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author maria
 *
 */
/**
 * EXPECIFICAÇÕES DESSA CLASSE:
 *
 * INSERIR >> ADICIONA NOVO USER NO BANCO
 *
 * BUSCAR POR ID >> PEGA AS INFORMAÇÕES DE UM USER PELO ID DELE
 *
 * ATUALIZAR >> ATUALIZAR OS DADOS -> CONFIGURAÇÕES
 *
 */
public class UsuariosDAO {

    // --------------------------------------------------------
    // SALVA UM NOVO USUÁRIO NO BANCO
    // --------------------------------------------------------
    public boolean inserir(Usuarios u) {
        String sql = "INSERT INTO usuarios (nome, email, foto_perfil, is_admin) VALUES (?,?,?,?)";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.criarConexao(); // Aqui pode lançar ClassNotFoundException
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return false;
            }

            stmt = con.prepareStatement(sql);

            // --------------------------------------------------------
            // PREENCHE AS ?
            // --------------------------------------------------------
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            //stmt.setBytes(3, u.getFotoPerfil());
            //stmt.setBoolean(4, u.isAdmin());
            if (u.getFotoPerfil() != null) {
                stmt.setBytes(3, u.getFotoPerfil());
            } else {
                stmt.setNull(3, java.sql.Types.BLOB);
            }

            stmt.setBoolean(4, u.isAdmin());

            // --------------------------------------------------------
            // EXECUTA COMANDO NO SQL
            // --------------------------------------------------------
            stmt.executeUpdate();

            // --------------------------------------------------------
            // RETORRNA SUCESSO
            // --------------------------------------------------------
            return true;

            // --------------------------------------------------------
            // SE ALGO DER ERRO
            // --------------------------------------------------------
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
            return false;
        } finally {
            Conexao.close(con, stmt);
        }
    }

    // --------------------------------------------------------
    // BUSCA UM USUÁRIO USANDO O EMAIL
    // --------------------------------------------------------
    public Usuarios buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = Conexao.criarConexao(); // pode lançar ClassNotFoundException
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return null;
            }

            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            // --------------------------------------------------------
            // SE ENCONTROU O USUÁRIO, CRIA OBJETO E RETORNA
            // --------------------------------------------------------
            if (rs.next()) {
                Usuarios u = new Usuarios();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setFotoPerfil(rs.getBytes("foto_perfil"));
                u.setAdmin(rs.getBoolean("is_admin"));
                return u;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar usuário por email: " + e.getMessage());
        } finally {
            Conexao.close(con, stmt, rs);
        }

        // --------------------------------------------------------
        // SE NÃO EXISTE, RETORNA NULO
        // --------------------------------------------------------
        return null;
    }

    // --------------------------------------------------------
    // CARREGA UM USUÁRIO DO BANCO
    // --------------------------------------------------------
    public Usuarios buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return null;
            }

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            // --------------------------------------------------------
            // EXECUTA O SELECT E GUARDA O RESULTADO EM RS
            // --------------------------------------------------------
            rs = stmt.executeQuery();

            if (rs.next()) {
                Usuarios u = new Usuarios();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setFotoPerfil(rs.getBytes("foto_perfil"));
                u.setAdmin(rs.getBoolean("is_admin"));

                // --------------------------------------------------------
                // OBJETO PREENCHIDO
                // --------------------------------------------------------
                return u;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        } finally {
            Conexao.close(con, stmt, rs);
        }
        return null;
    }

    // --------------------------------------------------------
    // ATURALIZA APENAS O NOME, E-MAIL E FOTO 
    // DE ACORDO COM O PROJETO O APP
    // --------------------------------------------------------
    public boolean atualizar(Usuarios u) {
        String sql = "UPDATE usuarios SET nome=?, email=?, foto_perfil=? WHERE id_usuario=?";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return false;
            }

            stmt = con.prepareStatement(sql);

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());

            if (u.getFotoPerfil() != null) {
                stmt.setBytes(3, u.getFotoPerfil());
            } else {
                stmt.setNull(3, java.sql.Types.BLOB);
            }

            stmt.setInt(4, u.getIdUsuario());

            stmt.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        } finally {
            Conexao.close(con, stmt);
        }
    }

    // --------------------------------------------------------
    // MÉTODO NECESSÁRIO PRA VALIDAR O ID
    // --------------------------------------------------------
    public static boolean existePorId(int id) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE id_usuario = ?";
        try (Connection con = Conexao.criarConexao();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean atualizarFoto(int idUsuario, byte[] novaFoto) {
        String sql = "UPDATE usuarios SET foto_perfil = ? WHERE id_usuario = ?";

        try (Connection conn = Conexao.criarConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Remove a foto 
            // Compatível com o banco
            // NULL no sql
            if (novaFoto != null) {
                stmt.setBytes(1, novaFoto);
            } else {
                stmt.setNull(1, java.sql.Types.BLOB);
            }

            stmt.setInt(2, idUsuario);

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
