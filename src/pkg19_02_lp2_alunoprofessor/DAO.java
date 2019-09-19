/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg19_02_lp2_alunoprofessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import modelo.Aluno;
import modelo.Pessoa;
import modelo.Professor;

/**
 *
 * @author Frank
 */
public class DAO {

    List<Aluno> listaAlunos = new LinkedList<>();
    List<Professor> listaProfessor = new LinkedList<>();
    
    public void exibirTodos(String opcao) throws SQLException, SQLException, ClassNotFoundException {
        String msg = "";
        if (opcao.equals("2")) {
            listaAlunos = getAlunos();
            for (int i = 0; i < listaAlunos.size(); i++) {
                Aluno aluno;
                aluno = listaAlunos.get(i);
                msg += aluno.getNome() + " - RA: " + aluno.getRa() + "\n";
            }
        } else {
            listaProfessor = getProfessores();
            for (int i = 0; i < listaProfessor.size(); i++) {
                Professor professor;
                professor = listaProfessor.get(i);
                msg += professor.getNome() + " - Siape: " + professor.getSiape() + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, msg);
    }

    public List<Aluno> getAlunos() throws ClassNotFoundException{
        Connection conexao = null;

        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
              
        try{
            conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lp2alunoprofessor", "nbuser", "nbuser");
            String sql = "select * from \"aluno\"";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ResultSet rs = null;
            
            rs = ps.executeQuery();
            while(rs.next()){
                Aluno aluno = new Aluno();
                
                aluno.setNome(rs.getString("nome"));
                aluno.setSexo(rs.getString("sexo"));
                aluno.setCpf(rs.getLong("cpf"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setRa(rs.getInt("ra"));
                alunos.add(aluno);
            }
            conexao.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar contatos" + ex.getMessage());
        }
        return alunos;
    }
    
    public List<Professor> getProfessores() throws ClassNotFoundException{
        Connection conexao = null;

        ArrayList<Professor> professores = new ArrayList<Professor>();
              
        try{
            conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lp2alunoprofessor", "nbuser", "nbuser");
            String sql = "select * from \"professor\"";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ResultSet rs = null;
            
            rs = ps.executeQuery();
            while(rs.next()){
                Professor professor = new Professor();
                
                professor.setNome(rs.getString("nome"));
                professor.setSexo(rs.getString("sexo"));
                professor.setCpf(rs.getLong("cpf"));
                professor.setIdade(rs.getInt("idade"));
                professor.setSiape(rs.getLong("siape"));
                professores.add(professor);
            }
            conexao.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar contatos" + ex.getMessage());
        }
        return professores;
    }
    
    public void removerAluno(long cpf) throws SQLException {
        Connection conexao = null;
        
        try{
            conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lp2alunoprofessor", "nbuser", "nbuser");
            String sql = "delete from  \"aluno\" where \"cpf\" = ?" ;
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setLong(1, cpf);
            
            int retorno = ps.executeUpdate();
            
            if (retorno > 0) {
                JOptionPane.showMessageDialog(null, "Excluido com Sucesso !");
            }
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        } finally{
            conexao.close();
        }
    }
    
    public void removerProfessor(long cpf) throws SQLException {
        Connection conexao = null;
        
        try{
            conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lp2alunoprofessor", "nbuser", "nbuser");
            String sql = "delete from  \"professor\" where \"cpf\" = ?" ;
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setLong(1, cpf);
            
            int retorno = ps.executeUpdate();
            
            if (retorno > 0) {
                JOptionPane.showMessageDialog(null, "Excluido com Sucesso !");
            }
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        } finally{
            conexao.close();
        }
    }
    
    public void salvarAluno(Aluno aluno) throws SQLException{
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lp2alunoprofessor", "nbuser", "nbuser");
            String sql = "insert into \"aluno\" (\"nome\", \"sexo\", \"idade\",\"cpf\", \"ra\") " +
                    "values(?,?,?,?,?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getSexo());
            ps.setInt(3, aluno.getIdade());
            ps.setLong(4, aluno.getCpf());
            ps.setInt(5, aluno.getRa());
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                JOptionPane.showMessageDialog(null, "Salvo com Sucesso !");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        } finally{
            conexao.close();
        }
    }
   
    public void salvarProfessor(Professor professor) throws SQLException{
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lp2alunoprofessor", "nbuser", "nbuser");
            String sql = "insert into \"professor\" (\"nome\", \"sexo\", \"idade\",\"cpf\", \"siape\") " +
                    "values(?,?,?,?,?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getSexo());
            ps.setInt(3, professor.getIdade());
            ps.setLong(4, professor.getCpf());
            ps.setLong(5, professor.getSiape());
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                JOptionPane.showMessageDialog(null, "Salvo com Sucesso !");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally{
            conexao.close();
        }
    }

    public void exibirUm(String opcao, String nome) throws SQLException{
        String msg = "";
        if (opcao.equals("2")) {
            Aluno aluno;
            aluno = buscarAlunoPorNome(nome);
            msg += aluno.getNome() + " - RA: " + aluno.getRa() + "\n";
        } else {
            Professor professor;
            professor = buscarProfessorPorNome(nome);
            msg += professor.getNome() + " - Siape: " + professor.getSiape() + "\n";
        }
        JOptionPane.showMessageDialog(null, msg);
    }
    
    public Aluno buscarAlunoPorNome(String nome) throws SQLException{
        Connection conexao = null;
        Aluno aluno = new Aluno();
        try {
            conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lp2alunoprofessor", "nbuser", "nbuser");
            String sql = "SELECT * FROM \"aluno\" WHERE \"nome\"= ? ";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ResultSet rs = null;
            ps.setString(1, nome);
            rs = ps.executeQuery();

            while(rs.next()){
                aluno.setNome(rs.getString("nome"));
                aluno.setSexo(rs.getString("sexo"));
                aluno.setCpf(rs.getLong("cpf"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setRa(rs.getInt("ra"));
            }
            
            conexao.close();
            
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally{
            conexao.close();
        }
        return aluno;
    }
    
    public Professor buscarProfessorPorNome(String nome) throws SQLException{
        Connection conexao = null;
        Professor professor = new Professor();
        try {
            conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lp2alunoprofessor", "nbuser", "nbuser");
            String sql = "SELECT * FROM \"professor\" WHERE \"nome\"=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ResultSet rs = null;
            ps.setString(1, nome);
            rs = ps.executeQuery();
           
            while(rs.next()){
                professor.setNome(rs.getString("nome"));
                professor.setSexo(rs.getString("sexo"));
                professor.setCpf(rs.getLong("cpf"));
                professor.setIdade(rs.getInt("idade"));
                professor.setSiape(rs.getInt("siape"));
            }
            
            conexao.close();
            
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally{
            conexao.close();
        }
        return professor;
    }
    
    public void editarAluno(Aluno aluno) throws SQLException{
        Connection conexao = null;
        try{
            conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lp2alunoprofessor", "nbuser", "nbuser");
            String sql = "UPDATE \"aluno\" SET \"sexo\" = ?, \"cpf\" = ?, \"idade\" = ?, \"ra\" = ? where \"nome\" = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, aluno.getSexo());
            ps.setLong(2, aluno.getCpf());
            ps.setInt(3, aluno.getIdade());
            ps.setInt(4, aluno.getRa());
            ps.setString(5, aluno.getNome());
            
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                JOptionPane.showMessageDialog(null, "Atualizado com Sucesso !");
            }
            
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally{
            conexao.close();
        }
    }
    
    public void editarProfessor(Professor professor) throws SQLException{
        Connection conexao = null;
        try{
            conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lp2alunoprofessor", "nbuser", "nbuser");
            String sql = "UPDATE \"professor\" SET \"sexo\" = ?, \"cpf\" = ?, \"idade\" = ?, \"siape\" = ? where \"nome\" = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, professor.getSexo());
            ps.setLong(2, professor.getCpf());
            ps.setInt(3, professor.getIdade());
            ps.setLong(4, professor.getSiape());
            ps.setString(5, professor.getNome());
            
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                JOptionPane.showMessageDialog(null, "Atualizado com Sucesso !");
            }
            
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally{
            conexao.close();
        }
    }
}
