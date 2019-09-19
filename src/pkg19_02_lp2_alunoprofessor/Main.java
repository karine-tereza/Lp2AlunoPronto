/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg19_02_lp2_alunoprofessor;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Frank
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        EntradaDeDados entrada = new EntradaDeDados();

        String menu = "0- Sair\n1 - Salvar Aluno\n2 - Exibir Aluno"
                + "\n3 - Remover Aluno\n4- Salvar Professor\n"
                + "5 - Exibir Professor\n6 - Remover Professor\n7 - Atualizar Aluno"
                + "\n8 - Atualizar Professor";

        String opt = "";
        String op = "";
        do {
            opt = JOptionPane.showInputDialog(menu);
            switch (opt) {
                case "0":
                    break;
                case "1":
                    entrada.entradaPessoa(opt);
                    break;
                case "2":
                    op = JOptionPane.showInputDialog("1 - Todos\n 2 - Buscar apenas um");
                    if(op.equals("1")){
                        entrada.exibirTodos(opt);
                    }
                    else{
                        entrada.exibirUm(opt);
                    }
                    break;
                case "3":
                    entrada.remover(opt);
                    break;
                case "4":
                    entrada.entradaPessoa(opt);
                    break;
                case "5":
                    op = JOptionPane.showInputDialog("1 - Todos\n 2 - Buscar apenas um");
                    if(op.equals("1")){
                        entrada.exibirTodos(opt);
                    }
                    else{
                        entrada.exibirUm(opt);
                    }
                    break;
                case "6":
                    entrada.remover(opt);
                    break;
                case "7":
                    entrada.editar(opt);
                    break;
                case "8":
                    entrada.editar(opt);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
            }
        } while (!opt.equals("0"));
    }

}
