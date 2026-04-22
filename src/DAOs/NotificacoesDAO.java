package DAOs;

import Classes.Notificacoes;
import Conecxao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author maria
 */
/**
 * EXPECIFICAÇÕES DESSA CLASSE:
 *
 * INSERIR >> SALVAR NO BANCO -> QUANDO CPU PASSA DO LIMITE...
 *
 * BUSCAR POR ID >> CARREGA NOTIFICAÇÃO INDIVIDUAL
 *
 * LISTAR POR USUÁRIO >> MOSTRA TODAS DAQUELE USER
 *
 * MARCAR COMO LIDA >> DE ' NÃO LIDA' -> 'LIDA'
 *
 * ATUALIZAR >> ATUALIZAR OS DADOS -> CONFIGURAÇÕES
 *
 */
public class NotificacoesDAO {

    // --------------------------------------------------------
    // SALVA UM NOVO USUÁRIO NO BANCO
    // --------------------------------------------------------
    public boolean inserir(Notificacoes n, String titulo) {
        String sql = "INSERT INTO notificacoes (id_usuario, mensagem, nivel, titulo) VALUES (?,?,?,?)";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return false;
            }

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, n.getIdUsuario());
            stmt.setString(2, n.getMensagem());
            stmt.setString(3, n.getTipo());
            stmt.setString(4, titulo);

            stmt.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir notificação: " + e.getMessage());
            return false;
        } finally {
            Conexao.close(con, stmt, null);
        }
    }

    // --------------------------------------------------------
    // CARREGA UM USUÁRIO DO BANCO
    // --------------------------------------------------------
    public Notificacoes buscarPorId(int id) {
        String sql = "SELECT * FROM notificacoes WHERE id_notificacao = ?";
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
            rs = stmt.executeQuery();

            if (rs.next()) {
                Notificacoes n = new Notificacoes(
                        rs.getInt("id_notificacao"),
                        rs.getInt("id_usuario"),
                        rs.getString("mensagem"),
                        rs.getString("nivel"),
                        rs.getTimestamp("data_envio").toLocalDateTime(),
                        rs.getBoolean("lida")
                );
                return n;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar notificação: " + e.getMessage());
        } finally {
            Conexao.close(con, stmt, rs);
        }
        return null;
    }

    // --------------------------------------------------------
    // LISTAR USUÁRIOS/ filtro da lista
    // --------------------------------------------------------
    public ObservableList<Notificacoes> listarPorUsuario(int idUsuario, String nivel) {
        ObservableList<Notificacoes> lista = FXCollections.observableArrayList();
        String sql = " SELECT mensagem, nivel, data_envio FROM notificacoes WHERE id_usuario = ? AND nivel = ? ORDER BY data_envio DESC";

        try (Connection con = Conexao.criarConexao();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setString(2, nivel);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Notificacoes n = new Notificacoes(
                        rs.getString("mensagem"),
                        rs.getString("nivel"),
                        rs.getTimestamp("data_envio").toLocalDateTime()
                );

                lista.add(n);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao listar notificações: " + e.getMessage());
        }

        return lista;
    }

    // --------------------------------------------------------
    // CLASSIFICAR NOTIFICAÇÕES
    // --------------------------------------------------------
    public boolean marcarComoLida(int idNotificacao) {
        String sql = "UPDATE notificacoes SET lida = 1 WHERE id_notificacao = ?";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return false;
            }

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idNotificacao);
            stmt.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao marcar como lida: " + e.getMessage());
            return false;
        } finally {
            Conexao.close(con, stmt, null);
        }
    }

    // --------------------------------------------------------
    // DELETAR DO BANCO 
    // --------------------------------------------------------
    public boolean deletar(int idNotificacao) {
        String sql = "DELETE FROM notificacoes WHERE id_notificacao = ?";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.criarConexao();
            if (con == null) {
                System.out.println("Erro: conexão nula!");
                return false;
            }

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idNotificacao);
            stmt.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao deletar notificação: " + e.getMessage());
            return false;
        } finally {
            Conexao.close(con, stmt, null);
        }
    }
}
