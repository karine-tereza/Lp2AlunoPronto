/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg19_02_lp2_alunoprofessor;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Aluno;
import modelo.Professor;

/**
 *
 * @author Frank
 */
public class EntradaDeDados {

    DAO dao = new DAO();

    public void entradaPessoa(String opcao) throws SQLException {
        String nome = JOptionPane.showInputDialog("Nome: ");
        String sexo = JOptionPane.showInputDialog("Sexo: ");
        long cpf = Long.parseLong(JOptionPane.showInputDialog("CPF: "));
        int idade = Integer.parseInt(JOptionPane.showInputDialog("Idade: "));
        if (opcao.equals("1")) {
            Aluno aluno = new Aluno(nome, sexo, cpf, idade);
            aluno.setRa(Integer.parseInt(JOptionPane.showInputDialog("RA: ")));
            dao.salvarAluno(aluno);
        } else {
            Professor professor = new Professor(nome, sexo, cpf, idade);
            professor.setSiape(Long.parseLong(JOptionPane.showInputDialog("SIAPE: ")));
            dao.salvarProfessor(professor);
        }
    }
    
    public void exibirTodos(String opcao) throws SQLException, ClassNotFoundException{
        dao.exibirTodos(opcao);
    }
    
    public void remover(String opcao) throws SQLException{
        String cpf = JOptionPane.showInputDialog("Entre com o cpf de quem deseja remover: ");
        
        if(opcao.equals("3")){
            dao.removerAluno(Long.parseLong(cpf));
        }
        else{
            dao.removerProfessor(Long.parseLong(cpf));
        }
    }

    void exibirUm(String opt) throws SQLException {
        String nome = JOptionPane.showInputDialog("Digite o nome: ");
        dao.exibirUm(opt, nome);
    }
    
    public void editar(String opcao) throws SQLException{
        String nome = JOptionPane.showInputDialog("Digite o nome: ");
        
        if(opcao.equals("7")){
            String sexo = JOptionPane.showInputDialog("Sexo: ");
            long cpf = Long.parseLong(JOptionPane.showInputDialog("CPF: "));
            int idade = Integer.parseInt(JOptionPane.showInputDialog("Idade: "));
            Aluno aluno = new Aluno(nome, sexo, cpf, idade);
            aluno.setRa(Integer.parseInt(JOptionPane.showInputDialog("RA: ")));
            dao.editarAluno(aluno);
        }
        else{
            String sexo = JOptionPane.showInputDialog("Sexo: ");
            long cpf = Long.parseLong(JOptionPane.showInputDialog("CPF: "));
            int idade = Integer.parseInt(JOptionPane.showInputDialog("Idade: "));
            Professor professor = new Professor(nome, sexo, cpf, idade);
            professor.setSiape(Long.parseLong(JOptionPane.showInputDialog("SIAPE: ")));
            dao.editarProfessor(professor);
        }
    }
}
