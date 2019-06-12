package com.clinica.controller;

import com.clinica.dal.Conexao;
import com.clinica.dal.FolhaPagamentoDAL;
import javax.swing.JOptionPane;
import com.clinica.model.Funcionario;
import java.sql.SQLException;

public class FolhaPagamentoCTRL {

    private Funcionario fp;

    private Conexao con = new Conexao();
    private FolhaPagamentoDAL fpd;

    private double calcularINSS() {

        if (fp.getSalario() <= 1693.72) {
            fp.getFp().setInss(fp.getSalario() * 0.08);
        }

        if (fp.getSalario() > 1693.72 && fp.getSalario() <= 2822.90) {
            fp.getFp().setInss(fp.getSalario() * 0.09);
        }

        if (fp.getSalario() > 2822.90 && fp.getSalario() <= 5645.80) {
            fp.getFp().setInss(fp.getSalario() * 0.11);
        }

        return fp.getFp().getInss();

    }

    private double calcularIRFF() {

        if (fp.getSalario() < 1903.99 && fp.getSalario() > 0) {
            fp.getFp().setIrff(0);
        }

        if (fp.getSalario() >= 1903.99 && fp.getSalario() <= 2826.65) {
            fp.getFp().setIrff(fp.getSalario() * (7.5 / 100) - 142.80);
        }

        if (fp.getSalario() >= 2826.66 && fp.getSalario() <= 3751.05) {
            fp.getFp().setIrff(fp.getSalario() * (15 / 100) - 354.80);
        }

        if (fp.getSalario() >= 3751.06 && fp.getSalario() <= 4664.68) {
            fp.getFp().setIrff(fp.getSalario() * (22.5 / 100) - 636.13);
        }

        if (fp.getSalario() > 4664.68) {
            fp.getFp().setIrff(fp.getSalario() * (27.5 / 100) - 869.36);
        }

        return fp.getFp().getIrff();
    }

    public void exibirFolha(String cpf) throws SQLException {

        fp = new Funcionario();
        fpd = new FolhaPagamentoDAL(con);

        fp.setCpf(Long.parseLong(cpf));

        double salario = fpd.searchSalario(fp);

        fp.setSalario(salario);

        double inss = calcularINSS();
        double irrf = calcularIRFF();

        fp.getFp().setSalarioLiquido(fp.getSalario() - inss - irrf);

        JOptionPane.showMessageDialog(null, "INSS: " + inss);
        JOptionPane.showMessageDialog(null, "IRRF: " + irrf);
        JOptionPane.showMessageDialog(null, "Salario Liquido: " + fp.getFp().getSalarioLiquido());

    }

    public boolean search(String cpf) throws SQLException {
        fp = new Funcionario();
        fpd = new FolhaPagamentoDAL(con);

        fp.setCpf(Long.parseLong(cpf));

        if (fpd.searchFuncionario(fp)) {
            return true;
        } else {
            return false;
        }

    }

}
